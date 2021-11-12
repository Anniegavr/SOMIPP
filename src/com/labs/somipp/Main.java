package com.labs.somipp;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.io.File.listRoots;

public class Main {
    public static void showOptions(){
        System.out.println("1.Shut down computer\n" +
                "2.Restart computer\n" +
                "3.Show my IP address\n" +
                "4.Text file manipulations\n" +
                "5.What time is it?\n" +
                "6.What date is today?\n" +
                "7.Calendar\n" +
                "8.Search\n" +
                "9.List roots\n" +
                "10.List files in a directory\n" +
                "11.Exit\n");
    }

    public static void getActions() throws IOException, InterruptedException {
        System.out.println("\n");
        Scanner scan = new Scanner(System.in);
        String allActions = scan.nextLine();
        String[] actions = allActions.split(",");
        for (String act : actions) {
            switch (act) {
                case "1":
                    shutdownPC();
                    getActions();
                    break;
                case "2":
                    restartPC();
                    getActions();
                    break;
                case "3":
                    showMyIp();
                    getActions();
                    break;
                case "4":
                    Scanner fileName = new Scanner(System.in);
                    System.out.println();
                    FileEditor fe = new FileEditor(fileName.nextLine());
                    fe.getActions();
                    getActions();
                    break;
                case "5":
                    System.out.println(java.time.LocalTime.now());
                    getActions();
                    break;
                case "6":
                    System.out.println(java.time.LocalDate.now());
                    getActions();
                    break;
                case "7":
                    CustomCalendar mainCalendar = new CustomCalendar();
                    mainCalendar.getAction();
                    getActions();
                    break;
                case "8":
                    searchForFile();
                    getActions();
                    break;
                case "9":
                    listRoots();
                    getActions();
                    break;
                case "10":
                    listFilesInADir();
                    getActions();
                    break;
                case "11":
                    System.out.println("Exiting...");
                    break;
            }
        }
    }

    public static void showMyIp() {
        try
        {
            InetAddress ip = InetAddress.getLocalHost();
            System.out.println("IP Address = " +ip.getHostAddress());
        }
        catch(Exception e)
        {
            System.out.println("Exception: " +e);
        }

    }

    public static void shutdownPC(){
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter the Number of Seconds: ");
        int seconds = scan.nextInt();
        Runtime r = Runtime.getRuntime();
        try
        {
            System.out.println("Shutting down the PC after " +seconds+" seconds.");
            r.exec("shutdown -s -t " +seconds);
        }
        catch(IOException e)
        {
            System.out.println("Exception: " +e);
        }
    }

    public static void restartPC(){
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter the Number of Seconds: ");
        int sec = scan.nextInt();
        Runtime r = Runtime.getRuntime();
        try
        {
            System.out.println("The PC will get restarted after " +sec+" seconds.");
            r.exec("shutdown -r -t " +sec);
        }
        catch(IOException e)
        {
            System.out.println("Exception: " +e);
        }
    }

    public static void searchForFile(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Directory path:\n");
        String DIR_PTH = sc.nextLine();
        System.out.println("Searched file:\n");
        String FILE_NAME = sc.nextLine();
        try {
            SearchFile searchFile = new SearchFile();
            searchFile.searchFile(new File(DIR_PTH), FILE_NAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void listFilesInADir() {
        String dir;
        Scanner dirSc = new Scanner(System.in);
        System.out.println("Directory: ");
        dir = dirSc.nextLine();
        Set files = Stream.of(Objects.requireNonNull(new File(dir).listFiles()))
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());
        for(Object f : files){
            System.out.println(f+"\n");
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        showOptions();
        System.out.println("Enter the number of the action.\nIn case you want to select multiple actions,\nwrite the numbers separated by comma:\n");
        getActions();

    }
}
