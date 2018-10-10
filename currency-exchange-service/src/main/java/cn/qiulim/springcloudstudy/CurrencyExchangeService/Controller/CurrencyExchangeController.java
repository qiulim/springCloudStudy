package cn.qiulim.springcloudstudy.CurrencyExchangeService.Controller;

import cn.qiulim.springcloudstudy.CurrencyExchangeService.Bean.ExchangeValue;

import cn.qiulim.springcloudstudy.CurrencyExchangeService.Repository.ExchangeRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Environment environment;

    @Autowired
    private ExchangeRepository exchangeRepository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
//        ExchangeValue exchangeValue = new ExchangeValue(1000L, from, to, BigDecimal.valueOf(65));
        ExchangeValue exchangeValue = exchangeRepository.findByFromAndTo(from, to);
        exchangeValue.setPort(Integer.valueOf(environment.getProperty("local.server.port")));
        logger.info("{}", exchangeValue);
        return exchangeValue;
    }

    @GetMapping("/currency-exchange-hystrix/from/{from}/to/{to}")
    @HystrixCommand(fallbackMethod = "hystrixRetrieveExchange")
    public ExchangeValue retrieveExchangeValueHystrix(@PathVariable String from, @PathVariable String to) {
//        ExchangeValue exchangeValue = new ExchangeValue(1000L, from, to, BigDecimal.valueOf(65));
        throw new RuntimeException("Not available");
    }

    public ExchangeValue hystrixRetrieveExchange(String from,String to) {
        return new ExchangeValue(9999L, "USD", "CN", BigDecimal.valueOf(60));
    }
}
