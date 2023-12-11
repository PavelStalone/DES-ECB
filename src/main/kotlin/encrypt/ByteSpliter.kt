package encrypt

object ByteSpliter {
    @OptIn(ExperimentalUnsignedTypes::class)
    fun splitForDES(text: String): List<UByteArray> {
        val bytes = text.map { it.toBinary() }.reduce { acc, s -> acc + s }
        val list = mutableListOf<UByteArray>()
        bytes.chunked(64) { chars ->
            val num = chars.toString().toULong(2)
            list.add(UByteArray(8) {
                (num shl it * 8 shr (7 * 8)).toUByte()
            })
        }
        return list
    }

    fun convertToString(bytes: List<UByteArray>): String {
        return bytes.map(::convertUBytesToString).reduce { acc, s -> acc + s }
    }

    private fun convertUBytesToString(bytes: UByteArray): String {
        return bytes.take(8).chunked(2)
            .map { (it[0].toBinary(8) + it[1].toBinary(8)).toInt(2).toChar().toString() }
            .filter { it.toCharArray()[0].code != 0 }
            .reduce { acc, s -> acc + s }
    }

    fun convertKey(key: String): UByteArray {
        return ubyteArrayOf()
    }
}

fun Char.toBinary(): String {
    val num = this.code
    return String.format("%" + 16 + "s", num.toString(2)).replace(" ".toRegex(), "0")
}