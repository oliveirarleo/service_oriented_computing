package car.stock.deployment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;
import data.*;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;



public class CarStock {
	public String checkAvailableCars(){
		String response = new String();
		try {
			SAXBuilder builder = new SAXBuilder();
	        // build a JDOM2 Document using the SAXBuilder.
	        Document doc = builder.build(new File("/Users/leonardo/tsp/files/project/carStock/db/cars.xml"));
	        Element rootNode = doc.getRootElement();
	        
	        List list = rootNode.getChildren("car");
	        for (int i = 0; i < list.size(); i++) {

				Element node = (Element) list.get(i);
				response += node.getAttribute("id")+ ";";
				response += node.getChildText("model") + ";";
				response += node.getChildText("city") + ";";
				response += "Fabr. Day : " + node.getChildText("fabday") + ";";
				Element reservations = node.getChild("reservation");
				for (Element day : reservations.getChildren("day")) {
					response += day.getText() + ",";
				}
	 		}
	        
		} catch (Exception io) {
			io.printStackTrace();
		} 
		
		
//		List<Car> availableCars = new ArrayList<Car>();
//		Calendar fabricationDate = new GregorianCalendar(2001, 5, 3);
//		List<DateInterval> reservation = new ArrayList<DateInterval>();
//		reservation.add(new DateInterval(new GregorianCalendar(2001, 5,5), new GregorianCalendar(2001, 5, 10)));
//		reservation.add(new DateInterval(new GregorianCalendar(2001, 5, 13), new GregorianCalendar(2001, 5, 18)));
//		reservation.add(new DateInterval(new GregorianCalendar(2001, 5, 20), new GregorianCalendar(2001, 5, 30)));
//		availableCars.add((new Car("modelo1", "London", fabricationDate, reservation)));
//		availableCars.add((new Car("modelo2", "Paris", fabricationDate, reservation)));
//		availableCars.add((new Car("modelo3", "Amsterdam", fabricationDate, reservation)));
//		availableCars.add((new Car("modelo4", "Berlim", fabricationDate, reservation)));
//		
//		for (Car ac : availableCars) {
//			response = response + "\n" + ac;
//		}
		
		return response;
		
	}
	
	public String updateCarAvailability(String ids){
		
		System.out.println(ids);
		return "success";
//		try {
//			SAXBuilder builder = new SAXBuilder();
//	        // build a JDOM2 Document using the SAXBuilder.
//	        Document doc = builder.build(new File("/Users/leonardo/tsp/files/project/carStock/db/cars.xml"));
//	        Element rootNode = doc.getRootElement();
//	        
//	        List list = rootNode.getChildren("car");
//	        
//	        
//		} catch (Exception io) {
//			io.printStackTrace();
//		} 
	}
}
