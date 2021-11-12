package com.labs.somipp;

import java.util.Date;
import java.util.Scanner;

public class CustomCalendar {
    java.util.Calendar c = java.util.Calendar.getInstance();

    /**
     * Function to display available commands.
     * Takes no parameters.
     */
    public void showOptions(){
        System.out.println("1. Display current date >> show full date\n" +
                "2.Day of the week -- show dow\n" +
                "3.Day of the month -- show dom\n" +
                "4.Day of the year -- show doy\n" +
                "5.Week of the month -- show wom\n" +
                "6.Week of the year -- show woy\n" +
                "7.Timezone -- show tmz\n");
    }

    /**
     * Function to get the commands from the user.
     * The commands are read during the runtime, through a scanner.
     * After the current commands are executed, the program re-calls main program's getActions(), in order
     * to receive new commands.
     */
    public void getAction(){
        showOptions();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the commands.\nIn case you want to run multiple commands,"+
                "\nwrite them separated by comma:\n");
        String allActions = scan.nextLine();
        String[] actions = allActions.split(",");
        for (String act : actions){
            switch (act) {
                case "show full date":
                    showFullDate();
                    break;
                case "show dow":
                    System.out.println("Day of the week: ");
                    showWeekDay();
                    break;
                case "show dom":
                    System.out.println("Day of the month: ");
                    showMonthDay();
                    break;
                case "show doy":
                    System.out.println("Day of the year: ");
                    showYearDay();
                    break;
                case "show wom":
                    System.out.println("Week of the month: ");
                    showMonthWeek();
                    break;
                case "show woy":
                    System.out.println("Week of the year: ");
                    showYearWeek();
                    break;
                case "show tmz":
                    System.out.println("Timezone: "+c.getTimeZone());
                    break;
            }
        }
    }

    /**
     * Allocates a Date object and initializes
     * it so that it represents the time at which it was allocated,
     * measured to the nearest millisecond.
     */
    public void showFullDate(){
        Date d1 = new Date();
        System.out.println("Current date is " + d1);
    }

    /**
     * Function to display the name of the day of the week.
     */
    public void showWeekDay(){
        int day = c.get(7);
        switch (day) {
            case 1:
                System.out.println("Monday");
                break;
            case 2:
                System.out.println("Tuesday");
                break;
            case 3:
                System.out.println("Wednesday");
                break;
            case 4:
                System.out.println("Thursday");
                break;
            case 5:
                System.out.println("Friday");
                break;
            case 6:
                System.out.println("Saturday");
                break;
            case 7:
                System.out.println("Sunday");
                break;
        }
    }

    /** The functions below return the value of the given calendar field. In lenient mode, all calendar fields
     * are normalized. In non-lenient mode, all calendar fields are validated and this method throws an exception
     * if any calendar fields have out-of-range values. The normalization and validation are handled by the complete() method,
     * which process is calendar system dependent.
     * params  field – the given calendar field, defined within the built-in Calendar class.
     * Each function returns the value for the given calendar field and @throws ArrayIndexOutOfBoundsException –
     * if the specified field is out of range (field smaller than 0 or field is greater tha or equal to FIELD_COUNT).*/
    public void showMonthDay(){
        System.out.println(c.get(2));
    }

    public void showYearDay(){
        System.out.println(c.get(7));
    }

    public void showMonthWeek(){
        System.out.println(c.get(4));
    }

    public void showYearWeek(){
        System.out.println(c.get(3));
    }


}
