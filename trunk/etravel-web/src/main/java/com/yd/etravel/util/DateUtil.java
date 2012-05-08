/**
 * 
 */
package com.yd.etravel.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * @author XPPRESP3
 * 
 */
public class DateUtil {

    public static final String SIMPLE_DATE_FORMAT = "dd/MM/yyyy";
    public static final String SIMPLE_DATE_TIME_FORMAT = "dd/MM/yyyy hh:mm aaa";

    /**
     * Returns number of days for a given year and month. Can be used to create
     * a "correct" calender GUI etc.
     * 
     * @param year
     * @param month
     *            0-11
     * @return number of days for given year and month, 1-31
     */
    public static int daysOfMonth(final int year, final int month) {
	final Calendar calendar = Calendar.getInstance();
	calendar.set(year, month, 1);
	calendar.add(Calendar.MONTH, 1);
	calendar.add(Calendar.DATE, -1);

	return calendar.get(Calendar.DATE);
    }

    public static Date convertToStore(final Date d) {
	final Date d2 = floor(d);
	final Calendar calendar = Calendar.getInstance();
	calendar.setTime(d2);
	calendar.add(Calendar.DATE, 1);
	return calendar.getTime();
    }

    public static Date convertFromStore(final Date d) {
	final Calendar calendar = Calendar.getInstance();
	calendar.setTime(d);
	calendar.add(Calendar.DATE, -1);

	return roof(calendar.getTime());
    }

    public static Date floor(final Date d) {
	final DateHolder dh = new DateHolder(d);

	final Calendar calendar = Calendar.getInstance();
	calendar.set(dh.getYear(), dh.getMonth(), dh.getDay(), 0, 0, 0);
	return calendar.getTime();
    }

    public static Date roof(final Date d) {
	final DateHolder dh = new DateHolder(d);

	final Calendar calendar = Calendar.getInstance();
	calendar.set(dh.getYear(), dh.getMonth(), dh.getDay(), 23, 59, 59);
	return calendar.getTime();
    }

    public static boolean before(final Date d1, final Date d2) {
	final DateHolder dh1 = new DateHolder(d1);
	final DateHolder dh2 = new DateHolder(d2);

	return dh1.getYear() < dh2.getYear()
		|| dh1.getYear() == dh2.getYear() && dh1.getMonth() < dh2
			.getMonth()
		|| dh1.getYear() == dh2.getYear()
			&& dh1.getMonth() == dh2.getMonth() && dh1.getDay() < dh2
			.getDay();
    }

    public static boolean equals(final Date d1, final Date d2) {
	final DateHolder dh1 = new DateHolder(d1);
	final DateHolder dh2 = new DateHolder(d2);

	return dh1.getYear() == dh2.getYear()
		&& dh1.getMonth() == dh2.getMonth()
		&& dh1.getDay() == dh2.getDay();
    }

    public static boolean after(final Date d1, final Date d2) {
	return !before(d1, d2) && !equals(d1, d2);
    }

    public static boolean adjacent(final Date d1, final Date d2) {
	final DateHolder dh1 = new DateHolder(d1);
	final DateHolder dh2 = new DateHolder(d2);

	return dh1.getYear() == dh2.getYear()
		&& dh1.getMonth() == dh2.getMonth() && dh1.getDay() == dh2
		.getDay() - 1
		|| dh1.getYear() == dh2.getYear()
			&& dh1.getMonth() == dh2.getMonth() - 1
			&& dh1.getDay() == daysOfMonth(dh1.getYear(),
				dh1.getMonth()) && dh2.getDay() == 1
		|| dh1.getYear() == dh2.getYear() - 1
			&& dh1.getMonth() == 11
			&& dh2.getMonth() == 0
			&& dh1.getDay() == daysOfMonth(dh1.getYear(),
				dh1.getMonth()) && dh2.getDay() == 1;

    }

    private static class DateHolder {
	protected Date date = null;
	protected int year;
	protected int month;
	protected int day;

	public DateHolder(final Date date) {
	    this.date = new Date(date.getTime());
	    initialize();
	}

