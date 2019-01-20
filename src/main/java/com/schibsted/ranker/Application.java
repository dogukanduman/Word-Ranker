package com.schibsted.ranker;


import com.schibsted.ranker.domain.FileContent;
import com.schibsted.ranker.service.DirectoryScanner;
import com.schibsted.ranker.service.FileReader;
import com.schibsted.ranker.service.SearchRanker;
import com.schibsted.ranker.service.imp.DirectoryScannerImp;
import com.schibsted.ranker.service.imp.FileReaderImp;
import com.schibsted.ranker.service.imp.SearchRankerImp;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {

    private static final String resultTemplate = "File Name:%s ,Search Result:%s";
    private static final String numberOfFilesTemplate = "%s files read in directory %s";
    /**
     * DirectoryScanner scans given folder
     */
    private static DirectoryScanner directoryScanner = new DirectoryScannerImp();
    /**
     * FileReader reads the file and create string version of the content
     */
    private static FileReader fileReader = new FileReaderImp();
    /**
     * Calculate search rank of the given words in a file
     */
    private static SearchRanker searchRanker = new SearchRankerImp();
    /**
     * Keeps file names and their content
     */
    private static List<FileContent> fileContentList = new ArrayList<>();

    public static void main(String args[]) {

        System.out.println("#####################################");
        System.out.println("Welcome to Search Ranker Application");
        System.out.println("Search Ranker calculates the containment\n" +
                "percentage of given words in a file\n" +
                "where is given directory");
        System.out.println("#####################################");

        if (args.length == 0) {
            System.out.println("Missing directory parameter!!");
            terminate();
        }

        System.out.println("Given directory is :" + args[0]);

        Path inputPath = Paths.get(args[0]);

        init(inputPath);


        Scanner keyboard = new Scanner(System.in);
        while (true) {
            System.out.println("search>");
            String line = keyboard.nextLine();
            if(!line.trim().isEmpty()){
                if (line.equals("exit")) {
                    terminate();
                }
                calculatePercentages(line);
            }else{
                System.out.println("invalid input");
            }
        }
    }


    private static void init(Path searchPath) {

        System.out.println("Scanning and creating indexes..");

        /** Scan directories*/
        List<Path> pathList = new ArrayList<>();
        try {
            pathList = directoryScanner.scan(searchPath, ".txt");
        } catch (IOException ioe) {
            System.out.println("Problem with the given path ex:" + ioe);
            terminate();
        }

        /**Is there any text file */
        if (pathList.isEmpty()) {
            System.out.println("There is no file under given directory");
            terminate();
        }

        /**Read files and retrieve theirs contents */
        System.out.println("Retrieve contents and create indexes.");
        pathList.forEach(path -> fileReader.read(path).ifPresent(fileContent -> {
            String fileName = path.getFileName().toString();
            fileContentList.add(new FileContent(fileName, fileContent));
        }));

        /**Is there any content given directory */
        if (fileContentList.isEmpty()) {
            System.out.println("There is no content under given directory");
            terminate();
        }

        System.out.println(String.format(numberOfFilesTemplate, fileContentList.size(), searchPath.toString()));
    }

    /**
     * Delegates
     */
    private static void calculatePercentages(String input) {
        System.out.println("Calculating percentages..");
        fileContentList.forEach(fileContent -> {
            Long result = searchRanker.calculatePercentage(fileContent, input);
            System.out.println(String.format(resultTemplate, fileContent.getName(), result + "%"));
        });
    }

    private static void terminate() {
        System.out.println("Application will be terminated");
        System.exit(0);
    }
}
