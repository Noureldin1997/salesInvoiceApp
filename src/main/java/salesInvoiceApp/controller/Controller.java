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
        String date = invoiceDialog.getDate();
        String customerName = invoiceDialog.getCustomerName().getText();
        int num = frame.getNextInvoiceNumber()+1;

        Invoice invoice = new Invoice(num,customerName,date);
        frame.getInvoicesArrayList().add(invoice);
        frame.getInvoiceTable().fireTableDataChanged();

        //finalizing..
        invoiceDialog.setVisible(false);
        invoiceDialog.dispose();
        invoiceDialog = null;
        frame.getInvoiceDateValue().setText("");
        frame.getInvoiceTotalValue().setText("");
        frame.getCustomerNameValue().setText("");
        frame.getInvoiceNumberValue().setText("");
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
        JFileChooser f = new JFileChooser();
        try {
            int res = f.showOpenDialog(null);
            if (res == JFileChooser.APPROVE_OPTION) {
                File file_header = f.getSelectedFile();
                Path path_header = Paths.get(file_header.getAbsolutePath());
                List<String> lines_header = Files.readAllLines(path_header);
                ArrayList<Invoice> invoicesArray = new ArrayList<>();
                for (String line_header : lines_header){
                    String[] headerSplitted = line_header.split(",");
                    int invoiceNumber = Integer.parseInt(headerSplitted[0]);
                    String invoiceDate = headerSplitted[1];
                    String customer = headerSplitted[2];
                    invoicesArray.add(new Invoice(invoiceNumber,customer,invoiceDate));
                }
                res = f.showOpenDialog(frame);
                if(res == JFileChooser.APPROVE_OPTION){
                    File linesFile = f.getSelectedFile();
                    Path path_lines = Paths.get(linesFile.getAbsolutePath());
                    List<String> lines = Files.readAllLines(path_lines);
                    for (String line : lines){
                        String lineSplitted[] = line.split(",");
                        int invoiceNumber = Integer.parseInt(lineSplitted[0]);
                        String itemName = lineSplitted[1];
                        double itemPrice = Double.parseDouble(lineSplitted[2]);
                        int count = Integer.parseInt(lineSplitted[3]);
                        Invoice currentInvoice = null;
                        for (Invoice i : invoicesArray){
                            if (i.getNum()==invoiceNumber){
                                currentInvoice = i;
                                break;
                            }
                        }
                        Line currentLine = new Line(invoiceNumber,count,itemPrice,itemName,currentInvoice);
                        currentInvoice.getLines().add(currentLine);
                    }
                }
                frame.setInvoicesArrayList(invoicesArray);
                invoicesTable invoice_table = new invoicesTable(invoicesArray);
                frame.setInvoiceTable(invoice_table);
                frame.getInvTable().setModel(invoice_table);
                frame.getInvoiceTable().fireTableDataChanged();
            }
        }catch (IOException e){
            e.printStackTrace();
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
            e.printStackTrace();
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