	private void initialize() {
	    final Calendar calendar = Calendar.getInstance();
	    calendar.setTime(this.date);
	    this.year = calendar.get(Calendar.YEAR);
	    this.month = calendar.get(Calendar.MONTH);
	    this.day = calendar.get(Calendar.DATE);
	}

	public int getYear() {
	    return this.year;
	}

	public int getMonth() {
	    return this.month;
	}

	public int getDay() {
	    return this.day;
	}
    }

    public static int getDay(final Date d) {
	final DateHolder dh1 = new DateHolder(d);
	return dh1.getDay();
    }

    public static int getYear(final Date d) {
	final DateHolder dh1 = new DateHolder(d);
	return dh1.getYear();
    }

    public static int getMonth(final Date d) {
	final DateHolder dh1 = new DateHolder(d);
	return dh1.getMonth();
    }

    /**
     * Parses a localized date string into a date object
     * 
     * @param s
     *            the string to parse
     * @return a date object or null if the string was not a parsable date
     */
    public static Date parseDate(final String s, final Locale locale) {
	try {
	    // Sanity check
	    if (s == null) {
		return null;
	    }

	    // Go back and forth to eliminate the datefunctions in all systems
	    // that accepts e.g. 2002-01-32 as 2002-02-01 etc
	    final Date d = DateFormat.getDateInstance(DateFormat.SHORT, locale)
		    .parse(s);
	    final String sd = DateFormat.getDateInstance(DateFormat.SHORT, locale)
		    .format(d);

	    if (s.equals(sd)) {
		return d;
	    }

	    // Try to parse the date with finer granularity
	    return parseDateShortTime(s, locale);
	} catch (final ParseException pe) {
	    return null;
	}
    }

    /**
     * Parses a localized date string into a date object
     * 
     * @param s
     *            the string to parse
     * @return a date object or null if the string was not a parsable date
     */
    public static Date parse(final String source) {
	try {

	    if (StringUtils.isEmpty(source)) {
		return null;
	    }

	    final SimpleDateFormat dateFormat = new SimpleDateFormat(
		    SIMPLE_DATE_FORMAT);
	    return dateFormat.parse(source);

	} catch (final ParseException pe) {
	    return null;
	}
    }

    /**
     * 
     * @param source
     * @return
     */
    public static String format(final Date source) {
	try {

	    if (StringUtils.isEmpty(source)) {
		return "";
	    }

	    final SimpleDateFormat dateFormat = new SimpleDateFormat(
		    SIMPLE_DATE_FORMAT);
	    return dateFormat.format(source);

	} catch (final Exception pe) {
	    return "";
	}
    }

    /**
     * Parses a localized date string into a date object sometime the old method
     * parsedate(above) are too strict to the format of the input String even a
     * string is parsed successfully to a date, it still compare further which
     * is no needed.
     * 
     * @param s
     *            the string to parse example "18/02/08 00:00" for en_AU
     * @return a date object or null if the string was not a parsable date
     */
    public static Date parseShortDate(final String s, final Locale locale) {
	try {
	    // Sanity check
	    if (s == null) {
		return null;
	    }

	    final Date d = DateFormat.getDateInstance(DateFormat.SHORT, locale)
		    .parse(s);

	    if (d != null) {
		return d;
	    } else {
		return parseDateShortTime(s, locale);
	    }
	} catch (final ParseException pe) {
	    return null;
	}
    }

    /**
     * Parses a localized date and short time (no seconds) string into a date
     * object
     * 
     * @param s
     *            the string to parse
     * @return a date object or null if the string was not a parsable date and
     *         time
     */
    public static Date parseDateShortTime(final String s, final Locale locale) {
	try {
	    // Sanity check
	    if (s == null) {
		return null;
	    }

	    // Go back and forth to eliminate the datefunctions in all systems
	    // that accepts e.g. 2002-01-32 as 2002-02-01 etc
	    final Date d = DateFormat.getDateTimeInstance(DateFormat.SHORT,
		    DateFormat.SHORT, locale).parse(s);
	    final String sd = DateFormat.getDateTimeInstance(DateFormat.SHORT,
		    DateFormat.SHORT, locale).format(d);

	    if (s.equals(sd)) {
		return d;
	    }

	    // Try to parse with finer granularity
	    return null;
	} catch (final ParseException pe) {
	    return null;
	}
    }

