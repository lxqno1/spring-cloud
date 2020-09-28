package com.learn.service.impl;

import com.learn.domain.Product;
import com.learn.service.OrderService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.Future;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private RestTemplate restTemplate;

    public String providerUrl ="http://service-provider/product";

    //-------------------------------------------------------------
    //   01 请求缓存
    //-------------------------------------------------------------

    //请求缓存
    @Cacheable(cacheNames ="orderService:product:list")
    public List findProductList(){
        return restTemplate.exchange(providerUrl, HttpMethod.GET,null,new ParameterizedTypeReference<List<Product>>() {
        }).getBody();
    }

    //-------------------------------------------------------------
    //   02 请求合并
    //-------------------------------------------------------------

    //声明需要服务容错的方法
    @HystrixCommand
    public List<Product> findProductList2(List<Integer> ids){
        return restTemplate.exchange(providerUrl, HttpMethod.GET,null,new ParameterizedTypeReference<List<Product>>() {
        }).getBody();
    }

    // 处理请求合并的方法一定要支持异步，返回值必须是 Future<T>
    // 请求合并
    @HystrixCollapser(batchMethod = "findProductList2", // 合并请求方法
            scope = com.netflix.hystrix.HystrixCollapser.Scope.GLOBAL, // 请求方式
            collapserProperties = {
                    // 间隔多久的请求会进行合并，默认 10ms
                    @HystrixProperty(name = "timerDelayInMilliseconds", value = "20"),
                    // 批处理之前，批处理中允许的最大请求数
                    @HystrixProperty(name = "maxRequestsInBatch", value = "200")
            })
    @Override
    public Future<Product> selectProductById(Integer id) {
        System.out.println("-----orderService-----selectProductById-----");
        return null;
    }

    //-------------------------------------------------------------
    //   03 服务熔断
    //-------------------------------------------------------------

    // 声明需要服务容错的方法
    // 服务熔断
    @HystrixCommand(commandProperties = {
            // 当请求符合熔断条件触发 fallbackMethod 默认 20 个
            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_REQUEST_VOLUME_THRESHOLD,
                    value = "10"),
            // 请求错误率大于 50% 就启动熔断器，然后 for 循环发起重试请求，当请求符合熔断条件触发 fallbackMethod
            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE,
                    value = "50"),
            // 熔断多少秒后去重试请求，默认 5s
            @HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_SLEEP_WINDOW_IN_MILLISECONDS,
                    value = "5000"),
    }, fallbackMethod = "selectProductByIdFallback")
    @Override
    public Product selectProductById2(Integer id) {
        System.out.println("-----selectProductById2-----" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
        // 模拟查询主键为 1 的商品信息会导致异常
        if (1 == id)
            throw new RuntimeException("查询主键为 1 的商品信息导致异常");
        return restTemplate.getForObject("http://service-provider/product/listById/{" + id+"}", Product.class);
    }

    // 托底数据
    private Product selectProductByIdFallback(Integer id) {
        return new Product(id, "托底数据", 1, 2666D);
    }
}
