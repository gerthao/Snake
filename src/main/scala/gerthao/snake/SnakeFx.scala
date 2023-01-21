package gerthao.snake

import scalafx.application.{JFXApp3, Platform}
import scalafx.beans.property.{IntegerProperty, ObjectProperty}
import scalafx.scene.Scene
import scalafx.scene.input.{KeyCode, KeyEvent}
import scalafx.scene.paint.Color

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object SnakeFx extends JFXApp3:
  override def start(): Unit =
    val state = ObjectProperty(State.initialState())
    val frame = IntegerProperty(0)
    val direction = ObjectProperty(Direction.None)

    frame.onChange(
      state.update(state.value.newState(direction = direction.value))
    )

    stage = new JFXApp3.PrimaryStage:
      width  = 625
      height = 635
      scene  = new Scene:
        fill         = Color.White
        content      = StateDisplay(state.value).get
        onKeyPressed = k => direction.update(handleDirection(direction.value, KeyEvent(k)))
        frame.onChange {
          Platform.runLater {
            content = StateDisplay(state.value).get
          }
        }

    gameLoop(() => frame.update(frame.value + 1))
  end start

  private def gameLoop(update: () => Unit): Unit =
    Future {
      update()
      Thread.sleep(1000 / 25 * 2)
    }.flatMap(_ => Future(gameLoop(update)))

  private def handleDirection(direction: Direction, keyEvent: KeyEvent) =
    KeyCode(keyEvent.getCode) match
      case KeyCode.W | KeyCode.Up    if direction == Direction.South => direction
      case KeyCode.S | KeyCode.Down  if direction == Direction.North => direction
      case KeyCode.A | KeyCode.Left  if direction == Direction.East => direction
      case KeyCode.D | KeyCode.Right if direction == Direction.West => direction
      case KeyCode.W | KeyCode.Up    => Direction.North
      case KeyCode.S | KeyCode.Down  => Direction.South
      case KeyCode.A | KeyCode.Left  => Direction.West
      case KeyCode.D | KeyCode.Right => Direction.East
      case _                         => direction
end SnakeFx
