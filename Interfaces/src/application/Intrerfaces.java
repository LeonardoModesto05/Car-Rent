package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Vehicle;
import model.entities.carRental;
import model.service.BrazilTaxServices;
import model.service.RentalService;

public class Intrerfaces {

	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		Locale.setDefault(Locale.US);
		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		System.out.println("Enter with vehicle data: ");
		System.out.print("Car model: ");
		String model = sc.nextLine();
		Vehicle vehicle = new Vehicle(model);
		System.out.print("withDrawl:(dd/MM/yyyy): ");
		LocalDateTime start = LocalDateTime.parse(sc.nextLine(),fmt);
		System.out.print("Return:(dd/MM/yyyy): ");
		LocalDateTime returns = LocalDateTime.parse(sc.nextLine(),fmt);
		carRental car = new carRental(start, returns, vehicle);
		
		System.out.print("Enter price per hour: ");
		double pricePerHour = sc.nextDouble();
		System.out.print("Enter price per day: ");
		double pricePerDay = sc.nextDouble();
		RentalService rs = new RentalService(pricePerHour, pricePerDay, new BrazilTaxServices());
		
		rs.processInvoice(car);
		
		System.out.println("Invoice: ");
		
		System.out.println("Basic Payment: " + car.getInvoice().getBasicPayment());
		System.out.println("Tax: " + car.getInvoice().getTax());
		System.out.println("Total payment: " + car.getInvoice().getTotalPayment());
		
		sc.close();

	}

}
