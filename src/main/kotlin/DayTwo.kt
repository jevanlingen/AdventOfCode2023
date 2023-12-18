data class Grab(val green: Int, val red: Int, val blue: Int)
data class Game(val number: Int, val grabs: List<Grab>)

fun grab(part: String): Grab {
    val numberRegex = Regex("""(\d+)\s+""")
    val colorsRegex = Regex("""\d+\s+(\w+)""")

    val numbers = numberRegex.findAll(part).map { it.groupValues[1].toInt() }.toList()
    val colors = colorsRegex.findAll(part).map { it.groupValues[1] }.toList()

    return Grab(
        numbers.getOrElse(colors.indexOf("green")) { 0 },
        numbers.getOrElse(colors.indexOf("red")) { 0 },
        numbers.getOrElse(colors.indexOf("blue")) { 0 },
    )
}

private fun makeGames(input: String): Game {
    val pattern = Regex("""Game (\d+):[^;]*""")
    val matchResult = pattern.find(input)

    return Game(
        matchResult?.groupValues?.get(1)!!.toInt(),
        input.split(":")[1].split(";").map { grab(it) }
    )
}

fun main() {
    val games = getInput(2).map { makeGames(it) }

    // First star
    val output = games
        .filter { game -> game.grabs.all { it.red <= 12 && it.green <= 13 && it.blue <= 14 } }
        .sumOf { it.number }

    println(output)

    // Second start
    val output2 = games
        .map { it.grabs.reduce { a, b -> Grab(maxOf(a.green,b.green), maxOf(a.red,b.red), maxOf(a.blue,b.blue)) } }
        .sumOf { it.red * it.green * it.blue }

    println(output2)
}
