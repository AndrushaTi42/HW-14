package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.Objects;

public class ProductBasket {
    private Product[] productBasket = new Product[5];
    private int count = 0;

    /**
     * метод добавления продуктов в корзину
     *
     * @param newProduct
     */
    public void addProduct(Product newProduct) {
        if (count < productBasket.length) {
            productBasket[count] = newProduct;
            count++;
        } else {
            System.out.println("Невозможно добавить продукт");
        }
    }

    /**
     * метод подсчета суммы продуктов в корзине
     *
     * @return
     */
    public int getBasketTotalCost() {
        int sumBasket = 0;
        for (int i = 0; i < count; i++) {
            sumBasket += productBasket[i].getPrice();
        }
        return sumBasket;
    }

    /**
     * метод метод вывода содержимого корзины
     */
    public void printBasketProduct() {
        if (count == 0) {
            System.out.println("в корзине пусто");
            return;
        }
        for (int i = 0; i < count; i++) {
            System.out.println(productBasket[i].getName() + ": " + productBasket[i].getPrice());
        }
        System.out.println("Итого: " + getBasketTotalCost());
    }

    /**
     * метод проверки наличия продукта в корзине по именованию
     *
     * @param targetName
     * @return
     */
    public boolean existsByProductName(String targetName) {
        for (int i = 0; i < count; i++) {
            if (Objects.equals(targetName, productBasket[i].getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * метод отчистки корзины
     */
    public void clearBasket() {
        if (count == 0) {
            System.out.println("в корзине пусто");
            return;
        }
        for (int i = 0; i < count; i++) {
            productBasket[i] = null;
        }
        count = 0;
    }

}
