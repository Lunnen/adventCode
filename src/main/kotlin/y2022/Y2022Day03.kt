package y2022

import utils.Utils

private fun main() {
    Y2022Day03().part1()
}

class Y2022Day03 {

    private val list = Utils.loadFile("2022", "03")

    fun part1() {

        list.forEach { rucksack ->
            val rucksackLength = rucksack.length
            val chunked = rucksack.chunked(rucksackLength/2)
            val firstCompartment = chunked[0]
            val secondCompartment = chunked[1]


            val commonValues = firstCompartment.any(secondCompartment::contains)
            println("common " + commonValues)


        }

        println(" is the total score be if everything goes exactly according to my strategy guide")
    }

    fun part2() {



        println("$ is the total score be if everything goes exactly according to my strategy guide")
    }


}