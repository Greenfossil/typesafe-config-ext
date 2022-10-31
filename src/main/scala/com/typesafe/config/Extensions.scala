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

import java.time.temporal.TemporalAmount
import java.time.{Duration, Period}
import java.util
import scala.jdk.CollectionConverters.*
import scala.util.Try

case class ConfigData(key: String, value: String, origin: ConfigOrigin) {
  override def toString: String = s"$key : [$value], origin [$origin]"
}

object DefaultConfig {

  def apply(): Config =
    apply(getClass.getClassLoader)

  def apply(resourceBasename: String): Config =
    apply(getClass.getClassLoader, resourceBasename)

  def apply(cl: ClassLoader): Config =
    ConfigFactory.load(cl)

  def apply(cl: ClassLoader, resourceBasename: String): Config =
    ConfigFactory.load(cl, resourceBasename)

  def parseString(string: String): Config =
    parseString(string, ConfigParseOptions.defaults().setClassLoader(getClass.getClassLoader))

  def parseString(string: String, parseOptions:ConfigParseOptions): Config =
    ConfigFactory.parseString(string, parseOptions)

  def parseMap(values: Map[String, Any]): Config =
    ConfigFactory.parseMap(values.asJava)

  def parseMap(values: Map[String, Any], originDescription: String): Config =
    ConfigFactory.parseMap(values.asJava, originDescription)

  def empty(): Config = ConfigFactory.empty()

  /**
   * Get config data from config value of path
   * @param configValue Config Value
   * @param path Config Path
   * @return Seq[ConfigData]
   */
  def getConfigData(configValue: ConfigValue, path: String): List[ConfigData] =
    configValue match {
      case nullObj if nullObj.valueType() == ConfigValueType.NULL =>
        List(ConfigData(path, null, nullObj.origin()))
      case configObj: ConfigObject =>
        import scala.jdk.CollectionConverters.*
        configObj.asScala.collect{
          case (key: String, value: ConfigValue) =>
            val newPath = path + "." + key
            getConfigData(value, newPath)
        }.toList.flatten

      case l: ConfigList =>
        List(ConfigData(path, l.unwrapped().toArray.mkString("[", ", ", "]"), l.origin()))
      case _ =>
        List(ConfigData(path, configValue.unwrapped().toString, configValue.origin()))
    }

  /**
   * Get config data from path
   * @param path Config Path
   * @return Try[ Seq[ConfigData] ]
   */
  def getConfigData(path: String): Try[List[ConfigData]] =
    Try(getConfigData(apply().getValue(path), path))

}

extension (config: Config)
  def getStringOpt(path: String): Option[String] =
    if config.getIsNull(path) then None else Option(config.getString(path))

  def getBooleanOpt(path: String): Option[Boolean] =
    if config.getIsNull(path) then None else Option(config.getBoolean(path))

  def getIntOpt(path: String): Option[Int] =
    if config.getIsNull(path) then None else Option(config.getInt(path))

  def getLongOpt(path: String): Option[Long] =
    if config.getIsNull(path) then None else Option(config.getLong(path))

  def getDoubleOpt(path: String): Option[Double] =
    if config.getIsNull(path) then None else Option(config.getDouble(path))

  def getNumberOpt(path: String): Option[Number] =
    if config.getIsNull(path) then None else Option(config.getNumber(path))

  def getDurationOpt(path: String): Option[Duration] =
    if config.getIsNull(path) then None else Option(config.getDuration(path))

  def getPeriodOpt(path: String): Option[Period] =
    if config.getIsNull(path) then None else Option(config.getPeriod(path))

  def getTemporalOption(path: String): Option[TemporalAmount] =
    if config.getIsNull(path) then None else Option(config.getTemporal(path))

  def getEnumOpt[T <: Enum[T]](clazz: Class[T], path: String): Option[T] =
    if config.getIsNull(path) then None else Option(config.getEnum(clazz, path))

  def getBooleanListOpt(path: String): Option[List[Boolean]] =
    if config.getIsNull(path)
    then None
    else
      Some(config.getBooleanList(path)
        .asScala
        .toList
        .map(x => Boolean.unbox(x)))

  def getIntListOpt(path: String): Option[List[Int]] =
    if config.getIsNull(path)
    then None
    else
      Some(config.getIntList(path)
        .asScala
        .toList
        .map(x => Int.unbox(x)))

  def getLongListOpt(path: String): Option[List[Long]] =
    if config.getIsNull(path)
    then None
    else
      Some(config.getLongList(path)
        .asScala
        .toList
        .map(x => Long.unbox(x)))

  def getDoubleList(path: String): Option[List[Double]] =
    if config.getIsNull(path)
    then None
    else
      Some(config.getDoubleList(path)
        .asScala
        .toList
        .map(x => Double.unbox(x)))

  def getNumberList(path: String): Option[List[Number]] =
    if config.getIsNull(path)
    then None
    else
      Some(config.getNumberList(path)
        .asScala
        .toList)

  def getStringListOpt(path: String): Option[List[String]] =
    if config.getIsNull(path)
    then None
    else
      Some(config.getStringList(path)
        .asScala
        .toList)

  def getDurationListOpt(path: String): Option[List[Duration]] =
    if config.getIsNull(path)
    then None
    else
      Some(config.getDurationList(path)
        .asScala
        .toList)

  def getEnumListOpt[T <: Enum[T]](clazz: Class[T], path: String): Option[List[T]] =
    if config.getIsNull(path)
    then None
    else
      Some(config.getEnumList(clazz, path)
        .asScala
        .toList)

  def getConfigListOpt(path: String): Option[List[Config]] =
    if config.getIsNull(path)
    then None
    else
      Some(config.getConfigList(path)
        .asScala
        .toList)

  def getAnyRefListOpt(path: String): Option[List[AnyRef]] =
    if config.getIsNull(path)
    then None
    else
      Some(config.getAnyRefList(path)
        .asScala
        .toList)