    /**
     * Parses a localized date and short time (no seconds) string into a date
     * object
     * 
     * @param s
     *            the string to parse
     * @return a date object or null if the string was not a parsable date and
     *         time
     */
    public static Date parseShortTime(final String s, final Locale locale) {
	try {
	    // Sanity check
	    if (s == null) {
		return null;
	    }

	    // Go back and forth to eliminate the datefunctions in all systems
	    // that accepts e.g. 2002-01-32 as 2002-02-01 etc
	    final Date d = DateFormat.getTimeInstance(DateFormat.SHORT, locale)
		    .parse(s);
	    final String sd = DateFormat.getTimeInstance(DateFormat.SHORT, locale)
		    .format(d);

	    if (s.equals(sd)) {
		return d;
	    }

	    // Try to parse with finer granularity
	    return parseDateTime(s, locale);
	} catch (final ParseException pe) {
	    return null;
	}
    }

    /**
     * Parses a localized date and time string into a date object
     * 
     * @param s
     *            the string to parse
     * @return a date object or null if the string was not a parsable date and
     *         time
     */
    public static Date parseDateTime(final String s, final Locale locale) {
	try {
	    // Sanity check
	    if (s == null) {
		return null;
	    }

	    // Go back and forth to eliminate the datefunctions in all systems
	    // that accepts e.g. 2002-01-32 as 2002-02-01 etc
	    final Date d = DateFormat.getDateTimeInstance(DateFormat.SHORT,
		    DateFormat.MEDIUM, locale).parse(s);
	    final String sd = DateFormat.getDateTimeInstance(DateFormat.SHORT,
		    DateFormat.MEDIUM, locale).format(d);

	    if (s.equals(sd)) {
		return d;
	    }

	    // Not a valid date
	    return null;
	} catch (final ParseException pe) {
	    return null;
	}
    }

    /**
     * Formats a date and time to a localized text representation of the date
     * and time.
     * 
     * @param date
     *            the Date to be formated
     * @return a string containing the formated time
     */
    public static String getDateShortTime(final Date date, final Locale locale) {
	if (date == null) {
	    return "";
	}
	return DateFormat.getDateTimeInstance(DateFormat.SHORT,
		DateFormat.SHORT, locale).format(date);
    }

    /**
     * Formats a date to a localized text representation of the date
     * 
     * @param date
     *            the Date to be formated
     * @return a string containing the formated date
     */
    public static String getDate(final Date date, final Locale locale) {
	if (date == null) {
	    return "";
	}
	return DateFormat.getDateInstance(DateFormat.SHORT, locale)
		.format(date);
    }

    /**
     * Formats a date to a localized text representation of the date
     * 
     * @param date
     *            the Date to be formated
     * @return a string containing the formated date in an long format
     */
    public static String getLongDate(final Date date, final Locale locale) {
	if (date == null) {
	    return "";
	}
	return DateFormat.getDateInstance(DateFormat.LONG, locale).format(date);
    }

    /**
     * Formats a date and time to a localized text representation of the date
     * and time.
     * 
     * @param date
     *            the Date to be formated
     * @return a string containing the formated time
     */
    public static String getDateTime(final Date date, final Locale locale) {
	if (date == null) {
	    return "";
	}
	return DateFormat.getDateTimeInstance(DateFormat.SHORT,
		DateFormat.MEDIUM, locale).format(date);
    }

    /**
     * Formats a time (actually also a Date object but where the date part is
     * ignored) to a localized text representation of the time. This includes
     * hours, minutes and seconds.
     * 
     * @param date
     *            the Date to be formated
     * @return a string containing the formated time
     */
    public static String getTime(final Date date, final Locale locale) {
	if (date == null) {
	    return "";
	}
	return DateFormat.getTimeInstance(DateFormat.LONG, locale).format(date);
    }

    /**
     * Formats a time (actually also a Date object but where the date part is
     * ignored) to a localized text representation of the time. This includes
     * hours, minutes and seconds.
     * 
     * @param date
     *            the Date to be formated
     * @return a string containing the formated time
     */
    public static String getShortTime(final Date date, final Locale locale) {
	if (date == null) {
	    return "";
	}
	return DateFormat.getTimeInstance(DateFormat.SHORT, locale)
		.format(date);
    }

