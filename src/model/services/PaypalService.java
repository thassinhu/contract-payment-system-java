package model.services;

public class PaypalService implements OnlineService {

	public double jurosSimples(double amount) {
		return amount * 0.01;
	}
	
	public double taxa(double amount) {
		return amount * 0.02;
	}
	
}
