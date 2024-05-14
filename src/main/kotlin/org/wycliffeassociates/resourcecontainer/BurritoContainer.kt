package org.wycliffeassociates.resourcecontainer

import com.fasterxml.jackson.annotation.JsonInclude.Include
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.apache.tika.Tika
import org.apache.tika.mime.MediaType
import org.wycliffeassociates.container.accessors.DirectoryAccessor
import org.wycliffeassociates.container.accessors.IContainerAccessor
import org.wycliffeassociates.container.accessors.ZipAccessor
import org.wycliffeassociates.resourcecontainer.entity.Content
import org.wycliffeassociates.resourcecontainer.entity.Project
import org.wycliffeassociates.scriptureburrito.Meta
import org.wycliffeassociates.scriptureburrito.MetadataDeserializer
import org.wycliffeassociates.scriptureburrito.MetadataSchema
import java.io.File
import java.io.IOException
import java.io.OutputStream


const val MANIFEST_FILENAME = "metadata.json"


class BurritoContainer internal constructor(val file: File) : AutoCloseable {

    lateinit var manifest: MetadataSchema

    val accessor: IContainerAccessor = when {
        // file may not exist at creation of a rc with .zip suffix in file path
        file.extension == "zip" -> ZipAccessor(file)
        file.isFile && detectFileType() == MediaType.APPLICATION_ZIP -> ZipAccessor(file)
        else -> DirectoryAccessor(file)
    }

    private fun detectFileType(): MediaType {
        return MediaType.parse(Tika().detect(file))
    }

    internal fun read(): Meta {
        if (accessor.fileExists(MANIFEST_FILENAME)) {
            val mapper = ObjectMapper()
            val module = SimpleModule()
            module.addDeserializer(MetadataSchema::class.java, MetadataDeserializer())
            mapper.registerModules(
                KotlinModule(),
                module)
            val schema = accessor.getReader(MANIFEST_FILENAME).use {
                mapper.readValue(it, MetadataSchema::class.java)
            }
            return schema.meta!!
        } else {
            throw IOException("Missing metadata.json")
        }
    }

    fun write() {
        writeManifest()
//        for (p in manifest.projects) {
//            if (p.path.isNotEmpty()) {
//                //writeTableOfContents(p)
//            }
//        }
    }

    fun writeManifest() {
        accessor.initWrite()
        accessor.write(MANIFEST_FILENAME) { writeManifest(it) }
    }

    private fun writeManifest(writer: OutputStream) {
        val mapper = ObjectMapper()
        mapper.registerModule(KotlinModule())
        mapper.setSerializationInclusion(Include.NON_NULL)
        mapper.writeValue(writer, manifest)
        writer.flush()
    }

    
    fun addFileToContainer(file: File, pathInRC: String) {
        accessor.write(pathInRC) { ofs ->
            file.inputStream().use { ifs ->
                ifs.copyTo(ofs)
            }
        }
    }

    
    fun getProjectContent(projectIdentifier: String? = null, extension: String): Content? {
        val project = project(projectIdentifier) ?: return null

        val contentStreams = accessor.getInputStreams(project.path, extension)
        return if (contentStreams.any()) {
            Content(project, contentStreams)
        } else {
            null
        }
    }

    
    fun addFilesToContainer(files: Map<String, File>) {
        val map = files.entries.associate { (pathInRC, file) ->
            pathInRC to { ofs: OutputStream ->
                file.inputStream().use { ifs ->
                    ifs.copyTo(ofs)
                }
                Unit
            }
        }
        accessor.write(map)
    }

    fun project(identifier: String? = null): Project? {
//        if (manifest.projects.isEmpty()) {
//            return null
//        }
//
//        if (!identifier.isNullOrEmpty()) {
//            for (p in manifest.projects) {
//                if (p.identifier == identifier) {
//                    return p
//                }
//            }
//        } else if (manifest.projects.size == 1) {
//            return manifest.projects[0]
//        } else {
//            throw RCException("Multiple projects found. Specify the project identifier.")
//        }

        return null
    }
//
//    fun projectIds(): List<String> = manifest.projects.map(Project::identifier)
//
//    fun projectCount(): Int = manifest.projects.size
//
//    fun conformsTo(): String = manifest.dublinCore.conformsTo.replace(Regex("^rc"), "")

    
    fun type(): String = this.manifest.meta!!.category.toString()

    companion object {

        const val conformsTo = "0.2"

        fun create(file: File, init: BurritoContainer.() -> Unit): BurritoContainer {
            val rc = BurritoContainer(file)
//            rc.init()
//            if (rc.conformsTo().isEmpty()) {
//                rc.manifest.dublinCore.conformsTo = conformsTo
//            }
            return rc
        }

        fun load(dir: File, strict: Boolean = true): BurritoContainer {
            val rc = BurritoContainer(dir)
            rc.read()

//            if (strict) {
//                if (Semver.gt(rc.conformsTo(), conformsTo)) {
//                    throw UnsupportedRCException("Found " + rc.conformsTo() + " but expected " + conformsTo)
//                }
//                if (Semver.lt(rc.conformsTo(), conformsTo)) {
//                    throw OutdatedRCException("Found " + rc.conformsTo() + " but expected " + conformsTo)
//                }
           // }

            return rc
        }
    }

    override fun close() {
        accessor.close()
    }
}

data class Resource(
    val slug: String,
    val title: String,
    val type: String,
    val checkingLevel: String,
    val version: String
)