    /**
     * Formats a text date and time into a date according to format specified.
     * 
     * @param date
     *            the Date to be parsed
     * @param format
     *            Date and Time format string
     * @return The created Date
     */
    public static Date parse(final String date, final String format) {
	if (date == null) {
	    return null;
	}
	try {
	    return new SimpleDateFormat(format).parse(date);
	} catch (final ParseException e) {
	    return null;
	}
    }

    /**
     * Formats a date and time into text representation of the date and time
     * according to format specified.
     * 
     * @param date
     *            the Date to be formated
     * @param format
     *            Time format string
     * @return a string containing the formated time
     */
    public static String format(final Date date, final String format) {
	if (date == null) {
	    return "";
	}
	return new SimpleDateFormat(format).format(date);
    }

    public static String convert(final String date, final String fromFormat, final String toFormat) {
	final Date tmp = parse(date, fromFormat);
	if (tmp == null) {
	    return "";
	}
	return format(tmp, toFormat);
    }

    /**
     * Compares two dates with precision day
     * 
     * @param date1
     * @param date2
     * @return true if date1 is equal to or greater than date2
     */
    public static boolean dateAfterOrEqual(final Date date1, final Date date2) {
	return dateLessOrEqualDayInternal(date2, date1, false);
    }

    /**
     * Compares two dates with precision minute
     * 
     * @param date1
     * @param date2
     * @return true if date1 is equal to or greater than date2
     */
    public static boolean dateAfterOrEqualMinute(final Date date1, final Date date2) {
	return dateLessOrEqualMinuteInternal(date2, date1, false);
    }

    /**
     * Compares two dates with precision day
     * 
     * @param date1
     * @param date2
     * @return true if date1 is equal to date2
     */
    public static boolean dateEqual(final Date date1, final Date date2) {
	return dateAfterOrEqual(date1, date2) && dateBeforeOrEqual(date1,
		date2);
    }

    /**
     * Compares two dates with precision second
     * 
     * @param date1
     * @param date2
     * @return true if date1 is equal to date2
     */
    public static boolean dateEqualSecond(final Date date1, final Date date2) {
	if (date1 == null || date2 == null) {
	    return false;
	}

	final Calendar cal1 = Calendar.getInstance();
	cal1.setTime(date1);

	final Calendar cal2 = Calendar.getInstance();
	cal2.setTime(date2);

	final int year1 = cal1.get(Calendar.YEAR);
	final int month1 = cal1.get(Calendar.MONTH);
	final int day1 = cal1.get(Calendar.DATE);
	final int hour1 = cal1.get(Calendar.HOUR_OF_DAY);
	final int minute1 = cal1.get(Calendar.MINUTE);
	final int second1 = cal1.get(Calendar.SECOND);

	final int year2 = cal2.get(Calendar.YEAR);
	final int month2 = cal2.get(Calendar.MONTH);
	final int day2 = cal2.get(Calendar.DATE);
	final int hour2 = cal2.get(Calendar.HOUR_OF_DAY);
	final int minute2 = cal2.get(Calendar.MINUTE);
	final int second2 = cal2.get(Calendar.SECOND);

	return year1 == year2 && month1 == month2 && day1 == day2
		&& hour1 == hour2 && minute1 == minute2
		&& second1 == second2;

    }

    /**
     * Compares two dates with precision day
     * 
     * @param date1
     * @param date2
     * @return true if date1 is greater than date2
     */
    public static boolean dateAfter(final Date date1, final Date date2) {
	return dateLessOrEqualDayInternal(date2, date1, true);
    }

    /**
     * Compares two dates with precision day
     * 
     * @param date1
     * @param date2
     * @return true if date1 is equal to or less than date2
     */
    public static boolean dateBeforeOrEqual(final Date date1, final Date date2) {
	return dateLessOrEqualDayInternal(date1, date2, false);
    }

    /**
     * Compares two dates with precision day
     * 
     * @param date1
     * @param date2
     * @return true if date1 is less than date2
     */
    public static boolean dateBefore(final Date date1, final Date date2) {
	return dateLessOrEqualDayInternal(date1, date2, true);
    }

