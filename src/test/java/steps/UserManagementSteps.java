package steps;

import com.typesafe.config.Config;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.UserManagementPage;
import utilities.ConfigLoader;

import java.io.IOException;
import java.util.List;

public class UserManagementSteps {
    Config conf = ConfigLoader.load();
    UserManagementPage users;


    @When("^User tap on the add User button$")
    public void userTapOnTheAddUserButton() {
        users.tapOnAddUserButton();
    }


    @When("^User enters all the field in user screen$")
    public void userEntersAllTheFieldInUserScreen() throws IOException {
        users.uploadProfilePicture();
        users.addInputFieldsOfUserForm();
    }


    @And("^User select Client Admin as the user role$")
    public void userSelectClientAdminAsTheUserRole() {
        users.selectFilterDropdown("Administrator");
    }


    @And("^User taps on the Submit button$")
    public void userTapsOnTheSubmitButton() {
        users.tapOnSubmitButton();
    }

    @And("^Verify user detail screen$")
    public void verifyUserDetailScreen() {
        users.userDetailsVerify();
    }

    @Then("^Success message \"([^\"]*)\" should be displayed$")
    public void successMessageShouldBeDisplayed(String message) throws Throwable {
        users.verifyValidationMessage(message);
    }

    @Then("^Error message should be displayed$")
    public void errorMessageShouldBeDisplayed(List<String> options) {
        for (String option : options) {
            users.verifyValidationMessage(option);
        }

    }

    @When("^User clicks on delete button$")
    public void userClicksOnDeleteButton() {
        users.clickOnDeleteButton();
        users.acceptOptionInJSPopup();
    }

}
