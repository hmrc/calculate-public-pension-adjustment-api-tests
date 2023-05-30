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

package uk.gov.hmrc.test.api.models

import play.api.libs.json.{Json, OFormat}

case class Income(incomeAboveThreshold: Boolean, adjustedIncome: Int)
case class TaxYearScheme(
  name: String,
  pstr: String,
  oPensionInputAmount: Int,
  rPensionInputAmount: Int,
  chargePaidByScheme: Int
)
case class TaxYear(
  period: String,
  totalIncome: Option[Int],
  pensionInputAmount: Option[Int],
  definedBenefitInputAmount: Option[Int],
  definedContributionInputAmount: Option[Int],
  flexiAccessDate: Option[String],
  preAccessDefinedContributionInputAmount: Option[Int],
  postAccessDefinedContributionInputAmount: Option[Int],
  chargePaidByMember: Int,
  income: Option[Income],
  taxYearSchemes: Seq[TaxYearScheme]
)
case class TaxYears(scottishTaxYears: Seq[String], taxYears: Seq[TaxYear])
object TaxYearsRequestDTO {
  implicit val taxYearPayload: OFormat[TaxYear]             =
    Json.format[TaxYear]
  implicit val incomePayload: OFormat[Income]               =
    Json.format[Income]
  implicit val taxYearSchemePayload: OFormat[TaxYearScheme] =
    Json.format[TaxYearScheme]
  implicit val taxYearsPayload: OFormat[TaxYears]           =
    Json.format[TaxYears]
}
