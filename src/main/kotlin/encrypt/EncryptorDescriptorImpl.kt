package encrypt

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

interface EncryptorDescriptor {
    suspend fun encrypt(bytes: List<UByteArray>, key: UByteArray): List<UByteArray>
    suspend fun decrypt(bytes: List<UByteArray>, key: UByteArray): List<UByteArray>
    fun keyInit(key: UByteArray)
}

class EncryptorDescriptorImpl : EncryptorDescriptor {
    val shifr: DesEcb = DesEcbImpl()

    override suspend fun encrypt(bytes: List<UByteArray>, key: UByteArray): List<UByteArray> {
        return withContext(Dispatchers.Default) {
            bytes.map { async(start = CoroutineStart.ATOMIC) { shifr.encrypt(it, key) } }.map { it.await() }.toList()
        }
    }

    override suspend fun decrypt(bytes: List<UByteArray>, key: UByteArray): List<UByteArray> {
        return withContext(Dispatchers.Default) {
            bytes.map { async(start = CoroutineStart.ATOMIC) { shifr.decrypt(it, key) } }.map { it.await() }.toList()
        }
    }

    override fun keyInit(key: UByteArray) {
        shifr.keyInit(key)
    }

}
