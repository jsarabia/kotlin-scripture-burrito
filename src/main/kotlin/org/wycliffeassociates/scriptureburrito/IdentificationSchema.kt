import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyDescription
import com.fasterxml.jackson.annotation.JsonPropertyOrder

/**
 * Identification
 *
 *
 * Identification section.
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(
    "name", "description", "abbreviation", "primary", "upstream"
)
class IdentificationSchema {
    /**
     * A textual string specified in one or multiple languages, indexed by IETF language tag.
     * (Required)
     *
     */
    /**
     * A textual string specified in one or multiple languages, indexed by IETF language tag.
     * (Required)
     *
     */
    /**
     * A textual string specified in one or multiple languages, indexed by IETF language tag.
     * (Required)
     *
     */
    @get:JsonProperty("name")
    @set:JsonProperty("name")
    @JsonProperty("name")
    @JsonPropertyDescription("A textual string specified in one or multiple languages, indexed by IETF language tag.")
    var name: LocalizedText? = null
    /**
     * A textual string specified in one or multiple languages, indexed by IETF language tag.
     *
     */
    /**
     * A textual string specified in one or multiple languages, indexed by IETF language tag.
     *
     */
    /**
     * A textual string specified in one or multiple languages, indexed by IETF language tag.
     *
     */
    @get:JsonProperty("description")
    @set:JsonProperty("description")
    @JsonProperty("description")
    @JsonPropertyDescription("A textual string specified in one or multiple languages, indexed by IETF language tag.")
    var description: LocalizedText? = null
    /**
     * A textual string specified in one or multiple languages, indexed by IETF language tag.
     *
     */
    /**
     * A textual string specified in one or multiple languages, indexed by IETF language tag.
     *
     */
    /**
     * A textual string specified in one or multiple languages, indexed by IETF language tag.
     *
     */
    @get:JsonProperty("abbreviation")
    @set:JsonProperty("abbreviation")
    @JsonProperty("abbreviation")
    @JsonPropertyDescription("A textual string specified in one or multiple languages, indexed by IETF language tag.")
    var abbreviation: LocalizedText? = null
    /**
     * Contains the primary authority and identification information.
     * (Required)
     *
     */
    /**
     * Contains the primary authority and identification information.
     * (Required)
     *
     */
    /**
     * Contains the primary authority and identification information.
     * (Required)
     *
     */
    @get:JsonProperty("primary")
    @set:JsonProperty("primary")
    @JsonProperty("primary")
    @JsonPropertyDescription("Contains the primary authority and identification information.")
    var primary: Primary? = null
    /**
     * Contains the upstream authority and identification information.
     *
     */
    /**
     * Contains the upstream authority and identification information.
     *
     */
    /**
     * Contains the upstream authority and identification information.
     *
     */
    @get:JsonProperty("upstream")
    @set:JsonProperty("upstream")
    @JsonProperty("upstream")
    @JsonPropertyDescription("Contains the upstream authority and identification information.")
    var upstream: Upstream? = null

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(IdentificationSchema::class.java.name).append('@').append(
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
        sb.append("description")
        sb.append('=')
        sb.append((if ((this.description == null)) "<null>" else this.description))
        sb.append(',')
        sb.append("abbreviation")
        sb.append('=')
        sb.append((if ((this.abbreviation == null)) "<null>" else this.abbreviation))
        sb.append(',')
        sb.append("primary")
        sb.append('=')
        sb.append((if ((this.primary == null)) "<null>" else this.primary))
        sb.append(',')
        sb.append("upstream")
        sb.append('=')
        sb.append((if ((this.upstream == null)) "<null>" else this.upstream))
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
        result = ((result * 31) + (if ((this.description == null)) 0 else description.hashCode()))
        result = ((result * 31) + (if ((this.upstream == null)) 0 else upstream.hashCode()))
        result = ((result * 31) + (if ((this.abbreviation == null)) 0 else abbreviation.hashCode()))
        result = ((result * 31) + (if ((this.primary == null)) 0 else primary.hashCode()))
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (other === this) {
            return true
        }
        if ((other is IdentificationSchema) == false) {
            return false
        }
        val rhs = other
        return ((((((this.name === rhs.name) || ((this.name != null) && (this.name == rhs.name))) && ((this.description === rhs.description) || ((this.description != null) && (this.description == rhs.description)))) && ((this.upstream === rhs.upstream) || ((this.upstream != null) && (this.upstream == rhs.upstream)))) && ((this.abbreviation === rhs.abbreviation) || ((this.abbreviation != null) && (this.abbreviation == rhs.abbreviation)))) && ((this.primary === rhs.primary) || ((this.primary != null) && (this.primary == rhs.primary))))
    }
}
