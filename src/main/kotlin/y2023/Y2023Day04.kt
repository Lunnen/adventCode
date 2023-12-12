package y2023

import utils.Utils

private fun main() {
    Y2023Day04().part2()
}

class Y2023Day04 {

    private val list = Utils.loadFile("2023", "04")

    fun part1() {

        var totalCounter = 0

        list.forEach { scratchCard ->
            val numbersArray = scratchCard.split(':')
                .drop(1)
                .flatMap { it.split('|').map { inner -> inner.trim().split(" ") } }
                .map { it.mapNotNull { number -> number.toIntOrNull() } }
                .toMutableList()

            val winningNumbers = numbersArray[0]
            val currentCardNumbers = numbersArray[1]

            var counter = 0

            winningNumbers.forEach { winningNr ->
                if (currentCardNumbers.contains(winningNr)) {
                    counter++
                }
            }

            println("Counter: $counter")
            println("Line value: ${calculateValue(counter)}")

            totalCounter += calculateValue(counter)
        }

       println("${totalCounter} points in total")
    }

    private fun calculateValue(n: Int): Int {
        return when (n) {
            0 -> 0
            1 -> 1
            2 -> 2
            else -> {
                var result = 2
                repeat(n - 2) {
                    result *= 2
                }
                result
            }
        }
    }

    fun part2() {
        var totalIndices = arrayListOf<Int>()

        list.forEachIndexed { index, scratchBoard ->
            totalIndices += index
        }


        val indices = getIndices(list, totalIndices, totalIndices)

        println("${indices.size} points in total")

    }

    private fun getIndices(list: List<String>, indices: List<Int>, totalIndices: MutableList<Int>): List<Int> {
        println("${totalIndices} Total")

        if (indices.isEmpty()) {
            return totalIndices
        }

        val nextIndices = mutableListOf<Int>()

        indices.forEach { insideIndex ->
            nextIndices.addAll(getIndice(list[insideIndex], insideIndex))
        }

        totalIndices.addAll(nextIndices)

        return getIndices(list, nextIndices, totalIndices)
    }


    private fun getIndice(scratchBoard: String, chosenIndex: Int): MutableList<Int> {

        var matchingIndices = arrayListOf<Int>()

            val numbersArray = extractNumbersArray(scratchBoard)
            val winningNumbers = numbersArray[0]
            val currentCardNumbers = numbersArray[1]
            var counter = countMatchingNumbers(winningNumbers, currentCardNumbers)

            for (i in chosenIndex until (chosenIndex + counter)) {
                matchingIndices.add(i+1)
            }

        return matchingIndices

    }

    private fun extractNumbersArray(scratchCard: String): List<List<Int>> {
        return scratchCard.split(':')
            .drop(1)
            .flatMap { it.split('|').map { inner -> inner.trim().split(" ") } }
            .map { it.mapNotNull { number -> number.toIntOrNull() } }
    }

    private fun countMatchingNumbers(winningNumbers: List<Int>, currentCardNumbers: List<Int>): Int {
        var counter = 0

        winningNumbers.forEach { winningNr ->
            if (currentCardNumbers.contains(winningNr)) {
                counter++
            }
        }

        return counter
    }

}