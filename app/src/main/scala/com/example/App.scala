package com.example

import com.example.liba.LibA
import com.example.libb.LibB
import collection.JavaConverters._
import java.io.File

object App {
  def main(args: Array[String]): Unit = {
    println("*** Running Example App.scala ***")
    LibA.run
    LibB.run

    // println("*** Listing Available pizza.txt Resource from App ***")
    // println(getClass.getClassLoader.getResources("pizza.txt").asScala.toList)
    // // jar:file:/tmp/sbt_f7d4c51e/target/513a0b45/282d5e04/lib-a_2.12.jar!/pizza.txt

    // getClass.getClassLoader.getResources("pizza.txt").asScala.toList.foreach { url =>
    //   // java.lang.IllegalArgumentException: URI is not hierarchical
    //   println(new File(url.toURI))
    // }
  }
}
