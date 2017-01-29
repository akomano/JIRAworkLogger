/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2013 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * =========================================================================== */
package eu.ibacz.o2sk.app;

import static eu.ibacz.o2sk.jiradata.JiraData.getJiraProp;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import eu.ibacz.o2sk.jiradata.JiraClaim;
import eu.ibacz.o2sk.jiradata.JiraClaimBuilder;
import eu.ibacz.o2sk.reporting.inputdata.ClaimFileParser;
import eu.ibacz.o2sk.webdriver.WebDriverFactory;
import eu.ibacz.o2sk.webdriver.jira.BasicJiraOperation;
import eu.ibacz.o2sk.webdriver.jira.JiraClaimer;
import eu.ibacz.o2sk.webdriver.jira.impl.BasicJiraOperationImpl;
import eu.ibacz.o2sk.webdriver.jira.impl.JiraClaimerNoCommit;
import eu.ibacz.o2sk.webdriver.jira.impl.JiraClaimerWithCommit;


/**
 * @author jan.jamrich@ibacz.eu
 *
 */
public class Main {
	
	private static final Logger log = LogManager.getLogger(Main.class);
    
    public static void main( String[] args ) throws IOException {
    	
    	log.info("Start JIRA Logger application");
    	    	
    	if (args.length == 0) {
    		log.warn("Expecting file name param containing claims");    		    		
    	}

    	BasicJiraOperation jira = null;
    	
        if (args.length > 0) {
        	log.info("Use following file as data source: " + args[0]);
        	ClaimFileParser fileParser = new ClaimFileParser(new File(args[0]));
        	List<JiraClaim> denniClaimy;
			try {
				// parsing assert input data validation 
				denniClaimy = fileParser.parseEffort(new JiraClaimBuilder());
				
				// WebDriver d = WebDriverFactory.getFirefoxDriver();
		    	WebDriver d = WebDriverFactory.getChromeDriver();
				jira = new BasicJiraOperationImpl(d);
				log.info("Opening JIRA");		        
		        jira.open(getJiraProp().getJiraURL());
		        
		        if (! jira.isLoggedIn()) {
		            log.info("Sign in as " + getJiraProp().getLogin());
		            jira.logIn();
		        }
				
				Iterator<JiraClaim> it = denniClaimy.iterator();
	        	while (it.hasNext()) {
	        		it.next().processClaim(new JiraClaimerWithCommit(d), new BasicJiraOperationImpl(d));
	        	}
	        	
	        	if (jira.isLoggedIn()) {
	                log.info("Log out");
	                jira.logOff();
	            }
	            	            
			} catch (ParseException e) {
				log.error("Error while parsing source file. " + e);
			} finally {				
				if (jira != null) {
					log.info("Closing browser.");
					jira.closeBrowser();
				}
			}
    	} else {
    		log.info("No params with input data file given, nothing to do");
    	}

    }

}
