import encrypt.DesEcb
import encrypt.DesEcbImpl

fun main() {
    println(ubyteArrayOf("11101001".toUByte(2)))
    val des: DesEcb = DesEcbImpl()
    val message: UByteArray = ubyteArrayOf(0u, 0u, "11000100".toUByte(2), "11000010".toUByte(2), "11001110".toUByte(2), "11010000".toUByte(2), "11011111".toUByte(2), "11001010".toUByte(2))
    val key: UByteArray = ubyteArrayOf(0u, 0u, 0u, "11000100".toUByte(2), "11001000".toUByte(2), "11000000".toUByte(2), "11001101".toUByte(2), "11000000".toUByte(2))

    val encryptMessage = des.encrypt(message, key)
    val decryptMessage = des.decrypt(encryptMessage, key)

    println("initialMessage: $message")
    println("encryptMessage: $encryptMessage")
    println("decryptMessage: $decryptMessage")
}

fun Int.toBinary(len: Int): String {
    return String.format("%" + len + "s", this.toString(2)).replace(" ".toRegex(), "0")
}
