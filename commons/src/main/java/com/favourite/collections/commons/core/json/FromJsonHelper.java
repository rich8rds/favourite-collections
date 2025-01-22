/* Collections #2024 */
package com.favourite.collections.commons.core.json;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Primary
@Component
public class FromJsonHelper {

	private final Gson gsonConverter;
	private final JsonParserHelper helperDelegator;

	public FromJsonHelper() {
		// this.gsonConverter = new Gson();
		GsonBuilder builder = new GsonBuilder();

		// builder.registerTypeAdapter(java.util.Date.class, new DateAdapter());
		builder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());
		// NOTE: was missing, necessary for GSON serialization with JDK 17's restrictive
		// module access
		// builder.registerTypeAdapter(LocalTime.class, new LocalTimeAdapter());
		// builder.registerTypeAdapter(ZonedDateTime.class, new JodaDateTimeAdapter());
		// builder.registerTypeAdapter(MonthDay.class, new JodaMonthDayAdapter());
		// builder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter());
		// builder.registerTypeAdapter(OffsetDateTime.class, new
		// OffsetDateTimeAdapter());
		this.gsonConverter = builder.setPrettyPrinting().create();
		this.helperDelegator = new JsonParserHelper();
	}

	public Map<String, Boolean> extractMap(final Type typeOfMap, final String json) {
		return this.gsonConverter.fromJson(json, typeOfMap);
	}

	public Map<String, String> extractDataMap(final Type typeOfMap, final String json) {
		return this.gsonConverter.fromJson(json, typeOfMap);
	}

	public Map<String, Object> extractObjectMap(final Type typeOfMap, final String json) {
		return this.gsonConverter.fromJson(json, typeOfMap);
	}

	public <T> T fromJson(final String json, final Type typeOfT) {
		return this.gsonConverter.fromJson(json, typeOfT);
	}

	public <T> T fromJson(final JsonElement json, final Class<T> classOfT) {
		return this.gsonConverter.fromJson(json, classOfT);
	}

	public <T> T fromJson(final String json, final Class<T> classOfT) {
		return this.gsonConverter.fromJson(json, classOfT);
	}

	public String toJson(final JsonElement jsonElement) {
		return this.gsonConverter.toJson(jsonElement);
	}

	public String toJson(final Object object) {
		return this.gsonConverter.toJson(object);
	}

	public JsonElement parse(final String json) {

		JsonElement parsedElement = null;
		if (StringUtils.isNotBlank(json)) {
			parsedElement = JsonParser.parseString(json);
		}
		return parsedElement;
	}

	// public boolean parameterExists(final String parameterName, final JsonElement
	// element) {
	// return this.helperDelegator.parameterExists(parameterName, element);
	// }
	//
	// public String extractStringNamed(final String parameterName, final
	// JsonElement element) {
	// return this.helperDelegator.extractStringNamed(parameterName, element, new
	// HashSet<String>());
	// }
	//
	// public String extractStringNamed(final String parameterName, final
	// JsonElement element,
	// final Set<String> parametersPassedInRequest) {
	// return this.helperDelegator.extractStringNamed(parameterName, element,
	// parametersPassedInRequest);
	// }
	//
	// public Long extractLongNamed(final String parameterName, final JsonElement
	// element) {
	// return this.helperDelegator.extractLongNamed(parameterName, element, new
	// HashSet<String>());
	// }
	//
	// public Long extractLongNamed(final String parameterName, final JsonElement
	// element, final
	// Set<String> parametersPassedInRequest) {
	// return this.helperDelegator.extractLongNamed(parameterName, element,
	// parametersPassedInRequest);
	// }
	//
	// public JsonArray extractJsonArrayNamed(final String parameterName, final
	// JsonElement
	// element) {
	// return this.helperDelegator.extractJsonArrayNamed(parameterName, element);
	// }
	//
	// public String[] extractArrayNamed(final String parameterName, final
	// JsonElement element) {
	// return this.helperDelegator.extractArrayNamed(parameterName, element, new
	// HashSet<String>());
	// }
	//
	// public String[] extractArrayNamed(final String parameterName, final
	// JsonElement element,
	// final Set<String> parametersPassedInRequest) {
	// return this.helperDelegator.extractArrayNamed(parameterName, element,
	// parametersPassedInRequest);
	// }
	//
	// public Boolean extractBooleanNamed(final String parameterName, final
	// JsonElement element) {
	// return this.helperDelegator.extractBooleanNamed(parameterName, element, new
	// HashSet<String>());
	// }
	//
	// public Boolean extractBooleanNamed(final String parameterName, final
	// JsonElement element,
	// final Set<String> parametersPassedInRequest) {
	// return this.helperDelegator.extractBooleanNamed(parameterName, element,
	// parametersPassedInRequest);
	// }
	//
	// public MonthDay extractMonthDayNamed(final String parameterName, final
	// JsonElement element)
	// {
	// return this.helperDelegator.extractMonthDayNamed(parameterName, element);
	// }
	//
	// public MonthDay extractMonthDayNamed(final String parameterName, final
	// JsonObject object,
	// final String dateFormat,
	// final Locale clientApplicationLocale) {
	// return this.helperDelegator.extractMonthDayNamed(parameterName, object,
	// dateFormat,
	// clientApplicationLocale);
	// }
	//
	// public LocalDate extractLocalDateNamed(final String parameterName, final
	// JsonElement
	// element) {
	// return this.helperDelegator.extractLocalDateNamed(parameterName, element, new
	// HashSet<>());
	// }
	//
	// public LocalDateTime extractLocalTimeNamed(final String parameterName, final
	// JsonElement
	// element) {
	// return this.helperDelegator.extractLocalTimeNamed(parameterName, element, new
	// HashSet<>());
	// }
	//
	// public LocalDateTime extractLocalTimeNamed(final String parameterName, final
	// JsonElement
	// element, final String dateFormat,
	// final Locale locale) {
	// return this.helperDelegator.extractLocalTimeNamed(parameterName, element,
	// dateFormat,
	// locale, new HashSet<>());
	// }
	//
	// public LocalDateTime extractLocalTimeNamed(final String parameterName, final
	// JsonElement
	// element, String timeFormat) {
	// return this.helperDelegator.extractLocalTimeNamed(parameterName, element,
	// timeFormat,
	// new HashSet<>());
	// }
	//
	// public LocalDate extractLocalDateNamed(final String parameterName, final
	// JsonElement
	// element, final String dateFormat,
	// final Locale locale) {
	// return this.helperDelegator.extractLocalDateNamed(parameterName,
	// element.getAsJsonObject(), dateFormat, locale, new HashSet<>());
	// }
	//
	// public LocalDate extractLocalDateNamed(final String parameterName, final
	// JsonElement
	// element,
	// final Set<String> parametersPassedInRequest) {
	// return this.helperDelegator.extractLocalDateNamed(parameterName, element,
	// parametersPassedInRequest);
	// }
	//
	// public LocalDate extractLocalDateAsArrayNamed(final String parameterName,
	// final JsonElement
	// element,
	// final Set<String> parametersPassedInRequest) {
	// return this.helperDelegator.extractLocalDateAsArrayNamed(parameterName,
	// element,
	// parametersPassedInRequest);
	// }
	//
	// public BigDecimal extractBigDecimalWithLocaleNamed(final String
	// parameterName, final
	// JsonElement element) {
	// return this.helperDelegator.extractBigDecimalWithLocaleNamed(parameterName,
	// element, new
	// HashSet<String>());
	// }
	//
	// public BigDecimal extractBigDecimalWithLocaleNamed(final String
	// parameterName, final
	// JsonElement element,
	// final Set<String>
	// parametersPassedInRequest) {
	// return this.helperDelegator.extractBigDecimalWithLocaleNamed(parameterName,
	// element,
	// parametersPassedInRequest);
	// }
	//
	// public BigDecimal extractBigDecimalNamed(final String parameterName, final
	// JsonElement
	// element, final Locale locale) {
	// return this.helperDelegator.extractBigDecimalNamed(parameterName,
	// element.getAsJsonObject(), locale, new HashSet<String>());
	// }
	//
	// public BigDecimal extractBigDecimalNamed(final String parameterName, final
	// JsonElement
	// element,
	// final Set<String> parametersPassedInRequest) {
	// return this.helperDelegator.extractBigDecimalNamed(parameterName,
	// element.getAsJsonObject(), Locale.US, parametersPassedInRequest);
	// }
	//
	// public Integer extractIntegerWithLocaleNamed(final String parameterName,
	// final JsonElement
	// element) {
	// return this.helperDelegator.extractIntegerWithLocaleNamed(parameterName,
	// element.getAsJsonObject(), new HashSet<String>());
	// }
	//
	// public Integer extractIntegerSansLocaleNamed(final String parameterName,
	// final JsonElement
	// element) {
	// return this.helperDelegator.extractIntegerSansLocaleNamed(parameterName,
	// element.getAsJsonObject(), new HashSet<String>());
	// }
	//
	// public Integer extractIntegerWithLocaleNamed(final String parameterName,
	// final JsonElement
	// element,
	// final Set<String> parametersPassedInRequest) {
	// return this.helperDelegator.extractIntegerWithLocaleNamed(parameterName,
	// element.getAsJsonObject(), parametersPassedInRequest);
	// }
	//
	// public Integer extractIntegerNamed(final String parameterName, final
	// JsonElement element,
	// final Locale locale) {
	// return this.helperDelegator.extractIntegerNamed(parameterName,
	// element.getAsJsonObject(), locale, new HashSet<String>());
	// }
	//
	// public Integer extractIntegerNamed(final String parameterName, final
	// JsonElement element,
	// final Set<String> parametersPassedInRequest) {
	// return this.helperDelegator.extractIntegerNamed(parameterName,
	// element.getAsJsonObject(), Locale.US, parametersPassedInRequest);
	// }
	//
	public Locale extractLocaleParameter(final JsonObject element) {
		return this.helperDelegator.extractLocaleParameter(element);
	}
	//
	// public String extractDateFormatParameter(final JsonObject element) {
	// return this.helperDelegator.extractDateFormatParameter(element);
	// }
	//
	// public String extractMonthDayFormatParameter(final JsonObject element) {
	// return this.helperDelegator.extractMonthDayFormatParameter(element);
	// }
	//
	// public JsonObject extractJsonObjectNamed(final String parameterName, final
	// JsonElement
	// element) {
	// return this.helperDelegator.extractJsonObjectNamed(parameterName, element);
	// }

}
