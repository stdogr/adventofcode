package stdogr.util

import java.io.File

fun loadResource(fileName: String): String {
    return File(ClassLoader.getSystemResource(fileName).file).readText()
}

fun loadResourceLines(fileName: String): List<String> {
    return loadResource(fileName)
        .lines()
        .filter { it.isNotBlank() }
}
