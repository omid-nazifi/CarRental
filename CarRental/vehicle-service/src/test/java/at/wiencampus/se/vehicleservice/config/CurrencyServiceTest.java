package at.wiencampus.se.vehicleservice.config;

import at.wiencampus.se.common.dto.CurrencyConvertReply;
import at.wiencampus.se.common.dto.CurrencyEnum;
import at.wiencampus.se.vehicleservice.service.CurrencyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContextConfiguration.class}, loader = AnnotationConfigContextLoader.class)
public class CurrencyServiceTest {

    @Autowired
    CurrencyService currencyService;

    @Test
    public void getCurrencyTest() {
        CurrencyConvertReply response = currencyService.getCurrency(1, CurrencyEnum.EUR);
        assertNotNull(response);
    }
}
