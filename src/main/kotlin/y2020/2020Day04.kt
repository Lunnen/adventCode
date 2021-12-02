import java.io.File

class `2020Day04` {

    private val year = "2020"
    private val day = "04"
    private val list = mutableListOf<String>()
    private val mainPath = "src/main/kotlin/y$year/files/"

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

    private fun part1() { // Answer: 222

        var counter = 0;
        /* Hashes values are removed, if they exist on input */
        val passportMissingDetails: HashMap<String, String> = hashMapOf()

        setNewPassport(passportMissingDetails);

        list.forEach { it ->

            if (it.isNotEmpty()) {
                val line: List<String> = it.split(" ")
                line.forEach {

                    val passValues: List<String> = it.split(":")
                    val passKey = passValues[0]

                    /* remove value from hashmap, if it exists in real passport input */
                    if (passportMissingDetails.containsKey(passKey)) passportMissingDetails.remove(passKey)
                }
            }
            if (it.isEmpty() || it == list.last()) { // if empty == end of current passport OR end of entire input list
                if (passportMissingDetails.isEmpty()) counter++;
                setNewPassport(passportMissingDetails);
            }
        }
        println("$year-$day part #1 result => $counter <= Mission: Passport processing")
    }

    private fun setNewPassport(passportMissingDetails: HashMap<String, String>) {
        passportMissingDetails["ecl"] = "toBeRemoved"
        passportMissingDetails["pid"] = "toBeRemoved"
        passportMissingDetails["eyr"] = "toBeRemoved"
        passportMissingDetails["hcl"] = "toBeRemoved"
        passportMissingDetails["byr"] = "toBeRemoved"
        passportMissingDetails["iyr"] = "toBeRemoved"
        passportMissingDetails["hgt"] = "toBeRemoved"
    }

    private fun part2() { // Answer: 140
        var counter = 0;
        /* Hashes values are removed, if they exist on input */
        val passportMissingDetails: HashMap<String, String> = hashMapOf()


        setNewPassport(passportMissingDetails);

        list.forEach { it ->

            if (it.isNotEmpty()) {
                val line: List<String> = it.split(" ")
                line.forEach {

                    val passValues: List<String> = it.split(":")
                    val passKey = passValues[0]
                    val passValue = passValues[1]

                    when(passKey){
                        "byr" -> if(passValue.toInt() in 1920..2002) passportMissingDetails.remove(passKey)
                        "iyr" -> if(passValue.toInt() in 2010..2020) passportMissingDetails.remove(passKey)
                        "eyr" -> if(passValue.toInt() in 2020..2030) passportMissingDetails.remove(passKey)
                        "hgt" -> {
                            if (passValue.contains("cm") && passValue.substring(0,3).toIntOrNull() in 150 .. 193) passportMissingDetails.remove(passKey)
                           else if (passValue.contains("in") && passValue.substring(0,2).toInt() in 59 .. 76) passportMissingDetails.remove(passKey)
                        }
                        "hcl" -> {
                            val pattern = "(#[a-f0-9]{6})".toRegex()
                            if (pattern.matches(passValue)) passportMissingDetails.remove(passKey)
                        }
                        "ecl" -> {
                            when(passValue){
                                "amb", "blu", "brn", "gry", "grn", "hzl", "oth" -> passportMissingDetails.remove(passKey)
                            }
                        }
                        "pid" -> {
                            val pattern = Regex( "[0-9]{9}" )
                            if (pattern.matches(passValue)) passportMissingDetails.remove(passKey)
                        }
                        "cid" -> {} //println("this isn't checked")
                        else -> print("ERROR! wrong passport key input")
                    }
                }
            }
            if (it.isEmpty() || it == list.last()) { // if empty == end of current passport OR end of entire input list
                if (passportMissingDetails.isEmpty()) counter++;
                setNewPassport(passportMissingDetails);
            }
        }
        println("$year-$day part #2 result => $counter <= Mission: Passport processing (with validation)")
    }
}

