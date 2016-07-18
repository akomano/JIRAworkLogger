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
 * 
 * @author jan.jamrich@ibacz.eu
 *
 */
public class JiraClaimRezie extends JiraClaim {
	
	public JiraClaimRezie( String id, WorkLogDTO workLog) {
		// since on Rezie is just time spent with description claim, no summary is provided
		super(id, null, null, workLog);
	}

	@Override
	public void processClaim(JiraClaimer handler, BasicJiraOperation jiraHandler) {
		System.out.println("processClaim: JiraClaimRezie: " + this.toString());
		
		jiraHandler.open(getJiraProp().getJiraURLbrowseTicket() + "/" + getJiraProp().getParentTicketIdRezie());
		
		handler.claimWorkLogOnCurrentTicket(getWorklog());
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("[id: ").append(getJiraTicketId())
			.append(", ")
			.append(getWorklog())
			.append("]");
		
		return sb.toString();
	}
}
