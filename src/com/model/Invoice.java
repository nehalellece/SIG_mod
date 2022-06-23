package com.model;

import com.view.MainFrame;

import javax.swing.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Invoice {
    private int invoiceNumber;
    private Date invoiceDate;
    private Customer invoiceCustomer;
    private MainFrame mainFrame;

    public Invoice(int invoiceNumber, Date invoiceDate, Customer invoiceCustomer) {
        this.invoiceNumber = invoiceNumber;
        this.invoiceDate = invoiceDate;
        this.invoiceCustomer = invoiceCustomer;
    }

    public Invoice(int invoiceNumber, String invoiceDate, Customer invoiceCustomer) {
        this.invoiceNumber = invoiceNumber;
        try {
            this.invoiceDate = new SimpleDateFormat("dd-mm-yyyy").parse(invoiceDate);

        } catch (ParseException e) {

                e.printStackTrace();
                JOptionPane.showMessageDialog(mainFrame, "Date Format Error\n" + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);

        }
        this.invoiceCustomer = invoiceCustomer;
    }
    public Invoice(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public void setInvoiceCustomer(Customer invoiceCustomer) {
        this.invoiceCustomer = invoiceCustomer;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public Customer getInvoiceCustomer() {
        return invoiceCustomer;
    }

   public Invoice(){}


    public  Invoice createInvoice(String[] metadata) {
        int invoiceNumber = Integer.parseInt(metadata[0]);
//        Date invoiceDate;
        String invoiceDate;
        /*try {
        */
            DateFormat formatter = new SimpleDateFormat("dd-mm-yyyy" );
//            System.out.println(metadata[1]);
//            invoiceDate = formatter.parse(new String(metadata[1]));
            invoiceDate = metadata[1];
//            System.out.println(invoiceDate);
        /*} catch (ParseException e) {
            throw new RuntimeException(e);
        }*/
        String customerName = metadata[2];


        return new Invoice(invoiceNumber, invoiceDate, new Customer(customerName));

    }

    @Override
    public String toString() {

        return "Invoice Data:  invoice number is :"+this.getInvoiceNumber() + " , Invoice Date is :"+
                this.getInvoiceDate() + " , Customer Name = "+ this.getInvoiceCustomer().getCustomerName();

    }
}
