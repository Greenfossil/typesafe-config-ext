package com.typesafe.config

class SystemPropertiesSuite extends munit.FunSuite {

  test("SystemProperties"){
    val config = ConfigFactory.load()
    assertNoDiff(System.getProperty("user.name"), config.getString("user.name"))
    assertEquals(System.getProperty("http.nonProxyHosts") == null, !config.hasPath("http.nonProxyHosts"))
    assertEquals(System.getProperty("http.nonProxyHosts") != null, config.hasPath("http.nonProxyHosts"))
  }

}
