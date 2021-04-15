package com.example.liba

import scala.io.Source

object LibA {
  def run: Unit = {
    println("*** Reading pizza.txt Resource as Stream from lib-a ***")
    val content = Source.fromInputStream(getClass.getResourceAsStream("/pizza.txt")).getLines.mkString
    println(content)
  }
}
