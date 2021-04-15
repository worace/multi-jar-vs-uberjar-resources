package com.example.libb

import scala.io.Source

object LibB {
  def run: Unit = {
    println("*** Reading pizza.txt as Stream from lib-b ***")
    println(s"Resource URL: ${getClass.getResource("/pizza.txt")}")
    val content = Source.fromInputStream(getClass.getResourceAsStream("/pizza.txt")).getLines.mkString
    println(content)
  }
}
