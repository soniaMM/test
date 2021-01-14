package com.example.uitest;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import static com.example.uitest.Base.assert_lauch;
import static com.example.uitest.Base.execCMD;
import static java.lang.Thread.sleep;

public class demotest1 {

    private static AppiumDriver driver;
    private Object assert_lauch;


    @BeforeClass
    public void beforeclass() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.APPIUM_VERSION, "1.19.1");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.0.0");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "192.168.56.101:5555");
        capabilities.setCapability("noReset", true);
        capabilities.setCapability("appPackage", "com.android.contacts");
        capabilities.setCapability("appActivity", "com.android.contacts.activities.PeopleActivity");
        capabilities.setCapability("newCommandTimeout", 2000);
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }

    @AfterClass
    public void AfterClass() {
        driver.quit();
    }

    @Test(enabled = false)
    public void lauch_close() {
        boolean test_result_close, test_result_lauch;
        driver.closeApp();
        driver.launchApp();
        test_result_lauch = Base.assert_lauch(false);
        Assert.assertEquals(test_result_lauch, true);
        driver.closeApp();
        test_result_close = Base.assert_close(false);
        Assert.assertEquals(test_result_close, true);
    }
    @Test()
    public void createpeople() throws InterruptedException {
        driver.findElementById("floating_action_button").click();
        sleep(1000);
        driver.findElementByXPath("//android.widget.TextView[contains(@text,'More fields')]").click();
        sleep(1000);
        List<WebElement> textFieldsList = driver.findElementsByClassName("android.widget.EditText");
        //driver.findElementById("left_button").click();
        textFieldsList.get(0).sendKeys("username");
        textFieldsList.get(0).clear();
        String a = "敬达科技";
        textFieldsList.get(0).sendKeys(a);
        textFieldsList.get(1).sendKeys("cidanachina");
        textFieldsList.get(2).sendKeys("021-6262662");
        textFieldsList.get(5).sendKeys("xxx@cidana.com");
        //textFieldsList.get(5).sendKeys("北京西路668");
        //driver.findElementById("com.android.contacts:id/change_button").click();
        //sleep(1000);
        //driver.findElementByXPath("//*[@resource-id='android:id/text1'][@text='选择照片']").click();
        // sleep(1000);
        // driver.findElementByXPath("//*[@resource-id='com.android.documentsui:id/date'][@text='10月16日']").click();
        //sleep(1000);
        //driver.findElementByXPath("//*[@resource-id='com.android.gallery3d:id/filtershow_done'][@text='保存']").click();
        driver.findElementById("com.android.contacts:id/editor_menu_save_button").click();
        sleep(1000);
        String ele =driver.findElementByXPath("//android.widget.TextView[contains(@text,'敬达科技 cidanachina')]").getText();
        Assert.assertEquals(ele,"敬达科技 cidanachina");
        sleep(1000);
    }
    @Test(enabled = false)
    public void open() throws InterruptedException, IOException {
        driver.findElementByXPath("//android.widget.TextView[contains(@text,'敬达科技 cidanachina')]").click();
        String large_title = driver.findElementById("com.android.contacts:id/large_title").getText();
        String phone_number = driver.findElementById("com.android.contacts:id/header").getText();
        System.out.println(large_title);
        System.out.println(phone_number);
        Assert.assertEquals(large_title,"敬达科技 cidanachina");
        Assert.assertEquals(phone_number,"021-6262662");
        sleep(1000);
    }
    @Test()
        public void delete() throws InterruptedException, IOException {
            while(Base.byElementIsExist(By.xpath("//*[@resource-id='com.android.contacts:id/cliv_name_textview'][@text='敬达科技 cidanachina']")) == true) {
                assert true;
                driver.findElementByXPath("//*[@resource-id='com.android.contacts:id/cliv_name_textview'][@text='敬达科技 cidanachina']").click();
                sleep(1000);
                driver.findElementByAccessibilityId("More options").click();
                sleep(1000);
                driver.findElementByXPath("//*[@resource-id='android:id/title'][@text='Delete']").click();
                sleep(1000);
                driver.findElementById("android:id/button1").click();
                sleep(1000);
                File screenShot = driver.getScreenshotAs(OutputType.FILE); //此段停止执行，提示 Connection reset
                FileUtils.copyFile(screenShot, new File("/home/sonia/AndroidStudioProjects/UItest/lib/src/main/java/com/example/lib/picture/delete.jpg"));
            }
        }


}