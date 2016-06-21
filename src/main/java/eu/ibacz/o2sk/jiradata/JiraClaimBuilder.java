/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2013 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * =========================================================================== */
package eu.ibacz.o2sk.jiradata;

import static eu.ibacz.o2sk.jiradata.JiraData.getJiraProp;
import static eu.ibacz.o2sk.reporting.inputdata.ClaimConstants.SDF;
import static eu.ibacz.o2sk.reporting.inputdata.ClaimConstants.periodFormatter;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.joda.time.Period;

/**
 * @author jjamrich
 *
 */
public class JiraClaimBuilder {
	
	private Date date;
	private String[] workLine;
	
	public JiraClaimBuilder addDate(String date) throws ParseException {
		this.date = SDF.parse(date);
		return this;
	}
	
	public JiraClaimBuilder addWork(String[] workLine) {
			
		if ( workLine != null ) {
			this.workLine = workLine;
		}
		
		return this;
	}
		
	public JiraClaim build() throws IOException {
		System.out.println("claim for date: " + date);
		return getClaimFromWorkLine(workLine);
	}
	
	private JiraClaim getClaimFromWorkLine(String[] workLine) throws IOException {
		// TODO JJa: implements logic on the top of WorkLine
		/*
		 * if (workLine.legth == 3) then
		 * if (workLine[0].startsWith("MVP-") and workLine[0].containsIgnoreCase("incident") then ...
		 */
		JiraClaim c = null;
		// time spent on task is always second field
		// TODO JJa: implementovat osetrenie chyby pri parsovani (IlleagalArgumentException)
		Period spentTime = periodFormatter.parsePeriod(workLine[1].trim());
		
		if (workLine[0].startsWith("SP")) {
			c = new JiraClaimSP(getJiraProp().getParentTicketIdSP(), workLine[0], null, 
					new WorkLogDTO(date, spentTime, "Prace na SP"));
		} else if (workLine[0].startsWith("Incident")) {
			c = new JiraClaimMvpIncident(null, workLine[0], 
					new WorkLogDTO(date, spentTime, "Reseni incidentu"));
		} else if (workLine[0].startsWith("MVP-")) {
			c = new JiraClaimMvpTask(workLine[0], null, workLine.length > 2 ? workLine[2] : "", 
					new WorkLogDTO(date, spentTime, ""));
		} else {
			c = new JiraClaimRezie(getJiraProp().getParentTicketIdRezie(), 
					new WorkLogDTO(date, spentTime, "Prace na vykazovacim nastroji"));
		}
		
		return c;
	}
	
}
