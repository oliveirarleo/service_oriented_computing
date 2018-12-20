package carRentalClient;

import java.io.IOException;

import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

public class ClientMain {

	public static void main(String[] args) {
		
		ClientResource resource = new ClientResource("http://localhost:8182/cars");
		 
		Form form = new Form();  
		form.add("uid", "1");
		
		// Write the response entity on the console
		try {
//			resource.get().write(System.out);
			resource.post(form).write(System.out);
		} catch (ResourceException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

}