    /**
     * Compares date with today with precision day
     * 
     * @param date
     * @return true if date is equal to or greater than todays date
     */
    public static boolean dateEqualOrAfterToday(final Date date) {
	return dateLessOrEqualDayInternal(new Date(System.currentTimeMillis()),
		date, false);
    }

    /**
     * Compares date with today with precision day
     * 
     * @param date
     * @return true if date is equal to or less than todays date
     */
    public static boolean dateEqualOrBeforeToday(final Date date) {
	return dateLessOrEqualDayInternal(date,
		new Date(System.currentTimeMillis()), false);
    }

    /**
     * Compares two date spans for overlap, the comparison is not strict
     * 
     * e.g. <----------------------> fromDate2 & toDate2 1. <------> fromDate1 &
     * toDate1 -> false 2. <--------------> fromDate1 & toDate1 -> true 3.
     * <---------------> fromDate1 & toDate1 -> true 4.
     * <-----------------------------------------------> fromDate1 & toDate1 ->
     * true 5. <---------------> fromDate1 & toDate1 -> true 6. <------->
     * fromDate1 & toDate1 -> false
     * 
     * @param fromDate1
     *            null if valid since the beginning of time
     * @param toDate1
     *            null if valid until the end of time
     * @param fromDate2
     *            null if valid since the beginning of time
     * @param toDate2
     *            null if valid until the end of time
     * @return true if fromDate1 and toDate1 overlap (fully or partially)
     *         fromDate2 and toDate2
     */
    public static boolean datesOverlap(Date fromDate1, Date toDate1,
	    Date fromDate2, Date toDate2) {
	fromDate1 = fromDate1 == null ? new Date(0) : fromDate1;
	toDate1 = toDate1 == null ? new Date(Long.MAX_VALUE) : toDate1;
	fromDate2 = fromDate2 == null ? new Date(0) : fromDate2;
	toDate2 = toDate2 == null ? new Date(Long.MAX_VALUE) : toDate2;

	// Sanity check
	if (dateAfter(fromDate2, toDate2) || dateAfter(fromDate1, toDate1)) {
	    throw new IllegalArgumentException(
		    "fromdate must be before or equal to todate");
	}

	// Normal case 1
	if (dateBefore(toDate1, fromDate2)) {
	    return false;
	}

	// Normal case 6
	if (dateAfter(fromDate1, toDate2)) {
	    return false;
	}

	// Normal case 3 & 4
	if (dateBeforeOrEqual(fromDate1, fromDate2)
		&& dateAfterOrEqual(toDate1, fromDate2)) {
	    return true;
	}

	// Normal case 5
	if (dateAfterOrEqual(toDate1, toDate2)
		&& dateBeforeOrEqual(fromDate1, toDate2)) {
	    return true;
	}

	// Normal case 2
	return dateAfterOrEqual(fromDate1, fromDate2)
		&& dateBeforeOrEqual(toDate1, toDate2);

    }

    /**
     * Same as above but the precision is minutes
     * 
     * @param fromDate1
     * @param toDate1
     * @param fromDate2
     * @param toDate2
     * @return boolean
     */
    public static boolean datesOverlapMinute(Date fromDate1, Date toDate1,
	    Date fromDate2, Date toDate2) {
	fromDate1 = fromDate1 == null ? new Date(0) : fromDate1;
	toDate1 = toDate1 == null ? new Date(Long.MAX_VALUE) : toDate1;
	fromDate2 = fromDate2 == null ? new Date(0) : fromDate2;
	toDate2 = toDate2 == null ? new Date(Long.MAX_VALUE) : toDate2;

	// Sanity check
	if (dateAfterOrEqualMinute(fromDate2, toDate2)
		|| dateAfterOrEqualMinute(fromDate1, toDate1)) {
	    throw new IllegalArgumentException(
		    "fromdate must be before or equal to todate");
	}

	// Normal case 1
	if (dateAfterOrEqualMinute(fromDate1, toDate2)) {
	    return false;
	}

	// Normal case 6
	if (dateAfterOrEqualMinute(fromDate2, toDate1)) {
	    return false;
	}

	// Overlapping on left side only
	if (dateAfterOrEqualMinute(fromDate2, fromDate1)
		&& dateAfterOrEqualMinute(toDate1, fromDate2)) {
	    return true;
	}

	// Overlapping on right side only
	return dateAfterOrEqualMinute(fromDate1, fromDate2)
		&& dateAfterOrEqualMinute(toDate2, fromDate1);

    }

