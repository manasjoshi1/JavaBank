import java.util.ArrayList;
import java.math.*;

import java.io.*;

public class Customer {
	int customerAccountNumber;
	String customerName;
	String customerAddress;
	double customerBalance;
	int accountStatus;
	public ArrayList<Transaction> transactions;
	Customer(String customerName, String customerAddress, double customerBalance, int customerAccountNumber) {
		this.customerName = customerName;
		this.customerAddress = customerAddress;
		this.customerBalance = customerBalance;
		this.accountStatus = 1;
		this.transactions = new ArrayList<Transaction> ();
		this.customerAccountNumber = customerAccountNumber;

	}

	public boolean updateTransaction(String Sender, String Reciever, Double Amt, Double UpdatedBalance, String transactionType) {
		this.transactions.add(new Transaction(Sender, Reciever, Amt, UpdatedBalance, transactionType));
		return true;
	}

}
