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
    val period_1: Period = config.getPeriod("app.interval.period.1")
    val period_2 = config.getPeriod("app.interval.period.2")
    assertEquals(period_1, period_2)
    assertEquals(period_1, Period.ofMonths(1))
    assertEquals(period_2, Period.ofMonths(1))

    //Temporal
    val temp_1 = config.getTemporal("app.interval.temporal.1")
    val temp_2 = config.getTemporal("app.interval.temporal.2")
    assertNotEquals(temp_1, temp_2)
    assertEquals(temp_1, Duration.ofMinutes(1))
    assertEquals(temp_2, Period.ofMonths(1))

  }

}
