package edu.campuswien.se.carrental.currencyconverter;

import edu.campuswien.se.carrental.currencyconverter.ConvertRequest;
import edu.campuswien.se.carrental.currencyconverter.ConvertResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CurrencyEndpoint {
	public CurrencyEndpoint() {
	}

	@PayloadRoot(namespace = WebServiceConfig.NAMESPACE_URI, localPart = "convertRequest")
	@ResponsePayload
	public ConvertResponse convert(@RequestPayload ConvertRequest request) {
		ConvertResponse response = new ConvertResponse();
		response.setPrice(11.22);

		return response;
	}
}
