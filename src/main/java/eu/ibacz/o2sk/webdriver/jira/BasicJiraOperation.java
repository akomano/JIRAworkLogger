/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2013 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * =========================================================================== */
package eu.ibacz.o2sk.webdriver.jira;

/**
 * @author jan.jamrich@ibacz.eu
 *
 */
public interface BasicJiraOperation {
	
	public void open(String url);
	public void closeBrowser();
	
	public void openWorkLogDialog();
	    
    public void logOff();    
    public void logIn();
    public boolean isLoggedIn();
    
}
