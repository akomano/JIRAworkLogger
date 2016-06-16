/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2013 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * =========================================================================== */
package eu.ibacz.o2sk.jiradata;

import eu.ibacz.o2sk.webdriver.JiraHandler;

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
	public void processClaim(JiraHandler handler) {
		System.out.println("processClaim: JiraClaimMvpIncident");
	}    	
}
