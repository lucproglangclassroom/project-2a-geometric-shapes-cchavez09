package edu.luc.cs.laufer.cs371.shapes

// TODO: implement this behavior

import Shape.*

object boundingBox:
  def apply(s: Shape): Location = s match
    case Rectangle(w,h) => Location(0, 0, Rectangle(w, h)) // not yet implemented
    case Ellipse(w, h) => Location(-w, -h, Rectangle(2 * w, 2 * h))
      
    case Location(x, y, shape) => 
      val Location(a, b, Rectangle(w, h)) = apply(shape)
      Location(x + a, y + b, Rectangle(w,h))

    case Group(shapes*) =>

      val bbs = shapes.map { s => apply(s) }
      val xMins = bbs.map { case Location(x, y, Rectangle(w, h)) => x}.min
      val yMins = bbs.map { case Location(x, y, Rectangle(w, h)) => y}.min
      val xMaxs = bbs.map { case Location(x, y, Rectangle(w, h)) => x + w}.max
      val yMaxs = bbs.map { case Location(x, y, Rectangle(w, h)) => y + h}.max

      val totalWidth = xMaxs - xMins
      val totalHeight = yMaxs - yMins

      Location(xMins, yMins, Rectangle(totalWidth, totalHeight))
end boundingBox


