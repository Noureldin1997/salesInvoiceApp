package salesInvoiceApp.view;

import java.util.ArrayList;

import salesInvoiceApp.controller.Controller;
import salesInvoiceApp.model.Invoice;
import salesInvoiceApp.model.invoicesTable;

import javax.swing.*;

public class GUI extends javax.swing.JFrame {


    public GUI() {initComponents();}

    private void initComponents() {
        setLocation(400,200);
        //adding GUI items
        jPanel1 = new javax.swing.JPanel();
        invoiceNumber = new javax.swing.JLabel();
        invoiceNumber.setText("Invoice Number");
        invoiceDate = new javax.swing.JLabel();
        invoiceDate.setText("Invoice Date");
        customerName = new javax.swing.JLabel();
        customerName.setText("Customer Name");
        invoiceTotal = new javax.swing.JLabel();
        invoiceTotal.setText("Invoice Total");
        jScrollPane2 = new javax.swing.JScrollPane();
        invoiceItem = new javax.swing.JTable();
        invoiceItemLabel = new javax.swing.JLabel();
        invoiceNumberValue = new javax.swing.JLabel();
        invoiceDateValue = new javax.swing.JLabel();
        customerNameValue = new javax.swing.JLabel();
        invoiceTotalValue = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        invoicesTable = new JTable();
        invoicesTable.getSelectionModel().addListSelectionListener(controller);
        invoicesTableLabel = new javax.swing.JLabel();
        createNewInvoiceBTN = new javax.swing.JButton();
        deleteInvoiceBTN = new javax.swing.JButton();
        saveBTN = new javax.swing.JButton();
        cancelBTN = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        loadFileMenuItem = new javax.swing.JMenuItem();
        saveFileMenuItem = new javax.swing.JMenuItem();

        //adding listeners
        createNewInvoiceBTN.addActionListener(controller);
        deleteInvoiceBTN.addActionListener(controller);
        saveBTN.addActionListener(controller);
        cancelBTN.addActionListener(controller);
        loadFileMenuItem.addActionListener(controller);
        saveFileMenuItem.addActionListener(controller);

        // turning the system to exit-on-close
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        //creating and organizing the row invoice items table
        invoiceItem.setAutoCreateRowSorter(true);
        invoiceItem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                //column items names
                "No.", "Item Name", "Item Price", "Count", "Item total"
            }
        ));
        jScrollPane2.setViewportView(invoiceItem);

        invoiceItemLabel.setText("Invoice item");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(invoiceItemLabel)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(invoiceNumber)
                                    .addComponent(invoiceDate))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(invoiceDateValue)
                                    .addComponent(invoiceNumberValue)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(customerName)
                                    .addComponent(invoiceTotal))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(invoiceTotalValue)
                                    .addComponent(customerNameValue))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(invoiceNumber)
                    .addComponent(invoiceNumberValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(invoiceDate)
                    .addComponent(invoiceDateValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerName)
                    .addComponent(customerNameValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(invoiceTotal)
                    .addComponent(invoiceTotalValue))
                .addGap(36, 36, 36)
                .addComponent(invoiceItemLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        invoicesTable.setAutoCreateRowSorter(true);
        invoicesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Date", "Customer", "Total"
            }
        ));
        jScrollPane1.setViewportView(invoicesTable);

        invoicesTableLabel.setText("Invoices table");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(invoicesTableLabel)
                        .addGap(0, 225, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(invoicesTableLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        createNewInvoiceBTN.setText("Create new invoice");
        createNewInvoiceBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createNewInvoiceBTNActionPerformed(evt);
            }
        });

        deleteInvoiceBTN.setText("Delete invoice");
        deleteInvoiceBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteInvoiceBTNActionPerformed(evt);
            }
        });

        saveBTN.setText("Add new item");

        cancelBTN.setText("Delete");

        jMenu1.setText("File");

        loadFileMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        loadFileMenuItem.setText("Load File");
        loadFileMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadFileMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(loadFileMenuItem);

        saveFileMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        saveFileMenuItem.setText("Save File");
        jMenu1.add(saveFileMenuItem);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(createNewInvoiceBTN)
                .addGap(18, 18, 18)
                .addComponent(deleteInvoiceBTN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
                .addComponent(saveBTN)
                .addGap(64, 64, 64)
                .addComponent(cancelBTN)
                .addGap(76, 76, 76))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createNewInvoiceBTN)
                    .addComponent(deleteInvoiceBTN)
                    .addComponent(saveBTN)
                    .addComponent(cancelBTN))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void createNewInvoiceBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createNewInvoiceBTNActionPerformed
        // TODO add your handling code here:
    }

    private void deleteInvoiceBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteInvoiceBTNActionPerformed
        // TODO add your handling code here:
    }

    private void loadFileMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadFileMenuItemActionPerformed
        // TODO add your handling code here:
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelBTN;
    private javax.swing.JButton createNewInvoiceBTN;
    private javax.swing.JLabel customerName;
    private javax.swing.JLabel customerNameValue;
    private javax.swing.JButton deleteInvoiceBTN;
    private javax.swing.JLabel invoiceDate;
    private javax.swing.JLabel invoiceDateValue;
    private javax.swing.JTable invoicesTable;
    private javax.swing.JTable invoiceItem;
    private javax.swing.JLabel invoiceItemLabel;
    private javax.swing.JLabel invoiceNumber;
    private javax.swing.JLabel invoiceNumberValue;
    private javax.swing.JLabel invoiceTotal;
    private javax.swing.JLabel invoiceTotalValue;
    private javax.swing.JLabel invoicesTableLabel;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenuItem loadFileMenuItem;
    private javax.swing.JButton saveBTN;
    private javax.swing.JMenuItem saveFileMenuItem;
    // End of variables declaration//GEN-END:variables
    private ArrayList<Invoice> invoicesArrayList;

    private invoicesTable invoiceTable;

    public JTable getInvTable(){
        return invoicesTable;
    }
    public invoicesTable getInvoiceTable() {
        return invoiceTable;
    }

    public void setInvoiceTable(invoicesTable invoiceTable) {
        this.invoiceTable = invoiceTable;
    }

    private Controller controller = new Controller(this);

    public ArrayList<Invoice> getInvoicesArrayList() {
        if (invoicesArrayList==null){
            //there will be an excpetion here
        }
        return invoicesArrayList;
    }

    public void setInvoicesArrayList(ArrayList<Invoice> invoicesArrayList) {
        this.invoicesArrayList = invoicesArrayList;
    }

    public JLabel getCustomerNameValue() {
        return customerNameValue;
    }

    public JLabel getInvoiceDateValue() {
        return invoiceDateValue;
    }

    public JTable getInvoiceItem() {
        return invoiceItem;
    }

    public JLabel getInvoiceNumberValue() {
        return invoiceNumberValue;
    }

    public JLabel getInvoiceTotalValue() {
        return invoiceTotalValue;
    }

    public JLabel getInvoicesTableLabel() {
        return invoicesTableLabel;
    }

    public Controller getController() {
        return controller;
    }

    public int getNextInvoiceNumber(){
        int returned = 0;
        invoicesArrayList = getInvoicesArrayList();
        for (Invoice invoice : invoicesArrayList){
            int num = invoice.getNum()+1;
            if (num > returned){
                returned = num;
            }
        }
        return returned;
    }
}
