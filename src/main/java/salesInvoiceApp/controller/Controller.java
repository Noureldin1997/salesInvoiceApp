package salesInvoiceApp.controller;

import salesInvoiceApp.model.Invoice;
import salesInvoiceApp.model.Line;
import salesInvoiceApp.model.invoicesTable;
import salesInvoiceApp.model.linesTable;
import salesInvoiceApp.view.GUI;
import salesInvoiceApp.view.addLineDialog;
import salesInvoiceApp.view.createInvoiceDialog;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Controller implements ActionListener, ListSelectionListener {

    private GUI frame;
    private createInvoiceDialog invoiceDialog;

    private addLineDialog lineDialog;

    public Controller(GUI frame) {
        this.frame = frame;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "Create new invoice":
                createNew();break;
            case "Delete invoice":
                deleteInvoice();break;
            case "Add new item":
                addItem();break;
            case "Delete":
                delete();break;
            case "Load File":
                loadFile();break;
            case "Save File":
                saveFile();break;
            case "createInvoiceCancel":
                createInvoiceCancel();break;
            case "createInvoiceOK":
                createInvoiceOK();break;
            case "addLineOK":
                addLineOK();break;
            case "addLineCancel":
                addLineCancel();break;

            default:
                throw new IllegalStateException("Unexpected value: " + e.getActionCommand());

        }
    }

    private void addLineCancel() {
        lineDialog.setVisible(false);
        lineDialog.dispose();
        lineDialog = null;
    }

    private void addLineOK() {
        String itemName = lineDialog.getProductNameValue().getText();
        int count = Integer.parseInt(lineDialog.getCountValue().getText());
        double price = Double.parseDouble(lineDialog.getPriceValue().getText());
        int invoiceIndex = frame.getInvTable().getSelectedRow();
        if (invoiceIndex != -1){
            Invoice selectedInvoice = frame.getInvoicesArrayList().get(invoiceIndex);
            Line line = new Line(selectedInvoice.getNum(),count,price,itemName,selectedInvoice);
            selectedInvoice.getLines().add(line);
            linesTable lTable = (linesTable) frame.getInvoiceItem().getModel();
            lTable.fireTableDataChanged();
            frame.getInvoiceTable().fireTableDataChanged();
        }
        lineDialog.setVisible(false);
        lineDialog.dispose();
        lineDialog = null;
        frame.getInvoiceTotalValue().setText(""+frame.getInvoicesArrayList().get(invoiceIndex).calculateTotal());
    }

    private void createInvoiceOK() {
        String date = invoiceDialog.getDate().getText();
        String customerName = invoiceDialog.getCustomerName().getText();
        int num = frame.getNextInvoiceNumber();

        try {
            String[] dateSplitted = date.split("-");
            if(dateSplitted.length != 3){
                JOptionPane.showMessageDialog(frame, "Wrong Date Format", "Error", JOptionPane.ERROR_MESSAGE);
            }else {
                int year = Integer.parseInt(dateSplitted[2]);
                int month = Integer.parseInt(dateSplitted[1]);
                if (month<1 || month >12){
                    JOptionPane.showMessageDialog(frame, "months are only valid between 1 and 12", "Error", JOptionPane.ERROR_MESSAGE);
                }else {
                    int maxDayFeb = 0;
                    int day = Integer.parseInt(dateSplitted[0]);
                    boolean generate = true;
                    switch (month){
                        case 1:
                            if(day < 1 || day > 31) {
                                JOptionPane.showMessageDialog(frame, "January days must be between 1 and 31", "Error", JOptionPane.ERROR_MESSAGE);
                                generate = false;
                            }
                            break;
                        case 2:
                            if(year%4 == 0){
                                if(year%100 == 0){
                                    if(year%400 == 0){
                                        maxDayFeb = 29;
                                    }else{
                                        maxDayFeb = 28;
                                    }
                                }else{
                                    maxDayFeb = 29;
                                }
                            }else{
                                maxDayFeb = 28;
                            }
                            if (day < 1 || day > maxDayFeb){
                                generate = false;
                                if (maxDayFeb == 29){
                                    JOptionPane.showMessageDialog(frame, "February days must be between 1 and 29 in leap years like "+ year, "Error", JOptionPane.ERROR_MESSAGE);

                                }else if (maxDayFeb == 28){
                                    JOptionPane.showMessageDialog(frame, "February days must be between 1 and 28 in common years like "+ year, "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                            break;
                        case 3:
                            if(day < 1 || day > 31) {
                                JOptionPane.showMessageDialog(frame, "March days must be between 1 and 31", "Error", JOptionPane.ERROR_MESSAGE);
                                generate = false;
                            }
                            break;
                        case 4:
                            if(day < 1 || day > 30) {
                                JOptionPane.showMessageDialog(frame, "April days must be between 1 and 31", "Error", JOptionPane.ERROR_MESSAGE);
                                generate = false;
                            }
                            break;
                        case 5:
                            if(day < 1 || day > 31) {
                                JOptionPane.showMessageDialog(frame, "May days must be between 1 and 31", "Error", JOptionPane.ERROR_MESSAGE);
                                generate = false;
                            }
                            break;
                        case 6:
                            if(day < 1 || day > 30) {
                                JOptionPane.showMessageDialog(frame, "June days must be between 1 and 31", "Error", JOptionPane.ERROR_MESSAGE);
                                generate = false;
                            }
                            break;
                        case 7:
                            if(day < 1 || day > 31) {
                                JOptionPane.showMessageDialog(frame, "July days must be between 1 and 31", "Error", JOptionPane.ERROR_MESSAGE);
                                generate = false;
                            }
                            break;
                        case 8:
                            if(day < 1 || day > 31) {
                                JOptionPane.showMessageDialog(frame, "August days must be between 1 and 31", "Error", JOptionPane.ERROR_MESSAGE);
                                generate = false;
                            }
                            break;
                        case 9:
                            if(day < 1 || day > 30) {
                                JOptionPane.showMessageDialog(frame, "September days must be between 1 and 31", "Error", JOptionPane.ERROR_MESSAGE);
                                generate = false;
                            }
                            break;
                        case 10:
                            if(day < 1 || day > 31) {
                                JOptionPane.showMessageDialog(frame, "October days must be between 1 and 31", "Error", JOptionPane.ERROR_MESSAGE);
                                generate = false;
                            }
                            break;
                        case 11:
                            if(day < 1 || day > 30) {
                                JOptionPane.showMessageDialog(frame, "November days must be between 1 and 31", "Error", JOptionPane.ERROR_MESSAGE);
                                generate = false;
                            }
                            break;
                        case 12:
                            if(day < 1 || day > 31) {
                                JOptionPane.showMessageDialog(frame, "December days must be between 1 and 31", "Error", JOptionPane.ERROR_MESSAGE);
                                generate = false;
                            }
                            break;
                        default:
                            generate = true;

                    }

                    if(generate == true){
                        Invoice invoice = new Invoice(num, customerName, date);
                        frame.getInvoicesArrayList().add(invoice);
                        frame.getInvoiceTable().fireTableDataChanged();
                    }
                    //finalizing..
                    invoiceDialog.setVisible(false);
                    invoiceDialog.dispose();
                    invoiceDialog = null;
                    frame.getInvoiceDateValue().setText("");
                    frame.getInvoiceTotalValue().setText("");
                    frame.getCustomerNameValue().setText("");
                    frame.getInvoiceNumberValue().setText("");
                }
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(frame, "Wrong date input, insert numbers only", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createInvoiceCancel() {
        invoiceDialog.setVisible(false);
        invoiceDialog.dispose();
        invoiceDialog = null;
    }

    private void createNew() {
        invoiceDialog = new createInvoiceDialog(frame);
        invoiceDialog.setVisible(true);
    }

    private void deleteInvoice() {
        int selectedInvoice = frame.getInvTable().getSelectedRow();
        if (selectedInvoice != -1){
            frame.getInvoicesArrayList().remove(selectedInvoice);
            frame.getInvoiceTable().fireTableDataChanged();
        }
        frame.getInvoiceDateValue().setText("");
        frame.getInvoiceTotalValue().setText("");
        frame.getCustomerNameValue().setText("");
        frame.getInvoiceNumberValue().setText("");
    }

    private void addItem() {
        lineDialog = new addLineDialog(frame);
        lineDialog.setVisible(true);
    }

    private void delete() {
        int selectedInvoiceIndex = frame.getInvTable().getSelectedRow();
        int selectedItemIndex = frame.getInvoiceItem().getSelectedRow();
        if (selectedItemIndex != -1 && selectedInvoiceIndex != -1){
            Invoice invoice = frame.getInvoicesArrayList().get(selectedInvoiceIndex);
            invoice.getLines().remove(selectedItemIndex);
            linesTable lTable = new linesTable(invoice.getLines());
            frame.getInvoiceItem().setModel(lTable);
            lTable.fireTableDataChanged();
            frame.getInvoiceTotalValue().setText(""+frame.getInvoicesArrayList().get(selectedInvoiceIndex).calculateTotal());
        }
    }

    private void loadFile() {
        JFileChooser f = new JFileChooser("././InvoiceHeader.csv");
        try {
            int res = f.showOpenDialog(null);
            if (res == JFileChooser.APPROVE_OPTION) {
                File file_header = f.getSelectedFile();
                Path path_header = Paths.get(file_header.getAbsolutePath());
                try {
                    List<String> lines_header = Files.readAllLines(path_header);
                    ArrayList<Invoice> invoicesArray = new ArrayList<>();
                    try {
                        for (String line_header : lines_header) {
                            String[] headerSplitted = line_header.split(",");
                            int invoiceNumber = Integer.parseInt(headerSplitted[0]);
                            String invoiceDate = headerSplitted[1];
                            String customer = headerSplitted[2];
                            invoicesArray.add(new Invoice(invoiceNumber, customer, invoiceDate));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(frame, "Line format error in invoices file", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }

                res = f.showOpenDialog(frame);
                if(res == JFileChooser.APPROVE_OPTION){
                    File linesFile = f.getSelectedFile();
                    Path path_lines = Paths.get(linesFile.getAbsolutePath());
                    try{
                        List<String> lines = Files.readAllLines(path_lines);
                        for (String line : lines){
                            try {
                                String lineSplitted[] = line.split(",");
                                int invoiceNumber = Integer.parseInt(lineSplitted[0]);
                                String itemName = lineSplitted[1];
                                double itemPrice = Double.parseDouble(lineSplitted[2]);
                                int count = Integer.parseInt(lineSplitted[3]);
                                Invoice currentInvoice = null;
                                for (Invoice i : invoicesArray) {
                                    if (i.getNum() == invoiceNumber) {
                                        currentInvoice = i;
                                        break;
                                    }
                                }
                                Line currentLine = new Line(invoiceNumber, count, itemPrice, itemName, currentInvoice);
                                currentInvoice.getLines().add(currentLine);
                            }catch (Exception e){
                                e.printStackTrace();
                                JOptionPane.showMessageDialog(frame, "Line format error in lines file", "ERROR", JOptionPane.ERROR_MESSAGE );
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(frame, "Line file not found", "ERROR", JOptionPane.ERROR_MESSAGE );
                    }


                }
                frame.setInvoicesArrayList(invoicesArray);
                invoicesTable invoice_table = new invoicesTable(invoicesArray);
                frame.setInvoiceTable(invoice_table);
                frame.getInvTable().setModel(invoice_table);
                frame.getInvoiceTable().fireTableDataChanged();
                } catch (Exception e){
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Invoices file not found", "ERROR", JOptionPane.ERROR_MESSAGE );
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "File cannot be read", "ERROR", JOptionPane.ERROR_MESSAGE );
        }
    }

    private void saveFile(){
        ArrayList<Invoice> invoices = frame.getInvoicesArrayList();
        String invoicesFile = "";
        String linesFile = "";
        for (Invoice invoice : invoices){
            invoicesFile += invoice.getNum()+","+invoice.getDate()+","+invoice.getCustomer()+"\n";
            ArrayList<Line> lines= invoice.getLines();
            for (Line line : lines){
                linesFile += line.getNum()+","+line.getItem()+","+(int)line.getPrice()+","+line.getCount()+"\n";
            }
        }
        //saving the invoices file
        JFileChooser fileChooser = new JFileChooser();
        int chosenFile = fileChooser.showSaveDialog(frame);
        try {
            if (chosenFile == JFileChooser.APPROVE_OPTION) {
                File invoiceFile = fileChooser.getSelectedFile();
                FileWriter InvoiceFileWriter = new FileWriter(invoiceFile);
                InvoiceFileWriter.write(invoicesFile);
                InvoiceFileWriter.flush();
                InvoiceFileWriter.close();
                //saving the lines file
                chosenFile = fileChooser.showSaveDialog(frame);
                if (chosenFile == JFileChooser.APPROVE_OPTION) {
                    File lineFile = fileChooser.getSelectedFile();
                    FileWriter LineFileWriter = new FileWriter(lineFile);
                    LineFileWriter.write(linesFile);
                    LineFileWriter.flush();
                    LineFileWriter.close();
                }
            }
        }catch (IOException e){
            JOptionPane.showMessageDialog(frame, "File cannot be saved", "ERROR", JOptionPane.ERROR_MESSAGE );
        }
    }


    //changing the labels of the selected invoice
    @Override
    public void valueChanged(ListSelectionEvent e) {
        int selectedRow = frame.getInvTable().getSelectedRow();
        if (selectedRow != -1) {
            Invoice selectedInvoice = frame.getInvoicesArrayList().get(selectedRow);
            frame.getInvoiceNumberValue().setText(String.valueOf(selectedInvoice.getNum()));
            frame.getInvoiceDateValue().setText(selectedInvoice.getDate());
            frame.getCustomerNameValue().setText(selectedInvoice.getCustomer());
            frame.getInvoiceTotalValue().setText(String.valueOf(selectedInvoice.calculateTotal()));
            linesTable lTable = new linesTable(selectedInvoice.getLines());
            frame.getInvoiceItem().setModel(lTable);
            lTable.fireTableDataChanged();
        }
    }
}
