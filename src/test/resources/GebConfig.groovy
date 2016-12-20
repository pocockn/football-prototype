import org.openqa.selenium.firefox.FirefoxDriver

driver = { new FirefoxDriver() }

baseUrl = "http://localhost:5050"

reportsDir = new File("target/geb-reports")
reportsOnTestFailureOnly = true