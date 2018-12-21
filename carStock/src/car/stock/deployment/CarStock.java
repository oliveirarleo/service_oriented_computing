package car.stock.deployment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import data.*;



public class CarStock {
	public String checkAvailableCars(String search){
		String response = new String();
		
		String filePath = new String("/Users/leonardo/tsp/files/project/carStock/db/cars.xml");
		
		NodeList nodes = null;
		Document doc = null;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.parse(filePath);
			XPath xPath = XPathFactory.newInstance().newXPath();
			
			nodes = (NodeList)xPath.evaluate(search, doc, XPathConstants.NODESET);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		if(nodes != null && doc != null) {
			for (int i = 0; i < nodes.getLength(); i++) {
				if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
					Element carElement = (Element) nodes.item(i);
					Car carro = new Car(carElement);
					response += carro.toString()+"\n";
				}
				
			}
		}
		return response;
	}
	
	public String updateCarAvailability(String search, String dates){
		String filePath = new String("/Users/leonardo/tsp/files/project/carStock/db/cars.xml");
		
		String[] dateIntervals = dates.split(",");
		
		NodeList nodes = null;
		Document doc = null;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.parse(filePath);
			XPath xPath = XPathFactory.newInstance().newXPath();
			
			nodes = (NodeList)xPath.evaluate(search, doc, XPathConstants.NODESET);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		if(nodes != null && doc != null) {
			for (int i = 0; i < nodes.getLength(); i++) {
				for(int j = 0; j < dateIntervals.length; j++) {
					if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
						Element carsElement = (Element) nodes.item(i);
						Node reservationNode = carsElement.getElementsByTagName("reservation").item(0);
						if (reservationNode.getNodeType() == Node.ELEMENT_NODE) {
							Element reservationElement = (Element) reservationNode;
							Element interval = doc.createElement("interval");
							interval.appendChild(doc.createTextNode(dateIntervals[j]));
							reservationElement.appendChild(interval);
						}
						
					}
				}
				
			}
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer;
			try {
				transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(filePath));
				transformer.transform(source, result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "success\n";
	}
}
