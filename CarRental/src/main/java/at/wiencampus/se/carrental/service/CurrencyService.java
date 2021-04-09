package at.wiencampus.se.carrental.service;

import at.wiencampus.se.carrental.gen.Convert;
import at.wiencampus.se.carrental.gen.ConvertResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

public class CurrencyService extends WebServiceGatewaySupport {

    private static final Logger logger = LoggerFactory.getLogger(CurrencyService.class);

    public ConvertResponse getCurrency(double dollar, String currency) {
        JAXBElement<Double> jaxbDollar = new JAXBElement<>(new QName(Double.class.getSimpleName()), Double.class, dollar);
        JAXBElement<String> jaxbCurrency = new JAXBElement<>(new QName(String.class.getSimpleName()), String.class, currency);
        Convert convert = new Convert();
        convert.setDollar(jaxbDollar);
        convert.setCurrency(jaxbCurrency);

        logger.info("Requesting information for " + dollar + currency);

        return (ConvertResponse) getWebServiceTemplate().marshalSendAndReceive(convert);
    }

}
