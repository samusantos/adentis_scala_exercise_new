package com.samuel
package database.postgres

import java.sql.{Connection, DriverManager, ResultSet}

class PostgresOrderFetcher() {

  private val connectionString = "jdbc:postgresql://localhost:5432/product_sales"
  private val connection: Connection = DriverManager.getConnection(connectionString, "postgres", "root");

  def fetchBefore(date: String): String = {
    var count: String = null
    val stm = connection.createStatement(ResultSet.FETCH_FORWARD, ResultSet.CONCUR_READ_ONLY)
    val resultSet = stm.executeQuery(s"SELECT COUNT(*) FROM orders WHERE order_date >= '${date}'")

    if resultSet.next() then
      count = resultSet.getString("count")

    count
  }

  def fetchCountBetween(lowerBound: String, upperBound: String): String = {

    var count: String = null
    val stm = connection.createStatement(ResultSet.FETCH_FORWARD, ResultSet.CONCUR_READ_ONLY)
    val resultSet = stm.executeQuery(s"SELECT COUNT(*) FROM orders WHERE '${lowerBound}' <= order_date AND order_date < '${upperBound}'")

    if resultSet.next() then
      count = resultSet.getString("count")

    count
  }

  def close(): Unit = {
    connection.close()
  }
}
