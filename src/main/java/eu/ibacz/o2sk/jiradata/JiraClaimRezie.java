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
	public void processClaim(JiraClaimer handler) {
		System.out.println("processClaim: JiraClaimRezie: " + this.toString());
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
