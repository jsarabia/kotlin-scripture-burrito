import com.fasterxml.jackson.annotation.*
import com.fasterxml.jackson.annotation.JsonSubTypes.Type
import org.wycliffeassociates.scriptureburrito.Category

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(
    "category"
)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "category")
@JsonSubTypes(
    Type(value = DerivedMetaSchema::class, name = "derived"),
    Type(value = SourceMetaSchema::class, name = "source"),
    Type(value = TemplateMetaSchema::class, name = "template")
)
abstract class Meta {
    @get:JsonProperty("category")
    @set:JsonProperty("category")
    @JsonProperty("category")
    open var category: Category? = null

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(Meta::class.java.name).append('@').append(
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
        if (sb[sb.length - 1] == ',') {
            sb.setCharAt((sb.length - 1), ']')
        } else {
            sb.append(']')
        }
        return sb.toString()
    }

    override fun hashCode(): Int {
        var result = 1
        result = ((result * 31) + (if ((this.category == null)) 0 else this.category.hashCode()))
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (other === this) {
            return true
        }
        if ((other is Meta) == false) {
            return false
        }
        val rhs = other
        return ((this.category == rhs.category) || ((this.category != null) && (this.category == rhs.category)))
    }

}
