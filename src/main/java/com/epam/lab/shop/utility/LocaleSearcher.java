package com.epam.lab.shop.utility;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class LocaleSearcher {

    public static Locale chooseLocaleFromAccessibleLocales(List<Locale> locales, Locale locale) {
        Locale result = null;
        Comparator<Locale> searchComparator = new SearchComparator();
        locales.sort(searchComparator);
        int index = Collections.binarySearch(locales, locale, searchComparator);
        if (index >= 0) {
            result = locales.get(index);
        }
        return result;
    }

    private static class SearchComparator implements Comparator<Locale> {
        @Override
        public int compare(Locale locale1, Locale locale2) {
            return locale1.getLanguage().compareTo(locale2.getLanguage());
        }
    }
}