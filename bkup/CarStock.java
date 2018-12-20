package car.stock.deployment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;
import data.*;

public class CarStock {
	public List<String> checkAvailableCars(){
		List<String> availableCars = new ArrayList<String>();
		Calendar fabricationDate = new GregorianCalendar(2001, 5, 3);
		List<DateInterval> reservation = new ArrayList<DateInterval>();
		reservation.add(new DateInterval(new GregorianCalendar(2001, 5,5), new GregorianCalendar(2001, 5, 10)));
		reservation.add(new DateInterval(new GregorianCalendar(2001, 5, 13), new GregorianCalendar(2001, 5, 18)));
		reservation.add(new DateInterval(new GregorianCalendar(2001, 5, 20), new GregorianCalendar(2001, 5, 30)));
		availableCars.add((new Car("modelo1", "London", fabricationDate, reservation)).toString());
		availableCars.add((new Car("modelo2", "Paris", fabricationDate, reservation)).toString());
		availableCars.add((new Car("modelo3", "Amsterdam", fabricationDate, reservation)).toString());
		availableCars.add((new Car("modelo4", "Berlim", fabricationDate, reservation)).toString());
		return availableCars;
		
	}
	
	public void updateCarAvailability(List<UUID> ids){
		
	}
}
