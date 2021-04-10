package edu.campuswien.se.carrental.currencyconverter.endpoint;

import edu.campuswien.se.carrental.currencyconverter.ConvertRequest;
import edu.campuswien.se.carrental.currencyconverter.ConvertResponse;
import edu.campuswien.se.carrental.currencyconverter.config.WebServiceConfig;
import edu.campuswien.se.carrental.currencyconverter.logic.Converter;
import edu.campuswien.se.carrental.currencyconverter.logic.ConverterImpl;
import edu.campuswien.se.carrental.currencyconverter.logic.ConverterProxy;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * Setup an endpoint to serve the request
 */
@Endpoint
public class CurrencyEndpoint {
	public CurrencyEndpoint() {
	}

	@PayloadRoot(namespace = WebServiceConfig.NAMESPACE_URI, localPart = "convertRequest")
	@ResponsePayload
	public ConvertResponse convert(@RequestPayload ConvertRequest request) {
		ConvertResponse response = new ConvertResponse();
		try {
			Converter converter = new ConverterImpl();
			Converter converterProxy = new ConverterProxy(converter);
			double convertedAmount = converterProxy.convertFromDollar(request.getCurrency(), request.getDollar());
			response.setPrice(convertedAmount);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Something went wrong, please try again!");
		}

		return response;
	}
}
