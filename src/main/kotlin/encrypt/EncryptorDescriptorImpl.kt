package encrypt

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

interface EncryptorDescriptor {
    suspend fun encrypt(bytes: List<UByteArray>, key: UByteArray): List<UByteArray>
    suspend fun decrypt(bytes: List<UByteArray>, key: UByteArray): List<UByteArray>
}

class EncryptorDescriptorImpl : EncryptorDescriptor {
    val shifr: DesEcb = DesEcbImpl()
    override suspend fun encrypt(bytes: List<UByteArray>, key: UByteArray): List<UByteArray> {
        return withContext(Dispatchers.IO) {
            bytes.map { async { shifr.encrypt(it, key) } }.map { it.await() }.toList()
        }
    }

    override suspend fun decrypt(bytes: List<UByteArray>, key: UByteArray): List<UByteArray> {
        return withContext(Dispatchers.IO) {
            bytes.map { async { shifr.decrypt(it, key) } }.map { it.await() }.toList()
        }
    }

}
