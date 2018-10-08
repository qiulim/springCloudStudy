package cn.qiulim.restservice.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static Contact DEFAULT_CONTACT = new Contact("qiulim", "http://baidu.com", "22792699@qq.com");
    private static ApiInfo DEFAULT_INFO = new ApiInfo(
            "Awesome API Title",
            "Awesome API Document",
            "1.0",
            "urn:tos",
            DEFAULT_CONTACT.getName(),
            "Apache 2.0",
            "http://www.apache.org/licenses/LICENSENS-2.0"
    );

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_INFO);
    }

}
