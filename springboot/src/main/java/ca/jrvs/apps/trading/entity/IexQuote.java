//-----------------------------------ca.jrvs.apps.trading.entity.IexQuote.java-----------------------------------
package ca.jrvs.apps.trading.entity;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "symbol",
        "companyName",
        "primaryExchange",
        "calculationPrice",
        "open",
        "openTime",
        "openSource",
        "close",
        "closeTime",
        "closeSource",
        "high",
        "highTime",
        "highSource",
        "low",
        "lowTime",
        "lowSource",
        "latestPrice",
        "latestSource",
        "latestTime",
        "latestUpdate",
        "latestVolume",
        "iexRealtimePrice",
        "iexRealtimeSize",
        "iexLastUpdated",
        "delayedPrice",
        "delayedPriceTime",
        "oddLotDelayedPrice",
        "oddLotDelayedPriceTime",
        "extendedPrice",
        "extendedChange",
        "extendedChangePercent",
        "extendedPriceTime",
        "previousClose",
        "previousVolume",
        "change",
        "changePercent",
        "volume",
        "iexMarketPercent",
        "iexVolume",
        "avgTotalVolume",
        "iexBidPrice",
        "iexBidSize",
        "iexAskPrice",
        "iexAskSize",
        "iexOpen",
        "iexOpenTime",
        "iexClose",
        "iexCloseTime",
        "marketCap",
        "peRatio",
        "week52High",
        "week52Low",
        "ytdChange",
        "lastTradeTime",
        "currency",
        "isUSMarketOpen"
})
@Generated("jsonschema2pojo")
public class IexQuote {

    @JsonProperty("symbol")
    private String symbol;
    @JsonProperty("companyName")
    private String companyName;
    @JsonProperty("primaryExchange")
    private String primaryExchange;
    @JsonProperty("calculationPrice")
    private String calculationPrice;
    @JsonProperty("open")
    private Double open;
    @JsonProperty("openTime")
    private Long openTime;
    @JsonProperty("openSource")
    private String openSource;
    @JsonProperty("close")
    private Double close;
    @JsonProperty("closeTime")
    private Long closeTime;
    @JsonProperty("closeSource")
    private String closeSource;
    @JsonProperty("high")
    private Double high;
    @JsonProperty("highTime")
    private Long highTime;
    @JsonProperty("highSource")
    private String highSource;
    @JsonProperty("low")
    private Double low;
    @JsonProperty("lowTime")
    private Long lowTime;
    @JsonProperty("lowSource")
    private String lowSource;
    @JsonProperty("latestPrice")
    private Double latestPrice;
    @JsonProperty("latestSource")
    private String latestSource;
    @JsonProperty("latestTime")
    private String latestTime;
    @JsonProperty("latestUpdate")
    private Long latestUpdate;
    @JsonProperty("latestVolume")
    private Integer latestVolume;
    @JsonProperty("iexRealtimePrice")
    private Double iexRealtimePrice;
    @JsonProperty("iexRealtimeSize")
    private Integer iexRealtimeSize;
    @JsonProperty("iexLastUpdated")
    private Long iexLastUpdated;
    @JsonProperty("delayedPrice")
    private Double delayedPrice;
    @JsonProperty("delayedPriceTime")
    private Long delayedPriceTime;
    @JsonProperty("oddLotDelayedPrice")
    private Double oddLotDelayedPrice;
    @JsonProperty("oddLotDelayedPriceTime")
    private Long oddLotDelayedPriceTime;
    @JsonProperty("extendedPrice")
    private Double extendedPrice;
    @JsonProperty("extendedChange")
    private Double extendedChange;
    @JsonProperty("extendedChangePercent")
    private Double extendedChangePercent;
    @JsonProperty("extendedPriceTime")
    private Long extendedPriceTime;
    @JsonProperty("previousClose")
    private Double previousClose;
    @JsonProperty("previousVolume")
    private Integer previousVolume;
    @JsonProperty("change")
    private Double change;
    @JsonProperty("changePercent")
    private Double changePercent;
    @JsonProperty("volume")
    private Integer volume;
    @JsonProperty("iexMarketPercent")
    private Double iexMarketPercent;
    @JsonProperty("iexVolume")
    private Integer iexVolume;
    @JsonProperty("avgTotalVolume")
    private Integer avgTotalVolume;
    @JsonProperty("iexBidPrice")
    private Integer iexBidPrice;
    @JsonProperty("iexBidSize")
    private Integer iexBidSize;
    @JsonProperty("iexAskPrice")
    private Integer iexAskPrice;
    @JsonProperty("iexAskSize")
    private Integer iexAskSize;
    @JsonProperty("iexOpen")
    private Double iexOpen;
    @JsonProperty("iexOpenTime")
    private Long iexOpenTime;
    @JsonProperty("iexClose")
    private Double iexClose;
    @JsonProperty("iexCloseTime")
    private Long iexCloseTime;
    @JsonProperty("marketCap")
    private Long marketCap;
    @JsonProperty("peRatio")
    private Double peRatio;
    @JsonProperty("week52High")
    private Double week52High;
    @JsonProperty("week52Low")
    private Double week52Low;
    @JsonProperty("ytdChange")
    private Double ytdChange;
    @JsonProperty("lastTradeTime")
    private Long lastTradeTime;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("isUSMarketOpen")
    private Boolean isUSMarketOpen;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("symbol")
    public String getSymbol() {
        return symbol;
    }

