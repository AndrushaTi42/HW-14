package org.skypro.skyshop.product;

public class DiscountedProduct extends Product {
    private int basePrice;
    private int discount;

    public DiscountedProduct(String name, int basePrice, int discount) {
        super(name);
        if (basePrice <= 0) {
            throw new IllegalArgumentException("Не корректная базовая цена.");
        } else if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Не корректный размер скидки.");
        }
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

    @Override
    public boolean isSpecial() {
        return false;
    }
}
