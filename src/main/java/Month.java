package main.java;

public enum Month {

    JANUARY(1, 31),
    FEBRUARY(2, 28),
    MARCH(3, 31),
    APRIL(4, 30),
    MAY(5, 31),
    JUNE(6, 30),
    JULY(7, 31),
    AUGUST(8, 31),
    SEPTEMBER(9, 30),
    OCTOBER(10, 31),
    NOVEMBER(11, 30),
    DECEMBER(12, 31);

    private int num;
    private int numDay;

    Month(int num, int numDay) {
        this.num = num;
        this.numDay = numDay;
    }

    public int getNum() {
        return num;
    }

    public int getNumDay() {
        return numDay;
    }

    public static Month getMonth(int num) {
        Month[] values = Month.values();

        for (Month item : values) {
            if (item.getNum() == num) {
                return item;
            }
        }

        return null;
    }

}
