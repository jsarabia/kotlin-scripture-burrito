import com.fasterxml.jackson.annotation.*
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValues
import org.wycliffeassociates.scriptureburrito.Format
import java.io.IOException


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

    @get:JsonProperty("type")
    @set:JsonProperty("type")
    @JsonProperty("type")
    open var type: TypeSchema? = null

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

    val mapper = ObjectMapper()
        .registerModules(
            KotlinModule(),
            SimpleModule().addDeserializer(FlavorSchema::class.java, FlavorSchemaDeserializer())
        )

    @Throws(IOException::class, JsonProcessingException::class)
    override fun deserialize(jp: JsonParser, ctx: DeserializationContext?): MetadataSchema {
        val node: JsonNode = jp.readValueAsTree() // Get the complete JSON structure

        // Extract the "format" field from the package object (assuming it's nested)
        val category = node["meta"]["category"].asText()

        // Leverage Jackson for deserializing the Package object
        val pkg: Meta = mapper.readValue(node["meta"].toString(), Meta::class.java)
        val type: TypeSchema = mapper.readValue(node["type"].toString(), TypeSchema::class.java)

        val format = mapper.readValue(node["format"].toString(), Format::class.java)
        val idAuthorities = mapper.readValue(node["idAuthorities"].toString(), IdAuthoritiesSchema::class.java)
        val identification = mapper.readValue(node["identification"].toString(), IdentificationSchema::class.java)
        val confidential = mapper.readValue(node["confidential"].toString(), Boolean::class.java)

//        val targetAreas = mapper.readValue(node["targetAreas"].toString(), TargetAreas::class.java)
//      val agencies = mapper.readValue(node["agencies"].toString(), Agencies::class.java)
//        val relationships = mapper.readValue(node["relationships"].toString(), Relationships::class.java)
        val copyright = mapper.readValue(node["copyright"].toString(), CopyrightSchema::class.java)


        val languages = mapper.readValue(node["languages"].toString(), Languages::class.java)
        val ingredients = mapper.readValue(node["ingredients"].toString(), IngredientsSchema::class.java)
        val localizedNames = mapper.readValue(node["localizedNames"].toString(), LocalizedNamesSchema::class.java)

        val metadata: MetadataSchema = when (category) {
            "source" -> {
                val out = SourceMetadataSchema(pkg as SourceMetaSchema, type)
                out.languages = languages
                out.ingredients = ingredients
                out.localizedNames = localizedNames
                out.format = format
                out.idAuthorities = idAuthorities
                out.identification = identification
                out.confidential = confidential
                out
            }
            "derived" -> {
                val out = DerivedMetadataSchema(pkg as DerivedMetaSchema, type)
                out.languages = languages
                out.ingredients = ingredients
                out.localizedNames = localizedNames
                out.format = format
                out.idAuthorities = idAuthorities
                out.identification = identification
                out.confidential = confidential
                out
            }
            // "template" -> metadata = TemplateMetadataSchema(pkg as TemplateMetaSchema) as MetadataSchema

            else -> throw JsonMappingException("Unsupported format string: $category")
        }
        return metadata
    }
}