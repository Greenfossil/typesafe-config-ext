package com.typesafe.config

import java.util
import scala.util.Try

class DefaultConfigSuite extends munit.FunSuite {

  val config = DefaultConfig()
  test("getStringOpt with declared path and non null"){
    val appName = config.getString("app.name")
    assertNoDiff(appName, "app-name")

    val appNameOpt = config.getStringOpt("app.name")
    assertEquals(appNameOpt, Option(appName))
  }

  test("getStringOpt with declared path and null value"){
    Try(config.getString("app.value.string.null"))
      .fold(
        ex => assert(ex.getMessage.contains("Configuration key 'app.value.string.null' is set to null but expected STRING")),
        value => fail("Should not have value")
      )

    val appValueNull = config.getStringOpt("app.value.string.null")
    assertEquals(appValueNull, None)
  }

  test("getStringOpt with declared path and empty value") {
    val appValueEmpty = config.getString("app.value.string.empty")
    assertNoDiff(appValueEmpty, "")

    val appValueEmptyOpt = config.getStringOpt("app.value.string.empty")
    assertEquals(appValueEmptyOpt, Some(""))
  }

  test("getIntOpt with declared path and null value"){
    Try(config.getInt("app.value.int.null"))
      .fold(
        ex => assert(ex.getMessage.contains("Configuration key 'app.value.int.null' is set to null but expected NUMBER")),
        value => fail("Should not have value")
      )

    val appIntNull = config.getIntOpt("app.value.int.null")
    assertEquals(appIntNull, None)
  }

  test("getIntOpt with declared path and value 1") {
    val appInt1 = config.getInt("app.value.int.1")
    assertEquals(appInt1, 1)

    val appInt1Opt = config.getIntOpt("app.value.int.1")
    assertEquals(appInt1Opt, Some(1))
  }

  test("getIntListOpt with declared path and empty") {
    assertEquals(config.getIntList("app.list.int.empty"), java.util.List.of())
    val appIntNull = config.getIntListOpt("app.list.int.empty")
    assertEquals(appIntNull, None)
  }

  test("getIntListOpt with declared path and values ") {
    val juIntList = config.getIntList("app.list.int.values")
    assertEquals(juIntList, java.util.List.of[Integer](1,2,3))

    val intListOpt = config.getIntListOpt("app.list.int.values")
    assertEquals(intListOpt, Some(List(1,2,3)))
  }

  test("getIntListOpt without declared path and values ") {
    Try(config.getIntList("app.list.int.missing"))
      .fold(
        ex => assert(ex.getMessage.contains("No configuration setting found for key 'app.list.int.missing'")),
        juIntList => fail("Should not succeed")
      )

    val intListOpt = config.getIntListOpt("app.list.int.missing")
    assertEquals(intListOpt, None)
  }


}
