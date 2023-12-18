fun createGrid(input: List<String>) =
    input.map { it.toCharArray().toTypedArray() }.toTypedArray()

fun hitsSymbol(grid: Array<Array<Char>>, i: Int, j: Int) =
    try {
        !grid[i][j].isDigit() && grid[i][j] != '.'
    } catch (e: IndexOutOfBoundsException) {
        false
    }

private fun isAdjacentToSymbol(grid: Array<Array<Char>>, i: Int, j: Int) =
    hitsSymbol(grid, i - 1, j - 1) // left above
            || hitsSymbol(grid, i - 1, j) // above
            || hitsSymbol(grid, i - 1, j + 1) // right above
            || hitsSymbol(grid, i, j - 1) // left
            || hitsSymbol(grid, i, j + 1) // right
            || hitsSymbol(grid, i + 1, j - 1) // left under
            || hitsSymbol(grid, i + 1, j) // under
            || hitsSymbol(grid, i + 1, j + 1) // right under

private fun isAtEndOfRow(grid: Array<Array<Char>>, i: Int, j: Int) =
    j == grid[i].size - 1

private fun sumNumbersWithAdjacentSymbols(grid: Array<Array<Char>>): Int {
    var counter = 0
    for (i in grid.indices) {
        var isPartNumber = false
        var partNumber = ""

        for (j in grid[i].indices) {
            val char = grid[i][j]

            if (char.isDigit()) {
                partNumber += char
                if (isAdjacentToSymbol(grid, i, j)) isPartNumber = true
                if (isAtEndOfRow(grid, i, j) && isPartNumber) counter += partNumber.toInt()
            } else if (partNumber.isNotEmpty()) {
                if (isPartNumber) counter += partNumber.toInt()

                // reset
                isPartNumber = false
                partNumber = ""
            }
        }
    }
    return counter
}

fun main() {
    val grid = createGrid(getInput(3))

    // First star
    val counter = sumNumbersWithAdjacentSymbols(grid)

    println(counter)
}