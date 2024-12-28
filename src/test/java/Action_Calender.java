import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Action_Calender {
        PageLocator_Calenders calender;
        WebDriver driver;

        @BeforeMethod
        void Browser() {

            driver = new ChromeDriver();
            driver.get("https://www.bestcase.com/date-calculator/");
            driver.manage().window().maximize();
            calender = new PageLocator_Calenders();
            Actions actions = new Actions(driver);
            actions.scrollByAmount(0, 500).pause(300).perform();
        }

        @Test
        void TC_A() {

            int number = 2032;
            calender.acceptCookies(driver).click();
            calender.inputFailed(driver).click();
            calender.getMonths(driver).click();
            calender.getYears(driver).click();
            int i;
            for (i = 0; i < calender.listOfYears(driver).size(); i++) {
                ArrayList<String> lists = new ArrayList<>();
                lists.add(calender.listOfYears(driver).get(i).getText());
                System.out.println(lists);
                WebElement min_Year = calender.listOfYears(driver).get(0);
                WebElement max_Year = calender.listOfYears(driver).get(11);
                int firstYear = Integer.parseInt(min_Year.getText());
                int lastYear = Integer.parseInt(max_Year.getText());
                System.out.println(lastYear);
                System.out.println(firstYear);
                if (number >= firstYear && number <= lastYear) {

                    if (calender.listOfYears(driver).get(i).getText().contains(String.valueOf(number))) {
                        driver.findElement(By.xpath(String.format(calender.year(), number))).click();
                        Random random = new Random();
                        int getMonthsRandomly = random.nextInt(calender.listOfMonths(driver).size());
                        WebElement getMonths = calender.listOfMonths(driver).get(getMonthsRandomly);
                        getMonths.click();
                        calender.listDays(driver).get(3).click();
                    }

                } else if (number < firstYear) {
                    calender.prevBtn(driver).click();
                    for (int y = 0; y < calender.listOfYears(driver).size(); y++) {
                        if (calender.listOfYears(driver).get(y).getText().contains(String.valueOf(number))) {

                            driver.findElement(By.xpath(String.format(calender.year(), number))).click();
                            Random random = new Random();
                            int getMonthsRandomly = random.nextInt(calender.listOfMonths(driver).size());
                            WebElement getMonths = calender.listOfMonths(driver).get(getMonthsRandomly);
                            getMonths.click();
                            calender.listDays(driver).get(3).click();
                        }
                    }
                } else {
                    calender.nextBtn(driver).click();
                    for (int z = 0; z < calender.listOfYears(driver).size(); z++) {
                        if (calender.listOfYears(driver).get(z).getText().contains(String.valueOf(number)))
                            driver.findElement(By.xpath(String.format(calender.year(), number))).click();
                        Random random = new Random();
                        int getMonthsRandomly = random.nextInt(calender.listOfMonths(driver).size());
                        WebElement getMonths = calender.listOfMonths(driver).get(getMonthsRandomly);
                        getMonths.click();
                        calender.listDays(driver).get(3).click();
                    }

                }

            }
        }

        @AfterMethod
        void finsh() throws InterruptedException {
            Thread.sleep(3000);
            driver.quit();
        }

    }