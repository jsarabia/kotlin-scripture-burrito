package org.bibletranslationtools.scriptureburrito

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyDescription
import com.fasterxml.jackson.annotation.JsonPropertyOrder


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(
    "short", "long", "abbr"
)
class LocalizedNameSchema {
    
    
    
    @get:JsonProperty("short")
    @set:JsonProperty("short")
    @JsonProperty("short")
    @JsonPropertyDescription("A textual string specified in one or multiple languages, indexed by IETF language tag.")
    var short: LocalizedText? = null
    
    
    
    @get:JsonProperty("long")
    @set:JsonProperty("long")
    @JsonProperty("long")
    @JsonPropertyDescription("A textual string specified in one or multiple languages, indexed by IETF language tag.")
    var long: LocalizedText? = null
    
    
    
    @get:JsonProperty("abbr")
    @set:JsonProperty("abbr")
    @JsonProperty("abbr")
    @JsonPropertyDescription("A textual string specified in one or multiple languages, indexed by IETF language tag.")
    var abbr: LocalizedText? = null

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(LocalizedNameSchema::class.java.name).append('@').append(
            Integer.toHexString(
                System.identityHashCode(
                    this
                )
            )
        ).append('[')
        sb.append("_short")
        sb.append('=')
        sb.append((if ((this.short == null)) "<null>" else this.short))
        sb.append(',')
        sb.append("_long")
        sb.append('=')
        sb.append((if ((this.long == null)) "<null>" else this.long))
        sb.append(',')
        sb.append("abbr")
        sb.append('=')
        sb.append((if ((this.abbr == null)) "<null>" else this.abbr))
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
        result = ((result * 31) + (if ((this.short == null)) 0 else short.hashCode()))
        result = ((result * 31) + (if ((this.abbr == null)) 0 else abbr.hashCode()))
        result = ((result * 31) + (if ((this.long == null)) 0 else long.hashCode()))
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (other === this) {
            return true
        }
        if ((other is LocalizedNameSchema) == false) {
            return false
        }
        val rhs = other
        return ((((this.short === rhs.short) || ((this.short != null) && (this.short == rhs.short))) && ((this.abbr === rhs.abbr) || ((this.abbr != null) && (this.abbr == rhs.abbr)))) && ((this.long === rhs.long) || ((this.long != null) && (this.long == rhs.long))))
    }
}
