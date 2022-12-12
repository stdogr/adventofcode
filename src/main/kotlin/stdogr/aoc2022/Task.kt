package stdogr.aoc2022

abstract class Task<R>(
    val filePrefix: String,
) {

    abstract fun partOne(input: String): R

    abstract fun partTwo(input: String): R
}