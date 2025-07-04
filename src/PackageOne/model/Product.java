package PackageOne.model;

import java.time.LocalDate;

public abstract class Product {
    protected String name;
    protected double price;
    protected int quantity;
    protected LocalDate expiryDate;
    protected double discount;

    public Product(String name,double price,int quantity){
        this.name=name;
        this.price=price;
        this.quantity=quantity;
    }

    public String getName(){
        return name;
    }

    public double getPrice(){
        return price;
    }

    public int getQuantity(){
        return quantity;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getFinalPrice() {
        return price - discount;
    }

    public void decreaseQuantity(int amount) {
        this.quantity -= amount;
    }

    public boolean isExpired() {
        return expiryDate != null && expiryDate.isBefore(LocalDate.now());
    }

    public abstract boolean isShippable();
}
