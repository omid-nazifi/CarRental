package at.wiencampus.se.carrental.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class CurrencyService extends WebServiceGatewaySupport {

    private static final Logger logger = LoggerFactory.getLogger(CurrencyService.class);
/*
    public ConvertResponse getCurrency(double dollar, CurrencyEnum currency) {
        ConvertRequest request = new ConvertRequest();
        request.setDollar(dollar);
        request.setCurrency(currency);

        logger.info("Requesting information for " + dollar + currency);

        return (ConvertResponse) getWebServiceTemplate().marshalSendAndReceive(request, new TokenHeaderRequestCallback("user", "password"));
    }*/
}
