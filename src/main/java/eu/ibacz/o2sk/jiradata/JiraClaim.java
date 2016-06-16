/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2013 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * =========================================================================== */
package eu.ibacz.o2sk.jiradata;

import eu.ibacz.o2sk.webdriver.JiraHandler;

/**
 * @author jjamrich
 *
 */
public abstract class JiraClaim {
    
    private WorkLogDTO worklog;
    private String jiraTicketId;
    private String summary;
    private String description;
    
    
    /**
     * @param id Jira ticket ID
     * @param worklog Time spent working on ticket
     * @param summary Jira ticket summary field
     * @param description Jira ticket description field
     */
    public JiraClaim( String id, String summary, String description, WorkLogDTO worklog ) {
        super();
        this.worklog = worklog;
        this.jiraTicketId = id;
        this.summary = summary;
        this.description = description;
    }
    
    public abstract void processClaim(JiraHandler handler);
    
    public JiraClaim getCopy() {
    	JiraClaim jiraClaim = null;
    	try {
			jiraClaim = (JiraClaim) this.clone();
			return jiraClaim;
			
		} catch (CloneNotSupportedException e) {
			System.err.println("There is no clone() suported on " + this.getClass() + ", making copy by myself.");
			return new JiraClaimSP(this.jiraTicketId, this.summary, this.description, this.worklog);
		}
    }
    
    /**
     * @return the summary
     */
    public String getSummary()
    {
        return summary;
    }
    /**
     * @param summary the summary to set
     */
    public void setSummary( String summary )
    {
        this.summary = summary;
    }
    /**
     * @return the description
     */
    public String getDescription()
    {
        return description;
    }
    /**
     * @param description the description to set
     */
    public void setDescription( String description )
    {
        this.description = description;
    }

    public WorkLogDTO getWorklog() {
		return worklog;
	}

	public void setWorklog(WorkLogDTO worklog) {
		this.worklog = worklog;
	}

	public String getJiraTicketId() {
		return jiraTicketId;
	}

	public void setJiraTicketId(String jiraTicketId) {
		this.jiraTicketId = jiraTicketId;
	}
    
}
