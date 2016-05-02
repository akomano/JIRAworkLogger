/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2013 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * =========================================================================== */
package eu.ibacz.o2sk.jiradata;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author jjamrich
 *
 */
public class JiraData {
    
    Properties properties = new Properties();
    
    private String login;
    private String password;
    
    private String jiraURL;    
    private String jiraURLbrowseTicket;
    
    private String parentTicketIdRezie;
    private String parentTicketIdSP;
    
    // Allow override properties file value by value from system property
    private String getProp(String prop) {
        return System.getProperty( prop, properties.getProperty( prop ));
    }
    
    private JiraData() throws IOException {
        InputStream is = this.getClass().getResource("jira.properties").openStream();
        properties.load( is );
        is.close();
        
        setLogin( getProp( "jira.login" ) );        
        setPassword( getProp( "jira.password" ) );
        
        setParentTicketIdRezie( getProp( "jira.ticket.id.rezie" ) );
        setParentTicketIdSP( getProp( "jira.ticket.id.sp" ) );
        
        // these properties are set by values from property file
        setJiraURL( properties.getProperty("jira.url.default") );
        setJiraURLbrowseTicket( properties.getProperty("jira.url.browse") );
        
    }
    
    public static JiraData getJiraProp() throws IOException {
        return new JiraData();
    }

    public String getLogin()
    {
        return login;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword( String password )
    {
        this.password = password;
    }
    /**
     * @return the jiraURL
     */
    public String getJiraURL()
    {
        return jiraURL;
    }

    /**
     * @param jiraURL the jiraURL to set
     */
    public void setJiraURL( String jiraURL )
    {
        this.jiraURL = jiraURL;
    }

    /**
     * @return the jiraURLbrowseTicket
     */
    public String getJiraURLbrowseTicket()
    {
        return jiraURLbrowseTicket;
    }

    /**
     * @param jiraURLbrowseTicket the jiraURLbrowseTicket to set
     */
    public void setJiraURLbrowseTicket( String jiraURLbrowseTicket )
    {
        this.jiraURLbrowseTicket = jiraURLbrowseTicket;
    }

    /**
     * @return the parentTicketIdRezie
     */
    public String getParentTicketIdRezie()
    {
        return parentTicketIdRezie;
    }

    /**
     * @param parentTicketIdRezie the parentTicketIdRezie to set
     */
    public void setParentTicketIdRezie( String parentTicketIdRezie )
    {
        this.parentTicketIdRezie = parentTicketIdRezie;
    }

    /**
     * @return the parentTicketIdSP
     */
    public String getParentTicketIdSP()
    {
        return parentTicketIdSP;
    }

    /**
     * @param parentTicketIdSP the parentTicketIdSP to set
     */
    public void setParentTicketIdSP( String parentTicketIdSP )
    {
        this.parentTicketIdSP = parentTicketIdSP;
    }
}
