package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.*;
import org.skypro.skyshop.search.BestResultNotFound;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;

import java.util.*;

public class App {
    public static void main(String[] args) {
//создаем продукты
        Product egg = new FixPriceProduct("Egg");
        Product milk = new SimpleProduct("Milk", 140);
        Product meat = new DiscountedProduct("Meat", 550, 15);
        Product tea = new SimpleProduct("Tea", 200);
        Product butter = new FixPriceProduct("Butter");
        Product water = new SimpleProduct("Water", 55);
//инициализация объекта класса ProductBasket
        ProductBasket basket = new ProductBasket();
// 1. добавляем продукты в корзину
        basket.addProduct(egg);
        basket.addProduct(meat);
        basket.addProduct(milk);
        basket.addProduct(butter);
        basket.addProduct(water);
// 2. Печать содержимого корзины с несколькими товарами.
        basket.printBasketProduct();
        printSep();
// 3. Получение стоимости корзины с несколькими товарами.
        System.out.println(basket.getBasketTotalCost());
        printSep();
// 4. Поиск товара, который есть в корзине.
        System.out.println(basket.existsByProductName("Milk"));
        printSep();
// 5. Поиск товара, которого нет в корзине.
        System.out.println(basket.existsByProductName("Tea"));
        printSep();
// 6. Очистка корзины.
        basket.clearBasket();
// 7. Печать содержимого пустой корзины.
        basket.printBasketProduct();
        printSep();
// 8. Получение стоимости пустой корзины.
        System.out.println(basket.getBasketTotalCost());
        printSep();
// 9. Поиск товара по имени в пустой корзине.
        System.out.println(basket.existsByProductName("Milk"));
        printSep();
// 10. Создаем статью о товаре.
        Article articleOfButter = new Article("Алтайские хлеба",
                "Наш хлеб мы выпекаем самостоятельно, потому он всегда свежий. " +
                        "После 20:00 скидка на хлебобулочную продукцию 35%");
// 11. Инициализация объекта класса searchEngine.
        SearchEngine searchEngine = new SearchEngine();
// 12. Добавляю продукты/статью в массив поиска.
        searchEngine.add(egg);
        searchEngine.add(meat);
        searchEngine.add(milk);
        searchEngine.add(butter);
        searchEngine.add(articleOfButter);
        searchEngine.add(water);
// 13. Проверка работы поиска по статье и продукту.
        printResults(searchEngine.search("алтайские хлеба"));
        printResults(searchEngine.search("water"));
        printSep();
// 14. Проверка новых методов.
        System.out.println(articleOfButter.getSearchTerm());
        System.out.println(articleOfButter.getContentType());
        printSep();
        System.out.println(water.getSearchTerm());
        System.out.println(water.getContentType());
        printSep();
// 15. Создание заведомо неверных продуктов для проверки исключений.
        try {
            Product cola = new DiscountedProduct("Cola", 150, 110);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
        try {
            Product chips = new SimpleProduct("Lays", 0);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
        try {
            Product rice = new FixPriceProduct(" ");
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
        printSep();
// 16. Проверка работы метода findBestMatch.
        try {
            Searchable result = searchEngine.findBestMatch("оладьи");
            System.out.println("Лучшее совпадение " + result.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println(e.getMessage());
        }
        try {
            Searchable result = searchEngine.findBestMatch("хлеб");
            System.out.println("Лучшее совпадение " + result.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println(e.getMessage());
        }
        printSep();
// 17. Проверка удаления по именованию removeProduct
        //сперва добавлю в корзину
        basket.addProduct(egg);
        basket.addProduct(meat);
        basket.addProduct(milk);
        basket.addProduct(butter);
        basket.addProduct(water);
        //далее проверяю removeProduct и вывожу удаленные продукты
        System.out.println(basket.removeProduct("Egg"));
        System.out.println(basket.removeProduct("meat"));
        printSep();
// 18. Вывод содержимого корзины после удаления
        basket.printBasketProduct();
        printSep();
// 19. Удаление несуществующего продукта
        basket.removeProduct("oil");
        printSep();
// 20.  Вывод содержимого корзины
        basket.printBasketProduct();
        printSep();
//21. Проверка нового вывода всех элементов с сортировкой длины имен по убыванию
        Set<Searchable> allElement = searchEngine.search("");
        printResults(allElement);

    }


    public static void printSep() {
        System.out.println("=======================");
    }

    /**
     * метод для вывода поиска без null
     *
     * @param results
     */
    public static void printResults(Set<Searchable> results) {
        if (results.isEmpty()) {
            System.out.println("Ничего не найдено.");
            return;
        }
        for (Searchable item : results) {
            System.out.println(item.getStringRepresentation());
        }
    }
}
