tailrec fun translateToDigits(input: String, pointer: Int = 0): String =
    if (pointer == input.length) input
    else translateToDigits(replaceFirstDigit(input.drop(pointer), input), pointer + 1)

private fun replaceFirstDigit(chunkedPart: String, input: String) =
    if (chunkedPart.startsWith("one")) input.replaceFirst("one", "1")
    else if (chunkedPart.startsWith("two")) input.replaceFirst("two", "2")
    else if (chunkedPart.startsWith("three")) input.replaceFirst("three", "3")
    else if (chunkedPart.startsWith("four")) input.replaceFirst("four", "4")
    else if (chunkedPart.startsWith("five")) input.replaceFirst("five", "5")
    else if (chunkedPart.startsWith("six")) input.replaceFirst("six", "6")
    else if (chunkedPart.startsWith("seven")) input.replaceFirst("seven", "7")
    else if (chunkedPart.startsWith("eight")) input.replaceFirst("eight", "8")
    else if (chunkedPart.startsWith("nine")) input.replaceFirst("nine", "9")
    else input

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
         .map {
             // debug logging
             val that = translateToDigits(it)
             println("$it -> $that -> ${getFirstDecimal(that)} & ${getLastDecimal(that)} => ${getFirstDecimal(that) + getLastDecimal(that)}")

             translateToDigits(it)
         }
         .map { getFirstDecimal(it) + getLastDecimal(it) }
         .sumOf { it.toInt() }

     println(output2)
}
