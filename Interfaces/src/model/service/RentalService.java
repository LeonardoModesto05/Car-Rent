package model.service;

import java.time.Duration;

import model.entities.Invoice;
import model.entities.carRental;

public class RentalService {
	private Double pricePerHour;
	private Double pricePerDay;
	
	private taxService taxService;	
	
	public RentalService (double pricePerHour, double pricePerDay, taxService taxServices)
	{
		this.pricePerHour = pricePerHour;
		this.pricePerDay = pricePerDay;
		this.taxService = taxServices;
	}

	public Double getPricePerHour() {
		return pricePerHour;
	}

	public void setPricePerHour(Double pricePerHour) {
		this.pricePerHour = pricePerHour;
	}

	public Double getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(Double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}
	
	public void processInvoice (carRental carRental)
	{	
		double minutes =Duration.between(carRental.getStart(), carRental.getFinish()).toMinutes();
		double hours = minutes / 60.;
		double basicPayment;
		if (hours <= 12.)
		{
			basicPayment = hours * Math.ceil(hours);
		} else {
			basicPayment = pricePerDay * Math.ceil(hours/24);
		}
		
		double tax = taxService.tax(basicPayment);
		
		carRental.setInvoice(new Invoice(basicPayment,tax ));
	}
}
