package cn.codesheep.auto;

import cn.codesheep.service.ISearchService;
import cn.codesheep.service.impl.SearchServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EsAutoConfiguration {

    @Bean
    ISearchService iSearchService() {
        return new SearchServiceImpl();
    }
}
