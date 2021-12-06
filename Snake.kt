
import java.util.*


internal class Snake(x: Int, y: Int, dir: Direction) {
    private val body: LinkedList<SquareCoords> = LinkedList()
    private var dir //Direction is named dir
            : Direction
    private var needsToGrow = false //It's not growing
    private var isAlive = true //the snake is alive
    fun eatsPellet(pelletX: Int, pelletY: Int): Boolean {
        val head = body.last
        return head.x == pelletX && head.y == pelletY
    }

    fun update(keysPressed: KeysPressed) {
        if (!isAlive) return
        val pos = body.last
        var x = pos.x
        var y = pos.y
        val newDirection = keysPressed.dir
        dir = newDirection
        when (dir) {
            Direction.Up -> y--
            Direction.Down -> y++
            Direction.Left -> x--
            Direction.Right -> x++
        }
        if (x < 0) x += SnakePanel.NumColumns
        if (x >= SnakePanel.NumColumns) x -= SnakePanel.NumColumns
        if (y < 0) y += SnakePanel.NumRows
        if (y >= SnakePanel.NumRows) y -= SnakePanel.NumRows
        // make a new 'square (SquareCoords) from x & y, add at the end:
        body.addLast(SquareCoords(x, y))
        if (!needsToGrow) body.removeFirst() else needsToGrow = false
        // need to check for collision--see if head is now on one of the body squares:
        for (i in 0 until body.size - 1) {
            val segment = body[i]
            if (segment.x == x && segment.y == y) isAlive = false
        }
        score = body.size
    }

    fun grow() {
        needsToGrow = true
    }

    fun draw(panel: SnakePanel) {
        for (pos in body) {
            panel.setBoard(pos.x, pos.y, 1)
        }
    }

    companion object {
        var score = 0
    }

    init {
        body.add(SquareCoords(x, y))
        this.dir = dir
    }
}