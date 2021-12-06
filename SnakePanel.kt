import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.util.*
import javax.swing.JLabel
import javax.swing.JPanel


internal class SnakePanel : JPanel() {
    // The board will contain ints. 0 = empty. 1 = is player 1, 2 = is player 2
    private val board: IntArray
    private val jeff //the name of the snake is Jeff
            : Snake
    private val keysPressed //calling the keysPressed keysPressed
            : KeysPressed
    var pelletX: Int
    var pelletY //the pellets coordinates
            : Int

    // The colors.
    var colors: Array<Color?>
    var sleep = 75
        private set

    fun readBoard(col: Int, row: Int): Int {
        return if (col >= 0 && col < NumColumns && row >= 0 && row < NumRows) {
            board[col + row * NumColumns]
        } else {
            OutOfBounds
        }
    }

    fun setBoard(col: Int, row: Int, value: Int) {
        if (col >= 0 && col < NumColumns && row >= 0 && row < NumRows) {
            board[col + row * NumColumns] = value
        } else {
        }
    }

    public override fun paintComponent(g: Graphics) {
        // CLEAR THE BOARD:
        for (i in 0 until NumRows * NumColumns) {
            board[i] = Empty
        }
        // LET THE SNAKE 'DRAW' WHATEVER IT NEEDS TO:
        jeff.draw(this)
        // fill in the pellet's square with some color right here:
        setBoard(pelletX, pelletY, 2)
        statusbar = JLabel("0")
        // ACTUALLY DRAW THE THING:
        for (i in 0 until NumColumns) {
            for (j in 0 until NumRows) {
                g.color = colors[readBoard(i, j)]
                g.fillRect(i * SquareSize, j * SquareSize, SquareSize, SquareSize)
                // draw grid:
                g.color = Color.gray
                g.drawRect(i * SquareSize, j * SquareSize, SquareSize, SquareSize)
            }
        }
    }

    fun update() {
        jeff.update(keysPressed)
        if (jeff.eatsPellet(pelletX, pelletY)) {
            jeff.grow()
            if (sleep > 50) sleep--
            val rand = Random()
            pelletX = rand.nextInt(NumColumns)
            pelletY = rand.nextInt(NumRows)
        }
    }

    companion object {
        const val NumColumns = 40 // the number of columns on the board
        const val NumRows = 25 // the number of rows on the boards
        const val SquareSize = 17 // the size of squares in pixels
        const val NumPlayers = 4 // the number of players
        const val Empty = 0
        const val OutOfBounds = -1
        var statusbar: JLabel? = null
    }

    //private int sleepPause=10000000;
    init {
        val rand1 = Random()
        pelletX = rand1.nextInt(NumColumns)
        pelletY = rand1.nextInt(NumRows)
        keysPressed = KeysPressed()
        // need this line otherwise never get keypress events:
        this.isFocusable = true
        addKeyListener(keysPressed)
        jeff = Snake(rand1.nextInt(NumColumns), rand1.nextInt(NumRows), Direction.Right)
        board = IntArray(NumRows * NumColumns)
        for (i in 0 until NumRows * NumColumns) {
            board[i] = Empty
        }
        colors = arrayOfNulls(NumPlayers + 1)
        colors[0] = Color.darkGray
        colors[1] = Color.GREEN
        colors[2] = Color.RED
        this.preferredSize = Dimension(SquareSize * NumColumns, SquareSize * NumRows)
    }
}