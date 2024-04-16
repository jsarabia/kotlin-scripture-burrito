import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder

/**
 * Metadata
 *
 *
 * Scripture Burrito root metadata object.
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(
    "meta"
)
class MetadataSchema {
    @get:JsonProperty("meta")
    @set:JsonProperty("meta")
    @JsonProperty("meta")
    var meta: Meta? = null

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(MetadataSchema::class.java.name).append('@').append(
            Integer.toHexString(
                System.identityHashCode(
                    this
                )
            )
        ).append('[')
        sb.append("meta")
        sb.append('=')
        sb.append((if ((this.meta == null)) "<null>" else this.meta))
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
        result = ((result * 31) + (if ((this.meta == null)) 0 else meta.hashCode()))
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (other === this) {
            return true
        }
        if (other !is MetadataSchema) {
            return false
        }
        val rhs = other
        return ((this.meta === rhs.meta) || ((this.meta != null) && (this.meta == rhs.meta)))
    }
}
