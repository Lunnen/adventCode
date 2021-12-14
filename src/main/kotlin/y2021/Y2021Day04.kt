package y2021

import java.io.File

private fun main() {
    Y2021Day04().run()
}

class Y2021Day04 {

    private val year = "2021"
    private val day = "04"
    val mainPath = "src/main/resources/y$year/"
    private val list: MutableList<String> = mutableListOf()
    private var draws: MutableList<String> = mutableListOf()
    private var boards: MutableList<MutableList<String>> = mutableListOf()

    /*
    What will your final score be if you choose that board?
    Part 1 => 16674
    Once it [the giant squid] wins, what would its final score be?
    Part 2 => 7075
    */

    fun run() {
        loadFile()
        part1()
        part2()
    }

    private fun loadFile() {
        File(mainPath + year + "day" + day + ".txt").forEachLine {
            list.add(it)
        }
        draws = list[0].split(",") as ArrayList<String>

        /* Draws and Boards in different lists */
        val eachBoard: ArrayList<String> = arrayListOf()
        for (rowIndex in 2 until list.size) {

            if (list[rowIndex].isNotEmpty()) eachBoard.add(list[rowIndex].trim())
            else {
                boards.add(eachBoard.toMutableList())
                eachBoard.clear()
            }
        }
    }

    private fun part1() {

        val numbersDrawn = mutableSetOf<String>() // The list every new number drawn is added to

        draws.forEach { thisDrawn ->
            numbersDrawn.add(thisDrawn) // every time a draw happens, at to the list that compares if victory has happened

            for (specGroup in 0 until boards.size) {
                val allAtChosenCol = mutableSetOf<String>()

                var loopCols = 0
                while (loopCols < boards[specGroup].size) {
                    boards[specGroup].forEach { specBoardIt -> // go through all boards
                        val specBoardRow = specBoardIt.split("\\s+".toRegex()) // Split if one/multi spaces
                        allAtChosenCol.add(specBoardRow[loopCols])

                        val drawnMatchesEntireRow = specBoardRow.stream().allMatch { l -> numbersDrawn.contains(l) }
                        if (drawnMatchesEntireRow) {
                            getWinningScore(specGroup, numbersDrawn, 1)
                            return
                        }
                    }

                    val drawnMatchesEntireCol = allAtChosenCol.stream().allMatch { l -> numbersDrawn.contains(l) }
                    if (drawnMatchesEntireCol) {
                        getWinningScore(specGroup, numbersDrawn, 1)
                        return
                    }

                    allAtChosenCol.clear()
                    loopCols++
                }
            }
        }
    }

    private fun getWinningScore(specGroup: Int, numbersDrawn: MutableSet<String>, whichPart: Int) {

        val allNrsWinningGroup = mutableSetOf<String>()
        boards[specGroup].forEach() {
            val specBoardRowWinner = it.split("\\s+".toRegex()) // Split if one/multi spaces
            specBoardRowWinner.forEach { nrOnRow ->
                allNrsWinningGroup.add(nrOnRow)
            }
        }

        /* only keeping unmarked nr */
        numbersDrawn.stream()// compare to this list and...
            .filter { p1 ->
                allNrsWinningGroup.stream().anyMatch { p2 -> p1 == p2 } // remove from this <---
            }
            .forEach(allNrsWinningGroup::remove)

        val lastDraw = numbersDrawn.last().toInt()
        val unmarkedOnWinningBoard = allNrsWinningGroup.stream().mapToInt { it.toInt() }.sum()

        println("$year-$day part #$whichPart => ${lastDraw * unmarkedOnWinningBoard}")
    }

    private fun part2() {

        val numbersDrawn = mutableSetOf<String>() // The list every new number drawn is added to
        val winGroupsInOrder = mutableListOf<String>()

        draws.forEach { thisDrawn ->
            numbersDrawn.add(thisDrawn) // every time a draw happens, at to the list that compares if victory has happened

            for (specGroup in 0 until boards.size) {

                val allAtChosenCol = mutableSetOf<String>()

                var loopCols = 0
                while (loopCols < boards[specGroup].size) {
                    boards[specGroup].forEach { specBoardIt -> // go through all boards
                        val specBoardRow = specBoardIt.split("\\s+".toRegex()) // Split if one/multi spaces
                        allAtChosenCol.add(specBoardRow[loopCols])

                        val drawnMatchesEntireRow = specBoardRow.stream().allMatch { l -> numbersDrawn.contains(l) }
                        if (drawnMatchesEntireRow) {
                            if(winGroupsInOrder.indexOfFirst { it.split(":")[0].toInt() == specGroup } < 0) winGroupsInOrder.add("$specGroup:$numbersDrawn")
                        }
                    }

                    val drawnMatchesEntireCol = allAtChosenCol.stream().allMatch { l -> numbersDrawn.contains(l) }
                    if (drawnMatchesEntireCol) {
                        if(winGroupsInOrder.indexOfFirst { it.split(":")[0].toInt() == specGroup } < 0) winGroupsInOrder.add("$specGroup:$numbersDrawn")
                    }

                    allAtChosenCol.clear()
                    loopCols++
                }
            }
        }

        /* Check Winner */
        val numbersDrawnUntilLastGroupWins = mutableSetOf<String>()
        val numberArrayString = winGroupsInOrder.last().split(":[")[1].trim()

        numberArrayString.replace("]", "").split(", ").forEach { numbersDrawnUntilLastGroupWins.add(it) }
        val lastGroupToWin = winGroupsInOrder.last().split(":[")[0].trim().toInt()

        getWinningScore(lastGroupToWin, numbersDrawnUntilLastGroupWins, 2)
    }
}

