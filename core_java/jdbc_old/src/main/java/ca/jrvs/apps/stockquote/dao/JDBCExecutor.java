package ca.jrvs.apps.stockquote.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class JDBCExecutor {

    public static void main(String[] args) {
        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost","stock_quote","postgres","password");
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

                        QuoteHttpHelper qhelper = new QuoteHttpHelper("78aaf7f7c4mshc87167b1e7f9539p14d55ejsn59e734b9a63c");
                        Quote newQuote = qhelper.fetchQuoteInfo(ticker);

                        quotedao.save(newQuote);

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
                        throw new RuntimeException(e);
                    }
                    break;
                case "5":
                    try{
                        Connection connection = dcm.getConnection();
                        QuoteDao quotedao = new QuoteDao(connection);

                        try{
                            quotedao.deleteAll();
                            System.out.println("Successfully Deleted All Stocks");
                        }
                        catch(IllegalArgumentException e){
                            e.printStackTrace();
                        }
                    }
                    catch (SQLException e){
                        throw new RuntimeException(e);
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
