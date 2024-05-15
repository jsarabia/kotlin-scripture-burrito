package org.bibletranslationtools.container

import org.bibletranslationtools.resourcecontainer.entity.Project
import org.bibletranslationtools.scriptureburrito.*
import java.io.File
import java.util.*

class BurritoProjectBuilder {
    fun build(meta: MetadataSchema): List<Project> {
        return when (meta) {
            is SourceMetadataSchema -> {
                val language = meta.languages.first()
                val projectKeys = TreeSet<String>()
                meta.ingredients.values.map {
                    it.scope!!.keys.forEach {
                        projectKeys.add(it)
                    }
                }

                val books = meta.localizedNames.getBooks()

                val vrs = findVersification(meta.ingredients!!)

                val projects = projectKeys.mapIndexed { idx, key ->
                    val book: LocalizedText? = books[key]
                    Project(
                        book!!.short?.get(language.tag!!)!!,
                        vrs ?: "",
                        key.toLowerCase(Locale.US),
                        idx,
                        findUsfmDocument(key, language.tag!!, meta.ingredients!!) ?: ""
                    )
                }
                projects
            }

            is DerivedMetadataSchema -> {
                meta as DerivedMetaSchema
                listOf()
            }

            is TemplateMetadataSchema -> {
                meta as TemplateMetaSchema
                listOf()
            }

            null -> throw NullPointerException()
            else -> {
                listOf()
            }
        }
    }

    private fun findVersification(
        ingredients: IngredientsSchema
    ): String? {
        return ingredients
            .entries
            .firstOrNull { it.value.role.equals("versification") }
            ?.key
    }

    private fun findUsfmDocument(
        bookSlug: String,
        languageCode: String,
        ingredientsSchema: IngredientsSchema
    ): String? {
        return ingredientsSchema.keys
            .filter { it.contains(bookSlug, true) }
            .filter { File(it).extension.endsWith("usfm", true) }
            .firstOrNull()
            .let {
                File(it).nameWithoutExtension
            }
    }

//    fun buildDublinCore(metadata: SourceMetadataSchema): DublinCore {
//        return DublinCore(
//            conformsTo = "rc0.2",
//            contributor = mutableListOf(),
//            identifier = metadata.identification!!.abbreviation!!.getOrElse("en")
//            { metadata!!.identification!!.abbreviation!!.keys.first() },
//            issued = ,
//            language = ,
//            modified = ,
//            publisher = ,
//            relation = metadata.relationships,
//            rights = metadata.copyright?.shortStatements?.firstOrNull()?.statement ?: "",
//            subject = metadata.identification!!.description!!.getOrElse("en")
//            { metadata!!.identification!!.description!!.keys.first() },
//            title = metadata.identification!!.name!!.getOrElse("en")
//            { metadata!!.identification!!.name!!.keys.first() },
//            type = "bundle",
//            version = ""
//            )
//    }

    /**
     * ---
     *
     * dublin_core:
     *     conformsto: 'rc0.2'
     *     contributor:
     *         - 'A Contributor'
     *         - 'Another Contributor'
     *     creator: 'Wycliffe Associates'
     *     description: 'The Unlocked Literal Bible is an open-licensed version of the Bible that is intended to provide a form-centric translation of the Bible.'
     *     format: 'text/usfm'
     *     identifier: 'ulb'
     *     issued: '2015-12-17'
     *     language:
     *         identifier: 'en'
     *         title: 'English'
     *         direction: 'ltr'
     *     modified: '2015-12-22T12:01:30-05:00'
     *     publisher: 'Door43'
     *     relation:
     *         - 'en/udb'
     *         - 'en/tn'
     *         - 'en/tq'
     *         - 'en/tw'
     *     rights: 'CC BY-SA 4.0'
     *     source:
     *         -
     *             identifier: 'asv'
     *             language: 'en'
     *             version: '1901'
     *     subject: 'Bible translation'
     *     title: 'Unlocked Literal Bible'
     *     type: 'book'
     *     version: '3'
     *
     * checking:
     *     checking_entity:
     *         - 'Wycliffe Associates'
     *     checking_level: '3'
     *
     * projects:
     *     -
     *         categories:
     *             - 'bible-ot'
     *         identifier: 'gen'
     *         path: './content'
     *         sort: 1
     *         title: 'Genesis'
     *         versification: 'kjv'
     */
}