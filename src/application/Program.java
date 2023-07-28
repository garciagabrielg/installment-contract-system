package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		
		System.out.println("Enter contract data: ");
		System.out.print("Number: ");
		int contractNumber = sc.nextInt();
		System.out.print("Date (dd/MM/yyyy): ");
		LocalDate contractDate = LocalDate.parse(sc.next(), fmt);
		System.out.print("Contract value: ");
		double contractValue = sc.nextDouble();
		Contract contract = new Contract(contractNumber, contractDate, contractValue);
		ContractService contractService = new ContractService(new PaypalService());
		System.out.print("Enter with the number of installments: ");
		int numberOfInstallments = sc.nextInt();
		contractService.processContract(contract, numberOfInstallments);
		System.out.println("Installments: ");
		for (Installment inst : contract.getInstallment()) {
			System.out.println(fmt.format(inst.getDueDate()) + " - " + String.format("%.2f", inst.getAmount()));
		}
		
		
		
		sc.close();
	}

}
