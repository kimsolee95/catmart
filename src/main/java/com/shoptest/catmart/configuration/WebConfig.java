package com.shoptest.catmart.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


//@EnableWebMvc
@EnableSwagger2
@Configuration
public class WebConfig implements WebMvcConfigurer { //implements WebMvcConfigurer extends WebMvcConfigurationSupport


  @Value("${uploadPath}")
  String uploadPath;

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    //이미지 data url_path html에서 출력 설정
    registry.addResourceHandler("/images/**")
        .addResourceLocations(uploadPath);

//    //swagger config
//    (swagger ui를 가져오기 위해 이곳에서 설정을 추가해 주어야만 http://localhost:8080/swagger-ui/index.html 로 api 확인이 가능.. )
      registry.addResourceHandler("swagger-ui.html")
          .addResourceLocations("classpath:/META-INF/resources/");
      registry.addResourceHandler("/webjars/**")
          .addResourceLocations("classpath:/META-INF/resources/webjars/");
  }

  //swagger config
  @Bean
  public Docket apiCart() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.shoptest.catmart.cart.controller.api"))
        .paths(PathSelectors.any())
        .build()
        .groupName("CART_API")
        .apiInfo(cartApiInfo());
  }

  @Bean
  public Docket apiOrder() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.shoptest.catmart.order.controller.api"))
        .paths(PathSelectors.any())
        .build()
        .groupName("ORDER_API")
        .apiInfo(orderApiInfo());
  }

  @Bean
  public Docket apiMember() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.shoptest.catmart.member.controller.api"))
        .paths(PathSelectors.any())
        .build()
        .groupName("MEMBER_API")
        .apiInfo(memberApiInfo());
  }

  @Bean
  public Docket apiAdmin() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.shoptest.catmart.admin.controller.api"))
        .paths(PathSelectors.any())
        .build()
        .groupName("ADMIN_API")
        .apiInfo(memberApiInfo());
  }

  private ApiInfo cartApiInfo() {
    return new ApiInfoBuilder()
        .title("CAT_MART 장바구니 API")
        .description("CAT_MART의 장바구니 상품 업무 API입니다.")
        .version("1.0")
        .build();
  }

  private ApiInfo orderApiInfo() {
    return new ApiInfoBuilder()
        .title("CAT_MART 주문 API")
        .description("CAT_MART의 주문 업무 API입니다.")
        .version("1.0")
        .build();
  }

  private ApiInfo memberApiInfo() {
    return new ApiInfoBuilder()
        .title("CAT_MART 회원 API")
        .description("CAT_MART의 회원 업무 API입니다.")
        .version("1.0")
        .build();
  }

  private ApiInfo adminApiInfo() {
    return new ApiInfoBuilder()
        .title("CAT_MART 관리자 API")
        .description("CAT_MART의 관리자 BO 업무 API입니다.")
        .version("1.0")
        .build();
  }


}

