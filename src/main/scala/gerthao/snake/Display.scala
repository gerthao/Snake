package gerthao.snake

import scalafx.scene.Node
import scalafx.scene.paint.Color.*
import scalafx.scene.shape.{Rectangle, Shape}

trait Display:
  def get: List[Node]
end Display

case class SnakeDisplay(snake: Snake) extends Display:
  def get: List[Node] = snake.map((cx, cy) => RectangleFactory.create(cx, cy, Blue))
end SnakeDisplay

case class FoodDisplay(food: Food) extends Display:
  def get: List[Node] =
    val (cx, cy) = food
    RectangleFactory.create(cx, cy, Red) :: Nil
end FoodDisplay

case class StateDisplay(state: State) extends Display:
  def get: List[Node] = List(
    SnakeDisplay(state.snake),
    FoodDisplay(state.food)
  ).flatMap(_.get)
end StateDisplay
