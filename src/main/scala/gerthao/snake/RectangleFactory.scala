package gerthao.snake

import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle

object RectangleFactory:
  def create(xCoord: Int, yCoord: Int, color: Color): Rectangle = new Rectangle {
    x = xCoord
    y = yCoord
    width = UnitWidth
    height = UnitWidth
    fill = color
  }
end RectangleFactory
