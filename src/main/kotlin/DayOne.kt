fun translateToDigits(input: String) =
    input
        .replace("one", "one1one")
        .replace("two", "two2two")
        .replace("three", "three3three")
        .replace("four", "four4four")
        .replace("five", "five5five")
        .replace("six", "six6six")
        .replace("seven", "seven7seven")
        .replace("eight", "eight8eight")
        .replace("nine", "nine9nine")

fun getFirstDecimal(input: String) =
    input.toCharArray().first { it.isDigit() }

fun getLastDecimal(input: String) =
    getFirstDecimal(input.reversed())

fun main() {
    // First star
    val output = getInput(1)
        .map { getFirstDecimal(it) + getLastDecimal(it) }
        .sumOf { it.toInt() }

    println(output)

    // Second start
    val output2 = getInput(1)
        .map { translateToDigits(it) }
        .map { getFirstDecimal(it) + getLastDecimal(it) }
        .sumOf { it.toInt() }

    println(output2)
}
