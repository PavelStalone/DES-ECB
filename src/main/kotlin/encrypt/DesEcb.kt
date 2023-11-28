package encrypt

import kotlinx.coroutines.flow.Flow

interface DesEcb {
    fun encrypt(bytes: List<Byte>, key: List<Byte>): Flow<List<Byte>>
    fun decrypt(bytes: List<Byte>, key: List<Byte>): List<Byte>
}
