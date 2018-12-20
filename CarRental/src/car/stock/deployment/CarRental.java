package car.stock.deployment;

import java.rmi.RemoteException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import org.restlet.Component;
import org.restlet.data.Protocol;




public class CarRental {

	public static void main(String[] args) {
		
		Component component = new Component();
		component.getServers().add(Protocol.HTTP, 8182); 
 
		// Attach the application to the component and start it  
		component.getDefaultHost().attach(new RouterApplication());
		try {
			component.start();  
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
