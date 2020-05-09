package chauttm;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;
import java.util.List;

public class PG {
    private static final String baseUrl = "https://github.com/";

    public static void main(String[] args) throws IOException {

        System.setProperty("webdriver.chrome.driver","G:\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        signInGithub(driver);

        //Pattern pattern = Pattern.compile("collaborate on the [\\S]+ repository");

        try {
            FileReader reader = new FileReader("C:\\Users\\ADMIN\\IdeaProjects\\WebDrive\\git-links.txt");
            BufferedReader in = new BufferedReader(reader);
            FileWriter writer = new FileWriter("err.txt");

            String line;
            while ((line = in.readLine()) != null) {
                System.err.println(line);
                if (!acceptInvitation(driver, line)) {
                    writer.write(line+"\n");
                }
            }
            reader.close();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        driver.close();
        System.exit(0);

    }

    private static boolean acceptInvitation(WebDriver driver, String repository) {
        driver.get(repository);
        List<WebElement> elementsList = driver.findElements(By.tagName("button"));
        for (WebElement e : elementsList) {
            if (e.getText().startsWith("Accept ")) {
                e.click();
                return true;
            }
        }
        return false;
    }

    private static void signInGithub(WebDriver driver) {
        String baseUrl = "https://github.com/login";
        driver.get(baseUrl);
        System.err.println("get-done");
        WebElement username = driver.findElement(By.id("login_field"));
        System.err.println(username.getAttribute("class"));
        username.sendKeys("chauttm");  //todo: usser name

        WebElement password = driver.findElement(By.id("password"));
        System.err.println(password.getAttribute("class"));
        password.sendKeys("sdfgdfgs");  //todo: password

        WebElement submit = driver.findElement(By.name("commit"));
        System.err.println(submit.getAttribute("value"));
        submit.click();
        System.out.println("Login Done with Click");
    }
}
