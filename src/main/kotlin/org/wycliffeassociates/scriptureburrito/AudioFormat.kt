package org.wycliffeassociates.scriptureburrito

import com.fasterxml.jackson.annotation.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(
    "compression",
    "trackConfiguration",
    "bitRate",
    "bitDepth",
    "samplingRate",
    "timingDir"
)
class AudioFormat {
    @get:JsonProperty("compression")
    @set:JsonProperty("compression")
    @JsonProperty("compression")
    @JsonPropertyDescription("A textual string specified in one or multiple languages, indexed by IETF language tag.")
    var compression: Compression? = null

    @get:JsonProperty("trackConfiguration")
    @set:JsonProperty("trackConfiguration")
    @JsonProperty("trackConfiguration")
    @JsonPropertyDescription("A textual string specified in one or multiple languages, indexed by IETF language tag.")
    var trackConfiguration: TrackConfiguration? = null

    @get:JsonProperty("bitRate")
    @set:JsonProperty("bitRate")
    @JsonProperty("bitRate")
    @JsonPropertyDescription("A textual string specified in one or multiple languages, indexed by IETF language tag.")
    var bitRate: Int? = null

    @get:JsonProperty("bitDepth")
    @set:JsonProperty("bitDepth")
    @JsonProperty("bitDepth")
    @JsonPropertyDescription("A textual string specified in one or multiple languages, indexed by IETF language tag.")
    var bitDepth: Int? = null

    @get:JsonProperty("samplingRate")
    @set:JsonProperty("samplingRate")
    @JsonProperty("samplingRate")
    @JsonPropertyDescription("A textual string specified in one or multiple languages, indexed by IETF language tag.")
    var samplingRate: Int? = null

    @get:JsonProperty("timingDir")
    @set:JsonProperty("timingDir")
    @JsonProperty("timingDir")
    @JsonPropertyDescription("A textual string specified in one or multiple languages, indexed by IETF language tag.")
    var timingDir: String? = null
}

enum class Compression(private val value: String) {
    MP3("mp3"),
    WAV("wav"),
    OGG("ogg");


    override fun toString(): String {
        return this.value
    }

    @JsonValue
    fun value(): String {
        return this.value
    }

    companion object {
        private val CONSTANTS: MutableMap<String, Compression> = HashMap()

        init {
            for (c in values()) {
                CONSTANTS[c.value] = c
            }
        }

        @JsonCreator
        fun fromValue(value: String): Compression {
            val constant = CONSTANTS[value]
            requireNotNull(constant) { value }
            return constant
        }
    }
}

enum class TrackConfiguration(private val value: String) {
    MONO("1/0 (Mono)"),
    DUAL_MONO("Dual mono"),
    STEREO("2/0 (Stereo)"),
    SURROUND("5.1 Surround");

    override fun toString(): String {
        return this.value
    }

    @JsonValue
    fun value(): String {
        return this.value
    }

    companion object {
        private val CONSTANTS: MutableMap<String, TrackConfiguration> = HashMap()

        init {
            for (c in values()) {
                CONSTANTS[c.value] = c
            }
        }

        @JsonCreator
        fun fromValue(value: String): TrackConfiguration {
            val constant = CONSTANTS[value]
            requireNotNull(constant) { value }
            return constant
        }
    }
}