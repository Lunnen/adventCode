package y2023
import utils.Utils

private fun main() {
    //Y2023Day14().part1() // SUCCESS > 110779
    Y2023Day14().part2() // SUCCESS > 86069 (1_000 and 1_000_000_000 are the same)

}

class Y2023Day14 {

    private var list = Utils.loadFile("2023", "14").map { it.toCharArray() }
    val cache = HashMap<String, String>()

    fun part1() {

        moveTo(list, Direction.NORTH)

        val totalScore = list.mapIndexed { index, charArray ->
            val countOfO = charArray.count { it == 'O' }
            countOfO * (list.size - index) // Multiply count by the index
        }.sum()

        println("Score is ${totalScore}")

    }

    fun part2() {

        cycles(3)

        list.forEach {
            println(it)
        }

        println("Score is ${calculateScore()}")

    }

    private fun cycles(times: Int) {

        repeat(times) { index ->
            list = oneCycle(list)
        }
    }


    private fun oneCycle(inputList: List<CharArray>): List<CharArray> {
        return Direction.values().map { moveTo(inputList, it) }.last()
    }

    private fun calculateScore(): Int {
        return list.mapIndexed { index, charArray ->
            val countOfO = charArray.count { it == 'O' }
            countOfO * (list.size - index)
        }.sum()
    }


    private fun moveTo(inputList: List<CharArray>, direction: Direction = Direction.NORTH): List<CharArray> {

        fun moveDirection(inputList: List<CharArray>, row: Int, col: Int, direction: Direction): List<CharArray> {
            var currentRowIndex = row
            var currentColIndex = col

            while (true) {
                when (direction) {
                    Direction.NORTH -> {
                        // Logic for moving NORTH direction
                        if (currentRowIndex - 1 >= 0 && (inputList[currentRowIndex - 1][col] == '#' || inputList[currentRowIndex - 1][col] == 'O')) {
                            inputList[row][col] = '.'
                            inputList[currentRowIndex][col] = 'O'
                            return inputList
                        }
                        if (currentRowIndex - 1 == 0 && inputList[0][col] == '.') {
                            inputList[row][col] = '.'
                            inputList[0][col] = 'O'
                            return inputList
                        }
                        if (currentRowIndex > 0) {
                            currentRowIndex--
                        } else {
                            break
                        }
                    }
                    Direction.SOUTH -> {
                        // Logic for moving SOUTH direction
                        if (currentRowIndex + 1 < inputList.size && (inputList[currentRowIndex + 1][col] == '#' || inputList[currentRowIndex + 1][col] == 'O')) {
                            inputList[row][col] = '.'
                            inputList[currentRowIndex][col] = 'O'
                            return inputList
                        }
                        if (currentRowIndex + 1 == inputList.size && inputList[inputList.size - 1][col] == '.') {
                            inputList[row][col] = '.'
                            inputList[inputList.size - 1][col] = 'O'
                            return inputList
                        }
                        if (currentRowIndex < inputList.size - 1) {
                            currentRowIndex++
                        } else {
                            break
                        }
                    }
                    Direction.WEST -> {
                        // Logic for moving WEST direction
                        if (currentColIndex - 1 >= 0 && (inputList[row][currentColIndex - 1] == '#' || inputList[row][currentColIndex - 1] == 'O')) {
                            inputList[row][col] = '.'
                            inputList[row][currentColIndex] = 'O'
                            return inputList
                        }
                        if (currentColIndex - 1 == 0 && inputList[row][0] == '.') {
                            inputList[row][col] = '.'
                            inputList[row][0] = 'O'
                            return inputList
                        }
                        if (currentColIndex > 0) {
                            currentColIndex--
                        } else {
                            break
                        }
                    }
                    Direction.EAST -> {
                        // Logic for moving EAST direction
                        if (currentColIndex + 1 < inputList[row].size && (inputList[row][currentColIndex + 1] == '#' || inputList[row][currentColIndex + 1] == 'O')) {
                            inputList[row][col] = '.'
                            inputList[row][currentColIndex] = 'O'
                            return inputList
                        }
                        if (currentColIndex + 1 == inputList[row].size && inputList[row][inputList[row].size - 1] == '.') {
                            inputList[row][col] = '.'
                            inputList[row][inputList[row].size - 1] = 'O'
                            return inputList
                        }
                        if (currentColIndex < inputList[row].size - 1) {
                            currentColIndex++
                        } else {
                            break
                        }
                    }
                }
            }

            return inputList
        }

        var tempList = inputList

        val rowRange = when (direction) {
            Direction.SOUTH -> tempList.indices.reversed()
            else -> tempList.indices
        }

        val colRange = when (direction) {
            Direction.EAST -> tempList[0].indices.reversed()
            else -> tempList[0].indices
        }

        for (rowIndex in rowRange) {
            for (colIndex in colRange) {
                if (tempList[rowIndex][colIndex] == 'O') {
                    tempList = moveDirection(tempList, rowIndex, colIndex, direction)
                }
            }
        }

        return tempList
    }

    enum class Direction {
        NORTH,
        WEST,
        SOUTH,
        EAST
    }
}
