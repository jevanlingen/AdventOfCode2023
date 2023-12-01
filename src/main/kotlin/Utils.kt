import java.io.File

fun getInput(day: Int): List<String>
        = File(getResourceAsText("input-day-$day.txt")).useLines { it.toList() }

operator fun Char.plus(that: Char) =
    this.plus(that.toString())

operator fun Char.plus(that: String) =
    this.toString().plus(that)

private fun getResourceAsText(path: String) =
    object {}.javaClass.getResource(path)?.file!!