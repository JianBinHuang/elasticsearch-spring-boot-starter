package cn.codesheep.config;

import cn.codesheep.auto.EsProperties;
import cn.codesheep.dto.DocModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Configuration
public class DocFieldsConfig {

    @Autowired
    private EsProperties esProperties;

    @Bean
    public DocModel docModel() {
        List<String> docFields = new ArrayList<>();
        String[] fields = esProperties.getDocFields().split(",");
        for( int i=0; i<fields.length; ++i )
            docFields.add( fields[i] );
        return new DocModel( docFields );
    }

}
