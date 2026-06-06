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
//        int sumBasket = 0;
//        // 1. Внешний цикл: беру списки товаров из Map'ы
//        for (List<Product> productsWithSameName : productBasket.values()) {
//            // 2. Внутренний цикл: перебираю товары внутри текущего списка
//            for (Product product : productsWithSameName) {
//                sumBasket += product.getPrice();
//            }
//        }
//        return sumBasket;
        return productBasket.values().stream()
                .flatMap(Collection::stream)  //list -> list.stream()
                .mapToInt(Product::getPrice) // Достаем цену каждого товара
                .sum();                      //суммируем
    }

    /**
     * метод подсчета спец товаров
     */
    private long getSpecialCount() {
        return (int) productBasket.values().stream()
                .flatMap(Collection::stream)
                .filter(Product::isSpecial) //фильтруем специальные товары
                .count();                   //подсчет количества
    }

    /**
     * метод вывода содержимого корзины
     */
    public void printBasketProduct() {
        if (productBasket.isEmpty()) {
            System.out.println("в корзине пусто");
            return;
        }
        //печать каждого товара из стрима
        productBasket.values().stream()
                .flatMap(Collection::stream)
                .forEach(System.out::println); //product -> System.out.println(product)
        //Итоговый вывод:
        System.out.println("Итого: " + getBasketTotalCost());
        System.out.println("Специальных товаров: " + getSpecialCount());
    }

    /**
     * метод проверки наличия продукта в корзине по именованию
     *
     * @param targetName
     * @return
     */
    public boolean existsByProductName(String targetName) {
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
