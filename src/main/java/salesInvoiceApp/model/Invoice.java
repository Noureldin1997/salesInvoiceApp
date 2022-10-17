package salesInvoiceApp.model;

import java.util.ArrayList;

public class Invoice {
    private int num;
    private String customer;
    private String Date;
    private ArrayList<Line> lines;

    public Invoice() {
    }

    public Invoice(int num, String customer, String Date) {
        this.num = num;
        this.customer = customer;
        this.Date = Date;
    }

    public Invoice(int num, String customer, String Date, ArrayList<Line> lines) {
        this.num = num;
        this.customer = customer;
        this.Date = Date;
        this.lines = lines;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public ArrayList<Line> getLines() {
        if(lines == null){
            lines = new ArrayList<>();
        }
        return lines;
    }

    public void setLines(ArrayList<Line> lines) {
        this.lines = lines;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "num=" + num +
                ", customer='" + customer + '\'' +
                ", Date='" + Date + '\'' +
                ", lines=" + lines +
                '}';
    }

    public double calculateTotal(){
        double toBeReturnedTotal = 0.0;
        for (Line line : getLines()){
            toBeReturnedTotal += line.calculateLineTotal();
        }
        return toBeReturnedTotal;
    }


}

