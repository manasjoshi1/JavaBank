import java.util.ArrayList;

import java.io.*;

public class Customer{

String customerName;
String customerAddress;
double customerBalance;
int accountStatus;
public ArrayList < Transaction > transactions;
Customer(String customerName, String customerAddress, double customerBalance)
{
	this.customerName=customerName;
	this.customerAddress=customerAddress;
	this.customerBalance=customerBalance;
	this.accountStatus=1;
	this.transactions = new ArrayList < Transaction > ();


}


}
