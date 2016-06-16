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

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.joda.time.Period;
import org.joda.time.PeriodType;

/**
 * @author jjamrich
 *
 */
public class JiraClaimBuilder {
	
	private JiraClaim claim;
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
		
		if (workLine.length > 1) {
			claim = getClaimFromWorkLine(workLine);
		}
		
		JiraClaim temp = claim.getCopy();
		
		claim = null;
		
		return temp;
	}
	
	private JiraClaim getClaimFromWorkLine(String[] workLine) throws IOException {
		// TODO JJa: implements logic on the top of WorkLine
		JiraClaim c = new JiraClaimRezie(getJiraProp().getParentTicketIdRezie(), 
				new WorkLogDTO(date, new Period( 10*60*1000, PeriodType.minutes() ), "Prace na vykazovacim nastroji"));
		return c;
	}
	
}
