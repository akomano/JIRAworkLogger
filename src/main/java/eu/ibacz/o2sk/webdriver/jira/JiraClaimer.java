/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2013 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * =========================================================================== */
package eu.ibacz.o2sk.webdriver.jira;

import eu.ibacz.o2sk.jiradata.WorkLogDTO;

/**
 * @author jan.jamrich@ibacz.eu
 *
 */
public interface JiraClaimer {
	
	public void claimWorkLogOnCurrentTicket(WorkLogDTO workLog);
	
	public void createSubtaskOfCurrentTicket(String taskSummary);

}