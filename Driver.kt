
import javax.swing.JFrame


object Driver {
    @JvmStatic
    fun main(args: Array<String>) {
        val mainWindow = JFrame("Snake Game") //Add a window and Name
        mainWindow.defaultCloseOperation = JFrame.EXIT_ON_CLOSE //Exit Button
        val snakeGame = SnakePanel() //declare and initialize snake
        mainWindow.add(snakeGame) //Put Game on window
        mainWindow.isVisible = true //Make It Show
        mainWindow.pack()
        snakeGame.setBoard(2, 2, 1)
        while (true) {
            snakeGame.update()
            try {
                Thread.sleep(snakeGame.sleep.toLong()) //Speed
            } catch (e: Exception) {
            }
            mainWindow.repaint() //refreshing it
        }
    }
}