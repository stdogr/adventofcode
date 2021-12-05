package stdogr.aoc2021.util

class FileLoader {

    fun load(fileName: String): String {
        return this::javaClass.get().classLoader
            .getResource(fileName)!!.readText()
    }

    fun loadLines(fileName: String): List<String> {
        return load(fileName)
            .lines()
            .filter { it.isNotBlank() }
    }
}
