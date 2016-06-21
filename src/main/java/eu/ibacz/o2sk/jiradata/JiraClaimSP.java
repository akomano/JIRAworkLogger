/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2013 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * =========================================================================== */
package eu.ibacz.o2sk.jiradata;

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
	public void processClaim(JiraClaimer handler) {
		System.out.println("processClaim: JiraClaimSP: " + this.toString());
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