    private static boolean dateLessOrEqualDayInternal(Date date1, Date date2,
	    final boolean strict) {
	date1 = date1 == null ? new Date(0) : date1;
	date2 = date2 == null ? new Date(Long.MAX_VALUE) : date2;

	final Calendar cal1 = new GregorianCalendar();
	cal1.setTime(date1);

	final int date1Year = cal1.get(Calendar.YEAR);
	final int date1Month = cal1.get(Calendar.MONTH);
	final int date1Day = cal1.get(Calendar.DATE);

	final Calendar cal2 = new GregorianCalendar();
	cal2.setTime(date2);

	final int date2Year = cal2.get(Calendar.YEAR);
	final int date2Month = cal2.get(Calendar.MONTH);
	final int date2Day = cal2.get(Calendar.DATE);

	return date1Year < date2Year
		|| date1Year == date2Year && date1Month < date2Month
		|| date1Year == date2Year && date1Month == date2Month && date1Day < date2Day
		|| !strict && date1Year == date2Year
			&& date1Month == date2Month && date1Day == date2Day;

    }

    private static boolean dateLessOrEqualMinuteInternal(Date date1,
	    Date date2, final boolean strict) {
	date1 = date1 == null ? new Date(0) : date1;
	date2 = date2 == null ? new Date(Long.MAX_VALUE) : date2;

	final Calendar cal1 = new GregorianCalendar();
	cal1.setTime(date1);

	final int date1Year = cal1.get(Calendar.YEAR);
	final int date1Month = cal1.get(Calendar.MONTH);
	final int date1Day = cal1.get(Calendar.DATE);
	final int date1Hour = cal1.get(Calendar.HOUR_OF_DAY);
	final int date1Minute = cal1.get(Calendar.MINUTE);

	final Calendar cal2 = new GregorianCalendar();
	cal2.setTime(date2);

	final int date2Year = cal2.get(Calendar.YEAR);
	final int date2Month = cal2.get(Calendar.MONTH);
	final int date2Day = cal2.get(Calendar.DATE);
	final int date2Hour = cal2.get(Calendar.HOUR_OF_DAY);
	final int date2Minute = cal2.get(Calendar.MINUTE);

	return date1Year < date2Year
		|| date1Year == date2Year && date1Month < date2Month
		|| date1Year == date2Year && date1Month == date2Month && date1Day < date2Day
		|| date1Year == date2Year && date1Month == date2Month
			&& date1Day == date2Day && date1Hour < date2Hour
		|| date1Year == date2Year && date1Month == date2Month
			&& date1Day == date2Day && date1Hour == date2Hour && date1Minute < date2Minute
		|| !strict && date1Year == date2Year
			&& date1Month == date2Month && date1Day == date2Day
			&& date1Hour == date2Hour && date1Minute == date2Minute;

    }

    /**
     * Returns a string with the weekday and the time of the day in date.
     * 
     * @param date
     * @param locale
     * @return weekday and the time of the day
     */
    public static String getDayAndTime(final Date date, final Locale locale) {
	if (date != null) {
	    return getWeekDay(date, locale) + " " + getShortTime(date, locale);
	}
	return "";
    }

    public static String getWeekDay(final Date date, final Locale locale) {
	if (date != null) {
	    return new SimpleDateFormat("EEEEEEEE", locale).format(date);
	}
	return "";
    }

    public static Date substract(final Date date, final int years, final int months, final int days,
	    final int hours, final int minutes, final int seconds) {
	final Calendar cal = new GregorianCalendar();
	cal.setTime(date);
	cal.add(Calendar.YEAR, -years);
	cal.add(Calendar.MONTH, -months);
	cal.add(Calendar.DATE, -days);
	cal.add(Calendar.HOUR, -hours);
	cal.add(Calendar.MINUTE, -minutes);
	cal.add(Calendar.SECOND, -seconds);

	return cal.getTime();
    }

