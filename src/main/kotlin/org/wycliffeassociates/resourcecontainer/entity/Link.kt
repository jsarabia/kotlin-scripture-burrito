package org.wycliffeassociates.resourcecontainer.entity

import java.util.regex.Matcher
import java.util.regex.Pattern



class Link {
    var title: String?
    val url: String?
    val resource: String?
    val project: String?
    val language: String?
    val arguments: String?
    val protocol: String?
    val chapter: String?
    val chunk: String?
    val lastChunk: String?

    
    val isExternal: Boolean
        get() = this.url != null

    
    val isMedia: Boolean
        get() = this.protocol != null

    
    val isPassage: Boolean
        get() = this.chapter != null && this.chunk != null

    
    private constructor(title: String?, url: String?) {
        this.title = title
        this.url = url

        protocol = null
        resource = null
        project = null
        chapter = null
        chunk = null
        lastChunk = null
        arguments = null
        language = null
    }

    
    private constructor(protocol: String?, title: String?, language: String?, project: String?, resource: String?, arguments: String?, chapter: String?, chunk: String?, lastChunk: String?) {
        this.url = null
        this.protocol = protocol
        this.title = title
        this.language = language
        this.project = project
        this.resource = resource
        this.arguments = arguments
        this.chapter = chapter
        this.chunk = chunk
        this.lastChunk = lastChunk
    }

    
    fun passageTitle(): String? {
        if (isPassage) {
            var tail = ""
            if (lastChunk != null) tail = "-" + formatNumber(lastChunk)
            return formatNumber(chapter) + ":" + formatNumber(chunk) + tail
        }
        return null
    }

    
    private fun formatNumber(value: String?): String {
        try {
            return Integer.parseInt(value!!).toString() + ""
        } catch (e: NumberFormatException) {
        }

        return value!!.trim { it <= ' ' }.toLowerCase()
    }

    companion object {

        
        @Throws(Exception::class)
        fun parseLink(link: String): Link? {
            val anonymousPattern = Pattern.compile("\\[\\[([^\\]]*)\\]\\]", Pattern.DOTALL)
            val titledPattern = Pattern.compile("\\[([^\\]]*)\\]\\(([^\\)]*)\\)", Pattern.DOTALL)

            var linkTitle: String? = null
            var linkPath: String? = link
            var m: Matcher
            var numMatches = 1

            // find anonymous links
            m = anonymousPattern.matcher(link)
            while (m.find()) {
                if (numMatches > 1) throw Exception("Invalid link! Multiple links found")
                numMatches++
                linkPath = m.group(1).toLowerCase()
            }

            // find titled links
            m = titledPattern.matcher(link)
            numMatches = 1
            while (m.find()) {
                if (numMatches > 1) throw Exception("Invalid link! Multiple links found")
                numMatches++
                linkTitle = m.group(1)
                linkPath = m.group(2).toLowerCase()
            }

            // process link path
            return if (linkPath != null) {
                // external link
                if (linkPath.startsWith("http")) {
                    Link(linkTitle, linkPath)
                } else parseResourceLink(linkTitle, linkPath)
            } else null

        }

        
        private fun parseResourceLink(titleIn: String?, pathIn: String): Link? {
            var title = titleIn
            var path = pathIn
            val pattern = Pattern.compile("^((\\w+):)?\\/?(.*)", Pattern.DOTALL)

            var protocol: String? = null
            var language: String? = null
            var project: String? = null
            var resource: String? = null
            var arguments: String? = null

            // pull out the protocol
            // TRICKY: also pulls off the first / so our string splitting correctly finds the language
            val m = pattern.matcher(path)
            if (m.find()) {
                protocol = m.group(2)
                path = m.group(3)
            }

            val components = path.split("\\/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

            // /chapter
            if (components.size == 1) arguments = components[0]

            // /language/project
            if (components.size > 1) {
                language = components[0]
                project = components[1]
            }

            // /language/project/resource
            if (components.size > 2) {
                language = components[0]
                project = components[1]
                resource = components[2]

                // TRICKY: resource can be skipped
                // /language/project/chapter:chunk
                if (resource.contains(":")) {
                    arguments = resource
                    resource = null
                }
            }

            // /language/project/resource/args
            if (components.size > 3) {
                language = components[0]
                project = components[1]
                resource = components[2]
                arguments = components[3]
                // re-build arguments that had the delimiter
                for (i in 4 until components.size - 1) {
                    arguments += "/" + components[i]
                }
            }

            // get chapter:chunk from arguments
            var chapter: String? = arguments
            var chunk: String? = null
            var lastChunk: String? = null
            if (arguments != null && arguments.contains(":")) {
                val bits = arguments.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                chapter = bits[0]
                chunk = bits[1]
            }

            // get last chunk
            if (chunk != null && chunk.contains("-")) {
                val bits = chunk.split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                chunk = bits[0]
                lastChunk = bits[1]
            }

            // assume resource
            if (resource == null && project != null) resource = project

            // nullify empty strings
            protocol = nullEmpty(protocol)
            title = nullEmpty(title)
            language = nullEmpty(language)
            project = nullEmpty(project)
            resource = nullEmpty(resource)
            arguments = nullEmpty(arguments)
            chapter = nullEmpty(chapter)
            chunk = nullEmpty(chunk)
            lastChunk = nullEmpty(lastChunk)

            // validate chunks
            if (chunk != null && chunk.contains(",") || lastChunk != null && lastChunk.contains(","))
                throw Exception("Invalid passage link $path")

            return if (project != null && resource != null || arguments != null) {
                Link(protocol, title, language, project, resource, arguments, chapter, chunk, lastChunk)
            } else null
        }


        
        private fun nullEmpty(value: String?): String? {
            return if (value != null && value.isEmpty()) null else value
        }

        
        fun findLinks(@Suppress("UNUSED_PARAMETER") text: CharSequence): List<Link> {
            TODO("10/11/16 automatically parse bible passages.")
        }
    }
}
