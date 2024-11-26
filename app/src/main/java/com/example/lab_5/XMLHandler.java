package com.example.lab_5;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.List;

public class XMLHandler extends DefaultHandler {

    private List<Currency> currencyList;
    private Currency currency;
    private boolean inCube = false;

    public XMLHandler(List<Currency> currencyList) {
        this.currencyList = currencyList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("Cube") && attributes.getValue("currency") != null) {
            inCube = true;
            currency = new Currency();
            currency.setCode(attributes.getValue("currency"));
            currency.setRate(Double.parseDouble(attributes.getValue("rate")));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("Cube") && inCube) {
            currencyList.add(currency);
            inCube = false;
        }
    }
}
