/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2013 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * =========================================================================== */
package eu.ibacz.o2sk.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * @author jan.jamrich@ibacz.eu
 *
 */
public class WebDriverFactory {
	
	public static WebDriver getFirefoxDriver() {
		ProfilesIni allProfiles = new ProfilesIni();
        FirefoxProfile profile = allProfiles.getProfile("webdriver");
        return new FirefoxDriver(profile);
	}
	
	/**
	 * Required binary Chrome driver placed in folder where System property <i>webdriver.chrome.driver</i> points to
	 * Binaries available at https://sites.google.com/a/chromium.org/chromedriver/downloads
	 * @return
	 */
	public static WebDriver getChromeDriver() {
		return new ChromeDriver();
	}
	
	public static WebDriver getIEDriver() {
		return new InternetExplorerDriver();
	}
	
}
