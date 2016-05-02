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
 * @author jjamrich
 *
 */
public class Effort {

    private Date date;
    private Period timeSpent;
    private String workDescription;
    
    public Effort(Date dateWhen, Period howMuch, String what) {
        setDate( dateWhen );
        setTimeSpent( howMuch );
        setWorkDescription( what );
    }

    /**
     * @return the date
     */
    public Date getDate()
    {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate( Date date )
    {
        this.date = date;
    }

    /**
     * @return the workDescription
     */
    public String getWorkDescription()
    {
        return workDescription;
    }

    /**
     * @param workDescription the workDescription to set
     */
    public void setWorkDescription( String workDescription )
    {
        this.workDescription = workDescription;
    }

    /**
     * @return the timeSpent
     */
    public Period getTimeSpent()
    {
        return timeSpent;
    }

    /**
     * @param timeSpent the timeSpent to set
     */
    public void setTimeSpent( Period timeSpent )
    {
        this.timeSpent = timeSpent;
    }

}
