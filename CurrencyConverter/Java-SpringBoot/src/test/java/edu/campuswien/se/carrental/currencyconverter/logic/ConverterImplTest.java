package edu.campuswien.se.carrental.currencyconverter.logic;

import edu.campuswien.se.carrental.currencyconverter.CurrencyEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConverterImplTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void convertFromEuro() {
        try {
            Converter converter = new ConverterImpl();
            assertEquals(8.5378, converter.convertFromEuro(CurrencyEnum.GBP, 10));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void convertFromDollar() {
        try {
            Converter converter = new ConverterImpl();
            assertEquals(7.271782642023679, converter.convertFromDollar(CurrencyEnum.GBP, 10));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void convertDollarToEuro() {
        try {
            Converter converter = new ConverterImpl();
            assertEquals(8.517162081594414, converter.convertDollarToEuro(10));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}