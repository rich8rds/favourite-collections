/* Collections #2024 */
package com.favourite.collections.commons.core.json;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.number.NumberStyleFormatter;
import org.springframework.http.HttpStatus;

import com.favourite.collections.commons.core.data.ApiSubError;
import com.favourite.collections.commons.core.data.ApiValidationError;
import com.favourite.collections.commons.core.exceptions.AbstractPlatformException;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import static java.time.temporal.ChronoField.DAY_OF_MONTH;
import static java.time.temporal.ChronoField.MONTH_OF_YEAR;
import static java.time.temporal.ChronoField.YEAR_OF_ERA;

/** Helper class to extract values of json named attributes. */
public class JsonParserHelper {

	public boolean parameterExists(final String parameterName, final JsonElement element) {
		if (element == null) {
			return false;
		}
		return element.getAsJsonObject().has(parameterName);
	}

	public Boolean extractBooleanNamed(final String parameterName, final JsonElement element,
			final Set<String> requestParamatersDetected) {
		Boolean value = null;
		if (element.isJsonObject()) {
			final JsonObject object = element.getAsJsonObject();
			if (object.has(parameterName) && object.get(parameterName).isJsonPrimitive()) {
				requestParamatersDetected.add(parameterName);

				final JsonPrimitive primitive = object.get(parameterName).getAsJsonPrimitive();
				value = primitive.getAsBoolean();
			}
		}
		return value;
	}

	public Long extractLongNamed(final String parameterName, final JsonElement element,
			final Set<String> parametersPassedInRequest) {
		Long longValue = null;
		if (element.isJsonObject()) {
			final JsonObject object = element.getAsJsonObject();
			if (object.has(parameterName) && object.get(parameterName).isJsonPrimitive()) {
				parametersPassedInRequest.add(parameterName);
				final JsonPrimitive primitive = object.get(parameterName).getAsJsonPrimitive();
				final String stringValue = primitive.getAsString();
				if (StringUtils.isNotBlank(stringValue)) {
					longValue = Long.valueOf(stringValue);
				}
			}
		}
		return longValue;
	}

	public String extractStringNamed(final String parameterName, final JsonElement element,
			final Set<String> parametersPassedInRequest) {
		String stringValue = null;
		if (element.isJsonObject()) {
			final JsonObject object = element.getAsJsonObject();
			if (object.has(parameterName) && object.get(parameterName).isJsonPrimitive()) {
				parametersPassedInRequest.add(parameterName);
				final JsonPrimitive primitive = object.get(parameterName).getAsJsonPrimitive();
				final String valueAsString = primitive.getAsString();
				if (StringUtils.isNotBlank(valueAsString)) {
					stringValue = valueAsString;
				}
			}
		}
		return stringValue;
	}

	public BigDecimal extractBigDecimalWithLocaleNamed(final String parameterName, final JsonElement element,
			final Set<String> modifiedParameters) {
		BigDecimal value = null;
		if (element.isJsonObject()) {
			final JsonObject object = element.getAsJsonObject();
			final Locale locale = extractLocaleValue(object);
			value = extractBigDecimalNamed(parameterName, object, locale, modifiedParameters);
		}
		return value;
	}

	public BigDecimal extractBigDecimalNamed(final String parameterName, final JsonObject element, final Locale locale,
			final Set<String> modifiedParameters) {
		BigDecimal value = null;
		if (element.isJsonObject()) {
			final JsonObject object = element.getAsJsonObject();
			if (object.has(parameterName) && object.get(parameterName).isJsonPrimitive()) {
				modifiedParameters.add(parameterName);
				final JsonPrimitive primitive = object.get(parameterName).getAsJsonPrimitive();
				final String valueAsString = primitive.getAsString();
				if (StringUtils.isNotBlank(valueAsString)) {
					value = convertFrom(valueAsString, parameterName, locale);
				}
			}
		}
		return value;
	}

