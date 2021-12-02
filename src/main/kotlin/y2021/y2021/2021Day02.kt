package y2021

import java.io.File

class `2021Day02` {

    private val list = arrayListOf<String>()
    private val mainPath = "src/main/kotlin/y2021/files/"
    private val year = "2021"
    private val day = "02"

    fun run() {
        loadFile()
        part1()
        part2()
    }


    private fun loadFile() {
        File(mainPath + year + "day"+ day +".txt").forEachLine {
            list.add(it)
        }
    }

    private fun part1() { // Answer: 1459206

        var horizontalX = 0
        var verticalY = 0

        for (currentIndex in 0 until list.size) {

            val line: List<String> = list[currentIndex].split(" ")
            val direction = line[0]
            val steps = line[1].toInt()

            when(direction){
                "forward" -> horizontalX += steps
                "down" -> verticalY += steps
                "up" -> verticalY -= steps
                else -> print("ERROR! Wrong direction")
            }
        }
        println("$year-$day part #1 => ${horizontalX * verticalY} <= Mission: Submarine-moves")
    }

    private fun part2() { // Answer: 1 320 534 480

        var horizontalX = 0
        var depth = 0
        var aim = 0

        for (currentIndex in 0 until list.size) {

            val line: List<String> = list[currentIndex].split(" ")
            val direction = line[0]
            val steps = line[1].toInt()

            when(direction){
                "forward" -> {
                    horizontalX += steps
                    depth += (steps * aim)
                }
                "down" -> aim += steps
                "up" -> aim -= steps
                else -> print("ERROR! Wrong direction")
            }
        }
        println("$year-$day part #2 => ${horizontalX * depth} <= Mission: Submarine-moves with aim")
    }

}