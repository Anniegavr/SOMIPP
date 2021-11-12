package com.labs.somipp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
public class FileEditor {
    public String fname;

    public void getActions() throws IOException {
        displayOptions();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the number of the action.\nIn case you want to select multiple actions,\nwrite the numbers separated by comma:\n");
        String allActions = scan.nextLine();
        String[] actions = allActions.split(",");
        for (String act : actions) {
            switch (act) {
                case "1":
                    readAndDisplayFile();
                    break;
                case "2":
                    System.out.println("Day of the week: ");
                    copyFile();
                    break;
                case "3":
                    System.out.println("This file's permissions: ");
                    showFilePermissions();
                    break;
                case "4":
                    replaceInFile();
                    break;
                case "5":
                    renameFile();
                    break;
            }
        }
    }

    public void displayOptions(){
        System.out.println("1.Read and display text in file\n" +
                "2.Copy this file to destination __\n" +
                "3.Show this file's permissions\n" +
                "4.Replace words in file\n");
    }

    public void readAndDisplayFile(){
        Scanner scan = new Scanner(System.in);
        // enter filename along with its extension
        System.out.print("Enter the Name of File: ");
        this.fname = scan.nextLine();

        String line = null;
        try
        {
            FileReader fileReader = new FileReader(fname);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null)
            {
                System.out.println(line);
            }

            bufferedReader.close();
        }
        catch(IOException ex)
        {
            System.out.println("\nCouldn't read file.");
            System.out.println("" +ex);
        }
    }

    public void copyFile() throws FileNotFoundException,IOException{
        String[] args = new String[2];
        args[0] = fname;
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the destination, along with the file name + its extension:");
        String destination = scan.nextLine();
        args[1] = destination;
        FileInputStream fis = new FileInputStream(args[0]);
        FileOutputStream fos = new FileOutputStream(args[1]);
        int b;
        while  ((b=fis.read()) != -1)
            fos.write(b);
        fis.close();
        fos.close();
    }

    public void showFilePermissions(){
        File file = new File(fname);

        // check if the file exists
        boolean exists = file.exists();
        if(exists == true)
        {
            // printing the permissions associated with the file
            System.out.println("Executable: " + file.canExecute());
            System.out.println("Readable: " + file.canRead());
            System.out.println("Writable: "+ file.canWrite());
        }
        else
        {
            System.out.println("File not found.");
        }
    }

    public void replaceInFile(){
        Scanner s = new Scanner(System.in);
        System.out.println("How many words would you like to replace?");
        int numOfWords = s.nextInt();
        Map<String, String> wordsAndRepl = new HashMap<>();
        for(int i=0; i<=numOfWords; i++){
            System.out.println("Original word: ");
            String wordToBeReplaced = s.nextLine();
            System.out.println("Replace with: ");
            String replacements = s.nextLine();
            wordsAndRepl.put(wordToBeReplaced, replacements);
        }

        List<String> lines = new ArrayList<>();
        String line = null;
        try {
            File f1 = new File(fname);
            FileReader fr = new FileReader(f1);
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                for(Map.Entry<String, String> entry : wordsAndRepl.entrySet()){
                    if (line.contains(entry.getKey()))
                        line = line.replace(entry.getKey(), entry.getValue());
                    lines.add(line);
                }
            }
            fr.close();
            br.close();

            FileWriter fw = new FileWriter(f1);
            BufferedWriter out = new BufferedWriter(fw);
            for(String sr : lines)
                out.write(sr);
            out.flush();
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public  void renameFile(){
        File current = new File(fname);
        Scanner name = new Scanner(System.in);
        System.out.println("Rename file to\n");
        String newName= name.nextLine();
        File renamed = new File(newName);
        if(current.renameTo(renamed)) {
            System.out.println("Renamed file to "+renamed.getName());
        } else {
            System.out.println("Error");
        }
    }


}
