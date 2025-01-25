import Pages.Constants.TestConstants;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class BaseTests {

    protected WindowsDriver driver; // Declare driver as a class-level variable
    Process process;

    {
        try {
            process = runWinAppDriverServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @BeforeMethod
    public void setUpNotepad() throws IOException {
        Boolean runWithRootCapabilities = false;
        String desktopApplicationLocation = TestConstants.NOTEPAD_PATH;
        DesiredCapabilities capabilities = new DesiredCapabilities();
        driver=StartWinAppDriver(false, desktopApplicationLocation);
        System.out.println("Driver initialized: " + true); // Debugging
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void killServer(){
        stopWinAppDriverServer(process);
    }


    public static Process runWinAppDriverServer()throws IOException {
        String winAppDriverPath = "C:\\Program Files (x86)\\Windows Application Driver\\WinAppDriver.exe";
        ProcessBuilder builder = new ProcessBuilder(winAppDriverPath).inheritIO();
        return builder.start();
    }

    public static void stopWinAppDriverServer(Process process) {
        process.destroy();
    }

    private static WindowsDriver StartWinAppDriver(Boolean root, String applicationLocation) throws IOException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        if (root) {
            desiredCapabilities.setCapability("app", "root");
        } else {
            desiredCapabilities.setCapability("app", applicationLocation);
        }
        desiredCapabilities.setCapability("platformName", "Windows");
        desiredCapabilities.setCapability("deviceName", "WindowsPC");
        return new WindowsDriver(new URL("http://127.0.0.1:4723"), desiredCapabilities);
    }

}



