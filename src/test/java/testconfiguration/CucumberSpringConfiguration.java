package testconfiguration;

import io.cucumber.java.Before;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Class to configure Cucumber to use SpringBoot to manage the application context.
 *
 * @author helk
 *
 */
@CucumberContextConfiguration
@SpringBootTest(classes = TestConfiguration.class)
public class CucumberSpringConfiguration {

    @Before
    public void setupCucumberSpringContext(){
        // Dummy method so cucumber will recognize this class as glue
        // and use its context configuration.
    }

}
