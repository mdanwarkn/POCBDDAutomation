@BOBTest @BOBQuarterlyPayoutTest
Feature: Checking Bank of Baroda application

Background:
Given The User opens the Bank of Baroda Application

@Scenario:FixedData
Scenario: Checking BOB Fixed Deposit Calculator -  Quarterly Payout with Fixed Data
Given User navigates to the "Fixed Deposit Calculator" screen
When The User selects the Fixed Deposit Type as "Quarterly Payout"
And The User enters the Amount of fixed deposit as "100000"
And The User enters the Tenure date as "5 Years: 0 Months : 0 Days"
Then Validate Rate of Interest is "6.5"
And Validate Maturity Date And Maturity Value
And Store the FD results in output file

@Scenario:VaryingAmount
@DataSetFromExcel
Scenario Outline: Checking BOB Fixed Deposit Calculator -  Quarterly Payout with varying amount
Given User navigates to the "Fixed Deposit Calculator" screen
When The User selects the Fixed Deposit Type as "Quarterly Payout"
Then Configure the data set to test from Excel "<Identifier>"
And The User enters the Amount of fixed deposit as "<Amount>"
And The User enters the Tenure date as "5 Years: 0 Months : 0 Days"
Then Validate Rate of Interest is "6.5"
And Validate Maturity Date And Maturity Value
And Store the FD results in output file
Examples:
|Identifier|
|AmountLessThan5000|
|AmountGreaterThan5000|

@Scenario:VaryingMultipleInputs
@DataSetFromExcel
Scenario Outline: Checking BOB Fixed Deposit Calculator -  Quarterly Payout with varying Inputs
Given User navigates to the "Fixed Deposit Calculator" screen
When The User selects the Fixed Deposit Type as "Quarterly Payout"
Then Configure the data set to test from Excel "<Identifier>"
And The User enters the Amount of fixed deposit as "<Amount>"
And The User enters the Tenure date as "<Tenure Date>"
Then Validate Rate of Interest is "<Tenure Interest>"
And Validate Maturity Date And Maturity Value
And Store the FD results in output file
Examples:
|Identifier|
|ShortTenureAndLessFund|
|LongTenureAndMoreFund|