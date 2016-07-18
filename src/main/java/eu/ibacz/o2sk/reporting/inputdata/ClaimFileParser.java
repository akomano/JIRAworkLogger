package eu.ibacz.o2sk.reporting.inputdata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import eu.ibacz.o2sk.jiradata.JiraClaim;
import eu.ibacz.o2sk.jiradata.JiraClaimBuilder;

public class ClaimFileParser
{

    private File file;
    
    public ClaimFileParser(File file2parse) {
        file = file2parse;
    }
    
    public List<JiraClaim> parseEffort(JiraClaimBuilder builder) throws IOException, ParseException {
        
        List<JiraClaim> results = new ArrayList<JiraClaim>();
        
        String lastDate = null;
        List<String> fileLines = null;
        
        int currentLine = 0;
        
        try
        {
            fileLines = getLines( file );
            for ( String line :  fileLines) {
            	
            	++currentLine;
            	
            	if ( line.trim().startsWith( "#" )) {                    
                    // just ignore it
                } else if ("".equals( line.trim() )) {
                    /* new line start section for new day */ 
                    // so, reset date 
                    lastDate = null;
                } else {                	                
                	// process not empty line
                	
                    String[] fragments = line.split( ";" );                        
                    
                    /* expect split returned 
                	 * String array of 1..n items
                	 * because of check for not empty/blank String at input 
                	 */
                    if (fragments.length > 1) {
                    	// at least one ";" >> consider it day work claim
                    	builder.addWork(fragments);
                        // and build and store result in cache
                        results.add( builder.build() );
                    } else {
                        // no ";" >> split returns line >> threat it as date
                    	// if previous line was empty
                    	if (lastDate == null) {
                    		// element containing date should contains comment (starting by "#"), as well as empty space
                    		lastDate = fragments[0].split("#")[0].trim();
                            try {
								builder.addDate(lastDate);
							} catch (ParseException e) {								
								e.printStackTrace();
								throw new ParseException("Error while parsing file for date from string: " 
										+ lastDate, currentLine);
							}
                    	}                    	
                    }                    
                }              
            }
        }
        catch ( FileNotFoundException fnfe )
        {
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
            	lines.add( line );                                
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
    
    /*
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
    */
    
    /*
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
    */
    

}
