package test;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.map.MapBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @Description:
 * @Author: xcq
 * @Version: 2022/5/25
 */
public class Test3 {

    private final static String stealthJsPath = "/Users/debug/work/stealth.min.js";

    private static final Logger logger = LoggerFactory.getLogger(Test3.class);

    static {
        System.setProperty("webdriver.chrome.driver", "/Users/debug/Downloads/chromedriver");
    }

    private static WebDriver getDriver() {
        ChromeOptions options = new ChromeOptions(); // 逃避反扒机制检测设置

        // 设置浏览器参数
        Map<String, Object> prefsMap = new HashMap<String, Object>();
        prefsMap.put("credentials_enable_service", false);
        prefsMap.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefsMap); // 不加载图片资源

        // 关闭开发者模式和谷歌检测是否被selenium控制
        options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("--disable-blink-features");
        options.addArguments("--disable-blink-features=AutomationControlled");

//        options.addArguments("--user-data-dir=/Users/debug/Library/Application Support/Google/Chrome/Default");

        ChromeDriver chromeDriver = new ChromeDriver(options);

        // 去除seleium全部指纹特征
        FileReader fileReader = new FileReader(stealthJsPath);
        String js = fileReader.readString();
        Map<String, Object> commandMap = MapBuilder.create(new LinkedHashMap<String, Object>()).put("source", js)
                .build();
        chromeDriver.executeCdpCommand("Page.addScriptToEvaluateOnNewDocument", commandMap);

        return chromeDriver;
    }

    private static WebElement safeFindElement(WebDriver driver, By by) {

        long start = System.currentTimeMillis();
        while (true) {
            try {
                return driver.findElement(by);
            } catch (NoSuchElementException e) {
                long now = System.currentTimeMillis();
                if (now - start >= 10000) {
                    logger.info("没有找到元素：{}", by.toString());
                    return null;
                } else {
                    sleep(300);
                }
            }
        }
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
        }
    }

    public static void main(String[] args) {
//        WebDriver driver = getDriver();
//
//        driver.get("https://max.book118.com/html/2021/0204/7040003151003052.shtm");
//
//        sleep(1000);
//
//        WebElement ele  = safeFindElement(driver,By.xpath("/html/body/div[@id='main']/div[@class='detail']/div[@class='preview']/div[@class='preview-bd']/div[@class='webpreview-grab']"));
        Date now = new Date();

        Date start = DateUtil.parse("2022-07-21 00:00:00", "yyyy-MM-dd HH:mm:ss");
        Date end = DateUtil.parse("2022-08-20 00:00:00", "yyyy-MM-dd HH:mm:ss");
        if (!now.after(start)) {
            System.out.println("(已过期)");
        } else if (!now.before(end)) {
            System.out.println("(已过期2)");
        } else {
            System.out.println("11");
        }
    }

}
