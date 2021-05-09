package at.wiencampus.se.vehicleservice.configuration;

import at.wiencampus.se.vehicleservice.service.CurrencyService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class SoapClientConfig {

    @Value("${soap.generatepackage}")
    private String contextPath;

    @Value("${soap.service.url}")
    private String serviceUrl;

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath(contextPath);
        return marshaller;
    }

    @Bean
    public CurrencyService currencyService(Jaxb2Marshaller marshaller) {
        CurrencyService client = new CurrencyService();
        client.setDefaultUri(serviceUrl);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
