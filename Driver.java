import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.lang.Double;

public class Driver {

	public static Bank bank = new Bank();
	public static void main(String Args[]) {

		Scanner sc = new Scanner(System.in);

		int menuOption = 0;
		do {

			System.out.println("1.Open Account\n2.Close Account\n3.Withdraw\n4.Deposit\n5.Tramsfer\n6.Print Last 10 Transactions\n7.Exit");
			boolean b = true;
			do {
				try {
					menuOption = sc.nextInt();
					b = false;
				} catch (InputMismatchException e) {
					System.out.println("Invalid input try again");
					sc.next();
				}
			} while (b);
			sc.nextLine();

			switch (menuOption) {
				case 1:
					openAccount();
					break;
				case 2:
					closeAccount();
					break;
				case 3:
					withdraw();
					break;
				case 4:
					deposit();
					break;
				case 5:
					transfer();
					break;
				case 6:
					printTransactions();
					break;
			}

		} while (menuOption != 7);

	}

	public static void openAccount() {

		Scanner sc = new Scanner(System.in);

		System.out.println("Thank you for opening account with us! Please enter your name");
		String customerName;
		customerName = sc.nextLine();
		System.out.println("Thanks! Now please enter your address");
		String customerAddress = sc.nextLine();
		System.out.println("Thanks! Now please enter initial deposit you want to make");
		double customerBalance = 0;
		boolean b = true;
		do {
			try {
				customerBalance = sc.nextDouble();
				sc.nextLine();
				if (Double.compare(customerBalance, bank.MIN_BALANCE)<0) {
					throw new MinimumBalanceException(bank.MIN_BALANCE);
				}
				b = false;

			} catch (InputMismatchException e) {
				System.out.println("Invalid input try again");
				sc.nextLine();
			} catch (MinimumBalanceException m) {
				System.out.println("Try increasing the amount");
			}
		} while (b);

		int customerAccountNumber = bank.addCustomer(customerName, customerAddress, customerBalance);
		System.out.println("Account Created Successfully!\n Your Account Number is: " + customerAccountNumber + "(Save it for further use)");
	}
	public static void closeAccount() {
		Scanner sc = new Scanner(System.in);
		boolean b = true;
		System.out.println("Enter Your Account Number");
		int customerAccountNumber = 0;
		do {
			try {
				customerAccountNumber = sc.nextInt();
				b = false;
			} catch (InputMismatchException e) {
				System.out.println("Invalid input try again");
				sc.next();
			}
		} while (b);
		if (bank.closeAccount(customerAccountNumber))
			System.out.println("Account Closed Successfully.");
		else
			System.out.println("Account Doesn't Exist.");
	}

	public static boolean withdraw() {
		Scanner sc = new Scanner(System.in);
		boolean b = true;
		System.out.println("Enter Your Account Number");
		int customerAccountNumber = 0;
		do {
			try {
				customerAccountNumber = sc.nextInt();
				b = false;
			} catch (InputMismatchException e) {
				System.out.println("Invalid input try again");
				sc.next();
			}
		} while (b);
		b = true;
		Customer c = bank.findAccount(customerAccountNumber);
		if (c == null || c.accountStatus == 0) {
			System.out.println("Not Found");
			return true;

		} else {
			double withdrawAmt = 0;
			System.out.println("Enter amount to be withdrawn");
			do {
				try {
					withdrawAmt = sc.nextDouble();
					if (Double.compare(c.customerBalance, withdrawAmt)<0) {
						throw new InsufficientBalanceException();
					}
					if (Double.compare((c.customerBalance - withdrawAmt), bank.MIN_BALANCE)<0) {
						throw new MinimumBalanceException(bank.MIN_BALANCE);
					}
					b = false;
				} catch (InputMismatchException e) {
					System.out.println("Invalid input try again");
					sc.next();
				} catch (InsufficientBalanceException e) {
					System.out.println("Insufficient Balance Please Try With Different Amount");
				} catch (MinimumBalanceException m) {
					System.out.println("Minimum Balance Wont Be Maintained Please Try With Different Amount");
				}
			} while (b);
			sc.nextLine();
			c.customerBalance -= withdrawAmt;
			c.updateTransaction(c.customerName, "Self Withdraw", withdrawAmt, c.customerBalance, bank.DEBIT); //
			System.out.println("Withdrawal Successfully Completed");

		}
		return true;
	}

