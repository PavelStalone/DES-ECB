import encrypt.ByteSpliter
import encrypt.EncryptorDescriptor
import encrypt.EncryptorDescriptorImpl
import encrypt.toBinary
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

@OptIn(ExperimentalUnsignedTypes::class)
fun main() {
    val encryptFlag: Boolean
    while (true) {
        println(
            """
        |Выберите операцию...
        |1: расшифровать
        |2: зашифровать
    """.trimMargin()
        )
        var read = readln()
        encryptFlag = try {
            when (read.toInt()) {
                1 -> false
                2 -> true
                else -> continue
            }
        } catch (e: Exception) {
            continue
        }
        break
    }

    fileRead(encryptFlag)
}

fun consoleRead(encryptFlag: Boolean) {
    println("Введите свое сообщение")
    val input = ByteSpliter.splitForDES(readln())

    print("Введите ключ: ")
    val key = ByteSpliter.convertKey(readln())

    println(ByteSpliter.convertToString(input))
    val encryptor: EncryptorDescriptor = EncryptorDescriptorImpl()

    var encryptMessage = ""
    var decryptMessage = ""

    if (encryptFlag) {
        runBlocking {
            encryptMessage = ByteSpliter.convertToString(encryptor.encrypt(input, key))
        }
        println("encrypt: $encryptMessage")
    } else {
        runBlocking {
            decryptMessage = ByteSpliter.convertToString(encryptor.decrypt(input, key))
        }
        println("decrypt: $decryptMessage")
    }
}

fun fileRead(encryptFlag: Boolean) {
    val encryptor: EncryptorDescriptor = EncryptorDescriptorImpl()

    print("Введите ключ: ")
    val key = ByteSpliter.convertKey(readln())

    println("Выберите файл...")
    val chooseFile = FileChose.chooseFile()
    val input = ByteSpliter.splitForDes(FileIO.read(chooseFile))

    println("Выберите путь сохранения файла...")
    val saveDirectory = FileChose.chooseSaveFile()

    var encryptBytes: UByteArray
    var decryptBytes: UByteArray

    if (encryptFlag) {
        encryptor.keyInit(key)
        val time = measureTimeMillis {
            runBlocking {
                encryptBytes = ByteSpliter.convertToBytes(encryptor.encrypt(input, key))
            }
        }
        println("time: $time")
        FileIO.write(saveDirectory, encryptBytes, "[шифр]" + chooseFile.name)
    } else {
        runBlocking {
            decryptBytes = ByteSpliter.convertToBytes(encryptor.decrypt(input, key))
        }
        FileIO.write(saveDirectory, decryptBytes, "[расшифровка]" + chooseFile.name)
    }
}

fun Int.toBinary(len: Int): String {
    return String.format("%" + len + "s", this.toString(2)).replace(" ".toRegex(), "0")
}
