package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ProductBasket {
    private List<Product> productBasket = new LinkedList<>();


    /**
     * метод добавления продуктов в корзину
     *
     * @param newProduct
     */
    public void addProduct(Product newProduct) {
        productBasket.add(newProduct);
    }

    /**
     * метод подсчета суммы продуктов в корзине
     *
     * @return
     */
    public int getBasketTotalCost() {
        int sumBasket = 0;
        for (Product product : productBasket) {
            sumBasket += product.getPrice();
        }
        return sumBasket;
    }

    /**
     * метод метод вывода содержимого корзины
     */
    public void printBasketProduct() {
        int specialPriceCount = 0;
        if (productBasket.isEmpty()) {
            System.out.println("в корзине пусто");
            return;
        }
        for (Product product : productBasket) {
            System.out.println(product);
            if (product.isSpecial()) {
                specialPriceCount++;
            }
        }
        System.out.println("Итого: " + getBasketTotalCost());
        System.out.println("Специальных товаров: " + specialPriceCount);
    }

    /**
     * метод проверки наличия продукта в корзине по именованию
     *
     * @param targetName
     * @return
     */
    public boolean existsByProductName(String targetName) {
        for (Product product : productBasket) {
            if (Objects.equals(targetName, product.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * метод отчистки корзины
     */
    public void clearBasket() {
        productBasket.clear();
    }

    /**
     * метод удаления продукта по имени из корзины
     *
     * @param name
     * @return
     */
    public List<Product> removeProduct(String name) {
        List<Product> removedProducts = new LinkedList();
        Iterator<Product> iterator = productBasket.iterator();
        while (iterator.hasNext()) {
            Product currentProduct = iterator.next();
            if (currentProduct.getName().equalsIgnoreCase(name)) {
                removedProducts.add(currentProduct);
                iterator.remove();
            }
        }
        if (removedProducts.isEmpty()) {
            System.out.println("Список пуст");
        }
        return removedProducts;
    }

}