	public static boolean deposit() {
		Scanner sc = new Scanner(System.in);
		boolean b = true;

		System.out.println("Enter Your Account Number");
		int customerAccountNumber = 0;
		do {
			try {
				customerAccountNumber = sc.nextInt();
				b = false;
			} catch (InputMismatchException e) {
				System.out.println("Invalid input try again");
				sc.next();
			}
		} while (b);
		b = true;
		Customer customer = bank.findAccount(customerAccountNumber);
		if (customer == null || customer.accountStatus == 0) {
			System.out.println("Not Found or The Account is Closed");
			return true;

		} else {
			System.out.println("Enter amount to be Deposited");

			double deptAmt = 0;
			do {
				try {
					deptAmt = sc.nextDouble();
					b = false;
				} catch (InputMismatchException e) {
					System.out.println("Invalid input try again");
					sc.next();
				}
			} while (b);
			sc.nextLine();

			customer.customerBalance += deptAmt;
			customer.updateTransaction(customer.customerName, "Self Deposit", deptAmt, customer.customerBalance, bank.CREDIT);
			System.out.println("Deposit Successfully Completed");

		}
		return true;
	}

	public static boolean transfer() {

		Scanner sc = new Scanner(System.in);
		boolean b = true;

		System.out.println("Enter Sender's Account Number");
		int senderAccountNumber = 0;
		do {
			try {
				senderAccountNumber = sc.nextInt();
				b = false;
			} catch (InputMismatchException e) {
				System.out.println("Invalid input try again");
				sc.next();
			}
		} while (b);
		b = true;
		Customer sender = bank.findAccount(senderAccountNumber);
		if (sender == null || sender.accountStatus == 0) {
			System.out.println("Not Found or The Account is Closed");
			return true;

		} else {
			System.out.println("Enter Reciever's Account Number");
			int recieverAccountNumber = 0;
			do {
				try {
					recieverAccountNumber = sc.nextInt();
					b = false;
				} catch (InputMismatchException e) {
					System.out.println("Invalid input try again");
					sc.next();
				}
			} while (b);
			b = true;

			Customer reciever = bank.findAccount(recieverAccountNumber);
			if (reciever == null || reciever.accountStatus == 0) {
				System.out.println("Not Found or The Account is Closed");
				return true;

			} else {
				System.out.println("Enter Amount");
				double amount = 0;
				do {
					try {
						amount = sc.nextDouble();
						if (Double.compare(sender.customerBalance, amount)<0) {
							throw new InsufficientBalanceException();
						}
						if (Double.compare((sender.customerBalance - amount), bank.MIN_BALANCE)<= 0) {
							throw new MinimumBalanceException(bank.MIN_BALANCE);
						}
						b = false;
					} catch (InputMismatchException e) {
						System.out.println("Invalid input try again");
						sc.next();
					} catch (InsufficientBalanceException e) {
						System.out.println("Insufficient Balance Please Try With Different Amount");
					} catch (MinimumBalanceException m) {
						System.out.println("Minimum Balance Wont Be Maintained Please Try With Different Amount");
					}
				} while (b);
				sc.nextLine();
				sender.customerBalance -= amount;
				reciever.customerBalance += amount;
				sender.updateTransaction(sender.customerName, " Amount Sent To: " + reciever.customerName, amount, sender.customerBalance, bank.DEBIT);
				reciever.updateTransaction(reciever.customerName, " Amount Recieved From: " + sender.customerName, amount, reciever.customerBalance, bank.CREDIT);
				System.out.println("Transfer Successfully Completed");
			}
		}

		return true;

	}

	public static void printTransactions() {
		Scanner sc = new Scanner(System.in);
		boolean b = true;
		System.out.println("Enter Account Number");
		int customerAccountNumber = 0;
		do {
			try {
				customerAccountNumber = sc.nextInt();
				b = false;
			} catch (InputMismatchException e) {
				System.out.println("Invalid input try again");
				sc.next();
			}
		} while (b);
		b = true;

		ArrayList<Transaction> recentTransactions = bank.printTransactions(customerAccountNumber);
		if (recentTransactions == null) {
			System.out.println("Account Doesn't Exist");
		} else {
			System.out.println("Date Time\t\t\tUser\t Transaction Details\tType\t\t Amount \t\t Balance");
			System.out.println("Latest 3 Transactions are: ");
			for (int i = recentTransactions.size(), j = 0; j<bank.MAX_PRINT_TRANSACTIONS && i > 0; i--, j++) {
				Transaction recentTransaction = recentTransactions.get(i - 1);
				System.out.println(recentTransaction.dateTime + " \t" + recentTransaction.Sender + " \t" + recentTransaction.Reciever + " \t" + recentTransaction.transactionType + " \t\t\t" + recentTransaction.Amount + "\t\t " + recentTransaction.Balance);
			}

		}

	}

}
