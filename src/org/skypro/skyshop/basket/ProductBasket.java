package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.*;

public class ProductBasket {
    private final Map<String, List<Product>> productBasket = new HashMap<>();


    /**
     * метод добавления продуктов в корзину
     *
     * @param newProduct
     */
    public void addProduct(Product newProduct) {
        // Если имени нет, computeIfAbsent создаст для него пустой ArrayList,
        // а затем добавляется (add) новый продукт в этот список
        productBasket.computeIfAbsent(newProduct.getName(), k -> new ArrayList<>()).add(newProduct);
    }

    /**
     * метод подсчета суммы продуктов в корзине
     *
     * @return
     */
    public int getBasketTotalCost() {
        int sumBasket = 0;
        // 1. Внешний цикл: беру списки товаров из Map'ы
        for (List<Product> productsWithSameName : productBasket.values()) {
            // 2. Внутренний цикл: перебираю товары внутри текущего списка
            for (Product product : productsWithSameName) {
                sumBasket += product.getPrice();
            }
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
        for (List<Product> productsWithSameName : productBasket.values()) {
            for (Product product : productsWithSameName) {
                System.out.println(product);
                if (product.isSpecial()) {
                    specialPriceCount++;
                }
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
//      return productBasket.getOrDefault(targetName, null) != null;
        return productBasket.containsKey(targetName);
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
        // 1. Удаление ключа из Map'ы и присваивание удаленного списка в removedProducts
        List<Product> removedProducts = productBasket.remove(name);
        // 2. Если имя не найдено, метод remove вернет null.
        if (removedProducts == null) {
            // Возвращаю пустой список вместо null
            System.out.println("Список пуст");
            return new ArrayList<>();
        }
        return removedProducts;
    }

}
