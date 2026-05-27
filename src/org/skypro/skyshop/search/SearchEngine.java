package org.skypro.skyshop.search;

import java.util.*;
import java.util.Comparator;
import java.util.TreeSet;

public class SearchEngine {
    private final Set<Searchable> searchables = new HashSet<>();

    /**
     * метод поиска, возвращающий отсортированный по именам Set
     *
     * @param query поисковый запрос
     * @return Map, где ключ это имя объекта, а значение это сам объект Searchable
     */
    public Set<Searchable> search(String query) {
        Set<Searchable> results = new TreeSet<>((s1, s2) -> {
            //сравниваем длину имен в обратном порядке(для изменения стандартной сортировки compare
            //от меньшего к большему на сортировку от большего к меньшему)
            int lengthCompare = Integer.compare(s2.getName().length(), s1.getName().length());
            //если compare возвращает 0 идем дальше, иначе возвращем имя
            if (lengthCompare != 0) {
                return lengthCompare;
            }
            //возврат в натуральном порядке при одинаковых длинах
            return s1.getName().compareTo(s2.getName());
        });
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
