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
import static eu.ibacz.o2sk.reporting.inputdata.ClaimConstants.PERIOD_FORMATTER_HOUR_AND_MINS;
import static eu.ibacz.o2sk.reporting.inputdata.ClaimConstants.PERIOD_FORMATTER_HOURS;
import static eu.ibacz.o2sk.reporting.inputdata.ClaimConstants.PERIOD_FORMATTER_MINS;

import java.io.IOException;
import java.util.Date;

import org.joda.time.Period;

/**
 * @author jan.jamrich@ibacz.eu
 *
 */
public class JiraClaimTypeFactory {
	
	public JiraClaim getClaimFromWorkLine(String[] workLine, Date date) throws IOException {
		// TODO JJa: implements logic on the top of WorkLine
		/*
		 * if (workLine.legth == 3) then
		 * if (workLine[0].startsWith("MVP-") and workLine[0].containsIgnoreCase("incident") then ...
		 */
		JiraClaim c = null;
		// time spent on task is always second field
		// TODO JJa: implementovat osetrenie chyby pri parsovani (IlleagalArgumentException)
				
		String periodString = trim(workLine[1]);
		Period spentTime = null;
		
		spentTime = parsePeriodFromString(periodString);
				
		if (trim(workLine[0]).startsWith("SP")) {		
			c = new JiraClaimSP(getJiraProp().getParentTicketIdSP(), trim(workLine[0]), null, 
					new WorkLogDTO(date, spentTime, workLine.length > 2 ? trim(workLine[2]) : "Praca na SP"));
		} else if (trim(workLine[0]).startsWith("Incident")) {
			// expects implements logic for search for related MVP
			c = new JiraClaimMvpIncident(null, trim(workLine[0]), 
					new WorkLogDTO(date, spentTime, workLine.length > 2 ? trim(workLine[2]) : "Praca na rieseni/analyze incidentu"));
		} else if (trim(workLine[0]).startsWith("MVP-")) {
			// there should be more investigation, since MVP is destination type for more tasks (including Incidents, Rezie...)
			
			// first of all: if there is description, like "MVP-xyz: Task X", then parse just string before ":"
			String id = workLine[0].split(":")[0];
			c = new JiraClaimMvpTask(id, null, null, 
					new WorkLogDTO(date, spentTime, workLine.length > 2 ? trim(workLine[2]) : ""));
		} else if (trim(workLine[0]).toLowerCase().startsWith("rezie")) {
			c = new JiraClaimRezie(getJiraProp().getParentTicketIdRezie(), 
					new WorkLogDTO(date, spentTime, workLine.length > 2 ? trim(workLine[2]) : ""));
		} else if (trim(workLine[0]).toLowerCase().startsWith("dev")) {
			String id = workLine[0].split(":")[0];
			c = new JiraClaimDev(id, null, null, new WorkLogDTO(date, spentTime, workLine.length > 2 ? trim(workLine[2]) : ""));
		} else {
			return new JiraClaimEmpty();
		}
		
		return c;
	}
	
	private String trim(String s) {
		if (s == null) {
			return s;
		}
		return s.trim();
	}
	
	public Period parsePeriodFromString(String spentTimeString) {
		Period spentTime = null;
		
		if (spentTimeString.contains(":")) {
			// time spent in format hh:mm:ss (or some variant)
			String[] spentTimeStringFragments = spentTimeString.split(":");
			int hour = Integer.parseInt(spentTimeStringFragments[0]);
			int min = Integer.parseInt(spentTimeStringFragments[1]);
			int sec = Integer.parseInt(spentTimeStringFragments[2]);
			 
			spentTime = new Period(hour, min, sec, 0);
			
		} else if (spentTimeString.contains("h") && spentTimeString.contains("m")) {
			spentTime = PERIOD_FORMATTER_HOUR_AND_MINS.parsePeriod(spentTimeString);
		} else if (spentTimeString.contains("h")) {
			spentTime = PERIOD_FORMATTER_HOURS.parsePeriod(spentTimeString);
		} else {
			spentTime = PERIOD_FORMATTER_MINS.parsePeriod(spentTimeString);
		}
		
		return spentTime;
	}
}
