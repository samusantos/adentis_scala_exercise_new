package com.samuel
package commerce

import java.text.{DateFormat, SimpleDateFormat}
import java.time.{LocalDate, LocalDateTime}
import java.time.format.DateTimeFormatter

class DateParser (){
  private val dateFormat = "yyyy-MM-dd kk:mm:ss"
  private val postgresFormat = "yyyy-MM-dd"
  private var dateOne: LocalDateTime = _
  private var dateTwo: LocalDateTime = _

  def isDateValid(date: String*): Boolean = {
    dateOne = LocalDateTime.parse(date(0), DateTimeFormatter.ofPattern(dateFormat))
    dateTwo = LocalDateTime.parse(date(1), DateTimeFormatter.ofPattern(dateFormat))

    if dateOne.isAfter(dateTwo) then
      false
    else true
  }

  def buildLowerBound(month: String): String = {
    LocalDate.parse(formatDate(dateOne), DateTimeFormatter.ofPattern(postgresFormat)).withMonth(month.toInt).toString
  }

  def buildUpperBound(month: String): String = {
    LocalDate.parse(formatDate(dateOne), DateTimeFormatter.ofPattern(postgresFormat)).plusMonths(month.toInt).toString
  }

  private def formatDate(date: LocalDateTime): String = {
    date.format(DateTimeFormatter.ofPattern(postgresFormat))
  }
}
