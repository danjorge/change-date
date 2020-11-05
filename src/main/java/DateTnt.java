package main.java;

public class DateTnt {

    private Integer day;
    private Integer month;
    private Integer year;
    private Integer hour;
    private Integer minute;
    private Integer minutesDay;

    /**
     * @param date  An date as String in the format ?dd/MM/yyyy HH24:mi?
     * @param op    Can be only ?+? | ?-?
     * @param value the value that should be incremented/decremented. It will be expressed in minutes
     * @return
     */
    public String changeDate(String date, char op, long value) throws Exception {
        disassemblesDate(date);

        if (value < 0) {
            value = value * -1;
            op = '-';
        }

        if (op != '-' && op != '+') {
            throw new Exception("Tis op is invalid");
        }

        addDaysInDate(countDays(value, op), op);

        return print();
    }

    private Integer countDays(long minutes, char op) {
        Integer days = 0;

        while (minutes != 0) {
            if (op == '+') {
                if (this.minutesDay.equals(1439)) {
                    this.minutesDay = 0;
                    days++;
                } else {
                    this.minutesDay++;
                }
            }

            if (op == '-') {
                if (this.minutesDay.equals(0)) {
                    this.minutesDay = 1439;
                    days++;
                } else {
                    this.minutesDay--;
                }
            }

            minutes--;
        }

        return days;
    }

    private void addDaysInDate(Integer days, char op) {
        Month month;

        while (days != 0) {
            if (op == '+') {
                month = Month.getMonth(this.month);

                if (this.day.intValue() == month.getNumDay()) {
                    this.day = 1;

                    if (this.month.intValue() == 12) {
                        this.year++;
                        this.month = 1;
                    } else {
                        this.month++;
                    }
                } else {
                    this.day++;
                }
            }

            if (op == '-') {
                if (this.day == 1) {
                    if (this.month == 1) {
                        this.year--;
                        this.month = 12;
                    } else {
                        this.month--;
                        month = Month.getMonth(this.month);
                        this.day = month.getNumDay();
                    }
                } else {
                    this.day--;
                }
            }

            days--;
        }
    }

    private void disassemblesDate(String dateHour) {
        String[] dates = dateHour.split(" ");

        if (dates != null) {
            if (dates.length == 2) {
                String[] date = dates[0].split("/");
                this.day = Integer.valueOf(date[0]);
                this.month = Integer.valueOf(date[1]);
                this.year = Integer.valueOf(date[2]);

                String[] hour = dates[1].split(":");
                this.hour = Integer.valueOf(hour[0]);
                this.minute = Integer.valueOf(hour[1]);
                this.minutesDay = (this.hour * 60) + this.minute;
            }
        }
    }

    private void convertMinutesDayForHourAndMinute(long minutes) {
        Long minute = minutes % 60;
        Long hour = (minutes - minute) / 60;

        this.hour = Integer.valueOf(hour.toString());
        this.minute = Integer.valueOf(minute.toString());
    }

    private String print() {
        convertMinutesDayForHourAndMinute(this.minutesDay);

        String d = this.day.toString();
        String m = this.month.toString();
        String h = this.hour.toString();
        String mi = this.minute.toString();

        if (this.day < 10) {
            d = "0" + this.day;
        }

        if (this.month < 10) {
            m = "0" + this.month;
        }

        if (this.hour < 10) {
            h = "0" + this.hour;
        }

        if (this.minute < 10) {
            mi = "0" + this.minute;
        }

        return  d + "/" + m + "/" + this.year + " " + h + ":" + mi;
    }


    public static void main(String[] args) throws Exception {
        String changeDate = new DateTnt().changeDate("15/01/2007 13:22", '+', 150672);
        System.out.println(changeDate);
    }
}