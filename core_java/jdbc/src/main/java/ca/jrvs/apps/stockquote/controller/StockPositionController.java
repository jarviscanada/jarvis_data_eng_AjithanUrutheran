package ca.jrvs.apps.stockquote.controller;

import ca.jrvs.apps.stockquote.dao.*;
import ca.jrvs.apps.stockquote.service.PositionService;
import ca.jrvs.apps.stockquote.service.QuoteService;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class StockPositionController {
    private static String host;
    private static String db;
    private static String user;
    private static String password;
    private static String apiKey;
    public static void main(String[] args) throws SQLException {

        Map<String, String> properties = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/resources/properties.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(":");
                properties.put(tokens[0], tokens[1]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Class.forName(properties.get("db-class"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        host = properties.get("server");
        db = properties.get("database");
        user = properties.get("username");
        password = properties.get("password");
        apiKey = properties.get("api-key");

        DatabaseConnectionManager dcm = new DatabaseConnectionManager(host,db,user,password);
        QuoteHttpHelper qhelper = new QuoteHttpHelper(apiKey);

        QuoteService qService;
        PositionService pService;

        Connection connection;
        QuoteDao quotedao;
        PositionDao positiondao;

        Scanner myObj = new Scanner(System.in);
        boolean loop = true;
        while(loop){
            System.out.println("1: Buy Position");
            System.out.println("2: Sell Position");
            System.out.println(("3: Exit"));

            String choice = myObj.nextLine();

            switch(choice){
                case "1":
                    try{
                        String ticker;
                        int numberShares;
                        double price;

                        connection = dcm.getConnection();
                        quotedao = new QuoteDao(connection);
                        positiondao = new PositionDao(connection);

                        System.out.println("Enter position ticker to create:");
                        ticker = myObj.nextLine();

                        System.out.println("How many would you like to buy?");
                        numberShares = Integer.parseInt(myObj.nextLine());


                        //Obtaining price

                        qService = new QuoteService(quotedao,qhelper);
                        pService = new PositionService(positiondao);

                        Optional<Quote> newQuote = qService.fetchQuoteDataFromAPI(ticker);
                        price = newQuote.get().getPrice();
                        pService.buy(ticker,numberShares,price);
                    }
                    catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "2":
                        String ticker;

                        System.out.println("Enter ticket of stock you wish to sell: ");
                        ticker = myObj.nextLine();
                        connection = dcm.getConnection();

                        positiondao = new PositionDao(connection);
                        pService = new PositionService(positiondao);
                        pService.sell(ticker);
                    break;
                default:
                    System.out.println("ERROR:");
                    System.out.println("Choose one of the following previous options. \n");
                    break;
            }

        }

    }
}

