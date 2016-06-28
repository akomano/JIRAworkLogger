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

import eu.ibacz.o2sk.jiradata.WorkLogDTO;
import eu.ibacz.o2sk.webdriver.jira.BasicJiraOperation;
import eu.ibacz.o2sk.webdriver.jira.JiraClaimer;

import static eu.ibacz.o2sk.reporting.inputdata.ClaimConstants.periodFormatter;
import static eu.ibacz.o2sk.reporting.inputdata.ClaimConstants.SDF;

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
        
        timeSpent.sendKeys(workLog.getTimeSpent().toString( periodFormatter ));        
        // need to rewrite existing default value
        dateStarted.sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END), SDF.format(workLog.getDateStarted()));       
        workDescription.sendKeys( workLog.getWorkDescription() );
        
        CancelBtn.click();
        
	}
}
