package com.model;

public class InvoiceItems {
    private Invoice invoice;
    private Item item;
    private float itemPrice;
    private int itemQun;

    public InvoiceItems() {
    }

    public InvoiceItems(Invoice invoice, Item item, float itemPrice, int itemQun) {
        this.invoice = invoice;
        this.item = item;
        this.itemPrice = itemPrice;
        this.itemQun = itemQun;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setItemPrice(float itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setItemQun(int itemQun) {
        this.itemQun = itemQun;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public Item getItem() {
        return item;
    }

    public float getItemPrice() {
        return itemPrice;
    }

    public int getItemQun() {
        return itemQun;
    }

    public InvoiceItems createInvoiceItem(String[] metadata) {
        Invoice invoice1 = new Invoice(Integer.parseInt(metadata[0]));
        Item item1 = new Item(metadata[1]);
        int itemPrice1 = Integer.parseInt(metadata[2]);
        int itemQun1 = Integer.parseInt(metadata[3]);




        return new InvoiceItems(invoice1, item1, itemPrice1,itemQun1);

    }

    @Override
    public String toString() {
        return "InvoiceItem Data invoice number is :"+this.invoice.getInvoiceNumber() + " , item name is :"+
                this.getItem() + " , item price = "+ this.getItemPrice() + " , qun = "+ this.getItemQun();
    }
}
