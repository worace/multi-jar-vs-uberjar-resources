package com.example.liba

import scala.io.Source

object LibA {
  def run: Unit = {
    println("*** Reading pizza.txt Resource as Stream from lib-a ***")
    println(s"Resource URL: ${getClass.getResource("/pizza.txt")}")
    val content = Source.fromInputStream(getClass.getResourceAsStream("/pizza.txt")).getLines.mkString
    println(content)
  }
}
