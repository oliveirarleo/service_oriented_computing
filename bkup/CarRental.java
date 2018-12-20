package car.stock.deployment;

import java.rmi.RemoteException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import car.stock.deployment.CarStockStub.CheckAvailableCars;
import car.stock.deployment.CarStockStub.CheckAvailableCarsResponse;
import car.stock.deployment.CarStockStub.UpdateCarAvailability;


public class CarRental {

	public static void main(String[] args) throws RemoteException{
		CarStockStub carStockStub = new CarStockStub();
		CheckAvailableCars checkCars = new CheckAvailableCars();
		CheckAvailableCarsResponse checkCarsResponse = carStockStub.checkAvailableCars(checkCars);
		
//		Car c = new Car()
//		List<String> list = new ArrayList<String>(Arrays.asList(checkCarsResponse.get_return()));
		
		String[] response = checkCarsResponse.get_return();
		for(int i = 0; i < response.length; i++) {
			System.out.println(response[i]);
		}
	}

}