    @JsonProperty("symbol")
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @JsonProperty("companyName")
    public String getCompanyName() {
        return companyName;
    }

    @JsonProperty("companyName")
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @JsonProperty("primaryExchange")
    public String getPrimaryExchange() {
        return primaryExchange;
    }

    @JsonProperty("primaryExchange")
    public void setPrimaryExchange(String primaryExchange) {
        this.primaryExchange = primaryExchange;
    }

    @JsonProperty("calculationPrice")
    public String getCalculationPrice() {
        return calculationPrice;
    }

    @JsonProperty("calculationPrice")
    public void setCalculationPrice(String calculationPrice) {
        this.calculationPrice = calculationPrice;
    }

    @JsonProperty("open")
    public Double getOpen() {
        return open;
    }

    @JsonProperty("open")
    public void setOpen(Double open) {
        this.open = open;
    }

    @JsonProperty("openTime")
    public Long getOpenTime() {
        return openTime;
    }

    @JsonProperty("openTime")
    public void setOpenTime(Long openTime) {
        this.openTime = openTime;
    }

    @JsonProperty("openSource")
    public String getOpenSource() {
        return openSource;
    }

    @JsonProperty("openSource")
    public void setOpenSource(String openSource) {
        this.openSource = openSource;
    }

    @JsonProperty("close")
    public Double getClose() {
        return close;
    }

    @JsonProperty("close")
    public void setClose(Double close) {
        this.close = close;
    }

    @JsonProperty("closeTime")
    public Long getCloseTime() {
        return closeTime;
    }

    @JsonProperty("closeTime")
    public void setCloseTime(Long closeTime) {
        this.closeTime = closeTime;
    }

    @JsonProperty("closeSource")
    public String getCloseSource() {
        return closeSource;
    }

    @JsonProperty("closeSource")
    public void setCloseSource(String closeSource) {
        this.closeSource = closeSource;
    }

    @JsonProperty("high")
    public Double getHigh() {
        return high;
    }

    @JsonProperty("high")
    public void setHigh(Double high) {
        this.high = high;
    }

    @JsonProperty("highTime")
    public Long getHighTime() {
        return highTime;
    }

    @JsonProperty("highTime")
    public void setHighTime(Long highTime) {
        this.highTime = highTime;
    }

    @JsonProperty("highSource")
    public String getHighSource() {
        return highSource;
    }

    @JsonProperty("highSource")
    public void setHighSource(String highSource) {
        this.highSource = highSource;
    }

    @JsonProperty("low")
    public Double getLow() {
        return low;
    }

    @JsonProperty("low")
    public void setLow(Double low) {
        this.low = low;
    }

    @JsonProperty("lowTime")
    public Long getLowTime() {
        return lowTime;
    }

    @JsonProperty("lowTime")
    public void setLowTime(Long lowTime) {
        this.lowTime = lowTime;
    }

    @JsonProperty("lowSource")
    public String getLowSource() {
        return lowSource;
    }

    @JsonProperty("lowSource")
    public void setLowSource(String lowSource) {
        this.lowSource = lowSource;
    }

    @JsonProperty("latestPrice")
    public Double getLatestPrice() {
        return latestPrice;
    }

    @JsonProperty("latestPrice")
    public void setLatestPrice(Double latestPrice) {
        this.latestPrice = latestPrice;
    }

    @JsonProperty("latestSource")
    public String getLatestSource() {
        return latestSource;
    }

    @JsonProperty("latestSource")
    public void setLatestSource(String latestSource) {
        this.latestSource = latestSource;
    }

    @JsonProperty("latestTime")
    public String getLatestTime() {
        return latestTime;
    }

    @JsonProperty("latestTime")
    public void setLatestTime(String latestTime) {
        this.latestTime = latestTime;
    }

    @JsonProperty("latestUpdate")
    public Long getLatestUpdate() {
        return latestUpdate;
    }

    @JsonProperty("latestUpdate")
    public void setLatestUpdate(Long latestUpdate) {
        this.latestUpdate = latestUpdate;
    }

