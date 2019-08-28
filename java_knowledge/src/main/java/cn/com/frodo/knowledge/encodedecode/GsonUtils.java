package cn.com.frodo.knowledge.encodedecode;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

import java.io.Reader;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static com.google.gson.FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES;

/**
 * Description: Gson Utilities
 * @author frodoking
 * @version [V1, 2019/8/16 11:23]
 */
public class GsonUtils
{
    private static final Gson GSON = createGson(true);

    private static final Gson GSON_NO_NULLS = createGson(false);

    /**
     * Create the standard {@link Gson} configuration
     *
     * @return created gson, never null
     */
    public static Gson createGson()
    {
        return createGson(true);
    }

    /**
     * Create the standard {@link Gson} configuration
     *
     * @param serializeNulls
     *            whether nulls should be serialized
     *
     * @return created gson, never null
     */
    public static Gson createGson(final boolean serializeNulls)
    {
        final GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Date.class, new DateFormatter());
        builder.setFieldNamingPolicy(LOWER_CASE_WITH_UNDERSCORES);
        if (serializeNulls)
            builder.serializeNulls();
        return builder.create();
    }

    /**
     * Get reusable pre-configured {@link Gson} instance
     *
     * @return Gson instance
     */
    public static Gson getGson()
    {
        return GSON;
    }

    /**
     * Get reusable pre-configured {@link Gson} instance
     *
     * @param serializeNulls
     * @return Gson instance
     */
    public static Gson getGson(final boolean serializeNulls)
    {
        return serializeNulls ? GSON : GSON_NO_NULLS;
    }

    /**
     * Convert object to json
     *
     * @param object
     * @return json string
     */
    public static String toJson(final Object object)
    {
        return toJson(object, true);
    }

    /**
     * Convert object to json
     *
     * @param object
     * @param includeNulls
     * @return json string
     */
    public static String toJson(final Object object, final boolean includeNulls)
    {
        return includeNulls ? GSON.toJson(object) : GSON_NO_NULLS.toJson(object);
    }

    /**
     * Convert string to given type
     *
     * @param json
     * @param type
     * @return instance of type
     */
    public static <V> V fromJson(String json, Class<V> type)
    {
        return GSON.fromJson(json, type);
    }

    /**
     * Convert string to given type
     *
     * @param json
     * @param type
     * @return instance of type
     */
    public static <V> V fromJson(String json, Type type)
    {
        return GSON.fromJson(json, type);
    }

    /**
     * Convert content of reader to given type
     *
     * @param reader
     * @param type
     * @return instance of type
     */
    public static <V> V fromJson(Reader reader, Class<V> type)
    {
        return GSON.fromJson(reader, type);
    }

    /**
     * Convert content of reader to given type
     *
     * @param reader
     * @param type
     * @return instance of type
     */
    public static <V> V fromJson(Reader reader, Type type)
    {
        return GSON.fromJson(reader, type);
    }

    /**
     * Formatter for date formats present in the GitHub v2 and v3 API.
     */
    public static class DateFormatter implements JsonDeserializer<Date>, JsonSerializer<Date>
    {
        /** */
        String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"; //$NON-NLS-1$

        /** */
        String DATE_FORMAT_V2_1 = "yyyy/MM/dd HH:mm:ss Z"; //$NON-NLS-1$

        /** */
        String DATE_FORMAT_V2_2 = "yyyy-MM-dd'T'HH:mm:ss"; //$NON-NLS-1$

        private final DateFormat[] formats;

        /**
         * Create date formatter
         */
        public DateFormatter()
        {
            formats = new DateFormat[3];
            formats[0] = new SimpleDateFormat(DATE_FORMAT);
            formats[1] = new SimpleDateFormat(DATE_FORMAT_V2_1);
            formats[2] = new SimpleDateFormat(DATE_FORMAT_V2_2);
            final TimeZone timeZone = TimeZone.getTimeZone("Zulu"); //$NON-NLS-1$
            for (DateFormat format : formats)
                format.setTimeZone(timeZone);
        }

        @Override public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException
        {
            JsonParseException exception = null;
            final String value = json.getAsString();
            for (DateFormat format : formats)
            {
                try
                {
                    synchronized (format)
                    {
                        return format.parse(value);
                    }
                }
                catch (ParseException e)
                {
                    exception = new JsonParseException(e);
                }
            }
            if (exception != null)
            { // Always true here.
                throw exception;
            }
            // We'll never get here, but JDT's null analysis get's confused.
            return null;
        }

        @Override public JsonElement serialize(Date date, Type type, JsonSerializationContext context)
        {
            final DateFormat primary = formats[0];
            String formatted;
            synchronized (primary)
            {
                formatted = primary.format(date);
            }
            return new JsonPrimitive(formatted);
        }
    }
}