	public Integer extractIntegerWithLocaleNamed(final String parameterName, final JsonElement element,
			final Set<String> modifiedParameters) {
		Integer value = null;
		if (element.isJsonObject()) {
			final JsonObject object = element.getAsJsonObject();
			final Locale locale = extractLocaleValue(object);
			value = extractIntegerNamed(parameterName, object, locale, modifiedParameters);
		}
		return value;
	}

	public Integer extractIntegerNamed(final String parameterName, final JsonElement element, final Locale locale,
			final Set<String> modifiedParameters) {
		Integer value = null;
		if (element.isJsonObject()) {
			final JsonObject object = element.getAsJsonObject();
			if (object.has(parameterName) && object.get(parameterName).isJsonPrimitive()) {
				modifiedParameters.add(parameterName);
				final JsonPrimitive primitive = object.get(parameterName).getAsJsonPrimitive();
				final String valueAsString = primitive.getAsString();
				if (StringUtils.isNotBlank(valueAsString)) {
					value = convertToInteger(valueAsString, parameterName, locale);
				}
			}
		}
		return value;
	}

	/**
	 * Method used to extract integers from unformatted strings. Ex: "1" , "100002"
	 * etc
	 *
	 * <p>
	 * Please note that this method does not support extracting Integers from locale
	 * specific formatted strings Ex "1,000" etc
	 *
	 * @param parameterName
	 * @param element
	 * @param parametersPassedInRequest
	 * @return
	 */
	public Integer extractIntegerSansLocaleNamed(final String parameterName, final JsonElement element,
			final Set<String> parametersPassedInRequest) {
		Integer intValue = null;
		if (element.isJsonObject()) {
			final JsonObject object = element.getAsJsonObject();
			if (object.has(parameterName) && object.get(parameterName).isJsonPrimitive()) {
				parametersPassedInRequest.add(parameterName);
				final JsonPrimitive primitive = object.get(parameterName).getAsJsonPrimitive();
				final String stringValue = primitive.getAsString();
				if (StringUtils.isNotBlank(stringValue)) {
					intValue = convertToIntegerSanLocale(stringValue, parameterName);
				}
			}
		}
		return intValue;
	}

	public String extractDateFormatParameter(final JsonObject element) {
		String value = null;
		if (element.isJsonObject()) {
			final JsonObject object = element.getAsJsonObject();

			final String dateFormatParameter = "dateFormat";
			if (object.has(dateFormatParameter) && object.get(dateFormatParameter).isJsonPrimitive()) {
				final JsonPrimitive primitive = object.get(dateFormatParameter).getAsJsonPrimitive();
				value = primitive.getAsString();
			}
		}
		return value;
	}

	public String extractTimeFormatParameter(final JsonObject element) {
		String value = null;
		if (element.isJsonObject()) {
			final JsonObject object = element.getAsJsonObject();

			final String timeFormatParameter = "timeFormat";
			if (object.has(timeFormatParameter) && object.get(timeFormatParameter).isJsonPrimitive()) {
				final JsonPrimitive primitive = object.get(timeFormatParameter).getAsJsonPrimitive();
				value = primitive.getAsString();
			}
		}
		return value;
	}

	public String extractMonthDayFormatParameter(final JsonObject element) {
		String value = null;
		if (element.isJsonObject()) {
			final JsonObject object = element.getAsJsonObject();

			final String monthDayFormatParameter = "monthDayFormat";
			if (object.has(monthDayFormatParameter) && object.get(monthDayFormatParameter).isJsonPrimitive()) {
				final JsonPrimitive primitive = object.get(monthDayFormatParameter).getAsJsonPrimitive();
				value = primitive.getAsString();
			}
		}
		return value;
	}

	public Locale extractLocaleParameter(final JsonObject element) {
		Locale clientApplicationLocale = null;
		if (element.isJsonObject()) {
			final JsonObject object = element.getAsJsonObject();

			String locale = null;
			final String localeParameter = "locale";
			if (object.has(localeParameter) && object.get(localeParameter).isJsonPrimitive()) {
				final JsonPrimitive primitive = object.get(localeParameter).getAsJsonPrimitive();
				locale = primitive.getAsString();
				clientApplicationLocale = localeFromString(locale);
			}
		}
		return clientApplicationLocale;
	}

