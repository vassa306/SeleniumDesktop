import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

public class TestCalc extends BaseTests {


    @Test
    public void verifyStatusBar() {
        // Ensure driver is initialized
        Assert.assertNotNull(driver, "Driver is null! Ensure @BeforeMethod is executed.");

        // Click on the "View" menu
        driver.findElementByName("View").click();

        // Locate the "Status Bar" menu item
        WebElement statusBarMenuItem = driver.findElement(By.xpath("//MenuItem[@Name='Status Bar']"));

        statusBarMenuItem.click();


        // Wait to ensure the action is completed

        // Use an explicit wait to ensure the status bar element's presence
        WebDriverWait wait = new WebDriverWait(driver, 10);
        boolean isStatusBarDisplayed;
        try {
            WebElement statusBarElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("msctls_statusbar32")));
            isStatusBarDisplayed = statusBarElement.isDisplayed();
        } catch (TimeoutException e) {
            isStatusBarDisplayed = false; // Element not found, implying it's not displayed
        }

        // Verify that the state of the status bar matches the menu item's state
        System.out.println(isStatusBarDisplayed);
        Assert.assertFalse(isStatusBarDisplayed, "Expected Status Bar to be hidden, but it is displayed.");

    }

    @Test
    public void verifyAboutNotepad() {

        driver.findElementByName("Help").click();
        driver.findElementByName("About Notepad").click();
        WebElement aboutNotepadElement = driver.findElement(By.name("About Notepad"));
        // Retrieve the value of the "Value.Value" attribute
        String value = aboutNotepadElement.getText();
        Assert.assertEquals(value, "About Notepad", "Actual string is not expected " + value);
        driver.findElementByName("OK").click();

    }

    @Test
    public void closeAboutNotepad() {
        driver.findElementByName("Help").click();
        driver.findElementByName("About Notepad").click();
        WebElement closeButton = driver.findElement(By.xpath("//Window[@ClassName='#32770']//Button[@Name='Close']"));
        // Retrieve the value of the "Value.Value" attribute
        boolean clsIsDispl = closeButton.isDisplayed();
        Assert.assertTrue(clsIsDispl, "Close button is missing in the About notepad dialog window");
        closeButton.click();

    }

    @Test
    public void loadFile() throws InterruptedException {
        //click on File menu
        driver.findElementByName("File").click();
        driver.findElementByName("Open...").click();
        String filePath = "C:\\DesktopApp\\TestNotepad.txt";
        Thread.sleep(2000);
        WebElement openFileNameField = driver.findElement(By.className("Edit"));
        openFileNameField.sendKeys(filePath);
    }
}




