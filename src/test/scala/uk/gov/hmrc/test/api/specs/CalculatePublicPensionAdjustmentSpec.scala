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
import uk.gov.hmrc.test.api.models.{Income, TaxYear, TaxYearScheme}
import uk.gov.hmrc.test.api.utils.ApiLogger

class CalculatePublicPensionAdjustmentSpec extends BaseSpec {
  val uri = "submit-public-pension-adjustment/calculation/submit"

  Feature("Calculate Public Pension Adjustment API functionality") {
    Scenario("Verify Calculate Public Pension Adjustment API works fine by using direct json") {

      Given("I got a valid url and test file name")
      val uri      = "show-calculation"
      val fileName = "valid"

      When("I use the calculate public pension adjustment API request to get a valid response")
      val response: StandaloneWSResponse =
        calculatePublicPensionAdjustmentHelper.calculatePostRequest(uri, fileName, "123")

      ApiLogger.log.info("Status : " + response.status)
      ApiLogger.log.info("Body : " + response.body)
      Then("I got the status code 200")
      response.status shouldBe 200
    }
  }
}
