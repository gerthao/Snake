package gerthao.snake

import scalafx.application.JFXApp3
import scalafx.scene.Scene
import scalafx.scene.paint.Color
import scalafx.scene.paint.Color.*
import scalafx.scene.shape.Rectangle

import scala.util.Random

val UnitWidth: Int = 25

case class Snake (private val coordinates: List[Coordinate]):
  def head: Coordinate                      = coordinates.head
  def tail: List[Coordinate]                = coordinates.tail
  def moveTo(coordinate: Coordinate): Snake = Snake(coordinate :: coordinates.init)
  def eat(food: Food): Snake                = Snake(food :: coordinates)
  def map[B](f: Coordinate => B): List[B]   = coordinates.map(f)
end Snake

