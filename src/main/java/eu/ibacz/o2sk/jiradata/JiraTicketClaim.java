/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2013 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * =========================================================================== */
package eu.ibacz.o2sk.jiradata;

import java.util.List;

/**
 * @author jjamrich
 *
 */
public class JiraTicketClaim {
    
    private List<Effort> effort;
    private String id;
    private String summary;
    private String description;
    
    
    
    public JiraTicketClaim( List<Effort> e, String id, String summary, String description ) {
        super();
        if ( e != null && !e.isEmpty() ) effort.addAll( e );
        this.id = id;
        this.summary = summary;
        this.description = description;
    }
    

    /**
     * @return the id
     */
    public String getId()
    {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId( String id )
    {
        this.id = id;
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


    /**
     * @return the effort
     */
    public List<Effort> getEffort()
    {
        return effort;
    }


    /**
     * @param effort the effort to set
     */
    public void addEffort( Effort e ) {
        this.effort.add( e );
    }
    

}
