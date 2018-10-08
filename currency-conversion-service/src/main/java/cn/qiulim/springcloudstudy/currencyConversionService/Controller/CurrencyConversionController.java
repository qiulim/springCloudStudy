package cn.qiulim.springcloudstudy.currencyConversionService.Controller;

import cn.qiulim.springcloudstudy.currencyConversionService.Bean.CurencyConversionBean;
import cn.qiulim.springcloudstudy.currencyConversionService.Proxy.CurrencyExchangeProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConversionController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CurrencyExchangeProxy currencyExchangeProxy;

    @GetMapping("/currency-convert/from/{from}/to/{to}/quantity/{quantity}")
    public CurencyConversionBean converCurrency(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {

        String url = "http://localhost:8000/currency-exchange/from/{from}/to/{to}";
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from",from);
        uriVariables.put("to",to);
        ResponseEntity<CurencyConversionBean> response = new RestTemplate().getForEntity(url, CurencyConversionBean.class, uriVariables);
        CurencyConversionBean body = response.getBody();
        return new CurencyConversionBean(body.getId(),
                from, to,
                body.getConversionMultiple(),
                quantity,
                quantity.multiply(body.getConversionMultiple()),
                body.getPort());
    }

    @GetMapping("/currency-convert-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurencyConversionBean converCurrencyFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {


        CurencyConversionBean body = currencyExchangeProxy.retrieveExchangeValue(from,to);

        logger.info("{}", body);
        return new CurencyConversionBean(body.getId(),
                from, to,
                body.getConversionMultiple(),
                quantity,
                quantity.multiply(body.getConversionMultiple()),
                body.getPort());
    }
}
