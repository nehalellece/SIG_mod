package com.view;

import com.controller.CSVReaderInJava;
import com.model.FileOperations;
import com.model.Invoice;
import com.model.InvoiceItems;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame implements ActionListener {

    private FileOperations fileOperations = null;
    //Panels
    private JPanel headerPanel;
    private JPanel headerPanelLeft;
    private JPanel headerPanelRight;

    private JPanel footerPanel;
    private JPanel footerPanelLeft;
    private JPanel footerPanelRight;

    private JPanel centerPanel;
    private JPanel centerPanelLeft;
    private JPanel centerPanelRight;
    private JPanel centerPanelRight01;
    private JPanel centerPanelRight02;
    private JPanel centerPanelRight03;
    private JPanel centerPanelRight04;
    /*------------------------Buttons------------------------*/
    private JButton loadFileBtn;
    private JButton saveFileBtn;

    private JButton saveBtn;
    private JButton cancelBtn;

    private JButton createNewInvoiceBtn;
    private JButton deleteInvoiceBtn;

    /*-------------------Text Fields---------------------------*/
    private JTextField invoiceNumberTxt;
    private JTextField invoiceDateTxt;
    private JTextField customerTxt;
    private JTextField invoiceTotalLbl;

    /*----------------Table---------------------------*/
    private JScrollPane invTblScroll = new JScrollPane();
    private JScrollPane invItemTblScroll;
    private DefaultTableModel invTblModel;
    private DefaultTableModel invItemTblModel;
    private JTable invoiceTbl = new JTable()/*= new JTable(5,4)*/;
    ;

    private JTable invoiceItemTbl = new JTable();

    /*--------------------Menu----------------------------*/
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem loadFileMenuItem;
    private JMenuItem saveFileMenuItem;


    //Reader
    CSVReaderInJava csvReaderInJava = new CSVReaderInJava();
    //    private String fileName ="G:\\Download\\sales-invoice-generator\\Sales Invoice Generator\\InvoiceHeader";
    private String fileName = "C:\\Users\\dell\\Documents\\Sales Invoice Generator\\InvoiceHeader.csv";
    private String fileItemName = "C:\\Users\\dell\\Documents\\Sales Invoice Generator\\InvoiceLine.csv";
/*
    private void openFile(){
        JFileChooser fileChooser = new JFileChooser();
        String path=new String();
        if ((fileChooser.showOpenDialog(this)==JFileChooser.APPROVE_OPTION))
        {
            path = fileChooser.getSelectedFile().getPath();

        }
//        return path;
    }
*/

    private ListSelectionModel cellSelectionModel;
    private String selectedDataInvTbl = new String();

    public MainFrame(String title) {
        super(title);
fileOperations = new FileOperations(this,this.fileName,this.fileItemName);
fileOperations.readFiles();
        /*-----------------------Text Fields------------------------------------*/

        invoiceNumberTxt = new JTextField("Invoice Number", 20);
        invoiceDateTxt = new JTextField("Invoice Date", 20);
        customerTxt = new JTextField("Customer Name", 20);
        invoiceTotalLbl = new JTextField(20);
        invoiceTotalLbl.setBackground(Color.LIGHT_GRAY);

        //Menu
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        loadFileMenuItem = new JMenuItem("Load File", 'l');
        saveFileMenuItem = new JMenuItem("Save File", 's');

        loadFileMenuItem.setActionCommand("L");
        loadFileMenuItem.addActionListener(this);

        saveFileMenuItem.setActionCommand("S");
        saveFileMenuItem.addActionListener(this);

        fileMenu.add(loadFileMenuItem);
        fileMenu.add(saveFileMenuItem);
        menuBar.add(fileMenu);


        //Panels
        headerPanel = new JPanel();
//        headerPanel.setBackground(Color.ORANGE);
        headerPanelLeft = new JPanel();
//        headerPanelLeft.setBackground(Color.BLACK);
        headerPanelRight = new JPanel();
//        headerPanelRight.setBackground(Color.BLUE);

        headerPanel.setLayout(new GridLayout(1, 2));
        headerPanel.add(headerPanelLeft);
        headerPanel.add(headerPanelRight);


        footerPanel = new JPanel();
//        footerPanel.setBackground(Color.BLACK);
        footerPanelLeft = new JPanel();
//        footerPanelLeft.setBackground(Color.CYAN);
        footerPanelRight = new JPanel();
//        footerPanelRight.setBackground(Color.DARK_GRAY);

        footerPanel.setLayout(new GridLayout(1, 2));
        footerPanel.add(footerPanelLeft);
        footerPanel.add(footerPanelRight);

        centerPanel = new JPanel();
//        centerPanel.setBackground(Color.green);
        centerPanelLeft = new JPanel();
//        centerPanelLeft.setBackground(Color.LIGHT_GRAY);
        centerPanelRight = new JPanel();
//        centerPanelRight.setBackground(Color.MAGENTA);

        centerPanelRight01 = new JPanel();
//        centerPanelRight01.setBackground(Color.RED);
        centerPanelRight02 = new JPanel();
//        centerPanelRight02.setBackground(Color.white);
        centerPanelRight03 = new JPanel();
//        centerPanelRight03.setBackground(Color.yellow);
        centerPanelRight04 = new JPanel();
//        centerPanelRight04.setBackground(Color.PINK);

        centerPanelRight.setLayout(new FlowLayout());
        centerPanelRight.add(centerPanelRight01);
        centerPanelRight.add(centerPanelRight02);
        centerPanelRight.add(centerPanelRight03);
        centerPanelRight.add(centerPanelRight04);

        centerPanel.setLayout(new GridLayout(1, 2));
        centerPanel.add(centerPanelLeft);
        centerPanel.add(centerPanelRight);


        /*----------------------Tables---------------------------------------*/

        String[] invoiceTblHeader = {"No.", "Date", "Customer", "Total"};
        String[][] invoiceTblData = {{"No.", "Date", "Customer", "Total"},
                {"No.", "Date", "Customer", "Total"},
                {"No.", "Date", "Customer", "Total"},
                {"No.", "Date", "Customer", "Total"}};
//        invoiceTbl = new JTable(invoiceTblData,invoiceTblHeader);

//        fillInvoiceTbl("");
//        invoiceTbl = new JTable(10,5);
//        DefaultTableModel tableModel= new DefaultTableModel(invoiceTblData,invoiceTblHeader);
//        invoiceTbl = fillInvoiceTbl(fileName);

        invTblModel = fillInvoiceTbl();
        invoiceTbl.setModel(invTblModel);

        invTblScroll = new JScrollPane();
        invTblScroll.getViewport().add(invoiceTbl);

        invoiceTbl.setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));

        cellSelectionModel = invoiceTbl.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


