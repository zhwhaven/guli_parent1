package com.haven.cmsService.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.haven.cmsService.mapper")
public class MybatisplusConfig {

    @Bean
    ISqlInjector iSqlInjector(){
        return new LogicSqlInjector();
    }

    @Bean
    PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
    //    乐观锁机制
    @Bean
    public OptimisticLockerInterceptor getOptimistimc(){
        return new OptimisticLockerInterceptor();
    }
}
