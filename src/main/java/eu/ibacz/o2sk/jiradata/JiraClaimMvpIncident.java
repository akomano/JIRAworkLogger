/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2013 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * =========================================================================== */
package eu.ibacz.o2sk.jiradata;

import eu.ibacz.o2sk.webdriver.jira.BasicJiraOperation;
import eu.ibacz.o2sk.webdriver.jira.JiraClaimer;

/**
 * @author jan.jamrich@ibacz.eu
 *
 */
public class JiraClaimMvpIncident extends JiraClaim {

	public JiraClaimMvpIncident(String id, String summary, WorkLogDTO worklog) {
		/**
		 * Usually @id should be known, since incident should be closed with MVP/Incident reference
		 * @summary should be composed from problem description and incident ID suffix
		 */
		super(id, summary, null, worklog);
	}

	@Override
	public void processClaim(JiraClaimer handler, BasicJiraOperation jiraHandler) {
		System.out.println("processClaim: JiraClaimMvpIncident: " + this.toString());
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("[id: ").append(getJiraTicketId())
			.append(", summary: ").append(getSummary()).append(" ")
			.append(getWorklog())
			.append("]");
		
		return sb.toString();
	}
}
