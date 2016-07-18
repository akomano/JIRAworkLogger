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

import eu.ibacz.o2sk.webdriver.jira.BasicJiraOperation;
import eu.ibacz.o2sk.webdriver.jira.JiraClaimer;

/**
 * @author jan.jamrich@ibacz.eu
 *
 */
public class JiraClaimMvpTask extends JiraClaim {

	public JiraClaimMvpTask(String id, String summary, String description, WorkLogDTO worklog) {
		/**
		 * Usually @id should be known, since incident should be closed with MVP/Incident reference
		 * @summary should be composed from problem description and incident ID suffix
		 */
		super(id, summary, description, worklog);
	}

	@Override
	public void processClaim(JiraClaimer handler, BasicJiraOperation jiraHandler) {
		System.out.println("processClaim: JiraClaimMvpTask: " + this.toString());
		
		jiraHandler.open(getJiraProp().getJiraURLbrowseTicket() + "/" + getJiraTicketId());
		
		handler.claimWorkLogOnCurrentTicket(getWorklog());
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
