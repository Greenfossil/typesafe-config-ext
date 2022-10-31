/*
 * Copyright 2022 Greenfossil Pte Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.typesafe.config

class ConfigSuite extends munit.FunSuite {

  test("load with config using load()"){
    val config = ConfigFactory.load()
    println(s"config.root().render() = ${config.root().render()}")
    val appName = config.getString("app.name")
    val appRef = config.getString("app.ver")
    println(s"appName = ${appName}")
    println(s"appRef = ${appRef}")
    assertNoDiff(appName, "app-name")
    assertNoDiff(appRef, "ref-ver")
  }

  test("load with config load(basename)"){
    val config = ConfigFactory.load("application")
    println(s"config.root().render() = ${config.root().render()}")
    val appName = config.getString("app.name")
    val appRef = config.getString("app.ver")
    println(s"appName = ${appName}")
    println(s"appRef = ${appRef}")
    assertNoDiff(appName, "app-name")
    assertNoDiff(appRef, "ref-ver")
  }

  test("load with config load(basename-1) "){
    val config = ConfigFactory.load("application.conf")
    println(s"config.root().render() = ${config.root().render()}")
    val appName = config.getString("app.name")
    val appRef = config.getString("app.ver")
    println(s"appName = ${appName}")
    println(s"appRef = ${appRef}")
    assertNoDiff(appName, "app-name")
    assertNoDiff(appRef, "ref-ver")
  }

}