	public String[] extractArrayNamed(final String parameterName, final JsonElement element,
			final Set<String> parametersPassedInRequest) {
		String[] arrayValue = null;
		if (element.isJsonObject()) {
			final JsonObject object = element.getAsJsonObject();
			if (object.has(parameterName)) {
				parametersPassedInRequest.add(parameterName);
				final JsonArray array = object.get(parameterName).getAsJsonArray();
				arrayValue = new String[array.size()];
				for (int i = 0; i < array.size(); i++) {
					arrayValue[i] = array.get(i).getAsString();
				}
			}
		}
		return arrayValue;
	}

	public JsonArray extractJsonArrayNamed(final String parameterName, final JsonElement element) {
		JsonArray jsonArray = null;

		if (element.isJsonObject()) {
			final JsonObject object = element.getAsJsonObject();
			if (object.has(parameterName)) {
				jsonArray = object.get(parameterName).getAsJsonArray();
			}
		}

		return jsonArray;
	}

	public JsonObject extractJsonObjectNamed(final String parameterName, final JsonElement element) {
		JsonObject jsonObject = null;

		if (element.isJsonObject()) {
			final JsonObject object = element.getAsJsonObject();
			if (object.has(parameterName)) {
				jsonObject = object.get(parameterName).getAsJsonObject();
			}
		}

		return jsonObject;
	}

	/** Used with the local date is in array format */
	public LocalDate extractLocalDateAsArrayNamed(final String parameterName, final JsonElement element,
			final Set<String> parametersPassedInCommand) {
		LocalDate value = null;
		if (element.isJsonObject()) {
			final JsonObject object = element.getAsJsonObject();

			if (object.has(parameterName) && object.get(parameterName).isJsonArray()) {

				parametersPassedInCommand.add(parameterName);

				final JsonArray dateArray = object.get(parameterName).getAsJsonArray();

				final Integer year = dateArray.get(0).getAsInt();
				final Integer month = dateArray.get(1).getAsInt();
				final Integer day = dateArray.get(2).getAsInt();

				value = LocalDate.now(DateUtils.getDateTimeZoneOfTenant()).with(YEAR_OF_ERA, year)
						.with(MONTH_OF_YEAR, month).with(DAY_OF_MONTH, day);
			}
		}
		return value;
	}

	public MonthDay extractMonthDayNamed(final String parameterName, final JsonElement element) {

		MonthDay value = null;

		if (element.isJsonObject()) {
			final JsonObject object = element.getAsJsonObject();

			final String monthDayFormat = extractMonthDayFormatParameter(object);
			final Locale clientApplicationLocale = extractLocaleParameter(object);
			value = extractMonthDayNamed(parameterName, object, monthDayFormat, clientApplicationLocale);
		}
		return value;
	}

	public MonthDay extractMonthDayNamed(final String parameterName, final JsonObject element, final String dateFormat,
			final Locale clientApplicationLocale) {
		MonthDay value = null;
		if (element.isJsonObject()) {
			final JsonObject object = element.getAsJsonObject();

			if (object.has(parameterName) && object.get(parameterName).isJsonPrimitive()) {

				final JsonPrimitive primitive = object.get(parameterName).getAsJsonPrimitive();
				final String valueAsString = primitive.getAsString();
				if (StringUtils.isNotBlank(valueAsString)) {
					try {
						final DateTimeFormatter formatter = new DateTimeFormatterBuilder().parseCaseInsensitive()
								.parseLenient().appendPattern(dateFormat).toFormatter(clientApplicationLocale);
						value = MonthDay.parse(valueAsString, formatter);
					} catch (final IllegalArgumentException e) {
						final Collection<ApiSubError> dataValidationErrors = new ArrayList<>();

						final ApiSubError error = new ApiValidationError("validation.msg.invalid.month.day",
								parameterName, valueAsString,
								"The parameter " + parameterName + " is invalid based on the monthDayFormat: '"
										+ dateFormat + "' and locale: '" + clientApplicationLocale + "' provided:");
						dataValidationErrors.add(error);

						throw new AbstractPlatformException("validation.msg.validation.errors.exist",
								"Validation errors exist.", HttpStatus.BAD_REQUEST.value(), dataValidationErrors);
					}
				}
			}
		}
		return value;
	}

