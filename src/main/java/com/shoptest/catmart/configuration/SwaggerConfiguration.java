//package com.shoptest.catmart.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//@Configuration
//@EnableSwagger2
//public class SwaggerConfiguration { //extends WebMvcConfigurationSupport
//
//
////  @Override
////  public void addResourceHandlers(ResourceHandlerRegistry registry) {
//////    registry.addResourceHandler("/swagger-ui/**")
//////        .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
//////
//////    registry.addResourceHandler("/webjars/**")
//////        .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
////
////    registry.addResourceHandler("/swagger-ui/**")
////        .addResourceLocations("classpath:/META-INF/resources/webjars/swagger-ui/2.7.0/");
////  }
//
//  @Bean
//  public Docket api() {
//    return new Docket(DocumentationType.SWAGGER_2)
//        .select()
//        .apis(RequestHandlerSelectors.basePackage("com.shoptest.catmart.cart"))
//        .paths(PathSelectors.any())
//        .build().apiInfo(cartApiInfo());
//  }
//
//  private ApiInfo cartApiInfo() {
//    return new ApiInfoBuilder()
//        .title("CAT_MART 장바구니 API")
//        .description("CAT_MART의 장바구니 상품을 CRUD하는 API입니다.")
//        .version("1.0")
//        .build();
//  }
//
//}
