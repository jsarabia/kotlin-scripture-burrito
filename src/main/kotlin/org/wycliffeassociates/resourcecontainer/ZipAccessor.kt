package org.wycliffeassociates.resourcecontainer

import java.io.*
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import java.util.zip.ZipOutputStream

class ZipAccessor(private val file: File) : IResourceContainerAccessor {
    private var _zipFile: ZipFile? = null

    private fun openZipFile(): ZipFile {
        if (_zipFile == null) _zipFile = ZipFile(file)
        return _zipFile as ZipFile
    }

    private fun closeZipFile() {
        _zipFile?.let(ZipFile::close)
        _zipFile = null
    }

    override fun getReader(filename: String): Reader {
        return openZipFile()
                .getInputStream(openZipFile().getEntry(filename))
                .bufferedReader()
    }

    override fun fileExists(filename: String): Boolean {
        return openZipFile()
                .entries()
                .asSequence()
                .map { it.name }
                .contains(filename)
    }

    override fun initWrite() {
        // noop
    }

    /** Thread safety warning: This is NOT thread safe, and additionally, write() will close any open Readers. */
    override fun write(filename: String, writeFunction: (Writer) -> Unit) {
        val doCopy = file.exists()
        val dest = when (doCopy) {
            true -> File.createTempFile("otter", ".zip", file.parentFile)
            false -> file
        }
        ZipOutputStream(FileOutputStream(dest)).use { zos ->
            var found = false
            if (doCopy) {
                openZipFile().entries().iterator().forEach {
                    if (it.name == filename) {
                        zos.putNextEntry(ZipEntry(filename))
                        writeFunction(zos.bufferedWriter())
                        found = true
                    } else {
                        // Simply doing zos.putNextEntry(it) resulted in ZipExceptions - invalid entry
                        // compressed size
                        val destEntry = ZipEntry(it.name)
                        zos.putNextEntry(destEntry)
                        openZipFile().getInputStream(destEntry).use { inStream -> inStream.copyTo(zos) }
                    }
                }
            }
            if (!found) {
                zos.putNextEntry(ZipEntry(filename))
                writeFunction(zos.bufferedWriter())
            }
        }
        if (doCopy) {
            closeZipFile()
            file.delete()
            dest.renameTo(file)
        }
    }

    override fun close() {
        closeZipFile()
    }

}