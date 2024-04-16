import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonPropertyOrder

/**
 * Ingredients
 *
 *
 * Describes the various files contained by the burrito, keyed by the canonical forward-slashed pathname of the file.
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder
class IngredientsSchema {
    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(IngredientsSchema::class.java.name).append('@').append(
            Integer.toHexString(
                System.identityHashCode(
                    this
                )
            )
        ).append('[')
        if (sb[sb.length - 1] == ',') {
            sb.setCharAt((sb.length - 1), ']')
        } else {
            sb.append(']')
        }
        return sb.toString()
    }

    override fun hashCode(): Int {
        val result = 1
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (other === this) {
            return true
        }
        if ((other is IngredientsSchema) == false) {
            return false
        }
        val rhs = other
        return true
    }
}