	public LocalDate extractLocalDateNamed(final String parameterName, final JsonElement element,
			final Set<String> parametersPassedInCommand) {

		LocalDate value = null;

		if (element.isJsonObject()) {
			final JsonObject object = element.getAsJsonObject();

			final String dateFormat = extractDateFormatParameter(object);
			final Locale clientApplicationLocale = extractLocaleParameter(object);
			value = extractLocalDateNamed(parameterName, object, dateFormat, clientApplicationLocale,
					parametersPassedInCommand);
		}
		return value;
	}

	public LocalDateTime extractLocalTimeNamed(final String parameterName, final JsonElement element,
			final Set<String> parametersPassedInCommand) {

		LocalDateTime value = null;

		if (element.isJsonObject()) {
			final JsonObject object = element.getAsJsonObject();
			value = extractLocalTimeNamed(parameterName, element, extractTimeFormatParameter(object),
					parametersPassedInCommand);
		}
		return value;
	}

	public LocalDateTime extractLocalTimeNamed(final String parameterName, final JsonElement element, String timeFormat,
			final Set<String> parametersPassedInCommand) {

		LocalDateTime value = null;

		if (element.isJsonObject()) {
			final JsonObject object = element.getAsJsonObject();
			final Locale clientApplicationLocale = extractLocaleParameter(object);
			value = extractLocalTimeNamed(parameterName, object, timeFormat, clientApplicationLocale,
					parametersPassedInCommand);
		}
		return value;
	}

	public LocalDateTime extractLocalTimeNamed(final String parameterName, final JsonElement element,
			final String timeFormat, final Locale clientApplicationLocale,
			final Set<String> parametersPassedInCommand) {
		LocalDateTime value = null;
		String timeValueAsString = null;
		if (element.isJsonObject()) {
			final JsonObject object = element.getAsJsonObject();
			if (object.has(parameterName) && object.get(parameterName).isJsonPrimitive()) {
				parametersPassedInCommand.add(parameterName);

				try {
					DateTimeFormatter timeFormtter = DateTimeFormatter.ofPattern(timeFormat);
					final JsonPrimitive primitive = object.get(parameterName).getAsJsonPrimitive();
					timeValueAsString = primitive.getAsString();
					if (StringUtils.isNotBlank(timeValueAsString)) {
						value = LocalDateTime.parse(timeValueAsString, timeFormtter);
					}
				} catch (IllegalArgumentException e) {

					final Collection<ApiSubError> dataValidationErrors = new ArrayList<>();

					final String defaultMessage = new StringBuilder(
							"The parameter '" + timeValueAsString + "' is not in correct format.").toString();

					final ApiSubError error = new ApiValidationError("validation.msg.invalid.TimeFormat",
							defaultMessage, parameterName, "Invalid time format");
					dataValidationErrors.add(error);

					throw new AbstractPlatformException("validation.msg.validation.errors.exist",
							"Validation errors exist.", HttpStatus.BAD_REQUEST.value(), dataValidationErrors);
				}
			}
		}
		return value;
	}

	public LocalDate extractLocalDateNamed(final String parameterName, final JsonElement element,
			final String dateFormat, final Locale clientApplicationLocale,
			final Set<String> parametersPassedInCommand) {
		LocalDate value = null;
		if (element.isJsonObject()) {
			final JsonObject object = element.getAsJsonObject();

			if (object.has(parameterName) && object.get(parameterName).isJsonPrimitive()) {

				parametersPassedInCommand.add(parameterName);

				final JsonPrimitive primitive = object.get(parameterName).getAsJsonPrimitive();
				final String valueAsString = primitive.getAsString();
				if (StringUtils.isNotBlank(valueAsString)) {
					value = convertFrom(valueAsString, parameterName, dateFormat, clientApplicationLocale);
				}
			}
		}
		return value;
	}

