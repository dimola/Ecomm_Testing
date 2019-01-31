package dataProviders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.NotFoundException;

public class ConfigFileReader {

	private Properties properties;
	private final String propertyFilePath = "configs/Configuration.properties";

	public ConfigFileReader() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new NotFoundException("Configuration.properties not found at " + propertyFilePath);
		}
	}

	public long getImplicitlyWait() {
		String implicitlyWait = properties.getProperty("implicitlyWait");
		if (implicitlyWait != null)
			return Long.parseLong(implicitlyWait);
		else
			throw new NotFoundException("implicitlyWait not specified in the Configuration.properties file.");
	}

	public String getHost() {
		String url = properties.getProperty("url");
		if (url != null)
			return url;
		else
			throw new NotFoundException("url not specified in the Configuration.properties file.");
	}

	public String getLoginPagePath() {
		String loginPageUrl = properties.getProperty("login-page-path");
		if (loginPageUrl != null) {
			return loginPageUrl;
		} else {
			throw new NotFoundException("login url not specified in the Configuration.properties file.");
		}

	}
}
