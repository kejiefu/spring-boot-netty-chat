package com.mountain.user.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;


@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Autowired
  private HttpMessageConverters httpMessageConverters;

  /**
   * MappingJackson2HttpMessageConverter 实现了HttpMessageConverter 接口，
   * httpMessageConverters.getConverters() 返回的对象里包含了MappingJackson2HttpMessageConverter
   * @return
   */
  @Bean
  public MappingJackson2HttpMessageConverter getMappingJackson2HttpMessageConverter() {
    return new MappingJackson2HttpMessageConverter(new JacksonMapper());
  }


  @Override
  public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
    converters.addAll(httpMessageConverters.getConverters());
  }
}
