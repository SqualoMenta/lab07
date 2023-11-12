package it.unibo.nestedenum;

import java.util.Comparator;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    @Override
    public Comparator<String> sortByDays() {
        return new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(Month.fromString(o1).getDays(), Month.fromString(o2).getDays());
            }

        };
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                return Month.fromString(o1).compareTo(Month.fromString(o2));
            }

        };
    }

    private enum Month {
        JANUARY(31),
        FEBRUARY(28),
        MARCH(31),
        APRIL(30),
        MAY(31),
        JUNE(30),
        JULY(31),
        AUGUST(31),
        SEPTEMBER(30),
        OCTOBER(31),
        NOVEMBER(30),
        DECEMBER(31);

        private final int days;

        Month(int days) {
            this.days = days;
        }

        public int getDays() {
            return days;
        }

        public static Month fromString(String month) {
            String name;
            Month out = JANUARY;
            boolean initialized = false;
            for (Month i : Month.values()) {
                if (i.name().length() >= month.length()) {
                    name = i.name().substring(0, month.length());
                    if (name.equalsIgnoreCase(month)) {
                        if (!initialized) {
                            out = i;
                            initialized = true;
                        } else {
                            throw new IllegalArgumentException("Invalid month string: " + month);
                        }
                    }
                }
            }
            if (!initialized) {
                throw new IllegalArgumentException("Invalid month string: " + month);
            } else {
                return out;
            }
        }

    }
}
