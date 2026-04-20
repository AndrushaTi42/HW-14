package org.skypro.skyshop.product;

public class DiscountedProduct extends Product {
    private int basePrice;
    private int discount;

    public DiscountedProduct(String name, int basePrice, int discount) {
        super(name);
        this.basePrice = basePrice;
        this.discount = discount;
    }

    @Override
    public int getPrice() {
        return (int) (basePrice * (1 - (discount / 100.0)));
    }

    @Override
    public String toString() {
        return getName() + ": " + getPrice() + " (" + discount + ")";
    }
}
