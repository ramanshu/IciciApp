package com.icici.mobi.skeleton;
import java.util.HashMap;


public class OptionSet extends HashMap<String, ScreenOption> {
	
	ScreenOption getOptionByID(String id)
	{
		return get(id);
	}

}
