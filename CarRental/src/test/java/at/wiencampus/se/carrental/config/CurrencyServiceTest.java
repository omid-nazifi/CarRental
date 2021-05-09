package at.wiencampus.se.carrental.config;

import at.wiencampus.se.carrental.service.CurrencyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.jupiter.api.Assertions.assertNotNull;

//Ensure that the Soap Webservice is running before executing this test
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SoapClientConfig.class, TestContextConfiguration.class}, loader = AnnotationConfigContextLoader.class)
public class CurrencyServiceTest {

    @Autowired
    CurrencyService currencyService;

   /* @Test
    public void getCurrencyTest() {
        ConvertResponse response = currencyService.getCurrency(1, CurrencyEnum.EUR);
        assertNotNull(response);
    }*/
}
