package y2020

import java.io.File

private fun main() {
    Y2020Day05().run()
}

class Y2020Day05 {

    private val year = "2020"
    private val day = "05"
    private val list = mutableListOf<String>()
    val mainPath = "src/main/resources/y$year/"

    fun run() {
        loadFile()
        part1()
        part2();
    }

    private fun loadFile() {
        File(mainPath + year + "day" + day + ".txt").forEachLine {
            list.add(it)
        }
    }

    private fun part1() {  // Answer: 951

        var highestSeatID = 0

        list.forEach { mainIt ->

            var row = 0
            var column = 0

            val pair = pair(mainIt, row, column)
            column = pair.first
            row = pair.second

            val thisSeatBookingID = (row * 8 + column)
            if(thisSeatBookingID > highestSeatID) highestSeatID = thisSeatBookingID
        }
        println("$year-$day part #1 result => $highestSeatID <= Mission: Binary Boarding")
    }

    private fun part2() { // Answer: 653

        val allSeats = mutableListOf<Int>()
        var thisSeat = 0

        list.forEach { mainIt ->

            var row = 0
            var column = 0

            val pair = pair(mainIt, row, column)
            column = pair.first
            row = pair.second

            val thisSeatBookingID = (row * 8 + column)
            allSeats.add(thisSeatBookingID)
        }

        allSeats.sort()
        for (currentIndex in 1..allSeats.size-2) {    // Finding the empty seat
                if(allSeats[currentIndex-1]+1 != allSeats[currentIndex] ) {
                    if(allSeats[currentIndex+1]-2 == allSeats[currentIndex] ) thisSeat = allSeats[currentIndex]+1
                    else if(allSeats[currentIndex-1]+2 == allSeats[currentIndex] ) thisSeat = allSeats[currentIndex]-1
                }
        }
        println("$year-$day part #1 result => $thisSeat <= Mission: Binary Boarding (only missing seat)")
    }

    private fun pair(mainIt: String, row: Int, column: Int): Pair<Int, Int> {
        var row1 = row
        var column1 = column
        val rowHandling = mainIt.substring(0, 7)
        if (rowHandling[0] == 'B') row1 += 64
        if (rowHandling[1] == 'B') row1 += 32
        if (rowHandling[2] == 'B') row1 += 16
        if (rowHandling[3] == 'B') row1 += 8
        if (rowHandling[4] == 'B') row1 += 4
        if (rowHandling[5] == 'B') row1 += 2
        if (rowHandling[6] == 'B') row1 += 1

        val colHandling = mainIt.substring(7, 10)
        if (colHandling[0] == 'R') column1 += 4
        if (colHandling[1] == 'R') column1 += 2
        if (colHandling[2] == 'R') column1 += 1
        return Pair(column1, row1)
    }
}

