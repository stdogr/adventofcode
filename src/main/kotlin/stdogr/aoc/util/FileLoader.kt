package stdogr.aoc.util

class FileLoader {

    fun load(fileName: String): String {
        return this::javaClass.get().classLoader
            .getResource(fileName)!!.readText()
    }
}
