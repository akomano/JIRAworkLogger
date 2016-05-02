/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2013 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * =========================================================================== */
package eu.ibacz.o2sk.webdriver;

import static eu.ibacz.o2sk.jiradata.JiraData.getJiraProp;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import eu.ibacz.o2sk.jiradata.Effort;

/**
 * @author jjamrich
 *
 */
public class JiraHandler {
        
    WebDriver driver;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd hh:mm");
    PeriodFormatter periodFormatter;
    
    public JiraHandler() throws IOException {        
        
        periodFormatter = new PeriodFormatterBuilder()
            .appendDays().appendSuffix("d ")
            .appendHours().appendSuffix("h ")
            .appendMinutes().appendSuffix("m")
            .toFormatter();
        
        // WebDriver driver = new FirefoxDriver();
        // WebDriver driver = new ChromeDriver();
        ProfilesIni allProfiles = new ProfilesIni();
        FirefoxProfile profile = allProfiles.getProfile("webdriver");
        // profile.setPreferences("foo.bar", 23);
        this.driver = new FirefoxDriver(profile);
        
        driver.get(getJiraProp().getJiraURL());
        
        (new WebDriverWait(driver, 5)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.linkText( "Log In" )).isDisplayed() || d.findElement( By.id("user-options") ).isDisplayed();
            }
        });
    }
    
    public boolean isLoggedIn() {
        // since expectiong more users, it would be more reliable checking "not logged in" status instead of check logged user name
        boolean isLoggedIn = driver.findElement( By.id( "user-options" ) ).findElements( By.linkText( "Log In" ) ).size() == 0;
        System.out.println("isLoggedIn() <- " + isLoggedIn );
        return isLoggedIn;
    }
    
    public void logOff() {
        driver.findElement(By.id( "user-options" )).findElement(By.id("header-details-user-fullname")).click();
    }
    
    public void open(String url) {
        driver.get(url);
    }
    
    public void logIn() throws IOException {
                
        driver.findElement(By.linkText( "Log In" )).click();
        
        (new WebDriverWait(driver, 5)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.id( "login-form-submit" )).isDisplayed();
            }
        });
                
        WebElement loginInput = driver.findElement( By.id("login-form-username") );
        WebElement passInput = driver.findElement( By.id( "login-form-password" ) );
        WebElement login = driver.findElement(By.id( "login-form-submit" ) );
        
        loginInput.sendKeys( getJiraProp().getLogin() );
        passInput.sendKeys( getJiraProp().getPassword() );
        
        // login.click();
        login.submit();
 
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                // return d.getTitle().toLowerCase().startsWith("cheese!");
                return d.getTitle().toLowerCase().startsWith( "mvp" );
            }
        });
        
    }
    
    /**
     * Claim data from param
     */
    public void claimRezie(Effort effort) {
        claimEffort( effort );
    }
    
    private void claimEffort(Effort effort) {
        
        openWorkLogDialog();
        
        WebElement timeSpent = driver.findElement( By.id( "log-work-time-logged" ) );
        WebElement dateStarted = driver.findElement( By.id( "log-work-date-logged-date-picker" ) );
        WebElement workDescription = driver.findElement( By.id( "log-work-dialog" ) ).findElement( By.id( "comment" ) );
        
        WebElement LogBtn = driver.findElement( By.id( "log-work-submit" ) );
        WebElement CancelBtn = driver.findElement( By.id( "log-work-cancel" ) );
        
        timeSpent.sendKeys(effort.getTimeSpent().toString( periodFormatter ));        
        // need to rewrite existing default value
        dateStarted.sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END), sdf.format(effort.getDate()));        
        workDescription.sendKeys( effort.getWorkDescription() );
        
        CancelBtn.click();
        
    }
    
    private void openWorkLogDialog() {
        WebElement moreBtn = driver.findElement( By.id("opsbar-operations_more") );
        WebElement logWorkBtn = driver.findElement( By.id( "log-work" ) );
        
        moreBtn.click();
        
        getDefaultWDWait().until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                // return d.getTitle().toLowerCase().startsWith("cheese!");
                return d.findElement( By.id( "log-work" ) ).isDisplayed();
            }
        });
        
        logWorkBtn.click();
        
        getDefaultWDWait().until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                // return d.getTitle().toLowerCase().startsWith("cheese!");
                return d.findElement( By.id( "log-work-dialog" ) ).isDisplayed();
            }
        });
    }
    
    public void closeBrowser() {
        driver.close();
    }
    
    private WebDriverWait getDefaultWDWait() {
        return new WebDriverWait(driver, 10);
    }

}
