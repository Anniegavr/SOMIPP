package com.labs.somipp;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.io.File.listRoots;

/**
 * <h1>OS simulation</h1>
 * The program implements a console application that
 * mimics and runs the functions of a real OS.
 * <p>
 * <b>Commands:</b> The available functionalities are displayed by the showOptions() function.
 *
 * @author  Anastasia Gavrilita
 * @version 1.0
 * @since   November 12, 2021
 */
public class Main {
    /**
     * Function to display available commands.
     * Takes no parameters.
     */
    public static void showOptions(){
        System.out.println("1.Shut down computer -- shutdown pc\n" +
                "2.Restart computer -- restart pc\n" +
                "3.Show my IP address -- show -h ip\n" +
                "4.Text file manipulations -- file -e\n" +
                "5.What time is it? -- show time\n" +
                "6.What date is today? -- show date\n" +
                "7.Calendar -- calendar\n" +
                "8.Search for file in a directory -- search -f\n" +
                "9.List roots -- ls -r\n" +
                "10.List files in a directory -- ls\n" +
                "11.Exit -- break\n");
    }

    /**
     * Function to get the commands from the user.
     * The commands are read during the runtime, through a scanner.
     * After the current commands are executed, the program re-calls getActions(), in order
     * to receive new commands.
     * The searchForFile() functions and the FileEditor.getActions() @throws IOException,
     * @throws InterruptedException when the specified files don't exist or can't be created.
     */
    public static void getActions() throws IOException, InterruptedException {
        System.out.println("=== main program commands");
        Scanner scan = new Scanner(System.in);
        String allActions = scan.nextLine();
        String[] actions = allActions.split(",");
        for (String act : actions) {
            switch (act) {
                case "shutdown pc":
                    shutdownPC();
                    getActions();
                    break;
                case "restart pc":
                    restartPC();
                    getActions();
                    break;
                case "show -h ip":
                    showMyIp();
                    getActions();
                    break;
                case "file -e":
                    Scanner fileName = new Scanner(System.in);
                    System.out.println();
                    FileEditor fe = new FileEditor(fileName.nextLine());
                    fe.getActions();
                    getActions();
                    break;
                case "show time":
                    System.out.println(java.time.LocalTime.now());
                    getActions();
                    break;
                case "show date":
                    System.out.println(java.time.LocalDate.now());
                    getActions();
                    break;
                case "calendar":
                    CustomCalendar mainCalendar = new CustomCalendar();
                    mainCalendar.getAction();
                    getActions();
                    break;
                case "search -f":
                    searchForFile();
                    getActions();
                    break;
                case "ls -r":
                    System.out.println(Arrays.toString(listRoots()));
                    getActions();
                    break;
                case "ls":
                    listFilesInADir();
                    getActions();
                    break;
                case "break":
                    System.out.println("Exiting...");
                    break;
            }
        }
    }

    /**
     * Function to display the user's IP address.
     * Takes no parameter.
     * Outputs the user's IP address in the console.
     * If the IP info can't be retrieved, the method
     * @throws UnknownHostException
     */
    public static void showMyIp() throws UnknownHostException {
        InetAddress ip = InetAddress.getLocalHost();
        System.out.println("IP Address = " +ip.getHostAddress());
    }

    /**
     * Function to shut down the PC. Takes information about the desired wait time
     * before shutting down during the runtime.
     */
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
            System.out.println("Couldn't shut down");
        }
    }

    /**
     * Function to restart the PC. Takes information about the desired wait time
     * before restarting during the runtime.
     */
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

    /**
     * Function to search for a file in a directory specified by the user during runtime.
     */
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
            System.out.println("Searching");;
        }
    }


    /**
     * Function to list files in a directory specified by the user during runtime.
     */
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

    /**
     * This is the main class which starts the command-line OS simulator.
     * @param args Unused.
     * The searchForFile() functions and the FileEditor.getActions()
     * @throws IOException and
     * @throws InterruptedException when the specified files don't exist or can't be created.*/
    public static void main(String[] args) throws IOException, InterruptedException {
        showOptions();
        System.out.println("Enter the command.\nAfter the command is executed, the program will return to main stage:\n");
        getActions();

    }
}
