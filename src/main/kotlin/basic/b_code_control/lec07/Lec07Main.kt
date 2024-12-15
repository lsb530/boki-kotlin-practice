package basic.b_code_control.lec07

import java.io.BufferedReader
import java.io.File
import java.io.FileReader

fun main() {
    run {
        val currentFile = File(".")
        val file = File("${currentFile.absolutePath}/a.txt")
        val reader = BufferedReader(FileReader(file))
        println(reader.readLine())
        reader.close()
    }
    // use(try-with-resources)
    run {
        FileReader().readFile("a.txt")
    }
}

class FileReader {
    fun readFile(path: String) {
        BufferedReader(FileReader(path)).use { reader ->
            println(reader.readLine()) }
    }
}