package com.target.retail.config;

public interface AppConfig {

	public void setProperty(String name, String value);

	public String getProperty(String value);

	public boolean getBoolean(String name);

	public int getInt(String name);

	public long getLong(String name);

}
