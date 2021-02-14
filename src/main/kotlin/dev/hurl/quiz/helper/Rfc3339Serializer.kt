package dev.hurl.quiz.helper

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*



class Rfc3339Serializer : KSerializer<Date> {

    companion object {
        private val formatter: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")
    }

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Date", PrimitiveKind.STRING)
    override fun serialize(encoder: Encoder, value: Date) {
        val stringValue = formatter.format(value)
        encoder.encodeString(stringValue)
    }
    override fun deserialize(decoder: Decoder): Date {
        val date = decoder.decodeString()
        return formatter.parse(date)
    }
}