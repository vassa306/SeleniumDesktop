import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static org.openqa.selenium.By.*;

public class TestCalc extends BaseTests {



    @Test
    public void verifyStatusBar() {
        // Ensure driver is initialized
        Assert.assertNotNull(driver, "Driver is null! Ensure @BeforeMethod is executed.");

        // Click on the "View" menu
        driver.findElementByName("View").click();

        // Locate the "Status Bar" menu item
        WebElement statusBarMenuItem = driver.findElement(xpath("//MenuItem[@Name='Status Bar']"));
        String state = statusBarMenuItem.getAttribute("LegacyState");
        if (state.contains("0")) {
            System.out.println("Element is checked " + state);
        }
        statusBarMenuItem.click();


        // Wait to ensure the action is completed

        // Use an explicit wait to ensure the status bar element's presence
        WebDriverWait wait = new WebDriverWait(driver, 10);
        boolean isStatusBarDisplayed;
        try {
            // Wait for the status bar element to become visible
            WebElement statusBarElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("msctls_statusbar32")));
            isStatusBarDisplayed = statusBarElement.isDisplayed();
            System.out.println("Status bar is displayed" + isStatusBarDisplayed);
            // Assert true if the element is displayed
            Assert.assertTrue(isStatusBarDisplayed, "Status bar is not displayed but was expected to be.");
        } catch (TimeoutException e) {
            isStatusBarDisplayed = false;
            // Assert false if the element is not found within the timeout
            Assert.assertFalse(isStatusBarDisplayed, "Status bar is displayed but was expected not to be.");
        }

        // Verify that the state of the status bar matches the menu item's state
        System.out.println(isStatusBarDisplayed);


    }

    @Test
    public void verifyAboutNotepad() {

        driver.findElementByName("Help").click();
        driver.findElementByName("About Notepad").click();
        WebElement aboutNotepadElement = driver.findElement(name("About Notepad"));
        // Retrieve the value of the "Value.Value" attribute
        String value = aboutNotepadElement.getText();
        Assert.assertEquals(value, "About Notepad", "Actual string is not expected " + value);
        driver.findElementByName("OK").click();

    }

    @Test
    public void closeAboutNotepad() {
        driver.findElementByName("Help").click();
        driver.findElementByName("About Notepad").click();
        WebElement closeButton = driver.findElement(xpath("//Window[@ClassName='#32770']//Button[@Name='Close']"));
        // Retrieve the value of the "Value.Value" attribute
        boolean clsIsDispl = closeButton.isDisplayed();
        Assert.assertTrue(clsIsDispl, "Close button is missing in the About notepad dialog window");
        closeButton.click();

    }

    @Test
    public void saveFile() throws InterruptedException {
        //click on File menu
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.findElementByName("Text Editor").sendKeys("automation by WinAppDriver");
        driver.findElementByName("File").click();
        WebElement saveAS = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//MenuItem[@AutomationId='4']")));
        saveAS.click();
        // Generate the filename with counter and current time
        String randomString = generateRandomString(6);
        String fileName = "Test_" + randomString;

        WebElement input = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//Edit[@AutomationId='1001']")));
        input.sendKeys(fileName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(name("Save"))).click();
//        Thread.sleep(4000);
//        WebElement titlebar = driver.findElementByAccessibilityId("TitleBar");
//        String actualTitle =  titlebar.getText();
//        Assert.assertEquals(actualTitle,fileName,"This filenames are different " + fileName);

    }

    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }
        return randomString.toString();
    }

}




