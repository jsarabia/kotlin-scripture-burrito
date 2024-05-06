package org.wycliffeassociates.resourcecontainer

import DerivedMetaSchema
import Meta
import SourceMetaSchema
import TemplateMetaSchema
import org.wycliffeassociates.resourcecontainer.entity.Project
import org.wycliffeassociates.scriptureburrito.Category

class BurritoProjectBuilder(val meta: Meta) {
    fun build(): Project? {
        when (meta.category) {
            Category.SOURCE -> {
                meta as SourceMetaSchema
                meta.
            }
            Category.DERIVED -> {
                meta as DerivedMetaSchema
            }
            Category.TEMPLATE -> {
                meta as TemplateMetaSchema
            }
            null -> throw NullPointerException()
        }
        return null
    }

}