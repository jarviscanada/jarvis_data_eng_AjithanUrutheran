package ca.jrvs.apps.trading.controller;

import ca.jrvs.apps.trading.entity.IexQuote;
import ca.jrvs.apps.trading.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quote")
public class QuoteController {

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
}