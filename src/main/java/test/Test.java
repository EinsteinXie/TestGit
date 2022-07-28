package test;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.map.MapBuilder;
import com.google.common.collect.Lists;
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
 * Author: xcq
 * Date: 2021/9/2 9:11 上午
 * FileName: test.Test
 */
public class Test {

    private static final Logger logger = LoggerFactory.getLogger(Test.class);

    private static final String URL = "https://www.aixuexi.com/";

    private static final String ACCOUNT = "15750809414";
    private static final String PWD = "cyj123456";

    private final static String stealthJsPath = "/Users/debug/work/stealth.min.js";

    static {
        System.setProperty("webdriver.chrome.driver", "/Users/debug/Downloads/chromedriver");
    }

    static ArrayList<User> arrayList = Lists.newArrayList();

//    public static void main(String[] args) {

//        Test test = new Test();
//        test.tt();

//        arrayList.add(User.of("张三", 11, "女"));
//        arrayList.add(User.of("李四", 23, "女"));
//        arrayList.add(User.of("王五", 11, "男"));
//        arrayList.add(User.of("赵六", 18, "女"));
//
//        //分组
//        Map<String, List<User>> collect = arrayList.stream().collect(Collectors.groupingBy(User::getSex));
//
//        collect.forEach((key, userList) -> {
//            System.out.println(key + ":" + userList.toString());
//        });
//        System.out.println("==========================================");
//
//        //排序
//        List<User> collect1 = arrayList.stream().sorted(Comparator.comparing(user -> user.getAge())).collect(Collectors.toList());
//        collect1.forEach(user -> System.out.println(user.toString()));
//        System.out.println("==========================================");
//
//        //过滤
//        arrayList.stream().filter(user -> user.getSex().equals("男"))
//                .forEach(user -> System.out.println(user.toString()));
//        System.out.println("==========================================");
//
//        //多条件去重
//        arrayList.stream().collect(Collectors.collectingAndThen(
//                        Collectors.toCollection(() -> new TreeSet<>(
//                                Comparator.comparing(user -> user.getAge() + ";" + user.getName()))), ArrayList::new))
//                .forEach(user -> System.out.println(user.getName()));
//        System.out.println("==========================================");
//        //最小值
//        Integer min = arrayList.stream().mapToInt(User::getAge).min().getAsInt();
//        System.out.println("==========================================");
//        //最大值
//        Integer max = arrayList.stream().mapToInt(User::getAge).max().getAsInt();
//        System.out.println("==========================================");
//        //平均值
//        Double average = arrayList.stream().mapToInt(User::getAge).average().getAsDouble();
//        System.out.println("==========================================");
//        //和
//        Integer sum = arrayList.stream().mapToInt(User::getAge).sum();
//        System.out.println("最小值:" + min + ", 最大值" + max + ", 平均值:" + average + ", 和:" + sum);
//        System.out.println("==========================================");
//        //分组求和
//        Map<String, IntSummaryStatistics> statisticsMap = arrayList.stream().collect(Collectors.groupingBy(User::getSex, Collectors.summarizingInt(User::getAge)));
//        IntSummaryStatistics statistics1 = statisticsMap.get("男");
//        IntSummaryStatistics statistics2 = statisticsMap.get("女");
//        System.out.println(statistics1.getSum());
//        System.out.println(statistics1.getAverage());
//        System.out.println(statistics1.getMax());
//        System.out.println(statistics1.getMin());
//        System.out.println(statistics1.getCount());
//        System.out.println(statistics2.getSum());
//        System.out.println(statistics2.getAverage());
//        System.out.println(statistics2.getMax());
//        System.out.println(statistics2.getMin());
//        System.out.println(statistics2.getCount());
//        System.out.println("==========================================");
//        //提取list中两个属性值，转为map
//        Map<String, String> userMap = arrayList.stream().collect(Collectors.toMap(User::getName, User::getSex));
//        System.out.println(userMap.toString());
//        System.out.println("==========================================");
//        //取出所有名字
//        List<String> names = arrayList.stream().map(User::getName).collect(Collectors.toList());
//        System.out.println(names.toString());

//    }

