import java.io.File

object FileIO {
    fun read(file: File): UByteArray {
        return file.readBytes().toUByteArray()
    }

    fun write(directory: File, bytes: UByteArray, fileName: String){
        val decFile = File(directory.absolutePath+"/$fileName")
        decFile.writeBytes(bytes.toByteArray())
    }
}