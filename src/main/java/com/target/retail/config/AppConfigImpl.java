package com.target.retail.config;

import java.io.Serializable;
import java.util.Properties;

public class AppConfigImpl implements AppConfig, Serializable {

	private static final long serialVersionUID = -2591529402375044679L;

	private Properties properties;

	public AppConfigImpl(Properties properties) {
		if (properties == null)
			throw new IllegalArgumentException("Property file missing at construction");
		this.properties = properties;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	@Override
	public void setProperty(String name, String value) {
		properties.setProperty(name, value);
	}

	@Override
	public String getProperty(String value) {
		String s = properties.getProperty(value);
		if (s != null)
			s = s.trim();
		return s;
	}

	@Override
	public boolean getBoolean(String name) {
		String propertyValue = properties.getProperty(name);
		propertyValue.trim();
		if (!propertyValue.isEmpty()) {
			Boolean b = Boolean.getBoolean(propertyValue);
			return b;
		} else {
			return false;
		}
	}

	@Override
	public int getInt(String name) {
		String propertyValue = properties.getProperty(name);
		if (propertyValue == null)
			return 0;
		propertyValue.trim();
		int value = Integer.parseInt(propertyValue);
		if (value == Integer.MIN_VALUE) {
			if (!("" + propertyValue).equals("" + Integer.MIN_VALUE)) {
				return 0;
			}
		}
		return value;
	}

	@Override
	public long getLong(String name) {
		String propertyValue = properties.getProperty(name);
		if (propertyValue == null)
			return 0;
		propertyValue.trim();
		long value = Long.parseLong(propertyValue);
		if (value == Long.MIN_VALUE) {
			if (!("" + propertyValue).equals("" + Long.MIN_VALUE)) {
				return 0;
			}
		}
		return value;
	}
}
