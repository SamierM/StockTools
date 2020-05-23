import StockCSVParsers.ZackCSVParser;
import Stocks.ZacksStock;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) throws IOException {

        final char END_OF_LINE = '\n';
        final String HEADERS = "Company Name,Ticker,Zacks Rank,VGM Score";

        String fileName = "zacks_custom_screen_2020-05-23.csv";
        System.out.println("Program starting with file name: " + fileName);

        ZackCSVParser myParser = new ZackCSVParser(fileName);
        ArrayList<ZacksStock> myFilteredPortfolio = myParser.filterForClassicZackPortfolio();

        Scanner inputScanner = new Scanner(System.in);

        System.out.println("Please type the output file name: ");
        String outputFileName = inputScanner.next() + ".csv";

        try {
            PrintWriter writer = new PrintWriter(new File(outputFileName));

            writer.write(HEADERS);
            System.out.println("Printing filtered portfolio and outputting to " + outputFileName);

            for(ZacksStock currStock: myFilteredPortfolio) {
                StringBuilder sb = new StringBuilder();
                writer.write(currStock.toString() + END_OF_LINE);
            }

            writer.close();
            System.out.println("Done");

        } catch (IOException e){
            throw new IOException(e.getMessage());
        }


    }
}