    private void tt() {

        WebDriver driver = getDriver();

        driver.get(URL);

        By loginBy = By.xpath("//*[@id='www-header']//a[text()='登录']");
        WebElement loginEle = safeFindElement(driver, loginBy);
        loginEle.click();

        By accountBy = By.xpath("//div[@id='axxLogin']//input[@class='login-username ']");
        WebElement accountEle = safeFindElement(driver, accountBy);
        accountEle.sendKeys(ACCOUNT);

        By pwdBy = By.xpath("//div[@id='axxLogin']//input[@class='login-password ']");
        WebElement pwdEle = safeFindElement(driver, pwdBy);
        pwdEle.sendKeys(PWD);

        By loginBtnBy = By.xpath("//div[@id='axxLogin']//div[@class='login-btn']");
        WebElement loginBtnEle = safeFindElement(driver, loginBtnBy);
        loginBtnEle.click();

        sleep(1000);

        By alertBy = By.xpath("//div[@class='dialog-show']");
        WebElement alertEle = safeFindElement(driver, alertBy);
        if (null != alertEle) {
            String alertEleCss = alertEle.getCssValue("display");
            if ("block".equals(alertEleCss)) {
                By alertErrDescBy = By.xpath("//div[@class='dialog-show']//div[@class='dialog-footer']//button[text()='暂不验证']");
                WebElement alertErrDescEle = safeFindElement(driver, alertErrDescBy);
                alertErrDescEle.click();
            }
        }

        By choiceBy = By.xpath("//div[@id='app']//li[@id='tab2']");
        WebElement choiceEle = safeFindElement(driver, choiceBy);
        choiceEle.click();

        sleep(1000);

        List<WebElement> eleList = driver.findElements(By.xpath("//div[@id=\"app\"]//div[@class='filter-more-item']//span[text()='学期']/..//ul/li[contains(@class,'fil-item')]"));
        sleep(2000);
        if (eleList != null && eleList.size() > 0) {
            for (WebElement webElement :
                    eleList) {
                logger.info("click:[{}]", webElement.getText());
                webElement.click();
                handle(driver);
            }
        } else {
            logger.info("exception");
        }
    }

    private void handle(WebDriver driver) {
        sleep(2000);

        List<WebElement> list = driver.findElements(By.xpath("//div[@id='app']//div[@class='ant-spin-container']//li[contains(@class,'classtype-item')]"));
        if (list != null && list.size() > 0) {
            for (WebElement webElement :
                    list) {
                webElement.click();
                sleep(1000);
                WebElement dialogEle = safeFindElement(driver, By.xpath("//div[@class='ant-modal-root']//div[@role='dialog']"));
                String dialogEleCss = dialogEle.getCssValue("display");
                if ("block".equals(dialogEleCss)) {
                    WebElement dataBtnEle = safeFindElement(driver, By.xpath("//div[@class='lesson-header']//a[text()=' 课程资料 ']"));
                    dataBtnEle.click();

                    sleep(1000);

                    String title = driver.getTitle();
                    if (!title.contains("课程资料页")) {
                        Set<String> winHand = driver.getWindowHandles();
                        for (String str :
                                winHand) {
                            if (str.contains("课程资料页")) {
                                driver.switchTo().window(str);
                                sleep(500);
                            }
                        }
                    }

                    sleep(500);

                    WebElement jianYiEle = safeFindElement(driver, By.xpath("//*[contains(text(),'教师讲义')]"));
                    jianYiEle.click();

                    sleep(500);

                    WebElement allDataChoiceEle = safeFindElement(driver, By.xpath("//span[contains(text(),'全部资料')]/..//input[@type='checkbox']"));
                    boolean choice = allDataChoiceEle.isSelected();
                    if (!choice) {
                        allDataChoiceEle.click();
                        sleep(200);

                        WebElement daYinEle = safeFindElement(driver, By.xpath("//span[contains(text(),'我要打印')]/.."));
                        daYinEle.click();
                        sleep(5000);
                    }

                }
            }
        } else {
            logger.info("exception2");
        }

    }

    private WebDriver getDriver() {
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

    private WebElement safeFindElement(WebDriver driver, By by) {

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

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
        }
    }

}

class User {
    private String name;
    private Integer age;
    private String sex;

    public static User of(String name, Integer age, String sex) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        user.setSex(sex);
        return user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}