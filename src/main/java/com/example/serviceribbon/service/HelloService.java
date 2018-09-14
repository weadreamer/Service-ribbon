package com.example.serviceribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

    @Autowired
    RestTemplate restTemp;

    //方式一
    @HystrixCommand(fallbackMethod = "fallBack")
    public String hiService(String name){

        return restTemp.getForObject("http://service-hi/hi?name="+name,String.class);
    }

    //方式二
    /*@HystrixCommand(fallbackMethod = "fallBack")
    public String hiService2(String name) {

        RestTemplate template = new RestTemplate();  //方式一RestTemplate已通过注解方式声明
        ServiceInstance instance = loadBalancerClient.choose("PRODUCT");
        String url = String.format("http://%s:%s", instance.getHost(), instance.getPort() + "/hi?name="+name);
        return template.getForObject(url, String.class);
    }*/
        public String fallBack(String name){

        return "hi,"+name+",sorry,error!";
    }
}
