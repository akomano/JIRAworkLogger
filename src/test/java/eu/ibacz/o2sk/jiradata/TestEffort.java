package eu.ibacz.o2sk.jiradata;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestEffort
{
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm");
    PeriodFormatter periodFormatter;
    Date when;
    Period howMuch;
    
    @Before
    public void setUp()
        throws Exception
    {
        try {
            when = sdf.parse( "2016.04.29 17:00" );
        } catch (ParseException e) {
            // 
        }
        
        howMuch = new Period( 10*60*1000, PeriodType.minutes() );
        
        periodFormatter = new PeriodFormatterBuilder()
            .appendDays().appendSuffix("d ")
            .appendHours().appendSuffix("h ")
            .appendMinutes().appendSuffix("m")
            .toFormatter();
    }

    @After
    public void tearDown()
        throws Exception
    {
    }

    @Test
    public void test()
    {
        assertEquals( "10m", howMuch.toString(periodFormatter) );
        // assertEquals( "10m", periodFormatter.print( howMuch ) );
    }

}
