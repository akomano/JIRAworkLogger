package eu.ibacz.o2sk.reporting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.Period;

import eu.ibacz.o2sk.jiradata.Effort;
import eu.ibacz.o2sk.jiradata.JiraTicketClaim;

import static eu.ibacz.o2sk.reporting.ClaimConstants.SDF;

public class ClaimFileParser
{

    private File file;
    
    public ClaimFileParser(File file2parse) {
        file = file2parse;
    }
    
    public List<JiraTicketClaim> parseEffort() throws IOException {
        
        List<JiraTicketClaim> results = new ArrayList<JiraTicketClaim>();
        
        Date lastDate = null;
        List<String> fileLines = null;
        LineObject claimLine = null;
        Effort e = null;
        JiraTicketClaim jiraTicketClaim = null;
        try
        {
            fileLines = getLines( file );
            for ( String line :  fileLines) {
                if ("".equals( line.trim() )) {
                    // new line start section for new day 
                    lastDate = null;
                } else {
                	try {
						claimLine = parseLine(line);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
                }                	
                
                if ( (claimLine) instanceof ClaimDate ) {
                    lastDate = claimLine.getEffortDate();
                } else if ( claimLine instanceof Claim ) {
                    e = new Effort( lastDate, claimLine.getSpentTime(), claimLine.getWorkDescription() );
                    
                    if (claimLine.getTicketId()!=null && claimLine.getTicketId().substring( 0, 3 ).equalsIgnoreCase( "MVP" )) {
                        // we have MVP ticked parsed
                        List<Effort> l = new ArrayList<Effort>();
                        l.add( e );                    
                        
                        // take MVP ID and effort
                        jiraTicketClaim = new JiraTicketClaim(l, claimLine.ticketId!=null ? claimLine.getTicketId() : null, null, claimLine.getWorkDescription() );
                        results.add( jiraTicketClaim );
                        
                    } else if (claimLine.getTicketId()!=null && claimLine.getTicketId().substring( 0, 2 ).equalsIgnoreCase( "SP" )) {
                        List<Effort> l = new ArrayList<Effort>();
                        l.add( e );                    
                        
                        // tak SP ID and related MVP will be found later
                        jiraTicketClaim = new JiraTicketClaim(l, null, claimLine.getTicketId(), claimLine.getWorkDescription() );
                        results.add( jiraTicketClaim );
                    }                    
                }
            }
        }
        catch ( FileNotFoundException fnfe )
        {
            // TODO Auto-generated catch block
            fnfe.printStackTrace();
        }
        
        
        return results;
    }
    
    private List<String> getLines(File file) throws IOException {
        
        List<String> lines = new ArrayList<String>();
        
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        
        String line = null;
        try
        {
            while ((line = br.readLine()) != null) {
                /* take just regular lines, which means empty lines as well, 
                 * since empty line is day delimiter
                 */
                if ( !line.trim().startsWith( "#" )) {                    
                    lines.add( line );
                }                
            }
        }
        catch ( IOException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            br.close();
        }
     
        br.close();
        
        return lines;
    }
    
    private LineObject parseLine(String line) throws ParseException {
        
        if (line != null && !"".equals(line.trim())) {
            // make sure we don't process empty line
            
            String[] fragments = line.split( ";" );
            
            
            // at least one ";"
            if (fragments.length > 1) {
                // at least 2 items (consider task ID and spent time
                return new Claim( fragments[0], new Period(fragments[1]), null);
            } else if (fragments.length > 2) {
            	return new Claim( fragments[0], new Period(fragments[1]), fragments[2]);
            } else {
                // no ";", so consider it is date and parse it
                return new ClaimDate( SDF.parse(fragments[0]) );
            }
        }
        return null;
    }
    
    private class LineObject {
        private String ticketId;
        private Period spentTime;
        private String workDesctription;
        private Date effortDate;
        
        public LineObject(String ticket, Date date, Period timeSpent, String workDesc) {
            this.ticketId = ticket;
            this.effortDate = date;
            this.spentTime = timeSpent;
            this.workDesctription = workDesc;
        }
        
        public String getTicketId() { return ticketId; };
        
        public Period getSpentTime() { return spentTime; };
        public String getWorkDescription() { return workDesctription; };
        public Date getEffortDate() { return effortDate; };
    }
    
    private class Claim extends LineObject {
        
        public Claim (String ticket, Period spentTime, String workDescription) {
            super( ticket, null, spentTime, workDescription);
        }
    }
    
    private class ClaimDate extends LineObject {
        
        public ClaimDate(Date date) {
           super( null, date, null, null );
        }
        
    }
    
    

}
