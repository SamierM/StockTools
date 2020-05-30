package StockFileParsers;

import Stocks.ZacksStock;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ZackCSVParser {
    private static final String COMMA_DELIMITER = ",";
    public static final String VALUE_DELIMITER = "\",\"";
    public static final String[] ZACK_HEADERS = new String[]{"Company Name","Ticker","Zacks Industry Rank","Zacks Rank","VGM Score"};
    private static final String RESOURCE_PATH = "C:\\Users\\Admin\\Desktop\\PersonalProjects\\backend\\StockTools\\res\\csv\\ZackStockScreenerCSVs\\";


    private String fileName;
    public boolean isValidFile = false;


    private ArrayList<ZacksStock> parsedStocks;
    private ArrayList<ZacksStock> fullStockList = null;

    private FileReader zackCSVFileReader;
    private BufferedReader lineReader;

    public ZackCSVParser(String fileName) throws FileNotFoundException, IOException{
        this.fileName = fileName;
        try {
            createFileReaders();
            validateZackCSV();
            isValidFile = true;
            fullStockList = parseFullStockListToPortfolio();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Failed to reader file:" + RESOURCE_PATH + fileName);
        } catch (IOException e) {
            throw new IOException("IOException when constructing new ZackCSVParser: " + fileName);
        }

    }
    private ArrayList<ZacksStock> parseFullStockListToPortfolio() throws IOException {
        ArrayList<ZacksStock> parsedPortfolio = new ArrayList<>();
        try {
            String line = lineReader.readLine();
            while((line = lineReader.readLine()) != null){
                String[] newStockValues =  line.split(VALUE_DELIMITER);
                String companyName = newStockValues[0].substring(1);
                System.out.println(companyName);
                String tickerSymbol = newStockValues[1];
                System.out.println(line);
                System.out.printf("%s, %s, %s, %s, %s \n", newStockValues[0],newStockValues[1],newStockValues[2],newStockValues[3],newStockValues[4]);
                int industryRank = Integer.parseInt(newStockValues[2]);
                int zacksRank = Integer.parseInt(newStockValues[3]);
                char vgmScore = newStockValues[4].charAt(0);
                parsedPortfolio.add(new ZacksStock(companyName, tickerSymbol, industryRank, zacksRank, vgmScore));
            }
            return parsedPortfolio;
        } catch (IOException e) {
            throw new IOException("Failed to parse full stock list of: " + fileName + "\n" + e);
        }
    }

    //Filters to the Standard Zacks buy ratings
    //Stocks with a rating of 1, top 50 in their industry, and A or B VGM rating
    public ArrayList<ZacksStock> filterForClassicZackPortfolio() {
        ArrayList<ZacksStock> parsedPortfolio = new ArrayList<>();
        for(ZacksStock currStock : fullStockList) {
            if(currStock.isZacksApproved()){
                parsedPortfolio.add(currStock);
            }
        }
        return parsedPortfolio;
    }

    private boolean validateZackCSV() throws IOException  {
        try {
            String line = lineReader.readLine();
            String[] headers = line.split(COMMA_DELIMITER);
            if(Arrays.equals(headers,ZACK_HEADERS)){
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void createFileReaders() throws FileNotFoundException {
        try {
            zackCSVFileReader = new FileReader(RESOURCE_PATH + fileName);
            lineReader = new BufferedReader(zackCSVFileReader);
        } catch (FileNotFoundException e){
            System.out.println("Your file was not found!!! Please check that the following has the correct path: ");
            System.out.println(RESOURCE_PATH + " file: " + fileName);
            throw new FileNotFoundException(e.getLocalizedMessage());
        }
    }
}
