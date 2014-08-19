package com.cyou.base.util;

import java.text.DateFormat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {

	private static Gson gson = null;
	
	static {
		gson = new GsonBuilder()
	     .enableComplexMapKeySerialization()
	     .setDateFormat(DateFormat.LONG)
	     .setPrettyPrinting()
	     .create();
	}
	public static Gson getGson(){
		return gson;
	}
}
