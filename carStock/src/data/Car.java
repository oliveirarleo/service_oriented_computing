package data;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

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

	
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return id.toString() + ";" + model + ";" + city  + ";" +  sdf.format(fabricationDate.getTime()) + ";" + reservation;
	}

	
}
