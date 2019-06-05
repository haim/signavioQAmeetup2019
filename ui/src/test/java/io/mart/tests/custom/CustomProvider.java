package io.mart.tests.custom;

import java.net.MalformedURLException;
import java.net.URL;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CustomProvider implements WebDriverProvider {
	
	@Override
	public WebDriver createDriver(DesiredCapabilities capabilities) {
		capabilities.setCapability("enableVNC", true);
		capabilities.setCapability("name", "myCoolTestName");    // session have this name on UI
		
		FirefoxOptions firefoxOptions = new FirefoxOptions();
		firefoxOptions.setAcceptInsecureCerts(true);
		firefoxOptions.merge(capabilities);
		try {
			return new RemoteWebDriver(new URL("http://172.16.11.19:4446/wd/hub"), firefoxOptions);
		} catch (final MalformedURLException e) {
			throw new RuntimeException("Unable to create driver", e);
		}
	}
}