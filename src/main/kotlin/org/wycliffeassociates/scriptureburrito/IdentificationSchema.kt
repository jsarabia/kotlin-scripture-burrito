import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyDescription
import com.fasterxml.jackson.annotation.JsonPropertyOrder


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(
    "name", "description", "abbreviation", "primary", "upstream"
)
class IdentificationSchema {
    
    
    
    @get:JsonProperty("name")
    @set:JsonProperty("name")
    @JsonProperty("name")
    @JsonPropertyDescription("A textual string specified in one or multiple languages, indexed by IETF language tag.")
    var name: Map<String,String>? = null
    
    
    
    @get:JsonProperty("description")
    @set:JsonProperty("description")
    @JsonProperty("description")
    @JsonPropertyDescription("A textual string specified in one or multiple languages, indexed by IETF language tag.")
    var description: Map<String,String>? = null
    
    
    
    @get:JsonProperty("abbreviation")
    @set:JsonProperty("abbreviation")
    @JsonProperty("abbreviation")
    @JsonPropertyDescription("A textual string specified in one or multiple languages, indexed by IETF language tag.")
    var abbreviation: Map<String,String>? = null
    
    
    
    @get:JsonProperty("primary")
    @set:JsonProperty("primary")
    @JsonProperty("primary")
    @JsonPropertyDescription("Contains the primary authority and identification information.")
    @JsonIgnore
    var primary: Any? = null
    
    
    
    @get:JsonProperty("upstream")
    @set:JsonProperty("upstream")
    @JsonProperty("upstream")
    @JsonPropertyDescription("Contains the upstream authority and identification information.")
    var upstream: Upstream? = null
}
