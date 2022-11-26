package com.epam.lab.shop.utility;

import com.epam.lab.shop.constant.Constant;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class Parser {

    private static final Logger LOGGER = Logger.getLogger(Parser.class.getName());

    public Map<String, List<String>> parseXmlToAccessMap(String accessPresetFileFullPath) {
        Map<String, List<String>> accessMap = new HashMap<>();
        try {
            File file = new File(accessPresetFileFullPath);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName(Constant.CONSTRAINT);
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    for (int j = 0; j < eElement.getElementsByTagName(Constant.ROLE).getLength(); j++) {
                        String role = eElement.getElementsByTagName(Constant.ROLE).item(j).getTextContent();
                        String uri = eElement.getElementsByTagName(Constant.URL_PATTERN).item(0).getTextContent();
                        if (accessMap.containsKey(role)) {
                            List<String> uriList = accessMap.get(role);
                            uriList.add(uri);
                        } else {
                            List<String> uriList = new ArrayList<>();
                            uriList.add(uri);
                            accessMap.put(role, uriList);
                        }
                    }
                }
            }
        } catch (Exception exception) {
            LOGGER.severe(Constant.FAILED_TO_PARSE_MSG + exception);
        }
        return accessMap;
    }
}