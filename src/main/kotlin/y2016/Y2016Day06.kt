package y2016;

import java.io.File
import java.util.*

private fun main() {
    Y2016Day06().run()
}

class Y2016Day06 {

    private val year = "2016"; val day = "06"
    val mainPath = "src/main/resources/y$year/"

    private val list = arrayListOf<CharArray>() // 2D Array

    fun run() {
        File(mainPath + year + "day" + day + ".txt").useLines { lines ->
            lines.forEach { line ->
                list.add(line.map { it }.toCharArray())
            }
        }

        println("part1 => MOST common letter in each column is: ${part1()}")  // Answer: dzqckwsd
        println("part2 => LEAST common letter in each column is: ${part2()}") // Answer: lragovly
    }

    private fun part1(): String {

        return calcResult("most") //Get the MOST common letter of EACH column
    }

    private fun part2(): String {

        return calcResult("least") //Get the LEAST common letter of EACH column
    }

    private fun calcResult(calcMostOrLeast: String = "most"): String {

        val map: HashMap<Char, Int> = HashMap<Char, Int>();
        var result = ""

        var x = 0
        while (x < list[0].size) {
            for (y in 0 until list.size) { // Columns

                if (map.containsKey(list[y][x]))
                    map[list[y][x]] = map.getValue(list[y][x]) + 1
                else map[list[y][x]] = 1
            }
            when(calcMostOrLeast){
                "least" -> result += map.minByOrNull { it.value }.toString().first()
                "most" -> result += map.maxByOrNull { it.value }.toString().first()
            }

            map.clear()
            x++
        }

        return result
    }

}
