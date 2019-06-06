package pers.jz.web.common.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Rest客户端
 * Created by Jemmy Zhang on 2019/4/17
 */
@Slf4j
public class RestClient {

    private volatile RestTemplate restTemplate;
    private final PooledRestClientConfigurer pooledRestClientConfigurer;

    public RestClient() {
        pooledRestClientConfigurer = null;
    }

    public RestClient(PooledRestClientConfigurer pooledRestClientConfigurer) {
        this.pooledRestClientConfigurer = pooledRestClientConfigurer;
    }

    public RestTemplate getRestTemplate() {
        if (Objects.isNull(restTemplate)) {
            synchronized (RestClient.class) {
                restTemplate = pooledRestClientConfigurer == null ? new PooledRestClientConfigurer().build() : pooledRestClientConfigurer.build();
            }
        }
        return restTemplate;
    }

    //The builder should be used in a single thread.
    public static class PooledRestClientConfigurer {

        private RestTemplate restTemplate;
        private int connectionLiveTime = 30;
        private int maxConnection = 1000;
        private int defaultMaxPerRoute = 1000;
        private int retryTimes = 2;
        private int clientConnectionTimeout = 5000;
        private int readTimeout = 5000;
        private int connectionRequestTimeout = 200;
        private boolean bufferRequestBody = false;
        private List<Header> defaultHeaders = new ArrayList<>();
        private ResponseErrorHandler responseErrorHandler = new DefaultResponseErrorHandler();

        public void setConnectionLiveTime(int connectionLiveTime) {
            this.connectionLiveTime = connectionLiveTime;
        }

        public void setMaxConnection(int maxConnection) {
            this.maxConnection = maxConnection;
        }

        public void setDefaultMaxPerRoute(int defaultMaxPerRoute) {
            this.defaultMaxPerRoute = defaultMaxPerRoute;
        }

        public void setRetryTimes(int retryTimes) {
            this.retryTimes = retryTimes;
        }

        public void addDefaultHeaders(List<Header> defaultHeaders) {
            this.defaultHeaders.addAll(defaultHeaders);
        }

        public void setClientConnectionTimeout(int clientConnectionTimeout) {
            this.clientConnectionTimeout = clientConnectionTimeout;
        }

        public void setReadTimeout(int readTimeout) {
            this.readTimeout = readTimeout;
        }

        public void setConnectionRequestTimeout(int connectionRequestTimeout) {
            this.connectionRequestTimeout = connectionRequestTimeout;
        }

        public void setBufferRequestBody(boolean bufferRequestBody) {
            this.bufferRequestBody = bufferRequestBody;
        }

        public void setRestTemplate(RestTemplate restTemplate) {
            this.restTemplate = restTemplate;
        }

        public void setResponseErrorHandler(ResponseErrorHandler responseErrorHandler) {
            this.responseErrorHandler = responseErrorHandler;
        }

        public RestTemplate build() {
            PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager(connectionLiveTime, TimeUnit.SECONDS);
            poolingHttpClientConnectionManager.setMaxTotal(maxConnection);
            poolingHttpClientConnectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
            HttpClientBuilder httpClientBuilder = HttpClients.custom();
            httpClientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(retryTimes, true));
            httpClientBuilder.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy());
            defaultHeaders.add(new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.16 Safari/537.36"));
            defaultHeaders.add(new BasicHeader("Accept-Encoding", "gzip,deflate"));
            defaultHeaders.add(new BasicHeader("Accept-Language", "zh-CN"));
            defaultHeaders.add(new BasicHeader("Connection", "Keep-Alive"));
            httpClientBuilder.setDefaultHeaders(defaultHeaders);
            HttpClient httpClient = httpClientBuilder.build();
            HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
            clientHttpRequestFactory.setConnectionRequestTimeout(connectionRequestTimeout);
            clientHttpRequestFactory.setBufferRequestBody(bufferRequestBody);
            clientHttpRequestFactory.setConnectTimeout(clientConnectionTimeout);
            clientHttpRequestFactory.setReadTimeout(readTimeout);
            List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
            messageConverters.add(new FormHttpMessageConverter());
            messageConverters.add(new MappingJackson2XmlHttpMessageConverter());
            messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
            MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
            ObjectMapper objectMapper = mappingJackson2HttpMessageConverter.getObjectMapper();
            objectMapper.setPropertyNamingStrategy(new PropertyNamingStrategy.SnakeCaseStrategy());
            messageConverters.add(mappingJackson2HttpMessageConverter);
            restTemplate = new RestTemplate(messageConverters);
            restTemplate.setRequestFactory(clientHttpRequestFactory);
            restTemplate.setErrorHandler(responseErrorHandler);
            return restTemplate;
        }
    }
}
