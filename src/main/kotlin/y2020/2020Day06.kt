package y2020

import java.io.File
import java.util.*

class `2020Day06` {

    private val year = "2020"
    private val day = "06"
    private val list = arrayListOf<String>()
    private val mainPath = "src/main/kotlin/y$year/files/"

    private val groups: HashMap<Int, String> = hashMapOf()

    /*
    For each group, count the number of questions to which anyone answered "yes". What is the sum of those counts?
    Part 1 => 6778
    For each group, count the number of questions to which everyone answered "yes". What is the sum of those counts?
    Part 2 => 3406
     */

    fun run() {
        loadAndArrangeFile()
        part1()
        part2()
    }

    private fun loadAndArrangeFile() {
        File(mainPath + year + "day" + day + ".txt").forEachLine {
            list.add(it)
        }

        var groupID = 0
        /* Order every row into a specific group with its own ID */
        for (rowIndex in 0 until list.size) {
            if (list[rowIndex].isNotEmpty()) {
                if (groups.containsKey(groupID)) groups[groupID] = groups[groupID] + ":" + list[rowIndex]
                else groups[groupID] = list[rowIndex]
            }
            if (list[rowIndex].isEmpty() || rowIndex == list.size) groupID++
        }
    }

    private fun part1() {

        var totalAnswers = 0
        val uniqueAnswers = arrayListOf<String>()

        groups.forEach { (key, value) ->
            for (currentCol in value.indices) {
                if(value[currentCol].toString() != ":") uniqueAnswers.add(value[currentCol].toString())
            }
            totalAnswers += uniqueAnswers.stream().distinct().count().toInt()
            uniqueAnswers.clear()
        }
        println("$year-$day part #1 result => $totalAnswers to which anyone answered yes")
    }

    private fun part2() {

     var totalScore = 0

        groups.forEach { (key, value) ->
            val groupMembers = value.split(":")
            val membersAnswers = arrayListOf<String>()
            val nrOfMembers = groupMembers.size

            for (letterIndex in groupMembers.indices){
                /* add every member's answer to list */
                groupMembers[letterIndex].forEach { membersAnswers.add(it.toString())  }
            }
            /* check if every member has voted for the letter, by using a distinct forEach against the entire list */
            membersAnswers.distinct().forEach(){ answerIt ->
                if ( membersAnswers.stream().filter { it.toString() == answerIt  }.count().toInt() == nrOfMembers) totalScore++
            }
            membersAnswers.clear()
        }
        println("$year-$day part #2 result => $totalScore to which everyone answered yes")
    }
}