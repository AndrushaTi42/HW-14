package org.skypro.skyshop.search;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SearchEngine {
    private final List<Searchable> searchables = new LinkedList<>();

    /**
     * метод поиска
     *
     * @param query
     * @return
     */
    public List<Searchable> search(String query) {
        List<Searchable> results = new LinkedList<>();
        for (Searchable searchable : searchables) {
            if (searchable.getSearchTerm().toLowerCase().contains(query.toLowerCase())) {
                results.add(searchable);
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
        searchables.add(item);
    }

    /**
     * метод поиска лучшего совпадения
     *
     * @param search
     * @return
     * @throws BestResultNotFound
     */
    public Searchable findBestMatch(String search) throws BestResultNotFound {
        Searchable bestMatch = null;
        int maxCount = 0;
        for (Searchable searchable : searchables) {
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
