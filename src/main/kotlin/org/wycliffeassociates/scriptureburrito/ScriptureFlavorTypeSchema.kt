import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder

/**
 * Scripture Flavor Type
 *
 *
 *
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(
    "flavorType"
)
class ScriptureFlavorTypeSchema {
    @get:JsonProperty("flavorType")
    @set:JsonProperty("flavorType")
    @JsonProperty("flavorType")
    var flavorType: FlavorType? = null

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(ScriptureFlavorTypeSchema::class.java.name).append('@').append(
            Integer.toHexString(
                System.identityHashCode(
                    this
                )
            )
        ).append('[')
        sb.append("flavorType")
        sb.append('=')
        sb.append((if ((this.flavorType == null)) "<null>" else this.flavorType))
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
        result = ((result * 31) + (if ((this.flavorType == null)) 0 else flavorType.hashCode()))
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (other === this) {
            return true
        }
        if ((other is ScriptureFlavorTypeSchema) == false) {
            return false
        }
        val rhs = other
        return ((this.flavorType === rhs.flavorType) || ((this.flavorType != null) && (this.flavorType == rhs.flavorType)))
    }
}
