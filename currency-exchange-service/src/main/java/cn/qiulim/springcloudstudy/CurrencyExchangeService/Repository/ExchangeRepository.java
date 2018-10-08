package cn.qiulim.springcloudstudy.CurrencyExchangeService.Repository;

import cn.qiulim.springcloudstudy.CurrencyExchangeService.Bean.ExchangeValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRepository extends JpaRepository<ExchangeValue,Long>{

    ExchangeValue findByFromAndTo(String from, String to);
}
