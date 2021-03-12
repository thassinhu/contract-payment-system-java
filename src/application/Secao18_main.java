package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.PaymentService;
import model.services.PaypalService;

public class Secao18_main {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter contract data: ");
		System.out.print("Number: ");
		int number = sc.nextInt();
		System.out.print("Date (dd/MM/yyyy): ");
		sc.nextLine();
		String dateString = sc.nextLine();
		System.out.print("Contract value: ");
		double value = sc.nextDouble();
		
		//Trabalhando com LocalDate
		LocalDate date = LocalDate.parse(dateString , DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		
		//Criando o contrato com base nas info recebidas 
		Contract contract = new Contract(number, date, value);
		
		//Criando o servico de pagamento de acordo com as normas do Paypal
		PaymentService paymentService = new PaymentService(new PaypalService());
		
		//Criando o numero de parcelas desejadas (ainda nulas) dentro da lista de parcelas no contrato
		System.out.print("Enter number of installments: ");
		int installmentQty = sc.nextInt();
		for(int i=0; i<installmentQty ; i++) {
			contract.creatingInstallment();
		}
		
		//Processando o pagamento do contrato
		paymentService.processPayment(contract);
		
		for(Installment x : contract.getInstallments()) {
			System.out.println(x);
		}
		
		
		sc.close();
	}

}
