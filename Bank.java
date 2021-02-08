import java.util.ArrayList;
import java.util.*;
import java.io.*;

interface BankRules{
final double MIN_BALANCE=1000;
}

public class Bank implements BankRules{
    Scanner sc = new Scanner(System.in);

    public ArrayList < Customer > customers;
    //public ArrayList < Transaction > transactions;

    public Bank() {

        this.customers = new ArrayList < Customer > ();
        //this.transactions = new ArrayList < Transaction > ();
    }


    public void addCustomer(String customerName,String customerAddress, Double customerBalance) {
			// this.customers.add(new Customer("ABC", "Nashik", 10000));
			// this.customers.add(new Customer("QRT", "Pune", 12000));
			// this.customers.add(new Customer("XYZ", "Mumbai", 13000));
        Customer customer=new Customer(customerName, customerAddress, customerBalance);
        this.customers.add(customer);

        customer.transactions.add(new Transaction(customerName, "Initial Deposit", customerBalance, customerBalance, "credit"));
    }


  //   public ArrayList<Transaction> printTransactions(String Name) {
  //     ArrayList <Transaction> recentTransactions= new ArrayList<Transaction>();
  //
	// 		Customer c = this.findAccount(Name);
	// 		if (c == null || c.accountStatus==0) {
	// 				return null;
  //
	// 		} else {
	// 			int tsize=transactions.size();
  //
  //       for (int i=tsize, j=10;i>0 && j>1;i--)
	// 			{
	// 				Transaction t= transactions.get(i-1);
  //
	// 				if(t.Sender.equals(Name)){
  //           recentTransactions.add(new Transaction(t.Sender, t.Reciever, t.Amount, t.Balance, t.transactionType));
  //           j--;
  //         }
  //     }
  //       return recentTransactions;
  //   }
  // }



    public boolean closeAccount(String toBeDeleted) {
        Customer c = findAccount(toBeDeleted);
				if(c==null){
					return false;
				}
				c.accountStatus = 0;

        return true;
    }
    public Customer findAccount(String toBeDeleted)
     {
        for (Customer c: this.customers) {
            if (c.customerName.equals(toBeDeleted)) {
                return c;
            }

        }
        return null;
    }
    public boolean updateTransaction(String Sender, String Reciever, Double Amt, Double UpdatedBalance, String transactionType) {
      //  this.transactions.add(new Transaction(Sender, Reciever, Amt, UpdatedBalance, transactionType));
        return true;
    }
    public void printTransactios() {
      System.out.println("Enter Your Name");
      String Name = sc.nextLine();
      Customer customer=findAccount(Name);
        for (Transaction t: customer.transactions) {
            System.out.print(t.transactionType+t.Amount+t.Sender);
        }
      //  return true;
    }

}

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
