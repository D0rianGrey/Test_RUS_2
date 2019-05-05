import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class SignUpTest {
    private WebDriver driver;
    private SignUpPage page;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.spotify.com/us/signup/");
    }

    @Test
    public void typeInvalidYear() {
        page = new SignUpPage(driver);
        page.setMonth("December")
                .typeDay("20")
                .typeYear("85")
                .setShare(true);
        Assert.assertTrue(page.isErrorVisible("Please enter a valid year."));
        Assert.assertFalse(page.isErrorVisible("When were you born"));
    }

    @Test
    public void typeInvalidEmail() {
        page = new SignUpPage(driver);
        page.typeEmail("test@mail.test")
                .typeConfirmEmail("wrong@mail.test")
                .typeName("Testname")
                .clickSignUpButton();
        Assert.assertTrue(page.isErrorVisible("Email address doesn't match."));

    }

    @Test
    public void signUpWithEmptyPassword() {
        page = new SignUpPage(driver);
        page.typeEmail("test@mail.test")
                .typeConfirmEmail("test@mail.test")
                .typeName("testname")
                .clickSignUpButton();
        Assert.assertTrue(page.isErrorVisible("Please choose a password."));
    }

    @Test
    public void typeInvalidValues() {
        page = new SignUpPage(driver);
        page.typeEmail("testmail")
                .typeConfirmEmail("wrong@test.mail")
                .typePassword("qwerty!123")
                .typeName("Name")
                .setSex("Male")
                .setShare(false)
                .clickSignUpButton();
        Assert.assertEquals(6, page.getErrors().size());
        Assert.assertEquals("Please enter your birth month.", page.getErrorByNumber(3));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
