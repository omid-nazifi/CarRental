package edu.campuswien.se.carrental.currencyconverter.logic;

import edu.campuswien.se.carrental.currencyconverter.CurrencyEnum;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.time.LocalDate;

/**
 * This Class is a kind of cache for Converter Impl that check version of eurofxref-daily.xml before calling converter's methods.
 */
public class ConverterProxy implements Converter {
    private static final String FILE_URL = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";
    private static final String CURRENCY_XML_FILENAME = "eurofxref-daily.xml";
    public static final int HOURS_OF_DAY = 24;

    private Converter service;
    private boolean forceToUpdate = false;

    public ConverterProxy(Converter service) {
        this.service = service;
    }

    @Override
    public double convertFromEuro(CurrencyEnum to, double amount) throws Exception {
        if (!isExchangeRateUpdate() || forceToUpdate) {
            updateExchangeRateFile();
        }
        return service.convertFromEuro(to, amount);
    }

    @Override
    public double convertFromDollar(CurrencyEnum to, double amount) throws Exception {
        if (!isExchangeRateUpdate() || forceToUpdate) {
            updateExchangeRateFile();
        }
        return service.convertFromDollar(to, amount);
    }

    @Override
    public double convertDollarToEuro(double dollarAmount) throws Exception {
        if (!isExchangeRateUpdate() || forceToUpdate) {
            updateExchangeRateFile();
        }
        return service.convertDollarToEuro(dollarAmount);
    }

    public boolean isExchangeRateUpdate() {
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            builderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            // parse XML file
            DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();

            URL resource = getClass().getClassLoader().getResource(CURRENCY_XML_FILENAME);
            Document document = documentBuilder.parse(new File(resource.toURI()));

            document.getDocumentElement().normalize();

            // get <Cubes>
            Node parentCube = document.getElementsByTagName("Cube").item(0);
            Node innerCube = ((Element) parentCube).getElementsByTagName("Cube").item(0);
            String time = ((Element) innerCube).getAttribute("time");
            LocalDate currentDate = LocalDate.parse(time);
            LocalDate today = LocalDate.now();
            if(currentDate.isBefore(today)) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void updateExchangeRateFile() {
        URL resource = this.getClass().getClassLoader().getResource(CURRENCY_XML_FILENAME);
        try (BufferedInputStream inputStream = new BufferedInputStream(new URL(FILE_URL).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(new File(resource.toURI()))) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
