package com.typesafe.config

import java.time.temporal.{ChronoUnit, Temporal}
import java.time.{Duration, Instant, LocalDate, LocalDateTime, Period}

class TimeSuite extends munit.FunSuite {

  test("load with config using load()") {
    val config = ConfigFactory.load()

    //Duration
    val dur_1: Duration = config.getDuration("app.interval.dur.1")
    assertEquals(dur_1, Duration.ofSeconds(1))
    assertNoDiff(dur_1.toString, "PT1S")

    val dur_2: Duration = config.getDuration("app.interval.dur.2")
    assertEquals(dur_2, Duration.ofMinutes(1))
    assertNoDiff(dur_2.toString, "PT1M")

    //Period
    val period_1 = config.getPeriod("app.interval.period.1")
    val period_2 = config.getPeriod("app.interval.period.2")
    assertEquals(period_1, period_2)
    println(s"period_2.getDays = ${period_2.getDays}")
    val x: Temporal = period_2.addTo(LocalDateTime.now)
    println(s"x = ${x}")

    //Temporal
    val temp_1 = config.getTemporal("app.interval.temporal.1")
    val temp_2 = config.getTemporal("app.interval.temporal.2")
    println(s"temp_1 = ${temp_1}")
    println(s"temp_2 = ${temp_2}")
    assert(temp_1 != temp_2)
    println("temp_1 = " + temp_2.get(ChronoUnit.DAYS))
    println(s"temp_1.getUnits = ${temp_1.getUnits}")
    println(s"temp_1.getUnits = ${temp_1.get(ChronoUnit.SECONDS)}")
    println(s"temp_1.getUnits = ${temp_1.get(ChronoUnit.NANOS)}")
    println(s"temp_2.getUnits = ${temp_2.getUnits}")
    println(s"temp_2.getUnits = ${temp_2.get(ChronoUnit.MONTHS)}")
    println(s"temp_2.getUnits = ${temp_2.get(ChronoUnit.DAYS)}")

  }

}
