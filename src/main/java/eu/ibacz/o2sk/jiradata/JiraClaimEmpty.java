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
public class JiraClaimEmpty extends JiraClaim {

	public JiraClaimEmpty() {
		super(null, null, null, new WorkLogDTO(null, null, null));
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see eu.ibacz.o2sk.jiradata.JiraClaim#processClaim(eu.ibacz.o2sk.webdriver.jira.JiraClaimer, eu.ibacz.o2sk.webdriver.jira.BasicJiraOperation)
	 */
	@Override
	public void processClaim(JiraClaimer handler, BasicJiraOperation jiraHandler) {
		System.out.println("processClaim: JiraClaimEmpty: " + this.toString());
	}

}