	public static LocalDate convertFrom(final String dateAsString, final String parameterName, final String dateFormat,
			final Locale clientApplicationLocale) {

		return convertDateTimeFrom(dateAsString, parameterName, dateFormat, clientApplicationLocale).toLocalDate();
	}

	public static LocalDateTime convertDateTimeFrom(final String dateTimeAsString, final String parameterName,
			final String dateTimeFormat, final Locale clientApplicationLocale) {

		validateDateFormatAndLocale(parameterName, dateTimeFormat, clientApplicationLocale);
		LocalDateTime eventLocalDateTime = null;
		if (StringUtils.isNotBlank(dateTimeAsString)) {
			try {
				DateTimeFormatter formatter = new DateTimeFormatterBuilder().parseCaseInsensitive().parseLenient()
						.appendPattern(dateTimeFormat).optionalStart().appendPattern(" HH:mm:ss").optionalEnd()
						.parseDefaulting(ChronoField.HOUR_OF_DAY, 0).parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
						.parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0).toFormatter(clientApplicationLocale);
				eventLocalDateTime = LocalDateTime.parse(dateTimeAsString, formatter);
			} catch (final IllegalArgumentException e) {
				final Collection<ApiSubError> dataValidationErrors = new ArrayList<>();
				final ApiSubError error = new ApiValidationError("validation.msg.invalid.dateFormat.format",
						parameterName, dateTimeFormat,
						"The parameter " + parameterName + " is invalid based on the dateFormat: '" + dateTimeFormat
								+ "' and locale: '" + clientApplicationLocale + "' provided:");

				dataValidationErrors.add(error);

				throw new AbstractPlatformException("validation.msg.validation.errors.exist",
						"Validation errors exist.", 400, dataValidationErrors);
			}
		}

