import com.fasterxml.jackson.annotation.*
import org.wycliffeassociates.scriptureburrito.Format


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(
    "format",
    "meta",
    "idAuthorities",
    "identification",
    "confidential",
    "type",
    "relationships",
    "languages",
    "targetAreas",
    "agencies",
    "copyright",
    "promotion",
    "ingredients",
    "localizedNames",
    "recipe"
)
class DerivedMetadataSchema(
    @JsonProperty("format")
    format: Format,
    @JsonProperty("meta")
    meta: DerivedMetaSchema,

    @JsonProperty("idAuthorities")
    @JsonPropertyDescription("Declares one or more identity authorities which may later be referred to using identifier prefixes.")
    idAuthorities: IdAuthoritiesSchema? = null,

    @JsonProperty("identification")
    @JsonPropertyDescription("Identification section.")
    identification: IdentificationSchema? = null,

    @JsonProperty("confidential")
    @JsonPropertyDescription("a true value indicates that the project should not be publicly known and that the identity of project members needs to be kept confidential.")
    confidential: Boolean? = null,

    @JsonProperty("type")
    @JsonPropertyDescription("Contains properties describing the burrito flavor type.")
    type: TypeSchema,

    @JsonProperty("copyright")
    copyright: CopyrightSchema,

    @JsonProperty("relationships")
    @JsonPropertyDescription("Describes a relationship to another burrito that may be obtained from an indicated server.")
    relationships: List<RelationshipSchema> = ArrayList(),

    @JsonProperty("languages")
    @JsonPropertyDescription("A list of all the languages of the contents of this burrito.")
    languages: Languages = Languages(),

    @JsonProperty("targetAreas")
    @JsonPropertyDescription("A list of areas of the primary target audience of this burrito.")
    targetAreas: List<TargetAreaSchema> = ArrayList(),

    @JsonProperty("agencies")
    @JsonPropertyDescription("A list of agencies involved with the contents of the burrito or the work it is derived from.")
    agencies: List<AgencySchema> = ArrayList(),

    @JsonProperty("ingredients")
    @JsonPropertyDescription("Describes the various files contained by the burrito, keyed by the canonical forward-slashed pathname of the file.")
    ingredients: IngredientsSchema = IngredientsSchema(),

    @JsonProperty("localizedNames")
    @JsonPropertyDescription("Contains localized names for books, etc.")
    localizedNames: LocalizedNamesSchema = LocalizedNamesSchema()
) : MetadataSchema(
    format,
    meta,
    copyright,
    idAuthorities,
    identification,
    confidential,
    type,
    relationships,
    languages,
    targetAreas,
    agencies,
    ingredients,
    localizedNames
) {
    val metadata: DerivedMetaSchema
        get() = meta as DerivedMetaSchema
    

    @get:JsonProperty("promotion")
    @set:JsonProperty("promotion")
    @JsonProperty("promotion")
    @JsonPropertyDescription("Contains promotional statements for the burrito.")
    var promotion: PromotionSchema? = null


    @get:JsonProperty("recipe")
    @set:JsonProperty("recipe")
    @JsonProperty("recipe")
    @JsonPropertyDescription("Scripture Burrito recipes.")
    var recipe: List<RecipeSchema>? = ArrayList()

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(DerivedMetadataSchema::class.java.name).append('@').append(
            Integer.toHexString(
                System.identityHashCode(
                    this
                )
            )
        ).append('[')
        sb.append("format")
        sb.append('=')
        sb.append((if ((this.format == null)) "<null>" else this.format))
        sb.append(',')
        sb.append("meta")
        sb.append('=')
        sb.append((if ((this.meta == null)) "<null>" else this.meta))
        sb.append(',')
        sb.append("idAuthorities")
        sb.append('=')
        sb.append((if ((this.idAuthorities == null)) "<null>" else this.idAuthorities))
        sb.append(',')
        sb.append("identification")
        sb.append('=')
        sb.append((if ((this.identification == null)) "<null>" else this.identification))
        sb.append(',')
        sb.append("confidential")
        sb.append('=')
        sb.append((if ((this.confidential == null)) "<null>" else this.confidential))
        sb.append(',')
        sb.append("type")
        sb.append('=')
        sb.append((if ((this.type == null)) "<null>" else this.type))
        sb.append(',')
        sb.append("relationships")
        sb.append('=')
        sb.append((if ((this.relationships == null)) "<null>" else this.relationships))
        sb.append(',')
        sb.append("languages")
        sb.append('=')
        sb.append((if ((this.languages == null)) "<null>" else this.languages))
        sb.append(',')
        sb.append("targetAreas")
        sb.append('=')
        sb.append((if ((this.targetAreas == null)) "<null>" else this.targetAreas))
        sb.append(',')
        sb.append("agencies")
        sb.append('=')
        sb.append((if ((this.agencies == null)) "<null>" else this.agencies))
        sb.append(',')
        sb.append("copyright")
        sb.append('=')
        sb.append((if ((this.copyright == null)) "<null>" else this.copyright))
        sb.append(',')
        sb.append("promotion")
        sb.append('=')
        sb.append((if ((this.promotion == null)) "<null>" else this.promotion))
        sb.append(',')
        sb.append("ingredients")
        sb.append('=')
        sb.append((if ((this.ingredients == null)) "<null>" else this.ingredients))
        sb.append(',')
        sb.append("localizedNames")
        sb.append('=')
        sb.append((if ((this.localizedNames == null)) "<null>" else this.localizedNames))
        sb.append(',')
        sb.append("recipe")
        sb.append('=')
        sb.append((if ((this.recipe == null)) "<null>" else this.recipe))
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
        result = ((result * 31) + (if ((this.copyright == null)) 0 else copyright.hashCode()))
        result = ((result * 31) + (if ((this.languages == null)) 0 else languages.hashCode()))
        result = ((result * 31) + (if ((this.localizedNames == null)) 0 else localizedNames.hashCode()))
        result = ((result * 31) + (if ((this.format == null)) 0 else format.hashCode()))
        result = ((result * 31) + (if ((this.recipe == null)) 0 else recipe.hashCode()))
        result = ((result * 31) + (if ((this.agencies == null)) 0 else agencies.hashCode()))
        result = ((result * 31) + (if ((this.type == null)) 0 else type.hashCode()))
        result = ((result * 31) + (if ((this.targetAreas == null)) 0 else targetAreas.hashCode()))
        result = ((result * 31) + (if ((this.relationships == null)) 0 else relationships.hashCode()))
        result = ((result * 31) + (if ((this.identification == null)) 0 else identification.hashCode()))
        result = ((result * 31) + (if ((this.meta == null)) 0 else meta.hashCode()))
        result = ((result * 31) + (if ((this.ingredients == null)) 0 else ingredients.hashCode()))
        result = ((result * 31) + (if ((this.idAuthorities == null)) 0 else idAuthorities.hashCode()))
        result = ((result * 31) + (if ((this.confidential == null)) 0 else confidential.hashCode()))
        result = ((result * 31) + (if ((this.promotion == null)) 0 else promotion.hashCode()))
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (other === this) {
            return true
        }
        if ((other is DerivedMetadataSchema) == false) {
            return false
        }
        val rhs = other
        return ((((((((((((((((this.copyright === rhs.copyright) || ((this.copyright != null) && (this.copyright == rhs.copyright))) && ((this.languages === rhs.languages) || ((this.languages != null) && (this.languages == rhs.languages)))) && ((this.localizedNames === rhs.localizedNames) || ((this.localizedNames != null) && (this.localizedNames == rhs.localizedNames)))) && ((this.format == rhs.format) || ((this.format != null) && (this.format == rhs.format)))) && ((this.recipe === rhs.recipe) || ((this.recipe != null) && (this.recipe == rhs.recipe)))) && ((this.agencies === rhs.agencies) || ((this.agencies != null) && (this.agencies == rhs.agencies)))) && ((this.type === rhs.type) || ((this.type != null) && (this.type == rhs.type)))) && ((this.targetAreas === rhs.targetAreas) || ((this.targetAreas != null) && (this.targetAreas == rhs.targetAreas)))) && ((this.relationships === rhs.relationships) || ((this.relationships != null) && (this.relationships == rhs.relationships)))) && ((this.identification === rhs.identification) || ((this.identification != null) && (this.identification == rhs.identification)))) && ((this.meta === rhs.meta) || ((this.meta != null) && (this.meta == rhs.meta)))) && ((this.ingredients === rhs.ingredients) || ((this.ingredients != null) && (this.ingredients == rhs.ingredients)))) && ((this.idAuthorities === rhs.idAuthorities) || ((this.idAuthorities != null) && (this.idAuthorities == rhs.idAuthorities)))) && ((this.confidential === rhs.confidential) || ((this.confidential != null) && (this.confidential == rhs.confidential)))) && ((this.promotion === rhs.promotion) || ((this.promotion != null) && (this.promotion == rhs.promotion))))
    }
}
