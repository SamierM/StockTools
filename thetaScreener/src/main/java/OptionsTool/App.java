import StockFileParsers.ArkCopiedTextParser;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        System.out.println( "Hello World!" );
        ArkCopiedTextParser arkCopiedTextParser = new ArkCopiedTextParser();
        try {
            arkCopiedTextParser.cleanRawCopiedText("arkTest");
            arkCopiedTextParser.outputPortfolioToCSV();
        } catch (IOException e){
            throw new IOException(e);
        }
    }
}