//        selectedDataInvTbl = new String();

        cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {


//                System.out.println("Hello");

                selectedDataInvTbl = (String) invoiceTbl.getValueAt(invoiceTbl.getSelectedRow(),
                        /*invoiceTbl.getColumnCount() - 1*/0);
                System.out.println(selectedDataInvTbl);

                invItemTblModel = fillInvoiceItemTbl( selectedDataInvTbl);

                invoiceItemTbl.setModel(invItemTblModel);

                /////////////////Fill Text Fields/////////////////////////////////////////

                invoiceNumberTxt.setText((String) invoiceTbl.getValueAt(invoiceTbl.getSelectedRow(), 0));
                invoiceDateTxt.setText((String) invoiceTbl.getValueAt(invoiceTbl.getSelectedRow(), 1));
                customerTxt.setText((String) invoiceTbl.getValueAt(invoiceTbl.getSelectedRow(), 2));
                invoiceTotalLbl.setText((String) invoiceTbl.getValueAt(invoiceTbl.getSelectedRow(), 3));
            }
        });
        System.out.println(selectedDataInvTbl);
//////////////////////////////////
        invItemTblModel = fillInvoiceItemTbl( selectedDataInvTbl);
        invoiceItemTbl.setModel(invItemTblModel);

        invItemTblScroll = new JScrollPane();
        invItemTblScroll.getViewport().add(invoiceItemTbl);
//        invoiceItemTbl = new JTable(10,5);
        invoiceItemTbl.setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));

        /*----------------------------Buttons-----------------------------------*/

        loadFileBtn = new JButton("Load File");
//        loadFileBtn.setActionCommand("L");

        saveFileBtn = new JButton("Save File");



        saveBtn = new JButton("Save");
        saveBtn.setActionCommand("SS");
        saveBtn.addActionListener(this);
        cancelBtn = new JButton("Cancel");
        cancelBtn.setActionCommand("X");
        cancelBtn.addActionListener(this);

        createNewInvoiceBtn = new JButton("Create New Invoice");
        createNewInvoiceBtn.setActionCommand("N");
        createNewInvoiceBtn.addActionListener(this);
        deleteInvoiceBtn = new JButton("Delete Invoice");
        deleteInvoiceBtn.setActionCommand("D");
        deleteInvoiceBtn.addActionListener(this);

        /*-------------------------Adding to Panel----------------------------------------*/

        headerPanelLeft.add(loadFileBtn);
        headerPanelLeft.add(saveFileBtn);

        centerPanelLeft.add(invTblScroll);

        footerPanelLeft.add(createNewInvoiceBtn);
        footerPanelLeft.add(deleteInvoiceBtn);

        /*--------------------------------------*/

        centerPanelRight01.add(new JLabel("Invoice Number"));
        centerPanelRight01.add(invoiceNumberTxt);
        centerPanelRight02.add(new JLabel("Invoice Date"));
        centerPanelRight02.add(invoiceDateTxt);
        centerPanelRight03.add(new JLabel("Customer Name"));
        centerPanelRight03.add(customerTxt);
        centerPanelRight04.add(new JLabel("Invoice Total"));
        centerPanelRight04.add(invoiceTotalLbl);

        centerPanelRight.add(invoiceItemTbl);

        footerPanelRight.add(saveBtn);
        footerPanelRight.add(cancelBtn);

        /*-------------------------Main Frame--------------------------------------*/

