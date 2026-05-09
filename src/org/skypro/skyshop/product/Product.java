package org.skypro.skyshop.product;

import org.skypro.skyshop.search.Searchable;

public abstract class Product implements Searchable {
    private final String name;

    public Product(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Отсутствует именование продукта.");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract int getPrice();

    public abstract boolean isSpecial();

    /**
     * возврат имени товара
     *
     * @return
     */
    @Override
    public String getSearchTerm() {
        return name;
    }

    /**
     * возврат типа товара
     *
     * @return
     */
    @Override
    public String getContentType() {
        return "PRODUCT";
    }

}
