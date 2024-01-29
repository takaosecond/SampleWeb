package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.constant.UrlConst;

/**
 * Spring Securityカスタマイズ用
 * 
 * @author takaosecond
 *
 */
@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

	/** ユーザー名のname属性 */
	private final String USERNAME_PARAMTER = "loginId";

	/**
	 * Spring Securityカスタマイズよ
	 * 
	 * @param http カスタマイズパラメータ
	 * @return カスタマイズ結果
	 * @throws Exception 予期せぬ例外 
	 */
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(
				authorize -> authorize.requestMatchers(UrlConst.NO_AUTHENTICATION).permitAll().anyRequest()
						.authenticated())
				.formLogin(login -> login.loginPage(UrlConst.LOGIN).usernameParameter(USERNAME_PARAMTER)
						.defaultSuccessUrl(UrlConst.MENU));

		return http.build();
	}

}
