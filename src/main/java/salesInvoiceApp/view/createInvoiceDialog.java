package salesInvoiceApp.view;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JDialog;

public class createInvoiceDialog extends JDialog {

    private JTextField customerName;
    private JTextField invoiceDate;
    private JLabel custName;
    private JLabel invoDate;

    private String dateString;
    private Date date = new Date();
    private JButton OKBTN;
    private JButton CancelBTN;



    public createInvoiceDialog(GUI frame) {

        custName = new JLabel("Customer Name");
        customerName =new JTextField(20);
        invoDate = new JLabel("Date");
        date =new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        dateString = sdf.format(date);
        invoiceDate = new JTextField(dateString);

        OKBTN = new JButton("OK");
        OKBTN.setActionCommand("createInvoiceOK");
        OKBTN.addActionListener(frame.getController());

        CancelBTN = new JButton("Cancel");
        CancelBTN.setActionCommand("createInvoiceCancel");
        CancelBTN.addActionListener(frame.getController());

        setLayout(new GridLayout(4,2));

        add(invoDate);
        add(invoiceDate);
        add(custName);
        add(customerName);
        add(OKBTN);
        add(CancelBTN);

        pack();

    }

    public JTextField getCustomerName() {
        return customerName;
    }

    public JTextField getDate() {
        return invoiceDate;
    }

}
