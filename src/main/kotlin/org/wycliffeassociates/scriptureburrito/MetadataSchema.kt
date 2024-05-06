import com.fasterxml.jackson.annotation.*
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import java.io.IOException


/**
 * Metadata
 *
 *
 * Scripture Burrito root metadata object.
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(
    "meta",
    "type"
)
// @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "meta")
@JsonSubTypes(
    JsonSubTypes.Type(value = DerivedMetadataSchema::class, name = "derived"),
    JsonSubTypes.Type(value = SourceMetadataSchema::class, name = "source"),
    JsonSubTypes.Type(value = TemplateMetadataSchema::class, name = "template")
)
abstract class MetadataSchema {
    @get:JsonProperty("meta")
    @set:JsonProperty("meta")
    @JsonProperty("meta")
    open var meta: Meta? = null

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

class MetadataDeserializer : JsonDeserializer<MetadataSchema?>() {
    val mapper = ObjectMapper().registerModules(KotlinModule())

    @Throws(IOException::class, JsonProcessingException::class)
    override fun deserialize(jp: JsonParser, ctx: DeserializationContext?): MetadataSchema {
        val node: JsonNode = jp.readValueAsTree() // Get the complete JSON structure


        // Extract the "format" field from the package object (assuming it's nested)
        val format = node["meta"]["category"].asText()

        // Leverage Jackson for deserializing the Package object
        val pkg: Meta = mapper.readValue(node["meta"].toString(), Meta::class.java)

        val metadata: MetadataSchema = when (format) {
            "source" -> SourceMetadataSchema(pkg as SourceMetaSchema)
            "derived" -> DerivedMetadataSchema(pkg as DerivedMetaSchema) as MetadataSchema
            // "template" -> metadata = TemplateMetadataSchema(pkg as TemplateMetaSchema) as MetadataSchema
            else -> throw JsonMappingException("Unsupported format string: $format")
        }
        return metadata
    }
}