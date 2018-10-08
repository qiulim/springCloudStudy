package cn.qiulim.springcloud.limitsService.Controller;

import cn.qiulim.springcloud.limitsService.Bean.LimitConfiguration;
import cn.qiulim.springcloud.limitsService.Configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigurationController {

    @Autowired
    private Configuration configuration;

    @GetMapping("/limits")
    public LimitConfiguration retrieveFromConfigurations() {
            return new LimitConfiguration(configuration.getMaximum(),configuration.getMinimum());
    }
}
