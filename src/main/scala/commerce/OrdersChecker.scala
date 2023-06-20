import com.samuel.commerce.DateParser
import com.samuel.database.postgres.PostgresOrderFetcher

private var intervalsToUse = Array("1-3", "4-6", "7-12")
private val dateParser = new DateParser()
private val fetcher = new PostgresOrderFetcher()

@main def main(dateOne: String, dateTwo: String, intervals: String*) = {

  dateParser.isDateValid(dateOne, dateTwo)

  defineIntervals(intervals: _*)
  println(showOrders())
  fetcher.close()

}

def defineIntervals(intervals: String*): Unit = {
  if intervals.nonEmpty then
    intervalsToUse = intervals.toArray
}

def showOrders(): String = {

  var ordersCount: String = ""

  intervalsToUse.foreach(elem => {
    val interval = elem.split("-")
    val count = fetcher.fetchCountBetween(dateParser.buildLowerBound(interval(0)), dateParser.buildUpperBound(interval(1)))
    ordersCount = ordersCount.concat(s"${elem} months: ${count} orders\n")
  })

  val lastCount = fetcher.fetchBefore(dateParser.buildLowerBound(intervalsToUse.last.split("-")(1)))
  ordersCount = ordersCount.concat(s">${intervalsToUse.last.split("-")(1)} months: ${lastCount} orders")
  ordersCount
}
