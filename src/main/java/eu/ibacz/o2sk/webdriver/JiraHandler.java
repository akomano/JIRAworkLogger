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
public interface JiraHandler {
	public boolean isLoggedIn();
    
    public void logOff();
    
    public void open(String url);
    
    public void logIn() throws IOException;
    
    public void claimWorkLogOnCurrentTicket(WorkLogDTO effort);
    
    public void openWorkLogDialog();
    
    public void closeBrowser();
}
