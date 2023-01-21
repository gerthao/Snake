package gerthao.snake

import scala.util.Random

case class State(snake: Snake, boundary: Boundary, food: Food):
  def newState(direction: Direction): State =
    val nc = newCoordinate(direction, snake.head)
    if isCollisionDetected(snake, boundary, food) then
      State.initialState()
    else if food == nc then
      State.withNewFood(snake.eat(food), boundary)
    else
      State(snake.moveTo(nc), boundary, food)

  def isOutsideBoundary(snake: Snake, boundary: Boundary): Boolean =
    val (x, y) = snake.head
    x < 0 || x >= boundary.xLength || y < 0 || y >= boundary.yLength

  def isCollisionDetected(snake: Snake, boundary: Boundary, coordinate: Coordinate): Boolean =
    isOutsideBoundary(snake, boundary) || snake.tail.contains(coordinate)

  def newCoordinate(direction: Direction, coordinate: Coordinate): Coordinate =
    val (x, y) = coordinate
    direction match
      case Direction.North => (x, y - UnitWidth)
      case Direction.South => (x, y + UnitWidth)
      case Direction.East  => (x + UnitWidth, y)
      case Direction.West  => (x - UnitWidth, y)
      case Direction.None  => (x, y)

object State {
  private val initialSnake = Snake(List((200, 200), (225, 200), (250, 200)))
  private val boundary     = Boundary(600, 600)

  def nextFood: Food                                       = ((Random.nextInt(24) + 1) * UnitWidth, (Random.nextInt(24) + 1) * UnitWidth)
  def initialState(): State                                = State(initialSnake, boundary, nextFood)
  def withNewFood(snake: Snake, boundary: Boundary): State = State(snake, boundary, nextFood)
}