    public static Date add(final Date date, final int years, final int months, final int days,
	    final int hours, final int minutes, final int seconds) {
	final Calendar cal = new GregorianCalendar();
	cal.setTime(date);
	cal.add(Calendar.YEAR, years);
	cal.add(Calendar.MONTH, months);
	cal.add(Calendar.DATE, days);
	cal.add(Calendar.HOUR, hours);
	cal.add(Calendar.MINUTE, minutes);
	cal.add(Calendar.SECOND, seconds);

	return cal.getTime();
    }

    public static int getDays(final GregorianCalendar g1, final GregorianCalendar g2) {
	int elapsed = 0;
	GregorianCalendar gc1, gc2;

	if (g2.after(g1)) {
	    gc2 = (GregorianCalendar) g2.clone();
	    gc1 = (GregorianCalendar) g1.clone();
	} else {
	    gc2 = (GregorianCalendar) g1.clone();
	    gc1 = (GregorianCalendar) g2.clone();
	}

	gc1.clear(Calendar.MILLISECOND);
	gc1.clear(Calendar.SECOND);
	gc1.clear(Calendar.MINUTE);
	gc1.clear(Calendar.HOUR_OF_DAY);

	gc2.clear(Calendar.MILLISECOND);
	gc2.clear(Calendar.SECOND);
	gc2.clear(Calendar.MINUTE);
	gc2.clear(Calendar.HOUR_OF_DAY);

	while (gc1.before(gc2)) {
	    gc1.add(Calendar.DATE, 1);
	    elapsed++;
	}
	return elapsed;
    }

    /**
     * Method to find the starting and ending dates of the week that the current
     * day falls in.
     * 
     * @return a Date array with first element being the start date of the week
     *         and the second element being the end date of the week.
     */
    public Date[] getStartAndEndOfWeek() {
	Date startDate;
	Date endDate;
	final GregorianCalendar gc = new GregorianCalendar();

	switch (gc.get(Calendar.DAY_OF_WEEK)) {
	// Set the first date of the week
	case 1:
	    break;
	case 2:
	    gc.add(Calendar.DAY_OF_MONTH, -1);
	    break;
	case 3:
	    gc.add(Calendar.DAY_OF_MONTH, -2);
	    break;
	case 4:
	    gc.add(Calendar.DAY_OF_MONTH, -3);
	    break;
	case 5:
	    gc.add(Calendar.DAY_OF_MONTH, -4);
	    break;
	case 6:
	    gc.add(Calendar.DAY_OF_MONTH, -5);
	    break;
	case 7:
	    gc.add(Calendar.DAY_OF_MONTH, -6);
	    break;
	}

	startDate = DateUtil.resetStartDate(gc);
	// Set the last date of the week
	gc.add(Calendar.DAY_OF_MONTH, 7);
	endDate = DateUtil.resetEndDate(gc);

	return new Date[] { startDate, endDate };
    }

