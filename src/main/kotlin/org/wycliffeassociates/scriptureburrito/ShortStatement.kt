package org.wycliffeassociates.scriptureburrito

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyDescription
import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(
    "statement", "lang", "mimetype"
)
class ShortStatement {



    @get:JsonProperty("statement")
    @set:JsonProperty("statement")
    @JsonProperty("statement")
    var statement: String? = null
    
    
    
    @get:JsonProperty("lang")
    @set:JsonProperty("lang")
    @JsonProperty("lang")
    @JsonPropertyDescription("A valid IETF language tag as specified by BCP 47.")
    var lang: String? = null
    
    
    
    @get:JsonProperty("mimetype")
    @set:JsonProperty("mimetype")
    @JsonProperty("mimetype")
    @JsonPropertyDescription("An IANA media type (also known as MIME type)")
    var mimetype: String? = null

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(ShortStatement::class.java.name).append('@').append(
            Integer.toHexString(
                System.identityHashCode(
                    this
                )
            )
        ).append('[')
        sb.append("statement")
        sb.append('=')
        sb.append((if ((this.statement == null)) "<null>" else this.statement))
        sb.append(',')
        sb.append("lang")
        sb.append('=')
        sb.append((if ((this.lang == null)) "<null>" else this.lang))
        sb.append(',')
        sb.append("mimetype")
        sb.append('=')
        sb.append((if ((this.mimetype == null)) "<null>" else this.mimetype))
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
        result = ((result * 31) + (if ((this.statement == null)) 0 else statement.hashCode()))
        result = ((result * 31) + (if ((this.lang == null)) 0 else lang.hashCode()))
        result = ((result * 31) + (if ((this.mimetype == null)) 0 else mimetype.hashCode()))
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (other === this) {
            return true
        }
        if ((other is ShortStatement) == false) {
            return false
        }
        val rhs = other
        return ((((this.statement === rhs.statement) || ((this.statement != null) && (this.statement == rhs.statement))) && ((this.lang === rhs.lang) || ((this.lang != null) && (this.lang == rhs.lang)))) && ((this.mimetype === rhs.mimetype) || ((this.mimetype != null) && (this.mimetype == rhs.mimetype))))
    }
}
