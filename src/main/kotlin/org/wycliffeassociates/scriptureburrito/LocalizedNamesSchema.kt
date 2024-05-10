import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import java.util.*

class LocalizedNamesSchema: HashMap<String, LocalizedText>()

fun LocalizedNamesSchema.getBooks(): Map<String, LocalizedText> {
    return this.entries
        .filter {
            it.key.startsWith("book-")
        }
        .associate { it.key.removePrefix("book-").toUpperCase(Locale.US) to it.value }
}