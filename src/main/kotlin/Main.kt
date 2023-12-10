import encrypt.DesEcb
import encrypt.DesEcbImpl

fun main() {
    println(ubyteArrayOf("11101001".toUByte(2)))
    val des: DesEcb = DesEcbImpl()
    val message: UByteArray = ubyteArrayOf("00000001".toUByte(2), "00100011".toUByte(2), "01000101".toUByte(2), "01100111".toUByte(2), "10001001".toUByte(2), "10101011".toUByte(2), "11001101".toUByte(2), "11101111".toUByte(2))
    val key: UByteArray = ubyteArrayOf("00010011".toUByte(2), "00110100".toUByte(2), "01010111".toUByte(2), "01111001".toUByte(2), "10011011".toUByte(2), "10111100".toUByte(2), "11011111".toUByte(2), "11110001".toUByte(2))

    val encryptMessage = des.encrypt(message, key)
    val decryptMessage = des.decrypt(encryptMessage, key)

    println("initialMessage: $message")
    println("encryptMessage: $encryptMessage")
    println("decryptMessage: $decryptMessage")

}

fun Int.toBinary(len: Int): String {
    return String.format("%" + len + "s", this.toString(2)).replace(" ".toRegex(), "0")
}
