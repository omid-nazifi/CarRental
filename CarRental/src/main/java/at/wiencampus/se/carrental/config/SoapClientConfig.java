package at.wiencampus.se.carrental.config;

import at.wiencampus.se.carrental.service.CurrencyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class SoapClientConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("at.wiencampus.se.carrental.gen");
        return marshaller;
    }

    @Bean
    public CurrencyService currencyService(Jaxb2Marshaller marshaller) {
        CurrencyService client = new CurrencyService();
        client.setDefaultUri("http://127.0.0.1:5000/soap/convert?wsdl");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
