package com.example.uitest;

import org.openqa.selenium.By;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import io.appium.java_client.AppiumDriver;

public class Base {
    private static AppiumDriver driver;

    //执行cmd命令，获取返回结果
    public static String execCMD(String command) {
        StringBuilder sb = new StringBuilder();
        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (Exception e) {
            return e.toString();
        }
        return sb.toString();
    }
    //
    public static boolean assert_lauch(boolean flag_lauch) {
        String lauch = execCMD("adb shell dumpsys window | grep mCurrentFocus");
        System.out.println(lauch);
        if (lauch.contains("PeopleActivity")) {
            flag_lauch = true;
            System.out.println("lauch successfully");
            return true;
        } else {
            flag_lauch = false;
            System.out.println("failed");
            return  false;
        }
    }
    //
    public static boolean assert_close(boolean flag_close) {
        String lauch = execCMD("adb shell dumpsys window | grep mCurrentFocus");
        System.out.println(lauch);
        if (lauch.contains("launcher3")) {
            flag_close = true;
            System.out.println("close successfully");
            return true;
        } else {
            flag_close = false;
            System.out.println("failed");
            return  false;
        }
    }
    public static  boolean byElementIsExist(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
    }
}

