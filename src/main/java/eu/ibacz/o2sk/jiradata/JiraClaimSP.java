/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2013 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * =========================================================================== */
package eu.ibacz.o2sk.jiradata;

import static eu.ibacz.o2sk.jiradata.JiraData.getJiraProp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.thoughtworks.selenium.webdriven.commands.WaitForCondition;

import eu.ibacz.o2sk.webdriver.jira.BasicJiraOperation;
import eu.ibacz.o2sk.webdriver.jira.JiraClaimer;

/**
 * @author jan.jamrich@ibacz.eu
 *
 */
public class JiraClaimSP extends JiraClaim {
    	
	public JiraClaimSP(String id, String summary, String description, WorkLogDTO worklog) {
		super(id, summary, description, worklog);		
	}

	@Override
	public void processClaim(JiraClaimer handler, BasicJiraOperation jiraHandler) {
		System.out.println("processClaim: JiraClaimSP: " + this.toString());
		// SP are claimed in subtask of MVP ticket valid for whole month
		jiraHandler.open(getJiraProp().getJiraURLbrowseTicket() + "/" + getJiraProp().getParentTicketIdSP());
		
		if (jiraHandler.getElementsByPartialLinkText(getSummary()).size()==0) {
			
			System.out.println("No subtask exists for " + getSummary());
			
			handler.createSubtaskOfCurrentTicket(getSummary());
			
			// and refresh page (to get newly created issue appear in the list)
			jiraHandler.open(getJiraProp().getJiraURLbrowseTicket() + "/" + getJiraProp().getParentTicketIdSP());
		}
		
		if (jiraHandler.getElementsByPartialLinkText(getSummary()).size() == 1) {
			System.out.println("Claim effort on " + getSummary());
			jiraHandler.clickOnLink(getSummary());
			// TODO JJa: add waiting for page load (subtask detail)
			handler.claimWorkLogOnCurrentTicket(getWorklog());
		} else {
			System.out.println("Still unable to claim effort on " + getSummary() + ". No subtask exists.");
		}
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("[id: ").append(getJiraTicketId())
			.append(", summary: ").append(getSummary())
			.append(", desc: ").append(getDescription()).append(" ")
			.append(getWorklog())
			.append("]");
		
		return sb.toString();
	}

}
