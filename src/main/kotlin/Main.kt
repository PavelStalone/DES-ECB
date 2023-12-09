import encrypt.DesEcb
import encrypt.DesEcbImpl

fun main() {
    println(ubyteArrayOf(128u).toString())
    println(((128u as UInt) shl 1).toString(2))
    val des: DesEcb = DesEcbImpl()
    val message: UByteArray = ubyteArrayOf(1u, 2u, 3u, 4u, 5u, 6u, 7u, 8u)
    val key: UByteArray = ubyteArrayOf(8u, 7u, 6u, 5u, 4u, 3u, 2u, 1u)

    val encryptMessage = des.encrypt(message, key)
    val decryptMessage = des.decrypt(encryptMessage, key)

    println("message: $message")
    println("encryptMessage : $encryptMessage")
    println("decryptMessage : $decryptMessage")
}

fun Int.toBinary(len: Int): String {
    return String.format("%" + len + "s", this.toString(2)).replace(" ".toRegex(), "0")
}
