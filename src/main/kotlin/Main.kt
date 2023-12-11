import encrypt.*
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalUnsignedTypes::class)
fun main() {
    val input = ByteSpliter.splitForDES("Тестовый текст на разбиение текста")
    input.forEach { byteArray ->
        byteArray.forEach { print(it.toBinary(8) + " ") }
        println()
    }
    println(ByteSpliter.convertToString(input))

    val key: UByteArray = ubyteArrayOf("00010011".toUByte(2), "00110100".toUByte(2), "01010111".toUByte(2), "01111001".toUByte(2), "10011011".toUByte(2), "10111100".toUByte(2), "11011111".toUByte(2), "11110001".toUByte(2))
    val encryptor: EncryptorDescriptor = EncryptorDescriptorImpl()

    var encryptMessage = ""
    var decryptMessage = ""
    runBlocking {
        encryptMessage = ByteSpliter.convertToString(encryptor.encrypt(input, key))
    }
    println("encrypt: $encryptMessage")

    runBlocking {
        decryptMessage = ByteSpliter.convertToString(encryptor.decrypt(ByteSpliter.splitForDES(encryptMessage), key))
    }
    println("decrypt: $decryptMessage")
}

fun Int.toBinary(len: Int): String {
    return String.format("%" + len + "s", this.toString(2)).replace(" ".toRegex(), "0")
}
