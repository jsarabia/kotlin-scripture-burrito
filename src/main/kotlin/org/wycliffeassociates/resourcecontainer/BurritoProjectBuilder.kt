package org.wycliffeassociates.resourcecontainer

import DerivedMetaSchema
import DerivedMetadataSchema
import IngredientsSchema
import Meta
import MetadataSchema
import SourceMetaSchema
import SourceMetadataSchema
import TemplateMetaSchema
import TemplateMetadataSchema
import getBooks
import org.wycliffeassociates.resourcecontainer.entity.Project
import org.wycliffeassociates.scriptureburrito.Category
import java.io.File
import java.util.*

class BurritoProjectBuilder {
    fun build(meta: MetadataSchema): Project? {
        when (meta) {
            is SourceMetadataSchema -> {
                val language = meta.languages!!.first()
                val projectKeys = TreeSet<String>()
                meta.ingredients!!.values.map {
                    it.scope!!.keys.forEach {
                        projectKeys.add(it)
                    }
                }

                val books = meta.localizedNames?.getBooks()!!

                val projects = projectKeys.map {
                    Project(
                        books[it]!!.short?.get(language.tag!!)!!,
                        "",
                        it.toLowerCase(Locale.US),
                        0,
                        findUsfmDocument(it, language.tag!!, meta.ingredients!!) ?: ""
                    )
                }
            }

            is DerivedMetadataSchema -> {
                meta as DerivedMetaSchema
            }

            is TemplateMetadataSchema -> {
                meta as TemplateMetaSchema
            }

            null -> throw NullPointerException()
        }
        return null
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
    }
}