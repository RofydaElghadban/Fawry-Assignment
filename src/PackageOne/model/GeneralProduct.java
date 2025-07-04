package PackageOne.model;

import java.time.LocalDate;
public class GeneralProduct extends Product implements Shippable {
    private final boolean isExpirable;
    private final LocalDate expiryDate;
    private final boolean isShippable;
    private final double weight;

    public GeneralProduct(String name,double price,int quantity,boolean isExpirable,LocalDate expiryDate,boolean isShippable,double weight){
        super(name,price,quantity);
        this.isExpirable=isExpirable;
        this.expiryDate=expiryDate;
        this.isShippable=isShippable;
        this.weight=weight;
    }

    @Override
    public boolean isExpired() {
        return isExpirable && expiryDate != null && expiryDate.isBefore(LocalDate.now());
    }

    @Override
    public boolean isShippable() {
        return isShippable;
    }

    public double getWeight(){
        return weight;
    }
}
