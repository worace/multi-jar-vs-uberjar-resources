package com.example

import com.example.liba.LibA
import com.example.libb.LibB

object App {
  def main(args: Array[String]): Unit = {
    println("*** Running Example App.scala ***")
    LibA.run
    LibB.run
  }
}
