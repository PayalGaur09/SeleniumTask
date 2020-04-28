package pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class DashboardPage extends PageObject {
   final String loaderLocator = "//div[@class='loader']";

    @FindBy(xpath = "//input[@name='userName']")
    private WebElementFacade userName;
    @FindBy(xpath = "//input[@name='password']")
    private WebElementFacade password;
    @FindBy(xpath = "//button[text()='Sign In']")
    private WebElementFacade signinButton;
    @FindBy(xpath = "//a[text()='View All']")
    private WebElementFacade viewAllButton;
    @FindBy(xpath = "//a[text()='View Record']")
    private WebElementFacade viewRecordButton;
    @FindBy(xpath = "//h3[contains(text(),'Status')]/../../..//*[name()='g']")
    private WebElementFacade pieChart;
    @FindBy(xpath = "//h3[contains(text(),'Log')]/../../..//*[name()='g']")
    private WebElementFacade offlineLogGraph;
    @FindBy(xpath = loaderLocator)
    WebElementFacade loader;


    private By menuItems(String module) {
        return By.xpath("//span[text()='" + module + "']/ancestor::a");
    }

    private By headings(String heading) {
        return By.xpath("//h3[contains(text(),'" + heading + "')]");
    }

    private By workOrderSubtitle(String tile) {
        return By.xpath("//a[contains(text(),'" + tile + "')]");
    }

    private By companySubtitle(String tile) {
        return By.xpath("//span[contains(text(),'" + tile + "')]/../..//div[@class='kt-widget17__item']");
    }

    public void enterCredentials(String userEmail, String pwd) {
        getDriver().manage().window().maximize();
        try {
            userName.sendKeys(userEmail);
            password.sendKeys(pwd);
            waitFor(signinButton).click();
        } catch (Exception ignored) {

        }
        withTimeoutOf(20, TimeUnit.SECONDS).waitForAbsenceOf(loaderLocator);

    }

    public void tapOnMenuItems(String module) {
        WebDriverWait webDriverWait = new WebDriverWait(getDriver(), 30);

        WebElement a = getDriver().findElement(menuItems(module));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(menuItems(module)));
        a.click();
    }

    public void verifyWidgetHeading(String text) {
        WebDriverWait webDriverWait = new WebDriverWait(getDriver(), 20);
        WebElementFacade woTile = element(headings(text));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(headings(text)));
        woTile.shouldBeVisible();
    }

    public void verifyWorkOrderTiles(String tile) {
        WebElementFacade woTile = element(workOrderSubtitle(tile));
        withTimeoutOf(20, TimeUnit.SECONDS).waitFor(woTile).shouldBeVisible();
    }

    public void verifyCompanyTiles(String tile) {
        WebElementFacade companyTile = element(companySubtitle(tile));
        withTimeoutOf(20, TimeUnit.SECONDS).waitFor(companyTile).shouldBeVisible();
    }

    public void verifyComponentOfHealthStatus() {
        withTimeoutOf(20, TimeUnit.SECONDS).waitFor(viewAllButton).shouldBePresent();
        withTimeoutOf(20, TimeUnit.SECONDS).waitFor(pieChart).shouldBePresent();
    }

    public void verifyComponentOfOfflineLog() {
        withTimeoutOf(10, TimeUnit.SECONDS).waitFor(viewRecordButton).shouldBePresent();
        withTimeoutOf(10, TimeUnit.SECONDS).waitFor(offlineLogGraph).shouldBePresent();
    }


}
