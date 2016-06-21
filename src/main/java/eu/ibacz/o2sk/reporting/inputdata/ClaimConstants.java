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
	public static final SimpleDateFormat SDF = new SimpleDateFormat(DATE_FORMAT);
	public static final PeriodFormatter periodFormatter = new PeriodFormatterBuilder()
            .appendDays().appendSuffix("d ")
            .appendHours().appendSuffix("h ")
            .appendMinutes().appendSuffix("m")
            .toFormatter();
}
