import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import java.io.File

fun main() {
    val file = File("/Users/joe/Desktop/burrito.json")

    val mapper = ObjectMapper()
    val module = SimpleModule()
    module.addDeserializer(MetadataSchema::class.java, MetadataDeserializer())
    module.addDeserializer(FlavorSchema::class.java, FlavorSchemaDeserializer())
    mapper.registerModules(
        KotlinModule(),
        module)
    val meta = mapper.readValue(file, MetadataSchema::class.java)
    println(meta)
}