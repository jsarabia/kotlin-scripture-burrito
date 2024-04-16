import com.fasterxml.jackson.annotation.*
import java.util.*

/**
 * Meta (Derived)
 *
 *
 * Information about the Scripture Burrito metadata file.
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(
    "category", "dateCreated", "version", "generator", "defaultLocale", "normalization", "comments"
)
class DerivedMetaSchema {
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
    @get:JsonProperty("category")
    @set:JsonProperty("category")
    @JsonProperty("category")
    var category: Any? = null
    /**
     * Meta Date created
     *
     *
     *
     * (Required)
     *
     */
    /**
     * Meta Date created
     *
     *
     *
     * (Required)
     *
     */
    /**
     * Meta Date created
     *
     *
     *
     * (Required)
     *
     */
    @get:JsonProperty("dateCreated")
    @set:JsonProperty("dateCreated")
    @JsonProperty("dateCreated")
    var dateCreated: Date? = null
    /**
     * Meta Version
     *
     *
     * Version of the Scripture Burrito specification this file follows.
     * (Required)
     *
     */
    /**
     * Meta Version
     *
     *
     * Version of the Scripture Burrito specification this file follows.
     * (Required)
     *
     */
    /**
     * Meta Version
     *
     *
     * Version of the Scripture Burrito specification this file follows.
     * (Required)
     *
     */
    @get:JsonProperty("version")
    @set:JsonProperty("version")
    @JsonProperty("version")
    @JsonPropertyDescription("Version of the Scripture Burrito specification this file follows.")
    var version: MetaVersionSchema? = null
    /**
     * Software and User Info
     *
     *
     *
     *
     */
    /**
     * Software and User Info
     *
     *
     *
     *
     */
    /**
     * Software and User Info
     *
     *
     *
     *
     */
    @get:JsonProperty("generator")
    @set:JsonProperty("generator")
    @JsonProperty("generator")
    var generator: SoftwareAndUserInfoSchema? = null
    /**
     * A valid IETF language tag as specified by BCP 47.
     * (Required)
     *
     */
    /**
     * A valid IETF language tag as specified by BCP 47.
     * (Required)
     *
     */
    /**
     * A valid IETF language tag as specified by BCP 47.
     * (Required)
     *
     */
    @get:JsonProperty("defaultLocale")
    @set:JsonProperty("defaultLocale")
    @JsonProperty("defaultLocale")
    @JsonPropertyDescription("A valid IETF language tag as specified by BCP 47.")
    var defaultLocale: String? = null
    /**
     * Normalization
     *
     *
     * Unicode normalization options. This applies to both ingredients and metadata.
     *
     */
    /**
     * Normalization
     *
     *
     * Unicode normalization options. This applies to both ingredients and metadata.
     *
     */
    /**
     * Normalization
     *
     *
     * Unicode normalization options. This applies to both ingredients and metadata.
     *
     */
    @get:JsonProperty("normalization")
    @set:JsonProperty("normalization")
    @JsonProperty("normalization")
    @JsonPropertyDescription("Unicode normalization options. This applies to both ingredients and metadata.")
    var normalization: NormalizationSchema? = null
    /**
     * Meta Comments
     *
     *
     * Arbitrary text strings attached by users with no effect on the interpretation of the Scripture Burrito.
     *
     */
    /**
     * Meta Comments
     *
     *
     * Arbitrary text strings attached by users with no effect on the interpretation of the Scripture Burrito.
     *
     */
    /**
     * Meta Comments
     *
     *
     * Arbitrary text strings attached by users with no effect on the interpretation of the Scripture Burrito.
     *
     */
    @get:JsonProperty("comments")
    @set:JsonProperty("comments")
    @JsonProperty("comments")
    @JsonPropertyDescription("Arbitrary text strings attached by users with no effect on the interpretation of the Scripture Burrito.")
    var comments: List<String>? = ArrayList()

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(DerivedMetaSchema::class.java.name).append('@').append(
            Integer.toHexString(
                System.identityHashCode(
                    this
                )
            )
        ).append('[')
        sb.append("category")
        sb.append('=')
        sb.append((if ((this.category == null)) "<null>" else this.category))
        sb.append(',')
        sb.append("dateCreated")
        sb.append('=')
        sb.append((if ((this.dateCreated == null)) "<null>" else this.dateCreated))
        sb.append(',')
        sb.append("version")
        sb.append('=')
        sb.append((if ((this.version == null)) "<null>" else this.version))
        sb.append(',')
        sb.append("generator")
        sb.append('=')
        sb.append((if ((this.generator == null)) "<null>" else this.generator))
        sb.append(',')
        sb.append("defaultLocale")
        sb.append('=')
        sb.append((if ((this.defaultLocale == null)) "<null>" else this.defaultLocale))
        sb.append(',')
        sb.append("normalization")
        sb.append('=')
        sb.append((if ((this.normalization == null)) "<null>" else this.normalization))
        sb.append(',')
        sb.append("comments")
        sb.append('=')
        sb.append((if ((this.comments == null)) "<null>" else this.comments))
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
        result = ((result * 31) + (if ((this.defaultLocale == null)) 0 else defaultLocale.hashCode()))
        result = ((result * 31) + (if ((this.dateCreated == null)) 0 else dateCreated.hashCode()))
        result = ((result * 31) + (if ((this.comments == null)) 0 else comments.hashCode()))
        result = ((result * 31) + (if ((this.normalization == null)) 0 else normalization.hashCode()))
        result = ((result * 31) + (if ((this.generator == null)) 0 else generator.hashCode()))
        result = ((result * 31) + (if ((this.category == null)) 0 else this.category.hashCode()))
        result = ((result * 31) + (if ((this.version == null)) 0 else version.hashCode()))
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (other === this) {
            return true
        }
        if ((other is DerivedMetaSchema) == false) {
            return false
        }
        val rhs = other
        return ((((((((this.defaultLocale === rhs.defaultLocale) || ((this.defaultLocale != null) && (this.defaultLocale == rhs.defaultLocale))) && ((this.dateCreated === rhs.dateCreated) || ((this.dateCreated != null) && (this.dateCreated == rhs.dateCreated)))) && ((this.comments === rhs.comments) || ((this.comments != null) && (this.comments == rhs.comments)))) && ((this.normalization == rhs.normalization) || ((this.normalization != null) && (this.normalization == rhs.normalization)))) && ((this.generator === rhs.generator) || ((this.generator != null) && (this.generator == rhs.generator)))) && ((this.category === rhs.category) || ((this.category != null) && (this.category == rhs.category)))) && ((this.version == rhs.version) || ((this.version != null) && (this.version == rhs.version))))
    }


    /**
     * Meta Version
     *
     *
     * Version of the Scripture Burrito specification this file follows.
     *
     */
    enum class MetaVersionSchema(private val value: String) {
        _1_0_0("1.0.0");

        override fun toString(): String {
            return this.value
        }

        @JsonValue
        fun value(): String {
            return this.value
        }

        companion object {
            private val CONSTANTS: MutableMap<String, MetaVersionSchema> = HashMap()

            init {
                for (c in values()) {
                    CONSTANTS[c.value] = c
                }
            }

            @JsonCreator
            fun fromValue(value: String?): MetaVersionSchema {
                val constant = CONSTANTS[value]
                requireNotNull(constant) { value }
                return constant
            }
        }
    }


    /**
     * Normalization
     *
     *
     * Unicode normalization options. This applies to both ingredients and metadata.
     *
     */
    enum class NormalizationSchema(private val value: String) {
        NFC("NFC"),
        NFD("NFD"),
        NFKC("NFKC"),
        NFKD("NFKD");

        override fun toString(): String {
            return this.value
        }

        @JsonValue
        fun value(): String {
            return this.value
        }

        companion object {
            private val CONSTANTS: MutableMap<String, NormalizationSchema> = HashMap()

            init {
                for (c in values()) {
                    CONSTANTS[c.value] = c
                }
            }

            @JsonCreator
            fun fromValue(value: String?): NormalizationSchema {
                val constant = CONSTANTS[value]
                requireNotNull(constant) { value }
                return constant
            }
        }
    }
}