//        add(headerPanel, BorderLayout.PAGE_START);
        add(centerPanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.PAGE_END);


        setJMenuBar(menuBar);
        setSize(950, 500);
        setLocation(100, 100);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }


    private void loadFile() {
        invTblModel = new DefaultTableModel();
//        invoiceTbl = new JTable();
        invItemTblModel = new DefaultTableModel();
        JFileChooser fileChooser = new JFileChooser();
        if ((fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)) {
            this.fileName = fileChooser.getSelectedFile().getPath();


        }
        else this.fileName = "C:\\Users\\dell\\Documents\\Sales Invoice Generator\\InvoiceHeader.csv";
        /***************************************************************************/
        JFileChooser fileChooserItems = new JFileChooser();
        if ((fileChooserItems.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)) {
            this.fileItemName = fileChooser.getSelectedFile().getPath();


        }
        else this.fileItemName = "C:\\Users\\dell\\Documents\\Sales Invoice Generator\\InvoiceLine.csv";
        fileOperations = new FileOperations(this,this.fileName,this.fileItemName);
        fileOperations.readFiles();
        this.invTblModel = this.fillInvoiceTbl();
        this.invItemTblModel = this.fillInvoiceItemTbl("0");
    }
    private DefaultTableModel fillInvoiceTbl() {

        /*--------------------------------------------------------------*/

        invoiceDateTxt.setText(this.fileName);
        ArrayList<Invoice> invoiceArrayList = fileOperations.readHeaderFile();
//        List<Invoice> invoices = csvReaderInJava.readFromCSV(fn);

        String[] invoiceTblHeader = {"mo", "date", "cus", "val"};
//        DefaultTableModel tableModel = new DefaultTableModel(invoiceTblHeader,0);

        this.invTblModel = new DefaultTableModel(invoiceTblHeader, 0);

        for (int i = 0; i < invoiceArrayList.size(); i++) {
            String[] invoiceData = {String.valueOf(invoiceArrayList.get(i).getInvoiceNumber()),
                    String.valueOf(invoiceArrayList.get(i).getInvoiceDate()),
                    String.valueOf(invoiceArrayList.get(i).getInvoiceCustomer().getCustomerName()),
                    ""};
            this.invTblModel.addRow(invoiceData);
//            invoiceDateTxt.setText(String.valueOf(invoices.get(i).getInvoiceDate()));
        }
//        invTbl = new JTable(tableModel);
//        return invTbl;
        return this.invTblModel;
    }

    private DefaultTableModel fillInvoiceItemTbl( String selectedData) {

        /*--------------------------------------------------------------*/


        ArrayList<InvoiceItems> invoiceItemsArrayList = fileOperations.readItemFile(selectedData);
//        List<InvoiceItems> invoices = csvReaderInJava.readitemFromCSV(fn);

        String[] invoiceTblHeader = {"mo", "date", "cus", "val", ""};
//        DefaultTableModel tableModel = new DefaultTableModel(invoiceTblHeader,0);

        this.invItemTblModel = new DefaultTableModel(invoiceTblHeader, 0);

        for (int i = 0; i < invoiceItemsArrayList.size(); i++) {
            if (String.valueOf(invoiceItemsArrayList.get(i).getInvoice().getInvoiceNumber()).equals(selectedData)) {
                String[] invoiceData = {String.valueOf(invoiceItemsArrayList.get(i).getInvoice().getInvoiceNumber()),
                        String.valueOf(invoiceItemsArrayList.get(i).getItem().getItemName()),
                        String.valueOf(invoiceItemsArrayList.get(i).getItemPrice()),
                        String.valueOf(invoiceItemsArrayList.get(i).getItemQun()),
                        ""};
                this.invItemTblModel.addRow(invoiceData);
            } else continue;
            ;
//            invoiceDateTxt.setText(String.valueOf(invoices.get(i).getInvoiceDate()));
        }
//        invTbl = new JTable(tableModel);
//        return invTbl;
        return this.invItemTblModel;
    }



    private void saveFile() {
        JFileChooser fileChooser = new JFileChooser();
        if ((fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)) {
            String path = fileChooser.getSelectedFile().getPath();
            FileOutputStream fileOutStream = null;
            try {
                fileOutStream = new FileOutputStream(path);

                String allData = new String();
                for (int i = 0; i < invTblModel.getRowCount(); i++) {
                    allData += invTblModel.getValueAt(i, 0) + "," +
                            invTblModel.getValueAt(i, 1) + "," +
                            invTblModel.getValueAt(i, 2)+"\n";
                }
                byte[] b = allData.getBytes();
                fileOutStream.write(b);
            } /*catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null,"File Not Found",
                        "Msg",JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException(e);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,"Wrong file format",
                        "Msg",JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            } */

         catch (NumberFormatException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Number Format Error\n" + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "File Error\n" + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Read Error\n" + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }finally {


                try {
                    fileOutStream.close();
                }/* catch (IOException e) {
                    JOptionPane.showMessageDialog(null,"Wrong file format",
                            "Msg",JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException(e);
                }*/
                 catch (NumberFormatException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Number Format Error\n" + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "File Error\n" + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Read Error\n" + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    private void saveItemFile() {
        JFileChooser fileChooser = new JFileChooser();
        if ((fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)) {
            String path = fileChooser.getSelectedFile().getPath();
            FileOutputStream fileOutStream = null;
            FileOutputStream fileOutStream1 = null;
            FileInputStream fileInputStream = null;
            try {
                fileOutStream = new FileOutputStream(path);
                fileOutStream1 = new FileOutputStream(this.fileItemName);

                fileInputStream = new FileInputStream(this.fileItemName);
                int size = fileInputStream.available();
                byte[] instream = new byte[size];
                fileInputStream.read(instream);




                String allData = new String();
                for (int i = 0; i < invTblModel.getRowCount(); i++) {
                    allData += invTblModel.getValueAt(i, 0) + "," +
                            invTblModel.getValueAt(i, 1) + "," +
                            invTblModel.getValueAt(i, 2)+"\n";
                }
                byte[] b = allData.getBytes();
                fileOutStream.write(b);

                String allItemData = new String();
                allItemData = "\n";
                for (int i = 0 ; i < invItemTblModel.getRowCount(); i++){
                    allItemData += invItemTblModel.getValueAt(i, 0) + "," +
                            invItemTblModel.getValueAt(i, 1) + "," +
                            invItemTblModel.getValueAt(i, 2) + "," +
                            invItemTblModel.getValueAt(i, 3)+"\n";
                }
                byte[] bi = new String(instream+allItemData).getBytes() ;

                fileOutStream1.write(bi);
            } /*catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null,"File not found",
                        "Msg",JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException(e);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,"Wrong file format",
                        "Msg",JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }*/

             catch (NumberFormatException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Number Format Error\n" + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "File Error\n" + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Read Error\n" + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }finally {


                try {
                    fileInputStream.close();
                    fileOutStream.close();
                    fileOutStream1.close();
                } /*catch (IOException e) {
                    JOptionPane.showMessageDialog(null,"Folder/File path is not found",
                            "Msg",JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException(e);
                }*/
                catch (NumberFormatException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Number Format Error\n" + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "File Error\n" + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Read Error\n" + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "L": {

//                invoiceTbl.clearSelection();
                invoiceTbl = new JTable();
                invoiceItemTbl = new JTable();
//                this.fileName = loadFile();
                loadFile();
                System.out.println(this.fileName);
                this.invTblModel = fillInvoiceTbl();
                this.invItemTblModel=fillInvoiceItemTbl(invTblModel.getValueAt(0,0).toString());
                System.out.println(invTblModel.getValueAt(1,0).toString()+"  99");
                System.out.println(this.invItemTblModel.getValueAt(1,1).toString());
//                invoiceTbl = new JTable(tableModel1);
                invoiceTbl.setModel(this.invTblModel);
                invoiceItemTbl.setModel(invItemTblModel);
//                invTblScroll = new JScrollPane();
                invTblScroll.getViewport().add(invoiceTbl);
                invoiceTbl.setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));

                break;
            }
            case "N":
                for (int i = 0; i < invTblModel.getRowCount(); i++) {

                }
//                String serial = (String) (invTblModel.getValueAt( invTblModel.getRowCount(),2))+1;
                invTblModel.addRow(new String[]{"", "", "", ""});
                invoiceTbl.setModel(invTblModel);
                invItemTblModel.addRow(new String[]{"serial", "", "", "", ""});
                invoiceItemTbl.setModel(invItemTblModel);
                break;
            case "S":
                saveFile();
                break;
            case "SS":
//                saveFile();
                saveItemFile();
                break;
            case "D":

                int invIndex = invoiceTbl.getSelectedRow()+1;
                System.out.println(invIndex);
                invTblModel.removeRow(invIndex);

                saveFile();

                break;
            case "X":
                System.exit(0);
                break;
        }

    }

}
