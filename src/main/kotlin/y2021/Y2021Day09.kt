package y2019

import java.io.File

private fun main() {
    Y2021Day09().run()
}

class Y2021Day09 {

    private val year = "2021"; val day = "09"
    val mainPath = "src/main/resources/y$year/"

    private val list = arrayListOf<IntArray>()
    private val lowPointsList = arrayListOf<Point>()
    private val visited = arrayListOf<Point>()

    fun run() {
        loadFile()
        part1() // Answer: 514
        part2()
    }

    private fun loadFile() {

        File(mainPath + year + "day" + day + ".txt").useLines { lines ->
            lines.forEach { line ->
                list.add(line.map { it.digitToInt() }.toIntArray())
            }
        }

    }

    private fun part1() {

        var totalScore = 0

        for (y in 0 until list.size) {
            for (x in 0 until list[y].size) {

                val currentHeight = list[y][x]

                if (y - 1 >= 0 && currentHeight >= list[y - 1][x]) continue // If the one on the left x-led is lower, skip
                if (y + 1 < list.size && currentHeight >= list[y + 1][x]) continue
                if (x - 1 >= 0 && currentHeight >= list[y][x - 1]) continue
                if (x + 1 < list[y].size && currentHeight >= list[y][x + 1]) continue

                lowPointsList.add(Point(y, x)) // For part two
                totalScore += (currentHeight + 1)
            }

        }
        println("Part 1 score is => $totalScore")
    }

    private fun part2() {
        // Find all basins
        val basinSizeResult = lowPointsList
            .map { basinRecursion(it) }
            .filter { it > 0 }
        // Find the three largest basins and multiply their sizes together, take the three highest values after sorted
        val largestBasin =
            basinSizeResult.sortedDescending().take(3).reduce(Int::times) // Multiplies this value by the other value
        println("Part 2 score is => $largestBasin")
    }

    private fun basinRecursion(point: Point): Int {
        val y = point.y
        val x = point.x

        if (notValidPoint(point, y, x)) return 0

        val topPoint = Point(y + 1, x)
        val bottomPoint = Point(y - 1, x)
        val leftPoint = Point(y, x - 1)
        val rightPoint = Point(y, x + 1)

        visited.add(point) // Current point is marked to make sure same point is not visited again

        // count every recursion
        return 1 +
                (basinRecursion(rightPoint)
                        + basinRecursion(leftPoint)
                        + basinRecursion(topPoint)
                        + basinRecursion(bottomPoint))
    }

    private fun notValidPoint(point: Point, y: Int, x: Int) =
        point in visited || y < 0 || x < 0 || x >= list[0].size || y >= list.size || list[y][x] == 9

    data class Point(val y: Int, val x: Int)
}

