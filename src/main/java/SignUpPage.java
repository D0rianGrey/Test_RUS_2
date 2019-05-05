import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class SignUpPage {
    private WebDriver driver;

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
    }

    private By emailField = By.xpath("//*[@id=\"register-email\"]");
    private By confirmEmailField = By.xpath("//*[@id=\"register-confirm-email\"]");
    private By passwordField = By.xpath("//*[@id=\"register-password\"]");
    private By nameField = By.xpath("//*[@id=\"register-displayname\"]");
    private By monthDropDown = By.xpath("//*[@id=\"register-dob-month\"]");
    private String monthDropDownOption = "//select[@id='register-dob-month']/option[text()='%s']";
    private By dayField = By.xpath("//*[@id=\"register-dob-day\"]");
    private By yearField = By.xpath("//*[@id=\"register-dob-year\"]");
    private String sexRadioButton = "//*[@id=\"li-gender\"]/label[normalize-space()='%s']";
    private By shareCheckbox = By.xpath("//*[@id=\"register-thirdparty\"]");
    private By registerButton = By.xpath("//*[@id=\"register-button-email-submit\"]");
    private By errorLabel = By.xpath("//label[@class=\"has-error\" and string-length(text())>0]");
    private String errorByText = "//label[@class=\"has-error\" and text()=\"%s\"]";

    public SignUpPage typeEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    public SignUpPage typeConfirmEmail(String email) {
        driver.findElement(confirmEmailField).sendKeys(email);
        return this;
    }

    public SignUpPage typePassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    public SignUpPage typeName(String name) {
        driver.findElement(nameField).sendKeys(name);
        return this;
    }

    public SignUpPage setMonth(String month) {
        driver.findElement(monthDropDown).click();
        new WebDriverWait(driver, 5).until(visibilityOfElementLocated(By.xpath(String.format(monthDropDownOption, month)))).click();
        return this;
    }

    public SignUpPage typeDay(String day) {
        driver.findElement(dayField).sendKeys(day);
        return this;
    }

    public SignUpPage typeYear(String year) {
        driver.findElement(yearField).sendKeys(year);
        return this;
    }

    public SignUpPage setSex(String value) {
        driver.findElement(By.xpath(String.format(sexRadioButton, value))).click();
        return this;
    }

    public SignUpPage setShare(boolean value) { // есть true - чекаем, если false - то анчекаем
        WebElement checkbox = driver.findElement(shareCheckbox);
        if (!checkbox.isSelected() == value) { // если не чекнуто - мы чекаем, если уже ченуто то оставляем как есть
            checkbox.click();
        }
        return this;
    }

    public void clickSignUpButton() {
        driver.findElement(registerButton).click();
    }

    public List<WebElement> getErrors() {
        return driver.findElements(errorLabel);
    }

    public String getErrorByNumber(int number) {
        return getErrors().get(number - 1).getText();
    }

    public boolean isErrorVisible (String message){
        return driver.findElements(By.xpath(String.format(errorByText,message))).size() > 0
                && driver.findElements(By.xpath(String.format(errorByText,message))).get(0).isDisplayed();
    }


}
