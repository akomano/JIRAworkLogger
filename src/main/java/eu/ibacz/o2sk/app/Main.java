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

import org.openqa.selenium.WebDriver;

import eu.ibacz.o2sk.jiradata.JiraClaim;
import eu.ibacz.o2sk.jiradata.JiraClaimBuilder;
import eu.ibacz.o2sk.reporting.inputdata.ClaimFileParser;
import eu.ibacz.o2sk.webdriver.WebDriverFactory;
import eu.ibacz.o2sk.webdriver.jira.BasicJiraOperation;
import eu.ibacz.o2sk.webdriver.jira.JiraClaimer;
import eu.ibacz.o2sk.webdriver.jira.impl.BasicJiraOperationImpl;
import eu.ibacz.o2sk.webdriver.jira.impl.JiraClaimerNoCommit;


/**
 * @author jan.jamrich@ibacz.eu
 *
 */
public class Main {
    
    public static void main( String[] args ) throws IOException {
    	
    	// WebDriver d = WebDriverFactory.getFirefoxDriver();
    	WebDriver d = WebDriverFactory.getChromeDriver();
    	
    	if (args.length == 0) {
    		System.out.println("Ocekavam parametr s cestou k souboru s vykazy. Jinak vyclaimuju pouze fake Rezii.");    		
    	}
        
        System.out.println("Oteviram JIRA.");
        BasicJiraOperation jira = new BasicJiraOperationImpl(d);
        
        jira.open(getJiraProp().getJiraURL());
        
        if (! jira.isLoggedIn()) {
            System.out.println("Prihlasuju se jako " + getJiraProp().getLogin());
            jira.logIn();
        }
        
        /*
        // Claiming logic

        System.out.println("Zkousim vyclaimovat Rezii");
        Date when = new Date(System.currentTimeMillis()); // sdf.parse( "2016.04.29 17:00" );
        Period howMuch = new Period( 10*60*1000, PeriodType.minutes() );
        JiraClaimer claimerDryRun = new JiraClaimerNoCommit(d);
        claimerDryRun.claimWorkLogOnCurrentTicket(new WorkLogDTO(when, howMuch, "Testovaci popis prace"));
        */
        
        JiraClaimer claimerDryRun = new JiraClaimerNoCommit(d);
        
        if (args.length > 0) {
        	System.out.println("Claimuju ze souboru " + args[0]);
        	ClaimFileParser fileParser = new ClaimFileParser(new File(args[0]));
        	List<JiraClaim> denniClaimy;
			try {
				denniClaimy = fileParser.parseEffort(new JiraClaimBuilder());
				Iterator<JiraClaim> it = denniClaimy.iterator();
	        	while (it.hasNext()) {
	        		it.next().processClaim(claimerDryRun);
	        	}
			} catch (ParseException e) {
				e.printStackTrace();
				System.err.println("Parsovanie vstupneho suboru sa nepodarilo.");
			}        	
    	}
        
        System.out.println("Oteviram JIRA ticket " + getJiraProp().getParentTicketIdSP());
        jira.open(getJiraProp().getJiraURLbrowseTicket() + getJiraProp().getParentTicketIdSP());
        
        if (jira.isLoggedIn()) {
            System.out.println("Odhlasuju se");
            jira.logOff();
        }
        
        System.out.println("Zaviram prohlizec");
        jira.closeBrowser();

    }

}