		return eventLocalDateTime;
	}

	private static void validateDateFormatAndLocale(final String parameterName, final String dateFormat,
			final Locale clientApplicationLocale) {
		if (StringUtils.isBlank(dateFormat) || clientApplicationLocale == null) {

			final Collection<ApiSubError> dataValidationErrors = new ArrayList<>();
			if (StringUtils.isBlank(dateFormat)) {
				final String defaultMessage = new StringBuilder(
						"The parameter '" + parameterName + "' requires a 'dateFormat' parameter to be passed with it.")
						.toString();
				final ApiSubError error = new ApiValidationError("validation.msg.missing.dateFormat.parameter",
						parameterName, dateFormat, defaultMessage);
				dataValidationErrors.add(error);
			}
			if (clientApplicationLocale == null) {
				final String defaultMessage = new StringBuilder(
						"The parameter '" + parameterName + "' requires a 'locale' parameter to be passed with it.")
						.toString();
				final ApiSubError error = new ApiValidationError("validation.msg.missing.locale.parameter",
						parameterName, dateFormat, defaultMessage);
				dataValidationErrors.add(error);
			}
			throw new AbstractPlatformException("validation.msg.validation.errors.exist", "Validation errors exist.",
					400, dataValidationErrors);
		}
	}

	public Integer convertToInteger(final String numericalValueFormatted, final String parameterName,
			final Locale clientApplicationLocale) {

		if (clientApplicationLocale == null) {

			final Collection<ApiSubError> dataValidationErrors = new ArrayList<>();
			final String defaultMessage = new StringBuilder(
					"The parameter '" + parameterName + "' requires a 'locale' parameter to be passed with it.")
					.toString();
			final ApiSubError error = new ApiValidationError("validation.msg.missing.locale.parameter", parameterName,
					numericalValueFormatted, defaultMessage);
			dataValidationErrors.add(error);

			throw new AbstractPlatformException("validation.msg.validation.errors.exist", "Validation errors exist.",
					400, dataValidationErrors);
		}

		try {
			Integer number = null;

			if (StringUtils.isNotBlank(numericalValueFormatted)) {

				String source = numericalValueFormatted.trim();

				final NumberFormat format = NumberFormat.getInstance(clientApplicationLocale);
				final DecimalFormat df = (DecimalFormat) format;
				final DecimalFormatSymbols symbols = df.getDecimalFormatSymbols();
				df.setParseBigDecimal(true);

				// http://bugs.sun.com/view_bug.do?bug_id=4510618
				final char groupingSeparator = symbols.getGroupingSeparator();
				if (groupingSeparator == '\u00a0') {
					source = source.replaceAll(" ", Character.toString('\u00a0'));
				}

				final Number parsedNumber = df.parse(source);

				final double parsedNumberDouble = parsedNumber.doubleValue();
				final int parsedNumberInteger = parsedNumber.intValue();

				if (source.contains(Character.toString(symbols.getDecimalSeparator()))) {
					throw new ParseException(source, 0);
				}

				if (!Double.valueOf(parsedNumberDouble).equals(Double.valueOf(Integer.valueOf(parsedNumberInteger)))) {
					throw new ParseException(source, 0);
				}

				number = parsedNumber.intValue();
			}

			return number;
		} catch (final ParseException e) {

			final Collection<ApiSubError> dataValidationErrors = new ArrayList<>();

			final ApiSubError error = new ApiValidationError("validation.msg.invalid.integer.format", parameterName,
					numericalValueFormatted,
					"The parameter " + parameterName + " has value: " + numericalValueFormatted
							+ " which is invalid integer value for provided locale of [" + clientApplicationLocale
							+ "].");
			dataValidationErrors.add(error);

			throw new AbstractPlatformException("validation.msg.validation.errors.exist", "Validation errors exist.",
					400, dataValidationErrors);
		}
	}

	public Integer convertToIntegerSanLocale(final String numericalValueFormatted, final String parameterName) {

		try {
			Integer number = null;

			if (StringUtils.isNotBlank(numericalValueFormatted)) {
				number = Integer.valueOf(numericalValueFormatted);
			}

			return number;
		} catch (final NumberFormatException e) {

			final Collection<ApiSubError> dataValidationErrors = new ArrayList<>();
			final ApiSubError error = new ApiValidationError("validation.msg.invalid.integer", parameterName,
					numericalValueFormatted, "The parameter " + parameterName + " has value: " + numericalValueFormatted
							+ " which is invalid integer.");
			dataValidationErrors.add(error);

			throw new AbstractPlatformException("validation.msg.validation.errors.exist", "Validation errors exist.",
					400, dataValidationErrors);
		}
	}

	public BigDecimal convertFrom(final String numericalValueFormatted, final String parameterName,
			final Locale clientApplicationLocale) {

		if (clientApplicationLocale == null) {

			final Collection<ApiSubError> dataValidationErrors = new ArrayList<>();
			final String defaultMessage = new StringBuilder(
					"The parameter '" + parameterName + "' requires a 'locale' parameter to be passed with it.")
					.toString();
			final ApiSubError error = new ApiValidationError("validation.msg.missing.locale.parameter", parameterName,
					numericalValueFormatted, defaultMessage);
			dataValidationErrors.add(error);

			throw new AbstractPlatformException("validation.msg.validation.errors.exist", "Validation errors exist.",
					400, dataValidationErrors);
		}

		try {
			BigDecimal number = null;

			if (StringUtils.isNotBlank(numericalValueFormatted)) {

				String source = numericalValueFormatted.trim();

				final NumberFormat format = NumberFormat.getNumberInstance(clientApplicationLocale);
				final DecimalFormat df = (DecimalFormat) format;
				final DecimalFormatSymbols symbols = df.getDecimalFormatSymbols();
				// http://bugs.sun.com/view_bug.do?bug_id=4510618
				final char groupingSeparator = symbols.getGroupingSeparator();
				if (groupingSeparator == '\u00a0') {
					source = source.replaceAll(" ", Character.toString('\u00a0'));
				}

				final NumberStyleFormatter numberFormatter = new NumberStyleFormatter();
				final Number parsedNumber = numberFormatter.parse(source, clientApplicationLocale);
				if (parsedNumber instanceof BigDecimal) {
					number = (BigDecimal) parsedNumber;
				} else {
					number = BigDecimal.valueOf(parsedNumber.doubleValue());
				}
			}

			return number;
		} catch (final ParseException e) {

			final Collection<ApiSubError> dataValidationErrors = new ArrayList<>();
			// ApiValidationError validationError = new ApiValidationError(object, message);
			final ApiSubError error = new ApiValidationError("validation.msg.invalid.decimal.format", parameterName,
					null,
					"The parameter " + parameterName + " has value: " + numericalValueFormatted
							+ " which is invalid decimal value for provided locale of [" + clientApplicationLocale
							+ "].");
			dataValidationErrors.add(error);

			throw new AbstractPlatformException("validation.msg.validation.errors.exist", "Validation errors exist.",
					400, dataValidationErrors);
		}
	}

	/** * TODO: Vishwas move all Locale related code to a separate Utils class * */
	public static Locale localeFromString(final String localeAsString) {

		if (StringUtils.isBlank(localeAsString)) {
			final Collection<ApiSubError> dataValidationErrors = new ArrayList<>();
			final ApiSubError error = new ApiValidationError("validation.msg.invalid.locale.format", "locale",
					localeAsString, "The parameter locale is invalid. It cannot be blank.");
			dataValidationErrors.add(error);

			throw new AbstractPlatformException("validation.msg.validation.errors.exist", "Validation errors exist.",
					400, dataValidationErrors);
		}

		String languageCode = "";
		String countryCode = "";
		String variantCode = "";

		final String[] localeParts = localeAsString.split("_");

		if (localeParts != null && localeParts.length == 1) {
			languageCode = localeParts[0];
		}

		if (localeParts != null && localeParts.length == 2) {
			languageCode = localeParts[0];
			countryCode = localeParts[1];
		}

		if (localeParts != null && localeParts.length == 3) {
			languageCode = localeParts[0];
			countryCode = localeParts[1];
			variantCode = localeParts[2];
		}

		return localeFrom(languageCode, countryCode, variantCode);
	}

	private static Locale localeFrom(final String languageCode, final String courntryCode, final String variantCode) {

		final Collection<ApiSubError> dataValidationErrors = new ArrayList<>();

		final List<String> allowedLanguages = Arrays.asList(Locale.getISOLanguages());
		if (!allowedLanguages.contains(languageCode.toLowerCase())) {
			final ApiSubError error = new ApiValidationError("validation.msg.invalid.locale.format", "locale",
					languageCode, "The parameter locale has an invalid language value " + languageCode + " .");
			dataValidationErrors.add(error);
		}

		if (StringUtils.isNotBlank(courntryCode.toUpperCase())) {
			final List<String> allowedCountries = Arrays.asList(Locale.getISOCountries());
			if (!allowedCountries.contains(courntryCode)) {
				final ApiSubError error = new ApiValidationError("validation.msg.invalid.locale.format", "locale",
						courntryCode, "The parameter locale has an invalid country value " + courntryCode + " .");
				dataValidationErrors.add(error);
			}
		}

		if (!dataValidationErrors.isEmpty()) {
			throw new AbstractPlatformException("validation.msg.validation.errors.exist", "Validation errors exist.",
					400, dataValidationErrors);
		}

		return new Locale(languageCode.toLowerCase(), courntryCode.toUpperCase(), variantCode);
	}

	private Locale extractLocaleValue(final JsonObject object) {
		Locale clientApplicationLocale = null;
		String locale = null;
		if (object.has("locale") && object.get("locale").isJsonPrimitive()) {
			final JsonPrimitive primitive = object.get("locale").getAsJsonPrimitive();
			locale = primitive.getAsString();
			clientApplicationLocale = localeFromString(locale);
		}
		return clientApplicationLocale;
	}
}
