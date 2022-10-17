package salesInvoiceApp.model;

public class Line {
    private int num;
    private int count;
    private double price;
    private String item;
    private Invoice invoice;

    public Line() {
    }

    public Line(int num, int count, double price, String item) {
        this.num = num;
        this.count = count;
        this.price = price;
        this.item = item;
    }

    public Line(int num, int count, double price, String item, Invoice invoice) {
        this.num = num;
        this.count = count;
        this.price = price;
        this.item = item;
        this.invoice = invoice;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    @Override
    public String toString() {
        return "Line{" +
                "num=" + num +
                ", count=" + count +
                ", price=" + price +
                ", item='" + item + '\'' +
                '}';
    }

    public double calculateLineTotal(){
        return count*price;
    }
}
