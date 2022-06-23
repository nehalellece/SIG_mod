package com.controller;

import com.model.Invoice;
import com.model.InvoiceItems;
import com.view.MainFrame;

import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class CSVReaderInJava {




    /*public List<Invoice> reafFile(String filePath){
        List<Invoice> invoiceList = new ArrayList<>();

        CSV
        FileInputStream fileInputStream = null ;
        try {
            fileInputStream = new FileInputStream(filePath);
            int size = fileInputStream.available();
            byte[] b = new byte[size];
            fileInputStream.read(b);


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        return invoiceList;
    }*/
    private MainFrame mainFrame;

    public List<Invoice> readFromCSV(String fileName) {
//        String[][] invoiceTbl ;

        List<Invoice> invoices = new ArrayList<>();
        Path filePath = Paths.get(fileName);
//        System.out.println("path:   "+filePath);
        BufferedReader bufferedReader;
        try {
            bufferedReader = Files.newBufferedReader(filePath, StandardCharsets.US_ASCII);
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
//                System.out.println(line);
                Invoice invoice = new Invoice().createInvoice(attributes);
                invoices.add(invoice);

                line = bufferedReader.readLine();

            }

        } /*catch (IOException e) {
            throw new RuntimeException(e);
        }*/
         catch (NumberFormatException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(mainFrame, "Number Format Error\n" + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(mainFrame, "File Error\n" + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(mainFrame, "Read Error\n" + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
//        catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        finally {


            return invoices;
        }
    }

    //read detail
    public List<InvoiceItems> readitemFromCSV(String fileName) {
//        String[][] invoiceTbl ;

        List<InvoiceItems> invoices = new ArrayList<>();
        Path filePath = Paths.get(fileName);

        BufferedReader bufferedReader;
        try {
            bufferedReader = Files.newBufferedReader(filePath, StandardCharsets.US_ASCII);
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                InvoiceItems invoice = new InvoiceItems().createInvoiceItem(attributes);
                invoices.add(invoice);

                line = bufferedReader.readLine();

            }

        } /*catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Wrong file format",
                    "Msg",JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }*/
        catch (NumberFormatException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(mainFrame, "Number Format Error\n" + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(mainFrame, "File Error\n" + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(mainFrame, "Read Error\n" + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
//        catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        finally {

            return invoices;
        }


    }

    }



