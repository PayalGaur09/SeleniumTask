package steps;

import com.typesafe.config.Config;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import pages.DashboardPage;
import utilities.ConfigLoader;
import utilities.LoadProperties;

import java.util.List;

public class DashboardSteps {
    private Config conf = ConfigLoader.load();
    private DashboardPage dashboard;

    @Given("^User is on work order sign in page$")
    public void userIsOnWorkOrderSignInPage() {
        dashboard.getDriver().get(conf.getString("test_url"));
    }

    @Given("^User sign in with valid credential of Account Owner$")
    public void userSignInWithValidCredentialOfAccountOwner() {
        String id= LoadProperties.getValueFromPropertyFile("testData","AccountOwnerId");
        String pwd= LoadProperties.getValueFromPropertyFile("testData","password");
        dashboard.enterCredentials(id,pwd);

    }


    @Given("^User is on \"([^\"]*)\" screen$")
    public void userIsOnScreen(String module) throws Throwable {
        dashboard.tapOnMenuItems(module);
    }

    @Then("^User should be able to see the widgets$")
    public void userShouldBeAbleToSeeTheWidgets(List<String> options) {
        for (String option : options) {
            dashboard.verifyWidgetHeading(option);
        }
    }

    @Then("^User should be able to see the workorder tiles$")
    public void userShouldBeAbleToSeeTheWorkorderTiles(List<String> options) {
        for (String option : options) {
            dashboard.verifyWorkOrderTiles(option);
        }
    }

    @Then("^User should be able to see the company tiles$")
    public void userShouldBeAbleToSeeTheCompanyTiles(List<String> options) {
        for (String option : options) {
            dashboard.verifyCompanyTiles(option);
        }
    }

    @Then("^User should be able to see the components of Kiosk Heath Status$")
    public void userShouldBeAbleToSeeTheComponentsOfKioskHeathStatus() {
        dashboard.verifyComponentOfHealthStatus();
    }

    @Then("^User should be able to see the components of Offline Kiosk Log$")
    public void userShouldBeAbleToSeeTheComponentsOfOfflineKioskLog() {
        dashboard.verifyComponentOfOfflineLog();
    }
}
