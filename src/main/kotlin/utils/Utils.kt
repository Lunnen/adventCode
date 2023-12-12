package utils

import java.io.File

class Utils {
    companion object {
        fun loadFile(year: String, day: String): List<String> {
            val mainPath = "src/main/resources/y$year/"
            val list = mutableListOf<String>()

            File(mainPath + "day$day.txt").forEachLine {
                list.add(it)
            }

            return list
        }
    }
}
