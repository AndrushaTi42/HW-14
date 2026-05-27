package org.skypro.skyshop.product;

import org.skypro.skyshop.search.Searchable;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Searchable)) return false;
        Searchable that = (Searchable) o;
        return Objects.equals(this.getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }
}
