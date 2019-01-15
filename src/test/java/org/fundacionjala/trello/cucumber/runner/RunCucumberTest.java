package org.fundacionjala.trello.cucumber.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.fundacionjala.core.CleanData;
import org.fundacionjala.core.ui.driver.DriverManager;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

/**
 * Class which runs all features.
 */
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"org.fundacionjala.trello.cucumber"},
        plugin = {"pretty"},
        tags = {"@bvt"}
)
public class RunCucumberTest extends AbstractTestNGCucumberTests {


    /**
     * this method execute before the tests.
     */
    @BeforeTest
    public void open() {
        CleanData cleanData = new CleanData();
        cleanData.cleanAllTeams("owner1");
    }

    /**
     * this method close the browser after the features finish.
     */
    @AfterTest
    public void close() {
        DriverManager.getInstance().getDriver().quit();
    }
}
