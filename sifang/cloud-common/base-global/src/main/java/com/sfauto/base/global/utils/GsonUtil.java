package com.sfauto.base.global.utils;

import com.google.common.collect.Lists;
import com.google.gson.*;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.internal.bind.util.ISO8601Utils;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

public class GsonUtil {
    private static final DateFormat enUsFormat = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.US);
    private static final DateFormat localFormat = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT);
    private static final DateFormat cn1Format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final DateFormat cn2Format = new SimpleDateFormat("yyyy-MM-dd");

    public static boolean isNull(JsonElement element) {
        return element == null || element.isJsonNull();
    }

    public static boolean isNotNull(JsonElement element) {
        return !isNull(element);
    }

    public static Long getLong(JsonObject json, String property) {
        return getAsLong(json.get(property));
    }

    public static long getPrimitiveLong(JsonObject json, String property) {
        return getAsPrimitiveLong(json.get(property));
    }

    public static Integer getInteger(JsonObject json, String property) {
        return getAsInteger(json.get(property));
    }

    public static int getPrimitiveInteger(JsonObject json, String property) {
        return getAsPrimitiveInt(json.get(property));
    }

    public static Double getDouble(JsonObject json, String property) {
        return getAsDouble(json.get(property));
    }

    public static double getPrimitiveDouble(JsonObject json, String property) {
        return getAsPrimitiveDouble(json.get(property));
    }

    public static Float getFloat(JsonObject json, String property) {
        return getAsFloat(json.get(property));
    }

    public static float getPrimitiveFloat(JsonObject json, String property) {
        return getAsPrimitiveFloat(json.get(property));
    }

    public static Boolean getBoolean(JsonObject json, String property) {
        return getAsBoolean(json.get(property));
    }

    public static String getString(JsonObject json, String property) {
        return getAsString(json.get(property));
    }

    public static String getAsString(JsonElement element) {
        return isNull(element) ? null : element.getAsString();
    }

    public static Long getAsLong(JsonElement element) {
        return isNull(element) ? null : element.getAsLong();
    }

    public static long getAsPrimitiveLong(JsonElement element) {
        Long r = getAsLong(element);
        return r == null ? 0l : r;
    }

    public static Integer getAsInteger(JsonElement element) {
        return isNull(element) ? null : element.getAsInt();
    }

    public static int getAsPrimitiveInt(JsonElement element) {
        Integer r = getAsInteger(element);
        return r == null ? 0 : r;
    }

    public static Boolean getAsBoolean(JsonElement element) {
        return isNull(element) ? null : element.getAsBoolean();
    }

    public static boolean getAsPrimitiveBool(JsonElement element) {
        Boolean r = getAsBoolean(element);
        return r != null && r.booleanValue();
    }

    public static Double getAsDouble(JsonElement element) {
        return isNull(element) ? null : element.getAsDouble();
    }

    public static double getAsPrimitiveDouble(JsonElement element) {
        Double r = getAsDouble(element);
        return r == null ? 0d : r;
    }

    public static Float getAsFloat(JsonElement element) {
        return isNull(element) ? null : element.getAsFloat();
    }

    public static float getAsPrimitiveFloat(JsonElement element) {
        Float r = getAsFloat(element);
        return r == null ? 0f : r;
    }

    public static Integer[] getIntArray(JsonObject o, String string) {
        JsonArray jsonArray = getAsJsonArray(o.getAsJsonArray(string));
        if (jsonArray == null) {
            return null;
        }

        List<Integer> result = Lists.newArrayList();
        for (int i = 0; i < jsonArray.size(); i++) {
            result.add(jsonArray.get(i).getAsInt());
        }

        return result.toArray(new Integer[0]);
    }

    public static Long[] getLongArray(JsonObject o, String string) {
        JsonArray jsonArray = getAsJsonArray(o.getAsJsonArray(string));
        if (jsonArray == null) {
            return null;
        }

        List<Long> result = Lists.newArrayList();
        for (int i = 0; i < jsonArray.size(); i++) {
            result.add(jsonArray.get(i).getAsLong());
        }

        return result.toArray(new Long[0]);
    }

    public static JsonArray getAsJsonArray(JsonElement element) {
        return element == null ? null : element.getAsJsonArray();
    }

    public static <T> T fromJson(String json, TypeToken<T> typeToken, final DateFormat dateFormat) {
        /**
         * 重写map的反序列化
         */
        GsonBuilder gsonBuilder = new GsonBuilder();

        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {

            @Override
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                if (json.getAsJsonPrimitive().isNumber()) {
                    return new Date(json.getAsJsonPrimitive().getAsLong());
                } else {
                    try {
                        return dateFormat.parse(json.getAsJsonPrimitive().getAsString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            }
        });

        Gson gson = gsonBuilder.registerTypeAdapter(new TypeToken<Map<String, Object>>() {
        }.getType(), new MapTypeAdapter()).create();

        return gson.fromJson(json, typeToken.getType());
    }

    public static <T> T fromJson(String json, TypeToken<T> typeToken) {
        /**
         * 重写map的反序列化
         */
        GsonBuilder gsonBuilder = new GsonBuilder();

        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            @Override
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                if (json.getAsJsonPrimitive().isNumber()) {
                    return new Date(json.getAsJsonPrimitive().getAsLong());
                } else {
                    return deserializeToDate(json.getAsJsonPrimitive().getAsString());
                }
            }
        });

        Gson gson = gsonBuilder.registerTypeAdapter(new TypeToken<Map<String, Object>>() {
        }.getType(), new MapTypeAdapter()).create();

        return gson.fromJson(json, typeToken.getType());
    }

    private static synchronized Date deserializeToDate(String json) {
        try {
            return localFormat.parse(json);
        } catch (ParseException ignored) {
        }
        try {
            return enUsFormat.parse(json);
        } catch (ParseException ignored) {
        }
        try {
            return cn1Format.parse(json);
        } catch (ParseException ignored) {
        }
        try {
            return cn2Format.parse(json);
        } catch (ParseException ignored) {
        }
        try {
            return ISO8601Utils.parse(json, new ParsePosition(0));
        } catch (ParseException e) {
            throw new JsonSyntaxException(json, e);
        }
    }

    static class MapTypeAdapter extends TypeAdapter<Object> {

        @Override
        public Object read(JsonReader in) throws IOException {
            JsonToken token = in.peek();
            switch (token) {
                case BEGIN_ARRAY:
                    List<Object> list = new ArrayList<Object>();
                    in.beginArray();
                    while (in.hasNext()) {
                        list.add(read(in));
                    }
                    in.endArray();
                    return list;

                case BEGIN_OBJECT:
                    Map<String, Object> map = new LinkedTreeMap<String, Object>();
                    in.beginObject();
                    while (in.hasNext()) {
                        map.put(in.nextName(), read(in));
                    }
                    in.endObject();
                    return map;

                case STRING:
                    return in.nextString();

                case NUMBER:
                    /**
                     * 改写数字的处理逻辑，将数字值分为整型与浮点型。
                     */
                    double dbNum = in.nextDouble();

                    // 数字超过long的最大值，返回浮点类型
                    if (dbNum > Long.MAX_VALUE) {
                        return dbNum;
                    }

                    // 判断数字是否为整数值
                    long lngNum = (long) dbNum;
                    if (dbNum == lngNum) {
                        return lngNum;
                    } else {
                        return dbNum;
                    }

                case BOOLEAN:
                    return in.nextBoolean();

                case NULL:
                    in.nextNull();
                    return null;

                default:
                    throw new IllegalStateException();
            }
        }

        @Override
        public void write(JsonWriter out, Object value) throws IOException {
            // 序列化无需实现
        }

    }


}

