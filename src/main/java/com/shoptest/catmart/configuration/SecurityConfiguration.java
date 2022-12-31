package com.shoptest.catmart.configuration;

import com.shoptest.catmart.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final MemberService memberService;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(); //비밀번호 암호화 후 저장
  }


  @Override
  public void configure(WebSecurity web) throws Exception {
    //로그인 후, static 리소스 파일을 필터링에서 제외하기
    web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    
    super.configure(web);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.csrf().disable();

    http.authorizeRequests().antMatchers(
        "/main"
        , "/member/join"
    ).permitAll();

    http.formLogin()
        .loginPage("/member/login")
        .defaultSuccessUrl("/main")
        .usernameParameter("email")
        .failureUrl("/member/login/fail")
        .permitAll();

    http.logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
        .logoutSuccessUrl("/main")
        .invalidateHttpSession(true);

    super.configure(http);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    auth.userDetailsService(memberService) //userDetailsService 구현체
        .passwordEncoder(passwordEncoder()); //비밀번호 암호화 진행

    super.configure(auth);
  }
}
