package utils

import java.io.File

class Utils {
    companion object {
        inline fun <reified T : Any> loadFile(
            year: String,
            day: String
        ): List<T> {
            val mainPath = "src/main/resources/y$year/"
            val list = mutableListOf<T>()

            File(mainPath + "day$day.txt").forEachLine {
                list.add(when (T::class) {
                    String::class -> it as T
                    else -> throw IllegalArgumentException("Unsupported type")
                })
            }

            return list
        }
    }
}