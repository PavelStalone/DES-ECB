import java.io.File
import javax.swing.JFileChooser

object FileChose {
    private val fileChooser = JFileChooser()

    fun chooseFile(): File {
        fileChooser.fileSelectionMode = JFileChooser.FILES_ONLY
        fileChooser.showOpenDialog(null)
        return fileChooser.selectedFile
    }

    fun chooseSaveFile(): File {
        fileChooser.fileSelectionMode = JFileChooser.DIRECTORIES_ONLY
        fileChooser.showSaveDialog(null)
        return fileChooser.selectedFile
    }
}