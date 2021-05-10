package edu.campuswien.se.carrental.currencyconverter.logic;

import at.wiencampus.se.common.dto.CurrencyEnum;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ConverterImpl implements Converter {
    private final static String CURRENCY_XML_FILENAME = "eurofxref-daily.xml";

    private Map<CurrencyEnum, Double> amountList = new HashMap<CurrencyEnum, Double>();

    public ConverterImpl() {
        amountList = readDailyCurrencyXml();
    }

    @Override
    public double convertFromEuro(CurrencyEnum to, double amount) throws Exception {
        if (!amountList.containsKey(to) || amountList.get(to) == 0) {
            throw new Exception("The rate value of the "+to +" is non-existent or wrong!");
        }
        return amount * amountList.get(to);
    }

    @Override
    public double convertFromDollar(CurrencyEnum to, double dollarAmount) throws Exception {
        double euroAmount = convertDollarToEuro(dollarAmount);
        if (to == CurrencyEnum.EUR) {
            return euroAmount;
        }

        double newAmount = convertFromEuro(to, euroAmount);
        return newAmount;
    }

    @Override
    public double convertDollarToEuro(double dollarAmount) throws Exception {
        if (!amountList.containsKey(CurrencyEnum.USD) || amountList.get(CurrencyEnum.USD) == 0) {
            throw new Exception("The rate value of the dollar is non-existent or wrong!");
        }

        return dollarAmount / amountList.get(CurrencyEnum.USD);
    }

    /**
     * Reads file eurofxref-daily.xml in the resources folder and returns a map of currency and amount
     */
    private Map<CurrencyEnum, Double> readDailyCurrencyXml() {
        Map<CurrencyEnum, Double> result = new HashMap<CurrencyEnum, Double>();

        // Instantiate the Factory
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        try {
            builderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            // parse XML file
            DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();

            URL resource = getClass().getClassLoader().getResource(CURRENCY_XML_FILENAME);
            Document document = documentBuilder.parse(new File(resource.toURI()));

            document.getDocumentElement().normalize();

            // get <Cubes>
            Node parentCube = document.getElementsByTagName("Cube").item(0);
            Node innerCube = ((Element) parentCube).getElementsByTagName("Cube").item(0);

            NodeList cubeItems = ((Element) innerCube).getElementsByTagName("Cube");

            for (int i = 0; i < cubeItems.getLength(); i++) {
                Node node = cubeItems.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String currency = element.getAttribute("currency");
                    String rate = element.getAttribute("rate");

                    result.put(CurrencyEnum.fromValue(currency), Double.valueOf(rate));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


}




