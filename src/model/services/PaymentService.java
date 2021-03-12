package model.services;

import model.entities.Contract;

public class PaymentService {

	private OnlineService onlineService;
	
	public PaymentService(OnlineService onlineService) {
		this.onlineService = onlineService;
	}

	public void processPayment(Contract contract) {
		int aux = contract.getInstallments().size();
		double precoLivre = contract.getTotalValue()/aux;
		
		for(int i=1; i<=aux ; i++) {	
			double aux1 = precoLivre + (onlineService.jurosSimples(precoLivre)*i)  ;
			double aux2 = aux1 + onlineService.taxa(aux1);
			contract.getInstallments().get(i-1).setAmount(aux2);
			contract.getInstallments().get(i-1).setDueDate(contract.getDate().plusMonths(i));
		}	
	}
	
	
}
