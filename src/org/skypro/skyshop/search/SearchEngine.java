package org.skypro.skyshop.search;

import java.util.*;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class SearchEngine {
    private final Set<Searchable> searchables = new HashSet<>();

    /**
     * метод поиска, возвращающий отсортированный по именам Set
     *
     * @param query поисковый запрос
     * @return Map, где ключ это имя объекта, а значение это сам объект Searchable
     */
    public Set<Searchable> search(String query) {

        return searchables.stream()
                .filter(s -> s.getSearchTerm().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toCollection(() -> new TreeSet<>((s1, s2) -> {
                    int lenghtCompare = Integer.compare(s2.getName().length(), s1.getName().length());
                    if (lenghtCompare != 0) {
                        return lenghtCompare;
                    }
                    return s1.getName().compareTo(s2.getName());
                })));
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
