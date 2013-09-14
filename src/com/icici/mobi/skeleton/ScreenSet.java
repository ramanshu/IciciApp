package com.icici.mobi.skeleton;
import java.util.HashMap;


public class ScreenSet extends HashMap<String,Screen> {

	Screen getStringByID(String id)
	{
		return get(id);
	}

}
