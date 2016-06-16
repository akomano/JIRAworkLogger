/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2013 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * =========================================================================== */
package eu.ibacz.o2sk.jiradata;

import java.util.Date;

import org.joda.time.Period;

/**
 * @author jan.jamrich@ibacz.eu
 *
 */
public class WorkLogDTO {
	
	private Date dateStarted;
    private Period timeSpent;
    private String workDescription;
    
    public WorkLogDTO(Date dateStarted, Period timeSpent, String workDescription) {
    	this.dateStarted = dateStarted;
    	this.timeSpent = timeSpent;
    	this.workDescription = workDescription;
    }

	public Date getDateStarted() {
		return dateStarted;
	}

	public void setDateStarted(Date dateStarted) {
		this.dateStarted = dateStarted;
	}

	public Period getTimeSpent() {
		return timeSpent;
	}

	public void setTimeSpent(Period timeSpent) {
		this.timeSpent = timeSpent;
	}

	public String getWorkDescription() {
		return workDescription;
	}

	public void setWorkDescription(String workDescription) {
		this.workDescription = workDescription;
	}
}