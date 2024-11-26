package com.example.lab_5;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Parser {

    public static List<Currency> parse(InputStream inputStream) {
        List<Currency> currencyList = new ArrayList<>();

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            XMLHandler handler = new XMLHandler(currencyList);
            saxParser.parse(inputStream, handler);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return currencyList;
    }
}
