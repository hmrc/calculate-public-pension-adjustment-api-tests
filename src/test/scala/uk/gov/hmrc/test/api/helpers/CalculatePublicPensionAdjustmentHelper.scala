/*
 * Copyright 2023 HM Revenue & Customs
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

package uk.gov.hmrc.test.api.helpers

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import play.api.libs.ws.{StandaloneWSRequest, StandaloneWSResponse}
import uk.gov.hmrc.test.api.service.CalculatePublicPensionAdjustmentService
import uk.gov.hmrc.test.api.utils.JsonUtils.getRequestJsonFileAsString

import scala.util.matching.Regex

class CalculatePublicPensionAdjustmentHelper {

  val calculatePublicPensionAdjustmentService: CalculatePublicPensionAdjustmentService =
    new CalculatePublicPensionAdjustmentService
  def post(
    uri: String,
    jsonFileName: String,
    token: String
  ) = {
    val json                                                           = getRequestJsonFileAsString(jsonFileName)
    val individualsMatchGetResponse: StandaloneWSRequest#Self#Response =
      calculatePublicPensionAdjustmentService.calculatePostRequest(uri, json, token)
    individualsMatchGetResponse
  }

  def post(
    uri: String,
    params: Map[String, String],
    token: String
  ) = {
    val individualsMatchGetResponse: StandaloneWSRequest#Self#Response =
      calculatePublicPensionAdjustmentService.calculatePostRequestWithFormData(uri, params, token)
    individualsMatchGetResponse
  }

  def get(authBearerToken: String, url: String) = {
    val individualsMatchGetResponse: StandaloneWSResponse =
      calculatePublicPensionAdjustmentService.calculateGetRequest(authBearerToken, url)
    individualsMatchGetResponse
    // (Json.parse(individualsMatchGetResponse.body) \ "individual").as[User]
  }

  def getCSRFToken(authBearerToken: String, url: String): String = {
    val htmlBody                       = get(authBearerToken, url).body
    val document: Document = Jsoup.parse(htmlBody)
    println("Html body"+document)
    val extractedValue: Option[String] = extractValueFromHTML(htmlBody)
    val result: String                 = extractedValue.getOrElse("Default Value")
    result
  }

  def extractValueFromHTML(htmlContent: String): Option[String] = {
    val pattern: Regex = """<input type="hidden" name="csrfToken"\s+value="([^"]+)""".r
    pattern.findFirstMatchIn(htmlContent) match {
      case Some(matched) => Some(matched.group(1))
      case None          => None
    }
  }

}
