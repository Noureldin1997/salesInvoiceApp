package salesInvoiceApp.view;

import javax.swing.*;
import java.awt.*;

public class addLineDialog extends JDialog {
    private JLabel productNameL;
    private JTextField productNameValue;
    private JLabel countL;
    private JTextField countValue;
    private JLabel priceL;
    private JTextField priceValue;
    private JButton OKBTNL;
    private JButton CancelBTNl;

    public addLineDialog(GUI frame) {
        productNameL = new JLabel("Enter the product");
        productNameValue = new JTextField(20);
        countL = new JLabel("Enter the count");
        countValue = new JTextField(20);
        priceL = new JLabel("Enter the price");
        priceValue = new JTextField(20);
        //Total=new JLabel("The total is");
        //  int Count=Integer.parseInt(countT.getText());
        //int price=Integer.parseInt(PriceT.getText());
        //int totalPrice=price*Count;
        //String theTotal=Integer.toString(totalPrice);
        //TotalT.setText(theTotal);
        OKBTNL = new JButton("OK");
        OKBTNL.addActionListener(frame.getController());
        OKBTNL.setActionCommand("addLineOK");


        CancelBTNl = new JButton("Cancel");
        CancelBTNl.addActionListener(frame.getController());
        CancelBTNl.setActionCommand("addLineCancel");

        setLayout(new GridLayout(7, 3));
        add(productNameL);
        add(productNameValue);
        add(countL);
        add(countValue);
        add(priceL);
        add(priceValue);
        add(OKBTNL);
        add(CancelBTNl);

        pack();
    }


    public JTextField getProductNameValue() {
        return productNameValue;
    }

    public JTextField getCountValue() {
        return countValue;
    }

    public JTextField getPriceValue() {
        return priceValue;
    }
}