    @JsonProperty("latestVolume")
    public Integer getLatestVolume() {
        return latestVolume;
    }

    @JsonProperty("latestVolume")
    public void setLatestVolume(Integer latestVolume) {
        this.latestVolume = latestVolume;
    }

    @JsonProperty("iexRealtimePrice")
    public Double getIexRealtimePrice() {
        return iexRealtimePrice;
    }

    @JsonProperty("iexRealtimePrice")
    public void setIexRealtimePrice(Double iexRealtimePrice) {
        this.iexRealtimePrice = iexRealtimePrice;
    }

    @JsonProperty("iexRealtimeSize")
    public Integer getIexRealtimeSize() {
        return iexRealtimeSize;
    }

    @JsonProperty("iexRealtimeSize")
    public void setIexRealtimeSize(Integer iexRealtimeSize) {
        this.iexRealtimeSize = iexRealtimeSize;
    }

    @JsonProperty("iexLastUpdated")
    public Long getIexLastUpdated() {
        return iexLastUpdated;
    }

    @JsonProperty("iexLastUpdated")
    public void setIexLastUpdated(Long iexLastUpdated) {
        this.iexLastUpdated = iexLastUpdated;
    }

    @JsonProperty("delayedPrice")
    public Double getDelayedPrice() {
        return delayedPrice;
    }

    @JsonProperty("delayedPrice")
    public void setDelayedPrice(Double delayedPrice) {
        this.delayedPrice = delayedPrice;
    }

    @JsonProperty("delayedPriceTime")
    public Long getDelayedPriceTime() {
        return delayedPriceTime;
    }

    @JsonProperty("delayedPriceTime")
    public void setDelayedPriceTime(Long delayedPriceTime) {
        this.delayedPriceTime = delayedPriceTime;
    }

    @JsonProperty("oddLotDelayedPrice")
    public Double getOddLotDelayedPrice() {
        return oddLotDelayedPrice;
    }

    @JsonProperty("oddLotDelayedPrice")
    public void setOddLotDelayedPrice(Double oddLotDelayedPrice) {
        this.oddLotDelayedPrice = oddLotDelayedPrice;
    }

    @JsonProperty("oddLotDelayedPriceTime")
    public Long getOddLotDelayedPriceTime() {
        return oddLotDelayedPriceTime;
    }

    @JsonProperty("oddLotDelayedPriceTime")
    public void setOddLotDelayedPriceTime(Long oddLotDelayedPriceTime) {
        this.oddLotDelayedPriceTime = oddLotDelayedPriceTime;
    }

    @JsonProperty("extendedPrice")
    public Double getExtendedPrice() {
        return extendedPrice;
    }

    @JsonProperty("extendedPrice")
    public void setExtendedPrice(Double extendedPrice) {
        this.extendedPrice = extendedPrice;
    }

    @JsonProperty("extendedChange")
    public Double getExtendedChange() {
        return extendedChange;
    }

    @JsonProperty("extendedChange")
    public void setExtendedChange(Double extendedChange) {
        this.extendedChange = extendedChange;
    }

    @JsonProperty("extendedChangePercent")
    public Double getExtendedChangePercent() {
        return extendedChangePercent;
    }

    @JsonProperty("extendedChangePercent")
    public void setExtendedChangePercent(Double extendedChangePercent) {
        this.extendedChangePercent = extendedChangePercent;
    }

    @JsonProperty("extendedPriceTime")
    public Long getExtendedPriceTime() {
        return extendedPriceTime;
    }

    @JsonProperty("extendedPriceTime")
    public void setExtendedPriceTime(Long extendedPriceTime) {
        this.extendedPriceTime = extendedPriceTime;
    }

    @JsonProperty("previousClose")
    public Double getPreviousClose() {
        return previousClose;
    }

    @JsonProperty("previousClose")
    public void setPreviousClose(Double previousClose) {
        this.previousClose = previousClose;
    }

    @JsonProperty("previousVolume")
    public Integer getPreviousVolume() {
        return previousVolume;
    }

    @JsonProperty("previousVolume")
    public void setPreviousVolume(Integer previousVolume) {
        this.previousVolume = previousVolume;
    }

    @JsonProperty("change")
    public Double getChange() {
        return change;
    }

    @JsonProperty("change")
    public void setChange(Double change) {
        this.change = change;
    }

    @JsonProperty("changePercent")
    public Double getChangePercent() {
        return changePercent;
    }

    @JsonProperty("changePercent")
    public void setChangePercent(Double changePercent) {
        this.changePercent = changePercent;
    }

    @JsonProperty("volume")
    public Integer getVolume() {
        return volume;
    }

    @JsonProperty("volume")
    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    @JsonProperty("iexMarketPercent")
    public Double getIexMarketPercent() {
        return iexMarketPercent;
    }

