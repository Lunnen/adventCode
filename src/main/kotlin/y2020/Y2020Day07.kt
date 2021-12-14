package y2020

private fun main() {
    Y2020Day07().run()
}

class Y2020Day07 {

    private val year = "2020"; private val day = "07"
    val mainPath = "src/main/resources/y$year/"

    private val fileInputList = mutableListOf<String>()
    private val luggageList = mutableListOf<MutableList<String>>()

    /*
  How many bag colors can eventually contain at least one shiny gold bag?
  Part 1 =>
 // TODO
  Part 2 =>
  */

    fun run() {
        loadFile()
        part1()
        part2();
    }

    private fun loadFile() {
        //File(mainPath + year + "day" + day + ".txt").forEachLine {
         //   list.add(it)
        //}

        fileInputList.add("light red bags contain 1 bright white bag, 2 muted yellow bags.")
        fileInputList.add("bright white bags contain 1 shiny gold bag.")
        fileInputList.add("muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.")
        fileInputList.add("shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.")
        fileInputList.add("dark olive bags contain 3 faded blue bags, 4 dotted black bags.")
        fileInputList.add("vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.")
        fileInputList.add("faded blue bags contain no other bags.")
        fileInputList.add("dotted black bags contain no other bags.")

        fileInputList.forEach { eachLine ->

            val bags = eachLine.replace("""bags|bag|,|\\.""".toRegex(), "").trim().split("contain") // Example : "light red"
            val containsThis =
                bags[1].replace(".", "").trim().split(" , ") // Example: "1 bright white bag" AND "2 muted yellow bags"


            val innerBags = mutableListOf<String>()
            innerBags.add(bags[0].trim())
            containsThis.forEach {
                println(it.length)
                if (it.length == 14) {
                    innerBags.add(it.substring(0, 1))
                    innerBags.add(it.substring(2, it.length))
                } else if (it.length == 12) {
                    innerBags.add(it.substring(0, 1))
                    innerBags.add("none")
                } else {
                    innerBags.add("none")
                    innerBags.add("none")
                }

            }



            luggageList.add(innerBags)
        }
    }

    fun part1() {


        luggageList.forEach { println(it) }

        for (i in luggageList.indices) {

            /*
            when (luggageList[i].size) {
                5 -> println(
                    " ${luggageList[i].subList(0, 1)} " +
                            "+ ${
                                luggageList[i].subList(2, 3)
                            } + ${luggageList[i].subList(4, 5)}"
                )
                3 -> println(" ${luggageList[i].subList(0, 1)} + ${luggageList[i].subList(2, 3)}")
                else -> println(" ${luggageList[i].subList(0, 1)} + ${luggageList[i].subList(1, 2)}")
            }

             */
        }


        //println("$year-$day part #1 result => $highestSeatID <= Mission: Binary Boarding")
    }

    private fun part2() {
        // TODO
    }


}