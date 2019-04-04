package dataProviders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.NotFoundException;

public class ConfigFileReader {

	private static final long DEFAULT_TIMEOUT = 50;
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
			System.err.println("Configuration.properties not found at " + propertyFilePath);
			System.exit(1); 
		}
	}

	public long getImplicitlyWait() {
		String implicitlyWait = properties.getProperty("implicitlyWait");
		if (implicitlyWait != null)
			return Long.parseLong(implicitlyWait);
		else
			return DEFAULT_TIMEOUT;

	}

	public String getHost() {
		String url = properties.getProperty("url");
		if (url != null)
			return url;
		else
			throw new NotFoundException("url not specified in the Configuration.properties file.");
	}
}
