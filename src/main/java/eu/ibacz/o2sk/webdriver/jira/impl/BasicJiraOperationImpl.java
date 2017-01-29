/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2013 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * =========================================================================== */
package eu.ibacz.o2sk.webdriver.jira.impl;

import static eu.ibacz.o2sk.jiradata.JiraData.getJiraProp;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import eu.ibacz.o2sk.webdriver.jira.BasicJiraOperation;

/**
 * @author jan.jamrich@ibacz.eu
 *
 */
public class BasicJiraOperationImpl implements BasicJiraOperation {
	
	private static final Logger log = LogManager.getLogger(BasicJiraOperationImpl.class);
	private WebDriver driver;
	
	public BasicJiraOperationImpl(WebDriver driver) {
		this.driver = driver;
	}
	
	@Override
	public void openWorkLogDialog() {
		WebElement logWorkBtn = driver.findElement( By.id( "log-work" ) );
        
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
	
	public void logIn() {
        
        // driver.findElement(By.linkText( "Log In" )).click();
		clickOnLink("Log In");
        
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
                return d.getTitle().startsWith( "MVP: IBA Status Meeting" )
                		|| d.getTitle().startsWith( "O2SK: Support dashboard" );
            }
        });
        
    }
	
	public boolean isLoggedIn() {
        // since expectiong more users, it would be more reliable checking "not logged in" status instead of check logged user name
        boolean isLoggedIn = driver.findElement( By.id( "user-options" ) ).findElements( By.linkText( "Log In" ) ).size() == 0;
        log.info("isLoggedIn() <- " + isLoggedIn );
        return isLoggedIn;
    }
    
    public void logOff() {
        driver.findElement(By.id( "user-options" )).findElement(By.id("header-details-user-fullname")).click();
    }

	private WebDriverWait getDefaultWDWait() {
        return new WebDriverWait(driver, 10);
    }

	@Override
	public void open(String url) {
		driver.get(url);
	}

	@Override
	public void closeBrowser() {
		driver.close();
	}

	@Override
	public void clickOnLink(String label) {
		driver.findElement(By.linkText(label)).click();		
	}
	
	@Override
	public void clickOnSPLink(String label) {
		driver.findElement(By.linkText(label)).click();
		
		(new WebDriverWait(driver, 15)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.cssSelector("H1#summary-val")).getText().startsWith("SP")
                		&& d.findElement(By.cssSelector("span#type-val")).getText().trim().equalsIgnoreCase("Sub-task");
            }
        });
	}

	@Override
	public List<WebElement> getElementsByPartialLinkText(String text) {
		return driver.findElements(By.partialLinkText(text));
	}

}
