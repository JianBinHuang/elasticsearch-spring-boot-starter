package cn.codesheep.config;

import cn.codesheep.auto.EsProperties;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class ElasticSearchConfig {

    @Autowired
    private EsProperties esProperties;

    @Bean
    public TransportClient esTransportClient() throws UnknownHostException {

        Settings settings = Settings.builder()
                .put( "cluster.name", esProperties.getClusterName() )
                .put("client.transport.sniff", true)
                .build();
        TransportAddress master = new TransportAddress( InetAddress.getByName( esProperties.getHost() ), esProperties.getTcpPort() );
		TransportClient esClient = new PreBuiltTransportClient(settings).addTransportAddress( master );
        return esClient;
    }

    @Bean
    public RestHighLevelClient esRestHighLevelClient() {

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost( esProperties.getHost(), esProperties.getHttpPort(), "http" )
                )
        );
        return client;
    }
}