    /**
     * Method to find the starting and ending dates of the month that the
     * current day falls in.
     * 
     * @return a Date array with first element being the start date of the month
     *         and the second element being the end date of the month.
     */
    public Date[] getStartAndEndOfMonth() {
	Date startDate;
	Date endDate;
	final GregorianCalendar gc = new GregorianCalendar();

	// Set the first date of month
	gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), 1);
	startDate = DateUtil.resetStartDate(gc);
	// Set the last date of the month
	switch (gc.get(Calendar.MONTH)) {
	case 0:
	    gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), 31);
	    break;
	case 1:
	    // Handle leap years
	    if (gc.isLeapYear(gc.get(Calendar.YEAR))) {
		gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), 29);
	    } else {
		gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), 28);
	    }
	    break;
	case 2:
	    gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), 31);
	    break;
	case 3:
	    gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), 30);
	    break;
	case 4:
	    gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), 31);
	    break;
	case 5:
	    gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), 30);
	    break;
	case 6:
	    gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), 31);
	    break;
	case 7:
	    gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), 31);
	    break;
	case 8:
	    gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), 30);
	    break;
	case 9:
	    gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), 31);
	    break;
	case 10:
	    gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), 30);
	    break;
	case 11:
	    gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), 31);
	    break;
	}

	endDate = DateUtil.resetEndDate(gc);
	return new Date[] { startDate, endDate };
    }

    /**
     * Method to find the starting and ending dates of the quarter that the
     * current day falls in.
     * 
     * @return a Date array with first element being the start date of the
     *         quarter and the second element being the end date of the quarter.
     */
    public Date[] getStartAndEndOfQuarter() {
	Date startDate = new Date();
	Date endDate = new Date();
	final GregorianCalendar gc = new GregorianCalendar();

	switch (gc.get(Calendar.MONTH)) {
	// Calculate the 1st quarter
	case 0:
	case 1:
	case 2:
	    gc.set(gc.get(Calendar.YEAR), 0, 1);
	    startDate = DateUtil.resetStartDate(gc);
	    gc.set(gc.get(Calendar.YEAR), 2, 31);
	    endDate = DateUtil.resetEndDate(gc);
	    break;
	// Calculate the 2nd quarter
	case 3:
	case 4:
	case 5:
	    gc.set(gc.get(Calendar.YEAR), 3, 1);
	    startDate = DateUtil.resetStartDate(gc);
	    gc.set(gc.get(Calendar.YEAR), 5, 30);
	    endDate = DateUtil.resetEndDate(gc);
	    break;
	// Calculate the 3rd quarter
	case 6:
	case 7:
	case 8:
	    gc.set(gc.get(Calendar.YEAR), 6, 1);
	    startDate = DateUtil.resetStartDate(gc);
	    gc.set(gc.get(Calendar.YEAR), 8, 30);
	    endDate = DateUtil.resetEndDate(gc);
	    break;
	// Calculate the 4th quarter
	case 9:
	case 10:
	case 11:
	    gc.set(gc.get(Calendar.YEAR), 9, 1);
	    startDate = DateUtil.resetStartDate(gc);
	    gc.set(gc.get(Calendar.YEAR), 11, 31);
	    endDate = DateUtil.resetEndDate(gc);
	    break;
	}
	return new Date[] { startDate, endDate };
    }

    /**
     * Method to find the starting and ending dates of the year that the current
     * day falls in.
     * 
     * @return a Date array with first element being the start date of the year
     *         and the second element being the end date of the year.
     */
    public Date[] getStartAndEndOfYear() {
	Date startDate;
	Date endDate;
	final GregorianCalendar gc = new GregorianCalendar();
	// Year start date set to 1st of January
	gc.set(gc.get(Calendar.YEAR), 0, 1);
	startDate = DateUtil.resetStartDate(gc);
	// Year ending date set to 31st of December
	gc.set(gc.get(Calendar.YEAR), 11, 31);
	endDate = DateUtil.resetEndDate(gc);

	return new Date[] { startDate, endDate };
    }

    public static Date resetStartDate(final Calendar cal) {
	// Re-set the hh:mm:ss to 00:00:00 of the start date
	cal.set(Calendar.HOUR_OF_DAY, 0);
	cal.set(Calendar.MINUTE, 0);
	cal.set(Calendar.SECOND, 0);

	return cal.getTime();
    }

    public static Date resetEndDate(final Calendar cal) {
	// Re-set the hh:mm:ss to 23:59:59 of the start date
	cal.set(Calendar.HOUR_OF_DAY, 23);
	cal.set(Calendar.MINUTE, 59);
	cal.set(Calendar.SECOND, 59);

	return cal.getTime();
    }

    /**
     * Checks if a given aDate falls between a given date period
     * 
     * @param aDate
     * @param firstDate
     * @param lastDate
     * @return
     */
    public static boolean isInsideInterval(final Date aDate, final Date firstDate,
	    final Date lastDate) {
	if (aDate != null) {
	    if (lastDate == null && firstDate == null) {
		// Interval: ------
		return true;
	    }

	    if (lastDate != null && firstDate != null) {

		// Interval: |------|
		// Date: |
		if (!aDate.before(firstDate) && !aDate.after(lastDate)) {
		    return true;
		}
	    } else if (lastDate != null) {

		// Interval: ------|
		// Date: |
		if (aDate.before(lastDate)) {
		    return true;
		}
	    } else {
		// Interval: |------
		// Date: |
		if (dateAfterOrEqual(aDate, firstDate)) {
		    return true;
		}
	    }
	}
	return false;
    }
}
