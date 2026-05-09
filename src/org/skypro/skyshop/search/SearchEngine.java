package org.skypro.skyshop.search;

import java.util.Arrays;

public class SearchEngine {
    private final Searchable[] searchables;
    int size = 0; //счетчик для добавления в массив

    public SearchEngine(int index) {
        this.searchables = new Searchable[index]; //размерность массива ч/з конструктор
    }

    /**
     * метод поиска
     *
     * @param query
     * @return
     */
    public Searchable[] search(String query) {
        Searchable[] results = new Searchable[5];
        int foundCount = 0;
        for (int i = 0; i < searchables.length; i++) {
            if (searchables[i] != null && searchables[i].getSearchTerm()
                    .toLowerCase().contains(query.toLowerCase())) {
                results[foundCount] = searchables[i];
                foundCount++;
                if (foundCount == 5) {
                    break;
                }
            }

        }
        return results;
    }


    /**
     * метод добавление в поисковый массив
     *
     * @param item
     */
    public void add(Searchable item) {
        if (item == null) return;
        if (size < searchables.length) {
            searchables[size] = item;
            size++;
        } else {
            System.out.println("Не возможно добавить - массив переполнен.");
        }

    }

    /**
     * метод поиска лучшего совпадения
     * @param search
     * @return
     * @throws BestResultNotFound
     */
    public Searchable findBestMatch(String search) throws BestResultNotFound {
        Searchable bestMatch = null;
        int maxCount = 0;
        for (Searchable searchable : searchables) {
            if (searchable == null) continue;
            int currentCount = 0;
            int index = 0;
            String text = searchable.getSearchTerm().toLowerCase();
            while ((index = text.indexOf(search.toLowerCase(), index)) != -1) {
                currentCount++;
                index += search.length();
            }
            if (currentCount > maxCount) {
                maxCount = currentCount;
                bestMatch = searchable;
            }
        }
        if (bestMatch == null) {
            throw new BestResultNotFound(search);
        }
        return bestMatch;
    }
}
