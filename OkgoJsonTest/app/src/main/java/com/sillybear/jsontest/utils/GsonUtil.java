package com.sillybear.jsontest.utils;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class GsonUtil {
	public static <T> T parseJsonWithGson(String jsonData, Class<T> type) {
		Gson gson = new GsonBuilder().registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory()).create();
		T result = gson.fromJson(jsonData, type);
		return result;
	}

	// 将Json数组解析成相应的映射对象列表
	public static <T> List<T> parseJsonArrayWithGson(String jsonData, Class<T> type) {
		List<T> list  = new Gson().fromJson(jsonData,new TypeToken<List<T>>(){}.getType());
        return list;
	}

	public static String objectToJson(Object object) {
		Gson gson = new Gson();
		return gson.toJson(object);
	}

	public static <T> List<T> getListFromJSON(String json, Class<T[]> type) {
		T[] list = new Gson().fromJson(json, type);
		return Arrays.asList(list);
	}

	/**
	 * 转成map的
	 *
	 * @param gsonString
	 * @return
	 */
	public static <T> Map<String, T> parseJsonMaps(String gsonString) {
		Map<String, T> map = null;
		Gson gson = new Gson();
		map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
		}.getType());

		return map;
	}
	public static  class NullStringToEmptyAdapterFactory<T> implements TypeAdapterFactory {
		@SuppressWarnings("unchecked")
		public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
			Class<T> rawType = (Class<T>) type.getRawType();
			if (rawType != String.class) {
				return null;
			}
			return (TypeAdapter<T>) new StringNullAdapter();
		}
	}
	public static class StringNullAdapter extends TypeAdapter<String> {
		@Override
		public String read(JsonReader reader) throws IOException {
			// TODO Auto-generated method stub
			if (reader.peek() == JsonToken.NULL) {
				reader.nextNull();
				return "";
			}
			return reader.nextString();
		}
		@Override
		public void write(JsonWriter writer, String value) throws IOException {
			// TODO Auto-generated method stub
			if (value == null) {
				writer.nullValue();
				return;
			}
			writer.value(value);
		}
	}

	public static <T> T deepCopy(T src) throws IOException, ClassNotFoundException{
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(byteOut);
		out.writeObject(src);

		ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
		ObjectInputStream in =new ObjectInputStream(byteIn);
		T dest = (T) in.readObject();
		return dest;
	}

}
