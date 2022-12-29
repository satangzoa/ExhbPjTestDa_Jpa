package com.Exhibition.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests().requestMatchers(				
				new AntPathRequestMatcher("/**")).permitAll()
			
			//로그인 로그아웃 설정
			.and()//로그인
				.formLogin()
				.loginPage("/login")//로그인 페이지가 보이게 하는 요청
				.defaultSuccessUrl("/index")//로그인 성공시 이동할 요청
			.and()//로그아웃
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))//로그아웃 요청
				.logoutSuccessUrl("/index")//로그아웃 성공시 이동할 요청
				.invalidateHttpSession(true);//세션삭제 로그아웃
				
		return http.build();
	}
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
}
