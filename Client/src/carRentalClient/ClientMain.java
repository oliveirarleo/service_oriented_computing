package carRentalClient;

import java.io.IOException;
import java.util.Scanner;

import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

public class ClientMain {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		String[] splitCommand = null;
		do {
			System.out.println("\n----------\nEnter command:");
			String command = scanner.nextLine();
			System.out.println("-"+command+"-");
			splitCommand = command.split(" ");
			if(splitCommand != null) {
				if(splitCommand.length == 1  && splitCommand[0].equals("help")) {
					System.out.println("Commands are:\n"
							+ "search parameter instance\n"
							+ "rent parameter instance dates\n"
							+ "help\n"
							+ "parameters are: @id, model, city, fabday\n"
							+ "dateIntervals are in dd/MM/yyyy:dd/MM/yyyy format");
				} else if(splitCommand.length == 3 
						&& splitCommand[0].equals("search")
						&& splitCommand[1] != null 
						&& splitCommand[2] != null) {
					String url = null;
					if(splitCommand[1].equals("all") && splitCommand[2].equals("cars") ) {
						url ="http://localhost:8182/cars";
					}else {
						url ="http://localhost:8182/cars/"+splitCommand[1]+"/"+splitCommand[2];
					}
					
					ClientResource resource = new ClientResource(url);
					// Write the response entity on the console
					try {
						resource.get().write(System.out);
					} catch (ResourceException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if(splitCommand.length == 4
						&& splitCommand[0].equals("rent")
						&& splitCommand[1] != null 
						&& splitCommand[2] != null
						&& splitCommand[3] != null) {
					ClientResource resource = new ClientResource("http://localhost:8182/cars");
					 
					Form form = new Form();
					form.add("parameter", splitCommand[1]);
					form.add("instance", splitCommand[2]);
					form.add("dates", splitCommand[3]);
					
					// Write the response entity on the console
					try {
//						resource.get().write(System.out);
						resource.post(form).write(System.out);
					} catch (ResourceException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}else if(splitCommand.length == 1
						&& splitCommand[0].equals("exit")) {
					System.out.println("Exiting...");
				} else {
					System.out.println("Problem with command, type \"help\" to see commands");
				}
			}
		} while(splitCommand != null && !splitCommand[0].equals("exit"));
		
		
	}

}
