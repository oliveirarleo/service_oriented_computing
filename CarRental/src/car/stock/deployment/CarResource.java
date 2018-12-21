package car.stock.deployment;

import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import car.stock.deployment.CarStockStub.CheckAvailableCars;
import car.stock.deployment.CarStockStub.CheckAvailableCarsResponse;
import car.stock.deployment.CarStockStub.UpdateCarAvailability;
import car.stock.deployment.CarStockStub.UpdateCarAvailabilityResponse;

 
public class CarResource extends ServerResource {  	
	@Get  
	public String toString() {
		String param = (String) getRequestAttributes().get("param");
		String instance = (String) getRequestAttributes().get("instance");
		String id = (String) getRequestAttributes().get("uid");
//		System.out.println(id);
		String response = null;
		if(id != null && !id.equals("")) {
			try {
				CarStockStub carStockStub = new CarStockStub();
				CheckAvailableCars checkCars = new CheckAvailableCars();
				checkCars.setSearch("//car[@id='"+id+"']");
				CheckAvailableCarsResponse checkCarsResponse = carStockStub.checkAvailableCars(checkCars);
				response = checkCarsResponse.get_return();
				
			} catch(Exception e ) {
				e.printStackTrace();
			}
		} else if((instance != null && !instance.equals("")) && (param != null && !param.equals(""))) {
			try {
				CarStockStub carStockStub = new CarStockStub();
				CheckAvailableCars checkCars = new CheckAvailableCars();
				checkCars.setSearch("//car["+ param +"='"+instance+"']");
				CheckAvailableCarsResponse checkCarsResponse = carStockStub.checkAvailableCars(checkCars);
				response = checkCarsResponse.get_return();
				
			} catch(Exception e ) {
				e.printStackTrace();
			}
		} else {
			try {
				CarStockStub carStockStub = new CarStockStub();
				CheckAvailableCars checkCars = new CheckAvailableCars();
				checkCars.setSearch("//car");
				CheckAvailableCarsResponse checkCarsResponse = carStockStub.checkAvailableCars(checkCars);
				response = checkCarsResponse.get_return();
				
			} catch(Exception e ) {
				e.printStackTrace();
			}
			
		}
		return response;
	}
	
	@Post
    public Representation acceptItem(Representation entity) {  
		Representation result = null;  
        // Parse the given representation and retrieve data
        Form form = new Form(entity);  
        String uid = form.getFirstValue("uid");
        String response = null;
		try {
			CarStockStub carStockStub = new CarStockStub();
			UpdateCarAvailability updateCars = new UpdateCarAvailability();
			updateCars.setSearch("//car[model='Palio']");
			updateCars.setDates("01/07/2020:29/07/2020");
			UpdateCarAvailabilityResponse updateCarsResponse = carStockStub.updateCarAvailability(updateCars);
			response = updateCarsResponse.get_return();
//				for(int i = 0; i < response.length; i++) {
//					System.out.println(response[i]);
//				}
			
		} catch(Exception e ) {
			e.printStackTrace();
		}
		
        result = new StringRepresentation("Sending uid="+ uid + " " + response, MediaType.TEXT_PLAIN); 
 
        return result;  
    } 
}  