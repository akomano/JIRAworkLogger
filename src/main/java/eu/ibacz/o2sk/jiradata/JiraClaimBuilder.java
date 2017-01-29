/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2013 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * =========================================================================== */
package eu.ibacz.o2sk.jiradata;

import static eu.ibacz.o2sk.reporting.inputdata.ClaimConstants.SDF_DATE;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author jjamrich
 *
 */
public class JiraClaimBuilder {
	
	private Date date;
	private String[] workLine;
	private JiraClaimTypeFactory claimFactory = new JiraClaimTypeFactory();
	
	private static final Logger log = LogManager.getLogger(JiraClaimBuilder.class);
	
	public JiraClaimBuilder addDate(String date) throws ParseException {
		
		this.date = SDF_DATE.parse(date);
		return this;
	}
	
	public JiraClaimBuilder addWork(String[] workLine) {
			
		if ( workLine != null ) {
			this.workLine = workLine;
		}
		
		return this;
	}
		
	public JiraClaim build() throws IOException {
		log.info("Building claim for date: " + date);
		return claimFactory.getClaimFromWorkLine(workLine, date);
	}
		
}
