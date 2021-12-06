import java.awt.event.KeyEvent
import java.awt.event.KeyEvent.*
import java.awt.event.KeyListener


internal class KeysPressed : KeyListener {
    var dir = Direction.Right
    var dir2 = Direction.Right
    override fun keyTyped(e: KeyEvent) {}
    override fun keyReleased(e: KeyEvent) {}

    override fun keyPressed(e: KeyEvent) {
        when (e.keyCode) {
            VK_UP -> if (dir !== Direction.Down) {
                dir = Direction.Up
            }
            VK_RIGHT -> if (dir !== Direction.Left) {
                dir = Direction.Right
            }
            VK_LEFT -> if (dir !== Direction.Right) {
                dir = Direction.Left
            }
            VK_DOWN -> if (dir !== Direction.Up) {
                dir = Direction.Down
            }
        }
    }
}