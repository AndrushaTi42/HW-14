package org.skypro.skyshop.product;

import org.skypro.skyshop.search.Searchable;

public final class Article implements Searchable {
    private final String name;
    private final String text;

    public Article(String name, String text) {
        this.name = name;
        this.text = text;
    }

    @Override
    public String toString() {
        return name + System.lineSeparator() + text;
    }

    /**
     * возврат названия и текста статьи
     *
     * @return
     */
    @Override
    public String getSearchTerm() {
        return this.toString();
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * тип товара
     *
     * @return
     */
    @Override
    public String getContentType() {
        return "ARTICLE";
    }
}
