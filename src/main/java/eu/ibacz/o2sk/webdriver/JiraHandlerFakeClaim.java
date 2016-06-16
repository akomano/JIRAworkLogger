/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2013 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * =========================================================================== */
package eu.ibacz.o2sk.webdriver;

import java.io.IOException;

import eu.ibacz.o2sk.jiradata.WorkLogDTO;

/**
 * @author jan.jamrich@ibacz.eu
 *
 */
public class JiraHandlerFakeClaim implements JiraHandler {
	
	private JiraCoreHandler jiraCore;
	
	public JiraHandlerFakeClaim () throws IOException {
		jiraCore = new JiraCoreHandler();
	}

	@Override
	public boolean isLoggedIn() {
		return jiraCore.isLoggedIn();
	}

	@Override
	public void logOff() {
		jiraCore.logOff();
	}
	
	@Override
	public void logIn() throws IOException {
		jiraCore.logIn();
	}

	@Override
	public void open(String url) {
		jiraCore.open(url);
	}

	@Override
	public void claimWorkLogOnCurrentTicket(WorkLogDTO effort) {
		jiraCore.claimWorkLogOnCurrentTicket(effort, false);
	}

	@Override
	public void openWorkLogDialog() {
		jiraCore.openWorkLogDialog();
	}

	@Override
	public void closeBrowser() {
		jiraCore.closeBrowser();
	}

}
