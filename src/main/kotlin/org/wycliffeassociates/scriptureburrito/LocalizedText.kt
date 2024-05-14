package org.wycliffeassociates.scriptureburrito

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import org.wycliffeassociates.scriptureburrito.Format


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(
    "abbr",
    "short",
    "long"
)
class LocalizedText {
    @get:JsonProperty("abbr")
    @set:JsonProperty("abbr")
    @JsonProperty("abbr")
    var abbr: Map<String, String>? = null

    @get:JsonProperty("short")
    @set:JsonProperty("short")
    @JsonProperty("short")
    var short: Map<String, String>? = null

    @get:JsonProperty("long")
    @set:JsonProperty("long")
    @JsonProperty("long")
    var long: Map<String, String>? = null
}
