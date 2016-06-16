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
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.joda.time.Period;
import org.joda.time.PeriodType;

import eu.ibacz.o2sk.jiradata.JiraClaim;
import eu.ibacz.o2sk.jiradata.JiraClaimBuilder;
import eu.ibacz.o2sk.jiradata.WorkLogDTO;
import eu.ibacz.o2sk.reporting.inputdata.ClaimFileParser;
import eu.ibacz.o2sk.webdriver.JiraCoreHandler;
import eu.ibacz.o2sk.webdriver.JiraHandler;
import eu.ibacz.o2sk.webdriver.JiraHandlerFakeClaim;


/**
 * @author jan.jamrich@ibacz.eu
 *
 */
public class Main {
    
    public static void main( String[] args ) throws IOException {
    	
    	if (args == null || args.length < 2) {
    		System.out.println("Ocekavam parametr s cestou k souboru s vykazy. Jinak vyclaimuju pouze fake Rezii.");    		
    	}
        
        System.out.println("Oteviram JIRA.");
        JiraHandler jira = new JiraHandlerFakeClaim();
        
        if (! jira.isLoggedIn()) {
            System.out.println("Prihlasuju se jako " + getJiraProp().getLogin());
            jira.logIn();
        }
        
        // Claiming logic
        
        System.out.println("Oteviram JIRA ticket " + getJiraProp().getParentTicketIdRezie());
        jira.open(getJiraProp().getJiraURLbrowseTicket() + getJiraProp().getParentTicketIdRezie());
        
        System.out.println("Zkousim vyclaimovat fake Rezii");
        
        // This is deprecated way how to claim
        Date when = new Date(System.currentTimeMillis()); // sdf.parse( "2016.04.29 17:00" );
        Period howMuch = new Period( 10*60*1000, PeriodType.minutes() );
        jira.claimWorkLogOnCurrentTicket(new WorkLogDTO(when, howMuch, "Testovaci popis prace"));
        
        if (args != null && args.length > 1) {
        	System.out.println("Claimuju ze souboru " + args[1]);
        	ClaimFileParser fileParser = new ClaimFileParser(new File(args[1]));
        	List<JiraClaim> denniClaimy;
			try {
				denniClaimy = fileParser.parseEffort(new JiraClaimBuilder());
				Iterator<JiraClaim> it = denniClaimy.iterator();
	        	while (it.hasNext()) {
	        		it.next().processClaim(jira);
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
