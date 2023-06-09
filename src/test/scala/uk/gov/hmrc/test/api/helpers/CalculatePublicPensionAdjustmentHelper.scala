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

import play.api.libs.ws.StandaloneWSRequest
import uk.gov.hmrc.test.api.service.CalculatePublicPensionAdjustmentService
import uk.gov.hmrc.test.api.utils.JsonUtils.getRequestJsonFileAsString

class CalculatePublicPensionAdjustmentHelper {

  val calculatePublicPensionAdjustmentService: CalculatePublicPensionAdjustmentService =
    new CalculatePublicPensionAdjustmentService
  def calculatePostRequest(
    uri: String,
    jsonFileName: String,
    token: String
  ) = {
    val json                                                           = getRequestJsonFileAsString(jsonFileName)
    val individualsMatchGetResponse: StandaloneWSRequest#Self#Response =
      calculatePublicPensionAdjustmentService.calculatePostRequest(uri, json, token)
    individualsMatchGetResponse
  }

}
