package cucumber.Options;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@SelectClasspathResource("featureFile")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "stepDefination")
@ConfigurationParameter(
		 key = PLUGIN_PROPERTY_NAME,
		 value = "pretty, json:target/logging_html_report/logging_html_report.json, json:target/cucumber.json, io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
		)
public class TestRunner {
	static {
	    System.out.println("🔥 RUNNER IS EXECUTED");
	}
}
