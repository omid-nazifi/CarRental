package edu.campuswien.se.carrental.currencyconverter.logic;

import edu.campuswien.se.carrental.currencyconverter.CurrencyEnum;

public interface Converter {
    /**
     * Converts given amount from euro to specified currency
     * @param to {@link CurrencyEnum} euro amount will be converted to this currency
     * @param amount euro amount
     * @return converted amount
     * @throws Exception
     */
    public double convertFromEuro(CurrencyEnum to, double amount) throws Exception;

    /**
     * Converts given amount from dollar to specified currency
     * @param to {@link CurrencyEnum} dollar amount will be converted to this currency
     * @param amount dollar amount
     * @return converted amount
     * @throws Exception
     */
    public double convertFromDollar(CurrencyEnum to, double amount) throws Exception;

    /**
     * Converts given amount (as dollar) to euro
     * @param dollarAmount dollar amount
     * @return converted amount
     * @throws Exception
     */
    public double convertDollarToEuro(double dollarAmount) throws Exception;
}
