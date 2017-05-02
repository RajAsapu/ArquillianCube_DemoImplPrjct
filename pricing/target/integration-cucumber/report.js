$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("features/pageobjects/Page_Index_CreatePage.feature");
formatter.feature({
  "line": 2,
  "name": "To verify if the user is able to access create page under index.",
  "description": "\nAcceptance Criteria: User shall be able to access features in the Create page under index.",
  "id": "to-verify-if-the-user-is-able-to-access-create-page-under-index.",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@PageObjects"
    }
  ]
});
formatter.scenarioOutline({
  "line": 10,
  "name": "To verify if the user is able to create an index.",
  "description": "",
  "id": "to-verify-if-the-user-is-able-to-access-create-page-under-index.;to-verify-if-the-user-is-able-to-create-an-index.",
  "type": "scenario_outline",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 11,
  "name": "the user has navigated to the \"Create\" page under the \"Index\"",
  "keyword": "Given "
});
formatter.step({
  "line": 12,
  "name": "the user enters rate basis as Price Point Scale",
  "keyword": "When "
});
formatter.step({
  "line": 13,
  "name": "\u003clowPrice\u003e ,\u003cmidPrice\u003e ,\u003chighPrice\u003e and \u003cclosePrice\u003e are entered",
  "keyword": "And "
});
formatter.step({
  "line": 14,
  "name": "name as NY RBOB Prem Brg",
  "keyword": "And "
});
formatter.step({
  "line": 15,
  "name": "start date as 2016-12-10 and end date as 2017-03-10",
  "keyword": "And "
});
formatter.step({
  "line": 16,
  "name": "currency as USD",
  "keyword": "And "
});
formatter.step({
  "line": 17,
  "name": "unit of measurement as USG",
  "keyword": "And "
});
formatter.step({
  "line": 18,
  "name": "comment as Created for new clients",
  "keyword": "And "
});
formatter.step({
  "line": 19,
  "name": "clicks on the submit button",
  "keyword": "And "
});
formatter.step({
  "line": 20,
  "name": "the user shall be able to view the created index in the list on filtering with Price Point Scale",
  "keyword": "Then "
});
formatter.examples({
  "line": 22,
  "name": "",
  "description": "",
  "id": "to-verify-if-the-user-is-able-to-access-create-page-under-index.;to-verify-if-the-user-is-able-to-create-an-index.;",
  "rows": [
    {
      "cells": [
        "lowPrice",
        "midPrice",
        "highPrice",
        "closePrice"
      ],
      "line": 23,
      "id": "to-verify-if-the-user-is-able-to-access-create-page-under-index.;to-verify-if-the-user-is-able-to-create-an-index.;;1"
    },
    {
      "cells": [
        "10.5",
        "15.8",
        "30.2",
        "18.8"
      ],
      "line": 24,
      "id": "to-verify-if-the-user-is-able-to-access-create-page-under-index.;to-verify-if-the-user-is-able-to-create-an-index.;;2"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 957586023,
  "status": "passed"
});
formatter.background({
  "line": 6,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 7,
  "name": "the docker containers are running",
  "keyword": "Given "
});
formatter.step({
  "line": 8,
  "name": "the user has logged into the pricing application",
  "keyword": "And "
});
formatter.match({
  "location": "PageCommonSteps.the_docker_containers_are_running()"
});
formatter.result({
  "duration": 665210920,
  "status": "passed"
});
formatter.match({
  "location": "PageCommonSteps.the_user_has_logged_into_the_pricing_application()"
});
formatter.result({
  "duration": 337501,
  "error_message": "java.lang.NullPointerException\n\tat stepdef.PageCommonSteps.the_user_has_logged_into_the_pricing_application(PageCommonSteps.java:56)\n\tat ✽.And the user has logged into the pricing application(features/pageobjects/Page_Index_CreatePage.feature:8)\n",
  "status": "failed"
});
formatter.scenario({
  "line": 24,
  "name": "To verify if the user is able to create an index.",
  "description": "",
  "id": "to-verify-if-the-user-is-able-to-access-create-page-under-index.;to-verify-if-the-user-is-able-to-create-an-index.;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 1,
      "name": "@PageObjects"
    }
  ]
});
formatter.step({
  "line": 11,
  "name": "the user has navigated to the \"Create\" page under the \"Index\"",
  "keyword": "Given "
});
formatter.step({
  "line": 12,
  "name": "the user enters rate basis as Price Point Scale",
  "keyword": "When "
});
formatter.step({
  "line": 13,
  "name": "10.5 ,15.8 ,30.2 and 18.8 are entered",
  "matchedColumns": [
    0,
    1,
    2,
    3
  ],
  "keyword": "And "
});
formatter.step({
  "line": 14,
  "name": "name as NY RBOB Prem Brg",
  "keyword": "And "
});
formatter.step({
  "line": 15,
  "name": "start date as 2016-12-10 and end date as 2017-03-10",
  "keyword": "And "
});
formatter.step({
  "line": 16,
  "name": "currency as USD",
  "keyword": "And "
});
formatter.step({
  "line": 17,
  "name": "unit of measurement as USG",
  "keyword": "And "
});
formatter.step({
  "line": 18,
  "name": "comment as Created for new clients",
  "keyword": "And "
});
formatter.step({
  "line": 19,
  "name": "clicks on the submit button",
  "keyword": "And "
});
formatter.step({
  "line": 20,
  "name": "the user shall be able to view the created index in the list on filtering with Price Point Scale",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "Create",
      "offset": 31
    },
    {
      "val": "Index",
      "offset": 55
    }
  ],
  "location": "PageCommonSteps.the_user_has_navigated_to_the_page_under_the(CommonFunctions$page,CommonFunctions$module)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "Price Point Scale",
      "offset": 30
    }
  ],
  "location": "PageIndexSteps.the_user_enters_rate_basis_as(String)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "10.5 ",
      "offset": 0
    },
    {
      "val": "15.8 ",
      "offset": 6
    },
    {
      "val": "30.2",
      "offset": 12
    },
    {
      "val": "18.8",
      "offset": 21
    }
  ],
  "location": "PageIndexSteps.and_are_entered(String,String,String,String)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "NY RBOB Prem Brg",
      "offset": 8
    }
  ],
  "location": "PageIndexSteps.name_as(String)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "2016-12-10",
      "offset": 14
    },
    {
      "val": "2017-03-10",
      "offset": 41
    }
  ],
  "location": "PageIndexSteps.enterStartDateAndEndDate(String,String)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "USD",
      "offset": 12
    }
  ],
  "location": "PageIndexSteps.currency_as(String)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "USG",
      "offset": 23
    }
  ],
  "location": "PageIndexSteps.unit_of_measurement_as(String)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "Created for new clients",
      "offset": 11
    }
  ],
  "location": "PageIndexSteps.comment_as(String)"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "location": "PageCommonSteps.clicks_on_the_submit_button()"
});
formatter.result({
  "status": "skipped"
});
formatter.match({
  "arguments": [
    {
      "val": "Price Point Scale",
      "offset": 79
    }
  ],
  "location": "PageIndexSteps.the_user_shall_be_able_to_view_the_created_index_in_the_list_on_filtering_with(String)"
});
formatter.result({
  "status": "skipped"
});
formatter.after({
  "duration": 98105,
  "error_message": "java.lang.NullPointerException\n\tat setup.Hooks.tearDown(Hooks.java:19)\n\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n\tat java.lang.reflect.Method.invoke(Method.java:498)\n\tat cucumber.runtime.Utils$1.call(Utils.java:40)\n\tat cucumber.runtime.Timeout.timeout(Timeout.java:16)\n\tat cucumber.runtime.Utils.invoke(Utils.java:34)\n\tat cucumber.runtime.java.JavaHookDefinition.execute(JavaHookDefinition.java:60)\n\tat cucumber.runtime.Runtime.runHookIfTagsMatch(Runtime.java:224)\n\tat cucumber.runtime.Runtime.runHooks(Runtime.java:212)\n\tat cucumber.runtime.Runtime.runAfterHooks(Runtime.java:206)\n\tat cucumber.runtime.model.CucumberScenario.run(CucumberScenario.java:46)\n\tat cucumber.runtime.junit.ExecutionUnitRunner.run(ExecutionUnitRunner.java:102)\n\tat org.junit.runners.Suite.runChild(Suite.java:128)\n\tat org.junit.runners.Suite.runChild(Suite.java:27)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.runtime.junit.ExamplesRunner.run(ExamplesRunner.java:59)\n\tat org.junit.runners.Suite.runChild(Suite.java:128)\n\tat org.junit.runners.Suite.runChild(Suite.java:27)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.runtime.junit.ScenarioOutlineRunner.run(ScenarioOutlineRunner.java:53)\n\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:63)\n\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:18)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.runtime.junit.FeatureRunner.run(FeatureRunner.java:70)\n\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:95)\n\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:38)\n\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n\tat cucumber.api.junit.Cucumber.run(Cucumber.java:100)\n\tat org.junit.runner.JUnitCore.run(JUnitCore.java:137)\n\tat com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:68)\n\tat com.intellij.rt.execution.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:51)\n\tat com.intellij.rt.execution.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:237)\n\tat com.intellij.rt.execution.junit.JUnitStarter.main(JUnitStarter.java:70)\n\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n\tat java.lang.reflect.Method.invoke(Method.java:498)\n\tat com.intellij.rt.execution.application.AppMain.main(AppMain.java:147)\n",
  "status": "failed"
});
});