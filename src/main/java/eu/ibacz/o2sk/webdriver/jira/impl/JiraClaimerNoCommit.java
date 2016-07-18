/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2013 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * =========================================================================== */
package eu.ibacz.o2sk.webdriver.jira.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import eu.ibacz.o2sk.jiradata.WorkLogDTO;
import eu.ibacz.o2sk.webdriver.jira.BasicJiraOperation;
import eu.ibacz.o2sk.webdriver.jira.JiraClaimer;

import static eu.ibacz.o2sk.reporting.inputdata.ClaimConstants.PERIOD_FORMATTER_HOUR_AND_MINS;
import static eu.ibacz.o2sk.reporting.inputdata.ClaimConstants.SDF_DATETIME;

/**
 * @author jan.jamrich@ibacz.eu
 *
 */
public class JiraClaimerNoCommit implements JiraClaimer {
	
	private WebDriver driver;
	private BasicJiraOperation jiraHandler;
	
	public JiraClaimerNoCommit(WebDriver wd) {
		this.driver = wd;
		jiraHandler = new BasicJiraOperationImpl(wd);
	}

	@Override
	public void claimWorkLogOnCurrentTicket(WorkLogDTO workLog) {
		
		jiraHandler.openWorkLogDialog();
        
        WebElement timeSpent = driver.findElement( By.id( "log-work-time-logged" ) );
        WebElement dateStarted = driver.findElement( By.id( "log-work-date-logged-date-picker" ) );
        WebElement workDescription = driver.findElement( By.id( "log-work-dialog" ) ).findElement( By.id( "comment" ) );
        
        WebElement CancelBtn = driver.findElement( By.id( "log-work-cancel" ) );
        
        timeSpent.sendKeys(workLog.getTimeSpent().toString( PERIOD_FORMATTER_HOUR_AND_MINS ));        
        // need to rewrite existing default value
        dateStarted.sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END), SDF_DATETIME.format(workLog.getDateStarted()));       
        workDescription.sendKeys( workLog.getWorkDescription() );
        
        CancelBtn.click();
        
	}

	@Override
	public void createSubtaskOfCurrentTicket(String taskSummary) {
		driver.findElement(By.id("opsbar-operations_more")).click();
		driver.findElement(By.className("issueaction-create-subtask")).click();
		
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                // return d.getTitle().toLowerCase().startsWith("cheese!");
                return driver.findElement(By.id("summary")).isDisplayed();
            }
        });
		
		// create subtask with summary given by Claim
		driver.findElement(By.id("summary")).sendKeys(taskSummary);
		// no commit: so no submit form
		// driver.findElement(By.id("create-issue-submit")).click();
		driver.findElement(By.linkText("Cancel")).click();
		
		driver.switchTo().alert().accept();
	}
}
