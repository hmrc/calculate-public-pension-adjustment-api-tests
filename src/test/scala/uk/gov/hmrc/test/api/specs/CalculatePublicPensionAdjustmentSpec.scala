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

package uk.gov.hmrc.test.api.specs

import play.api.libs.ws.StandaloneWSResponse
import uk.gov.hmrc.test.api.utils.JsonUtils.{convertJsonFormatASString, getResponseJsonFileAsString}

class CalculatePublicPensionAdjustmentSpec extends BaseSpec {
  val uri = "submit-public-pension-adjustment/calculation/submit"

  Feature("Calculate Public Pension Adjustment API functionality") {
    val requestArray: Array[String]  =
      Array("request10", "request11", "request12", "request14", "request16", "request17")
    val responseArray: Array[String] =
      Array("response10", "response11", "response12", "response14", "response16", "response17")
    requestArray.indices.foreach { index =>
      Scenario(s"Verify Calculate Public Pension Adjustment API works fine - Test data set $index") {
        Given("I got a valid url and test file name")
        val uri              = "show-calculation"
        val requestFileName  = requestArray(index)
        val responseFileName = responseArray(index)

        When("I use the calculate public pension adjustment API request to get a valid response")
        val response: StandaloneWSResponse =
          calculatePublicPensionAdjustmentHelper.calculatePostRequest(uri, requestFileName, "123")

        Then("I got the status code 200")
        response.status shouldBe 200

        When("I get the response body as a string")
        val jsonBody         = response.body.mkString
        val jsonResponseBody = convertJsonFormatASString(jsonBody)

        When("I get the test data response file as a string")
        val jsonResponseFile = convertJsonFormatASString(getResponseJsonFileAsString(responseFileName))

        println("£££££ : " + jsonResponseBody)
        println("££@@£££ : " + jsonResponseFile)

        Then("I verify the response exactly matching with the test data response file")
        assert(jsonResponseBody == jsonResponseFile, "Response is not as expected")

      }
    }
  }
}
