/* Collections #2024 */
package com.favourite.collections.commons.core.json;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.TimeZone;

import com.favourite.collections.commons.core.data.ApiSubError;
import com.favourite.collections.commons.core.data.ApiValidationError;
import com.favourite.collections.commons.core.exceptions.AbstractPlatformException;

public final class DateUtils {

	private DateUtils() {
	}

	public static ZonedDateTime getZonedDateTime() {
		ZonedDateTime zonedDateTime = ZonedDateTime.now();
		return zonedDateTime;
	}

	public static ZoneId getDateTimeZoneOfTenant() {
		ZoneId zone = ZoneId.systemDefault();
		return zone;
	}

	public static TimeZone getTimeZoneOfTenant() {
		TimeZone zone = TimeZone.getTimeZone(getDateTimeZoneOfTenant());
		return zone;
	}

	public static Date getDateOfTenant() {
		return Date.from(getLocalDateOfTenant().atStartOfDay(getDateTimeZoneOfTenant()).toInstant());
	}

	public static LocalDate getLocalDateOfTenant() {
		final ZoneId zone = getDateTimeZoneOfTenant();
		LocalDate today = LocalDate.now(zone);
		return today;
	}

	public static LocalDateTime getLocalDateTimeOfTenant() {
		final ZoneId zone = getDateTimeZoneOfTenant();
		LocalDateTime today = LocalDateTime.now(zone);
		return today;
	}

	public static String parseLocalDateToStringFormat(final LocalDate localDate, final String pattern) {
		final DateTimeFormatter dateStringFormat = DateTimeFormatter.ofPattern(pattern);
		return localDate.format(dateStringFormat);
	}

	public static LocalDate parseLocalDate(final String stringDate, final String pattern) {

		try {
			final DateTimeFormatter dateStringFormat = DateTimeFormatter.ofPattern(pattern)
					.withZone(getDateTimeZoneOfTenant());
			final ZonedDateTime dateTime = ZonedDateTime.parse(stringDate, dateStringFormat);
			return dateTime.toLocalDate();
		} catch (final IllegalArgumentException e) {
			final Collection<ApiSubError> dataValidationErrors = new ArrayList<>();
			final ApiSubError error = new ApiValidationError("validation.msg.invalid.date.pattern", stringDate, pattern,
					"The parameter date (" + stringDate + ") is invalid w.r.t. pattern " + pattern);
			dataValidationErrors.add(error);
			throw new AbstractPlatformException("validation.msg.validation.errors.exist", "Validation errors exist.",
					400, dataValidationErrors);
		}
	}

	public static String formatToSqlDate(final Date date) {
		final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		df.setTimeZone(getTimeZoneOfTenant());
		final String formattedSqlDate = df.format(date);
		return formattedSqlDate;
	}

	public static boolean isDateInTheFuture(final LocalDate localDate) {
		return localDate.isAfter(getLocalDateOfTenant());
	}
}
