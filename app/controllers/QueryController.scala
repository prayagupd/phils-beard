package controllers

import java.util.UUID

import play.api.libs.json.{JsArray, Json}
import play.api.mvc._

class QueryController extends Controller {


  def index = Action {
    Ok(views.html.react())
  }

  // https://fb.me/react-warning-keys.
  val RESULTS = "results"
  val queryKey = "document"
  val resultKey = "result"
  val docIdKey = "id"


  var resultsJson: JsArray = Json.arr(
    Json.obj(docIdKey -> UUID.randomUUID().toString, queryKey -> "db.Events.find({})", resultKey -> "{}"),
    Json.obj(docIdKey -> UUID.randomUUID().toString, queryKey -> "db.Events.find({})", resultKey -> "{}")
  )


  def queries = Action {
    Ok(resultsJson)
  }


  def query(document: String, text: String) = Action {

    val newResult = Json.obj(
      docIdKey -> UUID.randomUUID().toString,
      queryKey -> document,
      resultKey -> text)

    resultsJson = resultsJson :+ newResult
    Ok(newResult)
  }

}
