package com.typesafe.config

class SystemPropertiesSuite extends munit.FunSuite {

  test("SystemProperties"){
    val config = ConfigFactory.load()
//    println(s"config.root().render() = ${config.root().render()}")
    println(s"""System property = [${System.getProperty("user.home")}]""")
    println(s"""System http.nonProxyHosts = [${System.getProperty("http.nonProxyHosts")}]""")
    println(s"""Config user.home = [${config.getString("user.home")}]""")
    println(s"""Config http.nonProxyHosts = [${config.hasPath("http.nonProxyHosts")}]""")
    if config.hasPath("http.nonProxyHosts") then
      println(s"""config.path() = ${config.getString("http.nonProxyHosts")}""")
  }

}
