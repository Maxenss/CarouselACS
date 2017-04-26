package com.kontreras.carousel.utils;

import java.util.Properties;

public class ConfigHolder {

    public static String COMPORT_NAME;
    public static int MAIN_WINDOW_HEIGHT;
	public static int MAIN_WINDOW_WIDTH;
    public static String GET_DATA_CMD;

	
	public static void init(Properties prop) {
		MAIN_WINDOW_HEIGHT = Integer.parseInt(prop.getProperty("height"));
		MAIN_WINDOW_WIDTH = Integer.parseInt(prop.getProperty("width"));
        COMPORT_NAME = prop.getProperty("comport");
        GET_DATA_CMD = prop.getProperty("getdatacmd");
	}

}
