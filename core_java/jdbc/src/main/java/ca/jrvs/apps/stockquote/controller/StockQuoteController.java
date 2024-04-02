package ca.jrvs.apps.stockquote.controller;

import ca.jrvs.apps.stockquote.dao.DatabaseConnectionManager;
import ca.jrvs.apps.stockquote.dao.Quote;
import ca.jrvs.apps.stockquote.dao.QuoteDao;
import ca.jrvs.apps.stockquote.dao.QuoteHttpHelper;
import ca.jrvs.apps.stockquote.service.QuoteService;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StockQuoteController {
    private static String host;
    private static String db;
    private static String user;
    private static String password;
    private static String apiKey;
    private static final Logger logger = LoggerFactory.getLogger(StockQuoteController.class);
    public static void main(String[] args) {
        BasicConfigurator.configure();
        Map<String, String> properties = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/resources/properties.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(":");
                properties.put(tokens[0], tokens[1]);
            }
        } catch (FileNotFoundException e) {
            logger.error("ERROR: Cannot find properties file. Check if files exist.",e);
        } catch (IOException e) {
            logger.error("ERROR: Issue with properties resource. Please check contents of file.",e);
        }

        try {
            Class.forName(properties.get("db-class"));
        } catch (ClassNotFoundException e) {
            logger.error("ERROR: Check database connection and contents.", e);
        }


        host = properties.get("server");
        db = properties.get("database");
        user = properties.get("username");
        password = properties.get("password");
        apiKey = properties.get("api-key");

        DatabaseConnectionManager dcm = new DatabaseConnectionManager(host,db,user,password);


        Scanner myObj = new Scanner(System.in);
        boolean loop = true;
        while(loop){
            System.out.println("1: Create/Update Ticker");
            System.out.println("2: View Ticker");
            System.out.println(("3: Delete Ticker"));
            System.out.println(("4: View All"));
            System.out.println(("5: Delete All"));
            System.out.println(("6: Exit"));

            String choice = myObj.nextLine();

            switch(choice){
                case "1":
                    try{
                        Connection connection = dcm.getConnection();
                        QuoteDao quotedao = new QuoteDao(connection);

                        System.out.println("Enter ticker to create/update:");
                        String ticker = myObj.nextLine();

                        QuoteHttpHelper qhelper = new QuoteHttpHelper(apiKey);
                        QuoteService qService = new QuoteService(quotedao,qhelper);
                        Optional<Quote> newQuote = qService.fetchQuoteDataFromAPI(ticker);

                        quotedao.save(newQuote.get());

                    }
                    catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "2":
                    try{
                        Connection connection = dcm.getConnection();
                        QuoteDao quotedao = new QuoteDao(connection);

                        System.out.println("Enter ticker to view:");
                        String ticker = myObj.nextLine();
                        Optional<Quote> ticketQuote = quotedao.findById(ticker);
                        ticketQuote.ifPresent(quote -> System.out.println(quote.toString()));


                    }
                    catch (SQLException e){
                        throw new RuntimeException(e);
                    }
                    break;
                case "3":
                    try{
                        Connection connection = dcm.getConnection();
                        QuoteDao quotedao = new QuoteDao(connection);

                        System.out.println("Enter ticker to delete:");
                        String ticker = myObj.nextLine();

                        try{
                            quotedao.deleteById(ticker);
                            System.out.println("Successfully Deleted");
                        }
                        catch(IllegalArgumentException e){
                            e.printStackTrace();
                        }
                    }
                    catch (SQLException e){
                        throw new RuntimeException(e);
                    }
                    break;
                case "4":
                    try{
                        Connection connection = dcm.getConnection();
                        QuoteDao quotedao = new QuoteDao(connection);
                        Iterable<Quote> list = quotedao.findAll();

                        for(Quote x : list){
                            System.out.println(x.toString() + "\n");
                        }

                        try{
                            quotedao.findAll();
                        }
                        catch(IllegalArgumentException e){
                            e.printStackTrace();
                        }
                    }
                    catch (SQLException e){
                        logger.error("ERROR: Issue with database queries. ",e);
                    }
                    break;
                case "5":
                    try{
                        Connection connection = dcm.getConnection();
                        QuoteDao quotedao = new QuoteDao(connection);

                        try{
                            quotedao.deleteAll();
                            logger.info("Successfully deleted all values.");
                        }
                        catch(IllegalArgumentException e){
                            logger.error("ERROR: Issue with deleting all values.",e);
                        }
                    }
                    catch (SQLException e){
                        logger.error("ERROR: Issue with SQL. Please check database connection / queries / tables.",e);
                    }
                    break;

                case "6":
                    loop = false;
                    break;
                default:
                    System.out.println("ERROR:");
                    System.out.println("Choose one of the following previous options. \n");
                    break;
            }

        }

    }
}

