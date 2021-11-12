package com.labs.somipp;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import static java.util.Calendar.DAY_OF_WEEK;

public class CustomCalendar {
    java.util.Calendar c = java.util.Calendar.getInstance();

    public void getAction(){
        showOptions();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the number of the action.\nIn case you want to select multiple actions,\nwrite the numbers separated by comma:\n");
        String allActions = scan.nextLine();
        String[] actions = allActions.split(",");
        for (String act : actions){
            switch (act) {
                case "1":
                    showFullDate();
                    break;
                case "2":
                    System.out.println("Day of the week: ");
                    showWeekDay();
                    break;
                case "3":
                    System.out.println("Day of the month: ");
                    showMonthDay();
                    break;
                case "4":
                    System.out.println("Day of the year: ");
                    showYearDay();
                    break;
                case "5":
                    System.out.println("Week of the month: ");
                    showMonthWeek();
                    break;
                case "6":
                    System.out.println("Week of the year: ");
                    showYearWeek();
                    break;
                case "7":
                    System.out.println("Timezone: "+c.getTimeZone());
                    break;
            }
        }
    }

    public void showOptions(){
        System.out.println("1. Display current date\n" +
                "2.Day of the week\n" +
                "3.Day of the month\n" +
                "4.Day of the year\n" +
                "5.Week of the month\n" +
                "6.Week of the year\n" +
                "7.Timezone\n");
    }

    public void showFullDate(){
        Date d1 = new Date();
        System.out.println("Current date is " + d1);
    }

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
