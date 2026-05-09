package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.*;
import org.skypro.skyshop.search.BestResultNotFound;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;

import java.util.Arrays;

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
// 2. добавление в переполненную корзину
        basket.addProduct(tea);
        printSep();
// 3. Печать содержимого корзины с несколькими товарами.
        basket.printBasketProduct();
        printSep();
// 4. Получение стоимости корзины с несколькими товарами.
        System.out.println(basket.getBasketTotalCost());
        printSep();
// 5. Поиск товара, который есть в корзине.
        System.out.println(basket.existsByProductName("Milk"));
        printSep();
// 6. Поиск товара, которого нет в корзине.
        System.out.println(basket.existsByProductName("Tea"));
        printSep();
// 7. Очистка корзины.
        basket.clearBasket();
        basket.clearBasket(); //второй раз для проверки вывода "в корзине пусто"
        printSep();
// 8. Печать содержимого пустой корзины.
        basket.printBasketProduct();
        printSep();
// 9. Получение стоимости пустой корзины.
        System.out.println(basket.getBasketTotalCost());
        printSep();
// 10. Поиск товара по имени в пустой корзине.
        System.out.println(basket.existsByProductName("Milk"));
        printSep();
// 11. Создаем статью о товаре.
        Article articleOfButter = new Article("Алтайские хлеба",
                "Наш хлеб мы выпекаем самостоятельно, потому он всегда свежий. " +
                        "После 20:00 скидка на хлебобулочную продукцию 35%");
// 12. Инициализация объекта класса searchEngine.
        SearchEngine searchEngine = new SearchEngine(10);
// 13. Добавляю продукты/статью в массив поиска.
        searchEngine.add(egg);
        searchEngine.add(meat);
        searchEngine.add(milk);
        searchEngine.add(butter);
        searchEngine.add(articleOfButter);
        searchEngine.add(water);
// 14. Проверка работы поиска по статье и продукту.
        Searchable[] results = searchEngine.search("алтайские хлеба");
        printResults(results);
        results = searchEngine.search("water");
        printResults(results);
        printSep();
// 15. Проверка новых методов.
        System.out.println(articleOfButter.getSearchTerm());
        System.out.println(articleOfButter.getContentType());
        printSep();
        System.out.println(water.getSearchTerm());
        System.out.println(water.getContentType());
        printSep();
// 16. Создание заведомо неверных продуктов для проверки исключений.
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
// 17. Проверка работы метода findBestMatch.
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

    }


    public static void printSep() {
        System.out.println("=======================");
    }

    /**
     * метод для вывода поиска без null
     *
     * @param results
     */
    public static void printResults(Searchable[] results) {
        boolean found = false;
        for (Searchable item : results) {
            if (item != null) {
                System.out.println(item.getStringRepresentation());
                found = true;
            }
        }
        if (!found) {
            System.out.println("Ничего не найдено.");
        }
    }
}
