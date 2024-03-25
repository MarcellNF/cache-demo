package de.neuefische.cachedemo.controller;

import de.neuefische.cachedemo.model.Stock;
import de.neuefische.cachedemo.model.StockResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/stock")
@Cacheable(value = "stock", key = "#root.methodName")
public class StockController {

    private final RestClient restClient = RestClient.create("https://finnhub.io/api/v1/quote");

    private static final String TOKEN = "&token=cdpnvraad3ia8s05f5egcdpnvraad3ia8s05f5f0";
    private static final String APPLE = "?symbol=AAPL";
    private static final String NVIDIA = "?symbol=NVDA";
    private static final String MICROSOFT = "?symbol=MSFT";
    private static final String GOOGLE = "?symbol=GOOGL";

    @GetMapping("/apple")
    public Stock getStockPriceApple() {
        StockResponse stockResponse = restClient.get().uri(APPLE + TOKEN)
                .retrieve()
                .toEntity(StockResponse.class)
                .getBody();

        return new Stock("Apple", stockResponse != null ? stockResponse.c() : 0.0, LocalDateTime.now());
    }

    @GetMapping("/nvidia")
    public Stock getStockPriceNvidia() {
        StockResponse stockResponse = restClient.get().uri(NVIDIA + TOKEN)
                .retrieve()
                .toEntity(StockResponse.class)
                .getBody();

        return new Stock("Nvidia", stockResponse != null ? stockResponse.c() : 0.0, LocalDateTime.now());
    }

    @GetMapping("/microsoft")
    public Stock getStockPriceMicrosoft() {
        StockResponse stockResponse = restClient.get().uri(MICROSOFT + TOKEN)
                .retrieve()
                .toEntity(StockResponse.class)
                .getBody();

        return new Stock("Microsoft", stockResponse != null ? stockResponse.c() : 0.0, LocalDateTime.now());
    }

    @GetMapping("/google")
    public Stock getStockPriceGoogle() {
        StockResponse stockResponse = restClient.get().uri(GOOGLE + TOKEN)
                .retrieve()
                .toEntity(StockResponse.class)
                .getBody();

        return new Stock("Google", stockResponse != null ? stockResponse.c() : 0.0, LocalDateTime.now());
    }
}
