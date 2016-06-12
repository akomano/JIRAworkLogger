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

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.Period;
import org.joda.time.PeriodType;

import eu.ibacz.o2sk.jiradata.Effort;
import eu.ibacz.o2sk.webdriver.JiraHandler;


/**
 * @author jjamrich
 *
 */
public class Main {
    
    public static void main( String[] args ) throws IOException {
        
        System.out.println("Oteviram JIRA.");
        JiraHandler jira = new JiraHandler();
        
        if (! jira.isLoggedIn()) {
            System.out.println("Prihlasuju se jako " + getJiraProp().getLogin());
            jira.logIn();
        }
        
        // Claiming logic
        
        System.out.println("Oteviram JIRA ticket " + getJiraProp().getParentTicketIdRezie());
        jira.open(getJiraProp().getJiraURLbrowseTicket() + getJiraProp().getParentTicketIdRezie());
        
        System.out.println("Zkousim vyclaimovat fake Rezii");
        Date when = new Date(System.currentTimeMillis()); // sdf.parse( "2016.04.29 17:00" );
        Period howMuch = new Period( 10*60*1000, PeriodType.minutes() );
        jira.claimRezie(new Effort(when, howMuch, "Testovaci popis prace" ));
        
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
