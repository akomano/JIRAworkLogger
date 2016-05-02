package eu.ibacz.o2sk.jiradata;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestJiraUserData
{
    JiraData jud;

    @Before
    public void setUp()
        throws Exception
    {
        jud = JiraData.getJiraProp();
    }

    @After
    public void tearDown()
        throws Exception
    {
    }

    @Test
    public void test()
    {
        assertNotSame( "", jud.getLogin()); // assert jira.login not empty
        assertNotNull( jud.getLogin());
        
        assertNotNull( jud.getJiraURL() );
        assertNotNull( jud.getJiraURLbrowseTicket() );
        assertNotNull( jud.getParentTicketIdRezie() );
        assertNotNull( jud.getParentTicketIdSP() );
    }

}
