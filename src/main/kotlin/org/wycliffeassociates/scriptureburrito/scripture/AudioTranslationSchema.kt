package scripture

import FlavorSchema
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import org.wycliffeassociates.scriptureburrito.AudioFormat

typealias Formats = MutableMap<String, AudioFormat>

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(
    "name", "performance", "formats", "conventions"
)
class AudioFlavorSchema: FlavorSchema() {
    @get:JsonProperty("name")
    @set:JsonProperty("name")
    @JsonProperty("name")
    override var name: String? = null

    @JsonProperty("performance")
    @JsonDeserialize(`as` = LinkedHashSet::class)
    private var performance: Set<Performance>? = LinkedHashSet()

    @JsonProperty("formats")
    private var formats: Formats? = null

    @JsonProperty("conventions")
    private var conventions: Conventions? = null

    @JsonProperty("performance")
    fun getPerformance(): Set<Performance>? {
        return performance
    }

    @JsonProperty("performance")
    fun setPerformance(performance: Set<Performance>?) {
        this.performance = performance
    }

    @JsonProperty("formats")
    fun getFormats(): Formats? {
        return formats
    }

    @JsonProperty("formats")
    fun setFormats(formats: Formats?) {
        this.formats = formats
    }

    @JsonProperty("conventions")
    fun getConventions(): Conventions? {
        return conventions
    }

    @JsonProperty("conventions")
    fun setConventions(conventions: Conventions?) {
        this.conventions = conventions
    }
}
