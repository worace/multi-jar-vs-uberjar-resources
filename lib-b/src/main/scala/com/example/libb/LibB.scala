package com.example.libb

import scala.io.Source

object LibB {
  def run: Unit = {
    println("*** Reading pizza.txt as Stream from lib-b ***")
    val content = Source.fromInputStream(getClass.getResourceAsStream("/pizza.txt")).getLines.mkString
    println(content)
  }
}
