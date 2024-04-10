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

package uk.gov.hmrc.test.api.service

import play.api.libs.ws.{StandaloneWSRequest, StandaloneWSResponse}
import uk.gov.hmrc.test.api.client.HttpClient
import uk.gov.hmrc.test.api.conf.TestConfiguration

import scala.concurrent.Await
import scala.concurrent.duration._

class CalculatePublicPensionAdjustmentService extends HttpClient {
  val host: String                                = TestConfiguration.url("cppa")
  val ppaHost: String                                = TestConfiguration.url("ppa")
  val calculatePublicPensionAdjustmentURL: String = s"$host/calculate-public-pension-adjustment/"
  val publicPensionAdjustmentURL: String = s"$ppaHost/public-pension-adjustment"

  def calculatePostRequest(
    uri: String,
    individual: String,
    token: String
  ): StandaloneWSRequest#Self#Response =
    Await.result(
      post(
        calculatePublicPensionAdjustmentURL + uri,
        individual,
        ("Content-Type", "application/json"),
        ("Authorization", token),
        ("CorrelationId", "12345678"),
        ("Accept", "application/vnd.hmrc.P1.0+json")
      ),
      10.seconds
    )

  def calculatePostRequestWithFormData(
                            uri: String,
                            individual: Map[String, String],
                            token: String
                          ): StandaloneWSRequest#Self#Response =
    Await.result(
      postWithFormData(
        publicPensionAdjustmentURL + uri,
        individual,
        ("Content-Type", "application/json"),
        ("Authorization", token),
        ("CorrelationId", "12345678"),
        ("Accept", "application/vnd.hmrc.P1.0+json")
      ),
      10.seconds
    )

  def calculateGetRequest(authToken: String, url: String): StandaloneWSRequest#Self#Response =
    Await.result(
      get(
        publicPensionAdjustmentURL + url,
        ("Authorization", "123"),
        ("CorrelationId", "12345678"),
        ("Accept", "application/vnd.hmrc.P1.0+json")
      ),
      10.seconds
    )

}
