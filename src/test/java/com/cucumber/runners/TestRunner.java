package com.cucumber.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        dryRun = false,
        monochrome = true,
        features = "src/test/resources/features/",
        glue = "com.cucumber.steps",
        tags = "@amazon", //  use this tag to run only amazon @amazon
        plugin = {
                "pretty",
                "html:target/cucumber-reports",
                "json:target/cucumber.json",
                "rerun:target/failed.txt"
        })

public class TestRunner{


}
