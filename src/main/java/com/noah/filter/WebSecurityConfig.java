package com.noah.filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.noah.config.JwtAuthenticationEntryPoint;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        //關掉csrf保護
    	httpSecurity.csrf().disable()
    	//不需要session來儲存使用者資料 因為要使用token認證
    	.sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //加上剛剛寫的filter
    	httpSecurity.addFilterBefore(new AuthorizationCheckFilter(), BasicAuthenticationFilter.class);
    }  

}