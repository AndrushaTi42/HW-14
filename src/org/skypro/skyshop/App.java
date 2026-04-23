package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;

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

    }

    public static void printSep() {
        System.out.println("=======================");
    }
}
