package pers.jz.web.common.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Jemmy Zhang on 2019/6/6.
 */
@Configuration
public class RestClientConfig {

    @Bean
    public RestClient restClient() {
        RestClient.PooledRestClientConfigurer builder = new RestClient.PooledRestClientConfigurer();
        return new RestClient(builder);
    }
}