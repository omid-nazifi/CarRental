package edu.campuswien.se.carrental.currencyconverter.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConverterProxyTest {

    @Test
    void isExchangeRateUpdate() {
        try {
            Converter converter = new ConverterImpl();
            ConverterProxy proxy = new ConverterProxy(converter);
            assertEquals(false, proxy.isExchangeRateUpdate());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}