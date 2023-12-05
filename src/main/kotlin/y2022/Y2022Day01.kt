package y2022

import utils.Utils

private fun main() {
    Y2022Day01().part1()
    Y2022Day01().part2()
}

class Y2022Day01 {

    private val list = Utils.loadFile<String>("2022", "01")

    fun part1() {

        val listOfElves = arrayListOf<Int>()

        var currentElf = arrayListOf<Int>()

        for(caloriesCount in list) {


            if(caloriesCount.isNotEmpty()) {
                currentElf.add(caloriesCount.toInt())

                println("${currentElf.sum()} ELF now")

            } else {
                listOfElves.add(currentElf.sum())

                println("${currentElf.sum()} ELF total")

                currentElf = arrayListOf()
            }
        }

        val highestCaloriesCount = listOfElves.maxWithOrNull(compareBy { it })
        val elfWithHighestCaloriesCount  = listOfElves.indices.maxByOrNull { it }?.plus(1)

        println("Elf nr ${elfWithHighestCaloriesCount} has the most, with $highestCaloriesCount total Calories is that Elf carrying")
    }

    fun part2() {

        val listOfElves = arrayListOf<Int>()

        var currentElf = arrayListOf<Int>()

        for(caloriesCount in list) {


            if(caloriesCount.isNotEmpty()) {
                currentElf.add(caloriesCount.toInt())

                println("${currentElf.sum()} ELF now")

            } else {
                listOfElves.add(currentElf.sum())

                println("${currentElf.sum()} ELF total")

                currentElf = arrayListOf()
            }
        }

        val top3eleves  = listOfElves.indices.sortedByDescending { it }.take(3)
        val totalCalories = listOfElves.sortedByDescending { it }.take(3).sum()

        println("Top three elves are the numbers ${top3eleves} has the most, with $totalCalories total Calories is that Elf carrying")
    }

}