package data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Car {
	private UUID id;
	private String model;
	private String city;
	private Calendar fabricationDate;
	private List<DateInterval> reservation;
	
	public Car(String model, String city, Calendar fabricationDate, List<DateInterval> reservation) {
		this.id = UUID.randomUUID();
		this.model = model;
		this.city = city;
		this.fabricationDate = fabricationDate;
		this.reservation = reservation;
	}
	
	public Car(Element carNode) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		id = UUID.fromString(carNode.getAttribute("id"));
		model = carNode.getElementsByTagName("model").item(0).getTextContent();
		city = carNode.getElementsByTagName("city").item(0).getTextContent();
		fabricationDate = new GregorianCalendar();
		
		
		reservation = new ArrayList<>();
		try {
			fabricationDate.setTime(sdf.parse(carNode.getElementsByTagName("fabday").item(0).getTextContent()));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		Node reservationNode = carNode.getElementsByTagName("reservation").item(0);
		if(reservationNode.getNodeType() == Node.ELEMENT_NODE) {
			NodeList intervalNodes = ((Element) reservationNode).getElementsByTagName("interval");
			for(int i = 0; i < intervalNodes.getLength(); i++) {
//				System.out.println("-" +intervalNodes.item(i).getTextContent() + "-");
				reservation.add(new DateInterval(intervalNodes.item(i).getTextContent()));
			}
		}
		
		
	}

	
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String res = id.toString() + ";" + model + ";" + city  + ";" +  sdf.format(fabricationDate.getTime()) + ";";
//		System.out.println(reservation);
		for(int i = 0; i < reservation.size() ; i++) {
			if(i != 0)
				res += ",";
			res += reservation.get(i);
		}
		return res;
	}
	
	public Element addElement(Document doc) {
		Element e = doc.createElement("car");
		e.setAttribute("id", id.toString());
		
		Element modelNode = doc.createElement("model");
		modelNode.appendChild(doc.createTextNode(model));
		e.appendChild(modelNode);
		
		Element cityNode = doc.createElement("city");
		cityNode.appendChild(doc.createTextNode(city));
		e.appendChild(cityNode);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Element fabNode = doc.createElement("fabday");
		fabNode.appendChild(doc.createTextNode(sdf.format(fabricationDate.getTime())));
		e.appendChild(fabNode);
		
		Element reserNode = doc.createElement("reservation");
		for(DateInterval c: reservation) {
			Element dateIntervalNode = doc.createElement("interval");
			dateIntervalNode.appendChild(doc.createTextNode(c.toString()));
			reserNode.appendChild(dateIntervalNode);
		}
		e.appendChild(reserNode);
		
		return e;
	}

	
}
