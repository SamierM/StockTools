package StockFileParsers;

import Stocks.ArkStock;
import Stocks.Portfolio;
import Stocks.Stock;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ArkCopiedTextParser {
    public static final String PATH = "C:\\Users\\Admin\\Desktop\\PersonalProjects\\backend\\StockTools\\thetaScreener\\res\\ArkResources";
    public static final String PATH_RAW_TEXT = "C:\\Users\\Admin\\Desktop\\PersonalProjects\\backend\\StockTools\\thetaScreener\\res\\ArkResources\\RawText\\";
    public static final String CUSIP_PATTERN = "\\s[\\w\\d]{9}\\s";

    private FileReader arkRawTextReader;
    private Scanner scanner;
    private Portfolio parsedPortfolio;

    public ArkCopiedTextParser() {
    }

    public void cleanRawCopiedText(String fileName) throws IOException {
        String filePath = PATH_RAW_TEXT + fileName;
        try {
            arkRawTextReader = new FileReader(filePath);
            scanner = new Scanner(arkRawTextReader);
            parsedPortfolio = new Portfolio();

            parseFileToPortfolio();
        } catch (FileNotFoundException e){
            System.out.println("Your file was not found!!! Please check that the following has the correct path: ");
            System.out.println(PATH_RAW_TEXT + " file: " + fileName);
            throw new FileNotFoundException(e.getLocalizedMessage());
        }

    }

    private void parseFileToPortfolio(){
        String currLineText;
        Scanner lineParser;
        scanner.nextLine();
            while(scanner.hasNextLine()){
                //strip row number i.e 1,2,3,4
                int rowNumber = scanner.nextInt();
                currLineText = scanner.nextLine();
                currLineText = currLineText.replaceAll(",","");
                currLineText = currLineText.replaceAll(CUSIP_PATTERN," ");

                lineParser = new Scanner(currLineText);
                StringBuilder companyName = new StringBuilder();
                while(lineParser.hasNext() && !lineParser.hasNextInt()){
                    companyName.append(lineParser.next() + " ");
                }
                int shareNumber = lineParser.nextInt();
                float marketValue = lineParser.nextFloat();
                float weight = lineParser.nextFloat();

                parsedPortfolio.addStock(new ArkStock(companyName.toString(), marketValue, weight));
            }
    }

    public void outputPortfolioToCSV() throws IOException{
        String fileOutputPath = "C:\\Users\\Admin\\Desktop\\PersonalProjects\\backend\\StockTools\\thetaScreener\\res\\ArkResources\\RawText\\ParsedPortfolio";
        String formattedDateToAppend = getFormattedDate();
        String completeFilePath = fileOutputPath + formattedDateToAppend + ".txt";
        try {
            FileWriter fileWriter = new FileWriter(completeFilePath);
            ArrayList<Stock> stockArrayList = parsedPortfolio.getHoldings();
            for(int i = 0; i < stockArrayList.size(); i++) {
                fileWriter.write(stockArrayList.get(i).toString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new IOException(e);
        }

    }

    private String getFormattedDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
