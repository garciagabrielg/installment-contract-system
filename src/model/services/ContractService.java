package model.services;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {
	
	private OnlinePaymentService onlinePayment;
	
	public ContractService(OnlinePaymentService onlinePayment) {
		this.onlinePayment = onlinePayment;
	}

	public void processContract(Contract contract, int month) {
		
		for (int i = 1; i<=month;i++) {
			double monthlyPayment = contract.getTotalValue() / month;
			double interest = onlinePayment.interest(monthlyPayment, i);
			double fees = onlinePayment.paymentFee(monthlyPayment + interest);
			double totalPayment = monthlyPayment + interest + fees;
			contract.getInstallment().add(new Installment(contract.getDate().plusMonths(i),totalPayment));
		}
		
		
	}
}
