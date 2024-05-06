import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonPropertyOrder

typealias LocalizedNamesSchema = MutableMap<String, LocalizedText>

fun LocalizedNamesSchema.getBooks(): Map<String, LocalizedText> {
    return this.entries
        .filter {
            it.key.startsWith("book-")
        }
        .associate { it.key to it.value }
}