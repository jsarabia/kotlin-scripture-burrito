package org.wycliffeassociates.scriptureburrito

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyDescription
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import java.util.HashMap
class IdAuthoritiesSchema: HashMap<String, IdAuthority>()

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(
    "id", "name"
)
class IdAuthority {
    @get:JsonProperty("id")
    @set:JsonProperty("id")
    @JsonProperty("id")
    var id: String? = null

    @get:JsonProperty("name")
    @set:JsonProperty("name")
    @JsonProperty("name")
    var name: Map<String, String>? = null
}