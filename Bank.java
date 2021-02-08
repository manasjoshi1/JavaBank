import java.util.ArrayList;
import java.util.*;
import java.io.*;
import java.math.*;

interface BankRules {
	final double MIN_BALANCE = 1000;
	final int MAX_PRINT_TRANSACTIONS = 3;
	final String DEBIT = "Debit";
	final String CREDIT = "Credit";

}

public class Bank implements BankRules {
	Scanner sc = new Scanner(System.in);

	public ArrayList<Customer> customers;

	public Bank() {
		this.customers = new ArrayList<Customer> ();
	}

	public int getLastAccountNumber() {
		int indexLastCustomer = this.customers.size();
		Customer c;
		try {
			c = customers.get(indexLastCustomer - 1);
		} catch (IndexOutOfBoundsException e) {
			return 10001;
		}
		return c.customerAccountNumber + 1;

	}
	public int addCustomer(String customerName, String customerAddress, Double customerBalance) {

		int customerAccountNumber = getLastAccountNumber();
		Customer customer = new Customer(customerName, customerAddress, customerBalance, customerAccountNumber);
		this.customers.add(customer);
		customer.transactions.add(new Transaction(customerName, "Initial Deposit", customerBalance, customerBalance, CREDIT));
		return customerAccountNumber;
	}

	public ArrayList<Transaction> printTransactions(int customerAccountNumber) {
		ArrayList<Transaction> recentTransactions = new ArrayList<Transaction> ();

		Customer c = this.findAccount(customerAccountNumber);
		if (c == null || c.accountStatus == 0) {
			return null;

		} else {
			return c.transactions;
		}
	}

	public boolean closeAccount(int customerAccountNumber) {
		Customer c = findAccount(customerAccountNumber);
		if (c == null || c.accountStatus == 0) {
			return false;
		}
		c.accountStatus = 0;

		return true;
	}

	public Customer findAccount(int customerAccountNumber) {
		for (Customer c: this.customers) {
			if (c.customerAccountNumber == customerAccountNumber) {
				return c;
			}

		}
		return null;
	}

}

// public Customer findAccount(String toBeDeleted) {
//   for (Customer c: this.customers) {
//     if (c.customerName.equals(toBeDeleted)) {
//       return c;
//     }
//
//   }
//   return null;
// }
// public void printTransactios() {
// 	System.out.println("Enter Your Name");
// 	String Name = sc.nextLine();
// 	Customer customer = findAccount(Name);
// 	for (Transaction t: customer.transactions) {
// 		System.out.print(t.transactionType + t.Amount + t.Sender);
// 	}
// 	//  return true;
// }

// public boolean printCustomers() {
//
//
//     for (Customer c: this.customers) {
//         if (c.accountStatus == 1) {
//             System.out.println(c.customerName + c.customerBalance);
//
//         }
//     }
//     return true;
// }
