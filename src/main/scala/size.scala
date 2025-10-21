package edu.luc.cs.laufer.cs371.shapes

// TODO: implement this behavior

import Shape.*

object size:
    def countGroup(s: Shape): Int = s match
        case Rectangle(w, h) => 1
        case Ellipse(w, h) => 1
        case Location(x, y, c) => countGroup(c)
        case Group(shapes*) => 
            shapes.map { c => countGroup(c) } .sum

object shapeHeight:
    def treeHeight(s: Shape): Int = s match
        case Rectangle(w, h) => 1
        case Ellipse(w, h) => 1
        case Location(x, y, c) => 1 + treeHeight(c)
        case Group(shapes*) => 
            1 + shapes.map(treeHeight).sum

object scale:
    def scaleShape(s: Shape, scale: Int): Shape = s match
        case Rectangle(w, h) => Rectangle(w * scale, h * scale)
        case Ellipse(w,h) => Ellipse(w * scale, h * scale)
        case Location(x, y, shape) => Location(x * scale, y * scale, scaleShape(shape, scale))
        case Group(shapes*) => Group(shapes.map(child => scaleShape(shape, scale))*)