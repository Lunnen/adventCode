package y2021

import java.io.File

class Y2021Day04 {

    private val year = "2021"
    private val day = "04"
    private val mainPath = "src/main/kotlin/y$year/files/"
    private val list = arrayListOf<String>()
    private var draws = arrayListOf<String>()
    private val boards: MutableList<MutableList<String>> = mutableListOf()

    /*
    What will your final score be if you choose that board?
    Part 1 => 16674
    Once it [the giant squid] wins, what would its final score be?
    Part 2 =>
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

                for(specGroup in 0 until boards.size){
                    val allAtChosenCol = mutableSetOf<String>()

                    var loopCols = 0
                    while(loopCols < boards[specGroup].size){
                        boards[specGroup].forEach { specBoardIt -> // go through all boards
                            val specBoardRow = specBoardIt.split("\\s+".toRegex()) // Split if one/multi spaces
                            allAtChosenCol.add(specBoardRow[loopCols])

                            val drawnMatchesEntireRow = specBoardRow.stream().allMatch { l -> numbersDrawn.contains(l) }
                            if(drawnMatchesEntireRow) {
                                getWinningScore(specGroup, numbersDrawn)
                                return
                            }
                        }

                        val drawnMatchesEntireCol = allAtChosenCol.stream().allMatch { l -> numbersDrawn.contains(l) }
                        if(drawnMatchesEntireCol) {
                            getWinningScore(specGroup, numbersDrawn)
                            return
                        }

                        allAtChosenCol.clear()
                        loopCols++
                    }
                }
            }
    }

    private fun getWinningScore(specGroup: Int, numbersDrawn: MutableSet<String>) {
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

        println("$year-$day part #1 => ${lastDraw * unmarkedOnWinningBoard}")
    }

    private fun part2() {
        // TODO  -- -all!

    }

}