package ca.jrvs.apps.trading.controller;

import ca.jrvs.apps.trading.Application;
import ca.jrvs.apps.trading.entity.IexQuote;
import ca.jrvs.apps.trading.entity.Quote;
import ca.jrvs.apps.trading.service.QuoteService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quote")
public class QuoteController {
    private Logger logger = LoggerFactory.getLogger(QuoteController.class);

    private QuoteService quoteService;

    @Autowired
    public QuoteController(QuoteService quoteService){
        this.quoteService = quoteService;
    }
    @GetMapping(path="/iex/ticker/{ticker}")
    @ResponseStatus(HttpStatus.OK)
    public IexQuote getQuote(@PathVariable String ticker) {
        try{
            return quoteService.findIexQuoteByTicker(ticker);
        }
        catch(Exception e){
            throw ResponseExceptionUtil.getResponseStatusException(e);
        }
    }

    @PutMapping(path="/iexMarketData")
    @ResponseStatus(HttpStatus.OK)
    public void updateMarketData(){
        try{
            logger.info("Updating market data");
            quoteService.updateMarketData();
        }
        catch(Exception e){
            throw ResponseExceptionUtil.getResponseStatusException(e);
        }
    }

    @PostMapping(path="/tickerId/{tickerId}")
    @ApiResponses(value= {@ApiResponse(code=404,message="Ticker is not found in IEX System")})
    @ResponseStatus(HttpStatus.OK)
    public Quote createQuote(@PathVariable String tickerId){
        try{
            logger.info("Adding Quote to Database.");
            Quote quote = quoteService.saveQuote(tickerId);
            return quoteService.saveQuote(quote);
        }
        catch(Exception e){
            throw ResponseExceptionUtil.getResponseStatusException(e);
        }
    }

    @GetMapping(path = "/dailyList")
    @ResponseStatus(HttpStatus.OK)
    public List<Quote> getDailyList(){
        try{
            return quoteService.findAllQuotes();
        }
        catch(Exception e){
            throw ResponseExceptionUtil.getResponseStatusException(e);
        }
    }

    @PutMapping(path = "/")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Quote putQuote(@RequestBody Quote quote){
        try{
            return quoteService.saveQuote(quote);
        }
        catch(Exception e){
            throw ResponseExceptionUtil.getResponseStatusException(e);
        }
    }


}