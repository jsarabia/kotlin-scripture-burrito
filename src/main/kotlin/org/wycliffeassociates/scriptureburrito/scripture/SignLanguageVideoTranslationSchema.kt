package scripture

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import scripture.Conventions
import scripture.Formats
import scripture.SignLanguageVideoTranslationSchema

/**
 * Flavor Details: Scripture: Sign Language Video Translation
 *
 *
 * Schema of flavor field for signLanguageVideoTranslation flavor
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(
    "name", "contentByChapter", "formats", "conventions"
)
class SignLanguageVideoTranslationSchema {
    /**
     *
     * (Required)
     *
     */
    /**
     *
     * (Required)
     *
     */
    /**
     *
     * (Required)
     *
     */
    @get:JsonProperty("name")
    @set:JsonProperty("name")
    @JsonProperty("name")
    var name: String? = null
    /**
     *
     * (Required)
     *
     */
    /**
     *
     * (Required)
     *
     */
    /**
     *
     * (Required)
     *
     */
    @get:JsonProperty("contentByChapter")
    @set:JsonProperty("contentByChapter")
    @JsonProperty("contentByChapter")
    var contentByChapter: Boolean? = null

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("formats")
    private var formats: Formats? = null

    @JsonProperty("conventions")
    private var conventions: Conventions? = null

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("formats")
    fun getFormats(): Formats? {
        return formats
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("formats")
    fun setFormats(formats: Formats?) {
        this.formats = formats
    }

    @JsonProperty("conventions")
    fun getConventions(): Conventions? {
        return conventions
    }

    @JsonProperty("conventions")
    fun setConventions(conventions: Conventions?) {
        this.conventions = conventions
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(SignLanguageVideoTranslationSchema::class.java.name).append('@').append(
            Integer.toHexString(
                System.identityHashCode(
                    this
                )
            )
        ).append('[')
        sb.append("name")
        sb.append('=')
        sb.append((if ((this.name == null)) "<null>" else this.name))
        sb.append(',')
        sb.append("contentByChapter")
        sb.append('=')
        sb.append((if ((this.contentByChapter == null)) "<null>" else this.contentByChapter))
        sb.append(',')
        sb.append("formats")
        sb.append('=')
        sb.append((if ((this.formats == null)) "<null>" else this.formats))
        sb.append(',')
        sb.append("conventions")
        sb.append('=')
        sb.append((if ((this.conventions == null)) "<null>" else this.conventions))
        sb.append(',')
        if (sb[sb.length - 1] == ',') {
            sb.setCharAt((sb.length - 1), ']')
        } else {
            sb.append(']')
        }
        return sb.toString()
    }

    override fun hashCode(): Int {
        var result = 1
        result = ((result * 31) + (if ((this.name == null)) 0 else name.hashCode()))
        result = ((result * 31) + (if ((this.conventions == null)) 0 else conventions.hashCode()))
        result = ((result * 31) + (if ((this.formats == null)) 0 else formats.hashCode()))
        result = ((result * 31) + (if ((this.contentByChapter == null)) 0 else contentByChapter.hashCode()))
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (other === this) {
            return true
        }
        if ((other is SignLanguageVideoTranslationSchema) == false) {
            return false
        }
        val rhs = other
        return (((((this.name === rhs.name) || ((this.name != null) && (this.name == rhs.name))) && ((this.conventions === rhs.conventions) || ((this.conventions != null) && conventions?.equals(rhs.conventions) == true))) && ((this.formats === rhs.formats) || ((this.formats != null) && formats?.equals(rhs.formats) == true))) && ((this.contentByChapter === rhs.contentByChapter) || ((this.contentByChapter != null) && (this.contentByChapter == rhs.contentByChapter))))
    }
}