    @JsonProperty("iexMarketPercent")
    public void setIexMarketPercent(Double iexMarketPercent) {
        this.iexMarketPercent = iexMarketPercent;
    }

    @JsonProperty("iexVolume")
    public Integer getIexVolume() {
        return iexVolume;
    }

    @JsonProperty("iexVolume")
    public void setIexVolume(Integer iexVolume) {
        this.iexVolume = iexVolume;
    }

    @JsonProperty("avgTotalVolume")
    public Integer getAvgTotalVolume() {
        return avgTotalVolume;
    }

    @JsonProperty("avgTotalVolume")
    public void setAvgTotalVolume(Integer avgTotalVolume) {
        this.avgTotalVolume = avgTotalVolume;
    }

    @JsonProperty("iexBidPrice")
    public Integer getIexBidPrice() {
        return iexBidPrice;
    }

    @JsonProperty("iexBidPrice")
    public void setIexBidPrice(Integer iexBidPrice) {
        this.iexBidPrice = iexBidPrice;
    }

    @JsonProperty("iexBidSize")
    public Integer getIexBidSize() {
        return iexBidSize;
    }

    @JsonProperty("iexBidSize")
    public void setIexBidSize(Integer iexBidSize) {
        this.iexBidSize = iexBidSize;
    }

    @JsonProperty("iexAskPrice")
    public Integer getIexAskPrice() {
        return iexAskPrice;
    }

    @JsonProperty("iexAskPrice")
    public void setIexAskPrice(Integer iexAskPrice) {
        this.iexAskPrice = iexAskPrice;
    }

    @JsonProperty("iexAskSize")
    public Integer getIexAskSize() {
        return iexAskSize;
    }

    @JsonProperty("iexAskSize")
    public void setIexAskSize(Integer iexAskSize) {
        this.iexAskSize = iexAskSize;
    }

    @JsonProperty("iexOpen")
    public Double getIexOpen() {
        return iexOpen;
    }

    @JsonProperty("iexOpen")
    public void setIexOpen(Double iexOpen) {
        this.iexOpen = iexOpen;
    }

    @JsonProperty("iexOpenTime")
    public Long getIexOpenTime() {
        return iexOpenTime;
    }

    @JsonProperty("iexOpenTime")
    public void setIexOpenTime(Long iexOpenTime) {
        this.iexOpenTime = iexOpenTime;
    }

    @JsonProperty("iexClose")
    public Double getIexClose() {
        return iexClose;
    }

    @JsonProperty("iexClose")
    public void setIexClose(Double iexClose) {
        this.iexClose = iexClose;
    }

    @JsonProperty("iexCloseTime")
    public Long getIexCloseTime() {
        return iexCloseTime;
    }

    @JsonProperty("iexCloseTime")
    public void setIexCloseTime(Long iexCloseTime) {
        this.iexCloseTime = iexCloseTime;
    }

    @JsonProperty("marketCap")
    public Long getMarketCap() {
        return marketCap;
    }

    @JsonProperty("marketCap")
    public void setMarketCap(Long marketCap) {
        this.marketCap = marketCap;
    }

    @JsonProperty("peRatio")
    public Double getPeRatio() {
        return peRatio;
    }

    @JsonProperty("peRatio")
    public void setPeRatio(Double peRatio) {
        this.peRatio = peRatio;
    }

    @JsonProperty("week52High")
    public Double getWeek52High() {
        return week52High;
    }

    @JsonProperty("week52High")
    public void setWeek52High(Double week52High) {
        this.week52High = week52High;
    }

    @JsonProperty("week52Low")
    public Double getWeek52Low() {
        return week52Low;
    }

    @JsonProperty("week52Low")
    public void setWeek52Low(Double week52Low) {
        this.week52Low = week52Low;
    }

    @JsonProperty("ytdChange")
    public Double getYtdChange() {
        return ytdChange;
    }

    @JsonProperty("ytdChange")
    public void setYtdChange(Double ytdChange) {
        this.ytdChange = ytdChange;
    }

    @JsonProperty("lastTradeTime")
    public Long getLastTradeTime() {
        return lastTradeTime;
    }

    @JsonProperty("lastTradeTime")
    public void setLastTradeTime(Long lastTradeTime) {
        this.lastTradeTime = lastTradeTime;
    }

    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    @JsonProperty("currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @JsonProperty("isUSMarketOpen")
    public Boolean getIsUSMarketOpen() {
        return isUSMarketOpen;
    }

    @JsonProperty("isUSMarketOpen")
    public void setIsUSMarketOpen(Boolean isUSMarketOpen) {
        this.isUSMarketOpen = isUSMarketOpen;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}