package encrypt

interface DesEcb {
    fun encrypt(bytes: UByteArray, key: UByteArray): UByteArray
    fun decrypt(bytes: UByteArray, key: UByteArray): UByteArray
    fun keyInit(key: UByteArray)
}
