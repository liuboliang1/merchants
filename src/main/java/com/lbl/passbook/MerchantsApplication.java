package com.lbl.passbook;

import com.lbl.passbook.security.AuthCheckInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

@SpringBootApplication
public class MerchantsApplication extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(MerchantsApplication.class, args);
    }

    @Resource
    private AuthCheckInterceptor authCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(authCheckInterceptor)
                .addPathPatterns("/merchants/**");
        super.addInterceptors(registry);
    }

}
