package pages;

import models.DetailsModel;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import utilities.RandomGenerator;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class UserManagementPage extends PageObject {
    private DetailsModel detailsModel = new DetailsModel();

    @FindBy(xpath = "//span[text()='Users']")
    private WebElementFacade userLink;
    @FindBy(xpath = "//a[contains(text(),'New User')]")
    private WebElementFacade addUserButton;
    @FindBy(xpath = "//select")
    private WebElementFacade userRoleDropdown;
    @FindBy(xpath = "//button[contains(text(),'Choose file')]")
    private WebElementFacade chooseFile;
    @FindBy(xpath = "//button[contains(text(),'Submit')]")
    private WebElementFacade submitButton;
    @FindBy(xpath = "//button[contains(text(),'Action')]")
    private WebElementFacade actionButton;
    @FindBy(xpath = "//span[contains(text(),'Delete')]")
    private WebElementFacade deleteButton;
    @FindBy(xpath = "//button[text()='Upload']")
    private WebElementFacade uploadImageButton;


    private By userField(String text) {
        return By.xpath("//label[contains(text(),'" + text + "')]/..//input");
    }

    private By userFieldOnDetail(String text) {
        return By.xpath("//label[contains(text(),'" + text + "')]/..//p");
    }

    private By validationMessage(String text) {
        return By.xpath("//*[contains(text(),'" + text + "')]");
    }

    private By roleDropdownOptions(String opt) {
        return By.xpath("//option[text()='" + opt + "']");
    }

    public void uploadProfilePicture() throws IOException {
        withTimeoutOf(20,TimeUnit.SECONDS).waitFor(chooseFile).shouldBeVisible();
        String path = new File(".").getCanonicalPath() + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "testData" + File.separator + "profileIcon.png";
        getDriver().findElement(By.xpath("//input[@type='file']")).sendKeys(path);
        withTimeoutOf(20,TimeUnit.SECONDS).waitFor(uploadImageButton).click();
    }


    public void tapOnAddUserButton() {
        withTimeoutOf(20, TimeUnit.SECONDS).waitFor(addUserButton).click();
    }

    private void enterValueInFirstNameField() {
        element(userField("First")).withTimeoutOf(20, TimeUnit.SECONDS).waitUntilVisible();
        detailsModel.setName("User" + RandomGenerator.randomAlphabetic(3));
        element(userField("First")).sendKeys(detailsModel.getName());
    }

    private void enterValueInLastNameField() {
        element(userField("Last")).withTimeoutOf(20, TimeUnit.SECONDS).waitUntilVisible();
        detailsModel.setLastName(RandomGenerator.randomAlphabetic(3));
        element(userField("Last")).sendKeys(detailsModel.getLastName());
    }

    private void enterValueInEmailField() {
        element(userField("Email")).withTimeoutOf(20, TimeUnit.SECONDS).waitUntilVisible();
        detailsModel.setEmail("User" + RandomGenerator.randomInteger(4) + "@mailinator.com");
        element(userField("Email")).sendKeys(detailsModel.getEmail());
    }

    private void enterValueInPhoneField() {
        element(userField("Phone")).withTimeoutOf(20, TimeUnit.SECONDS).waitUntilVisible();
        detailsModel.setPhoneNo(RandomGenerator.randomInteger(10));
        element(userField("Phone")).sendKeys(detailsModel.getPhoneNo());
    }


    public void addInputFieldsOfUserForm() {
        enterValueInFirstNameField();
        enterValueInLastNameField();
        enterValueInEmailField();
        enterValueInPhoneField();
    }

    public void selectFilterDropdown(String opt) {
        WebElementFacade a = element(roleDropdownOptions(opt));
        detailsModel.setUserRole(opt);
        withTimeoutOf(20,TimeUnit.SECONDS).waitFor(a).click();
    }


    public void tapOnSubmitButton() {
        withTimeoutOf(30, TimeUnit.SECONDS).waitFor(submitButton).click();
    }

    public void verifyValidationMessage(String text) {
        WebElementFacade a = element(validationMessage(text));
        withTimeoutOf(40, TimeUnit.SECONDS).waitFor(a).shouldBePresent();
    }

    public void userDetailsVerify() {
        Assert.assertEquals(detailsModel.getName(), element(userFieldOnDetail("First")).getText());
        Assert.assertEquals(detailsModel.getLastName(), element(userFieldOnDetail("Last")).getText());
        Assert.assertEquals(detailsModel.getEmail(), element(userFieldOnDetail("Email")).getText());
        Assert.assertEquals(detailsModel.getPhoneNo(), element(userFieldOnDetail("Phone")).getText());
        Assert.assertEquals(detailsModel.getUserRole(), element(userFieldOnDetail("Role")).getText());
    }

    public void clickOnDeleteButton() {
        withTimeoutOf(10, TimeUnit.SECONDS).waitFor(actionButton).click();
        withTimeoutOf(10, TimeUnit.SECONDS).waitFor(deleteButton).click();
    }

    //Accept Javascript popup
    public void acceptOptionInJSPopup() {
        Alert alert = getDriver().switchTo().alert();
        alert.accept();
    }


}


