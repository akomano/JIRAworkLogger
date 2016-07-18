/**
 * 
 */
package eu.ibacz.o2sk.reporting.inputdata;

import java.text.SimpleDateFormat;

import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

/**
 * @author jjamrich
 *
 */
public class ClaimConstants {
	
	public static final String DATE_FORMAT = "dd.MM.yyyy";
	public static final String DATE_TIME_FORMAT = "dd.MM.yyyy hh:mm";
	public static final SimpleDateFormat SDF_DATE = new SimpleDateFormat(DATE_FORMAT);
	public static final SimpleDateFormat SDF_DATETIME = new SimpleDateFormat(DATE_TIME_FORMAT);
	public static final PeriodFormatter PERIOD_FORMATTER_HOUR_AND_MINS = new PeriodFormatterBuilder()            
            .appendHours().appendSuffix("h ")
            .appendMinutes().appendSuffix("m")
            .toFormatter();
	
	public static final PeriodFormatter PERIOD_FORMATTER_HOURS = new PeriodFormatterBuilder()            
            .appendHours().appendSuffix("h")            
            .toFormatter();
	
	public static final PeriodFormatter PERIOD_FORMATTER_MINS = new PeriodFormatterBuilder()            
            .appendMinutes().appendSuffix("m")
            .toFormatter();
}
