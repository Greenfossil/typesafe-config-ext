# typesafe-config-ext

![GitHub Workflow Status](https://img.shields.io/github/actions/workflow/status/Greenfossil/typesafe-config-ext/run-tests.yml?branch=master)
![](https://img.shields.io/github/license/Greenfossil/typesafe-config-ext)
![](https://img.shields.io/github/v/tag/Greenfossil/typesafe-config-ext)
![Maven Central](https://img.shields.io/maven-central/v/com.greenfossil/typesafe-config-ext_3)
[![javadoc](https://javadoc.io/badge2/com.greenfossil/typesafe-config-ext_3/javadoc.svg)](https://javadoc.io/doc/com.greenfossil/typesafe-config-ext_3)

This is the official Greenfossil Scala library for Typesafe-config-ext.

## How to Build

This library uses sbt as its build tool. It requires at least Java 17 or later to build.

Follow the official guide on how to install [sbt](https://www.scala-sbt.org/download.html).

## Getting Started

### Configuration File

Create the application.conf file under:

```scala
src/main/resources/application.conf
```

Default configurations can be found in the reference.conf files of the corresponding library.

Configuration keys found in application.conf will override configurations in the reference.conf files.

## Getting the config values

```scala
import com.typesafe.config.DefaultConfig

object Config:
  def checkEnv = 
    val env = DefaultConfig().getString("app.env")
    println(s"Current environment is set to: [$env]")
```

## License

typesafe-config-ext is licensed under the Apache license version 2.
See [LICENSE](LICENSE.txt).
