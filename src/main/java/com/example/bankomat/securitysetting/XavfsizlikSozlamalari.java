package com.example.bankomat.securitysetting;
import com.example.bankomat.service.XodimService;
import com.example.bankomat.token.Felter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Properties;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class XavfsizlikSozlamalari extends WebSecurityConfigurerAdapter {
  @Lazy
    @Autowired
    Felter felter;
    @Autowired
    XodimService xodimService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/bank/addbank","/kartaturi/addkartaturi").permitAll()
                .antMatchers("/kartaMuddati/addMuddat/{{bankId}},/{{turId}}").permitAll()
                .antMatchers("/usersCrud/addUsers/{{email}}").permitAll()
                .antMatchers("/karta/kartaQoshish/{{bankID}}/{{userID}}").permitAll()
                .antMatchers("/karta/TekshirishKarta").permitAll()
                .antMatchers("/karta/kartaADD").permitAll()
                .antMatchers("/karta/kartaADD/{{kartaMuddatiId}}").permitAll()
                .antMatchers("/xodimQoshish/xodimadd/{{username}}").permitAll()
                .antMatchers("/xodimQoshish/tasdiqlash").permitAll()
                .antMatchers("/xodimQoshish/login").permitAll()
                .antMatchers("/kpyuralar/kpyuraADD").permitAll()
                .antMatchers("/komissiya/komissiyaADD").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .disable();
        http
                .addFilterBefore(felter, UsernamePasswordAuthenticationFilter.class);
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
    @Bean
    public JavaMailSender getJavaMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("xushvaqitovjaloliddin@gmail.com");
        mailSender.setPassword("wvamuomhvhvtdgrl");
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol","smtp");
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.debug","true");
        return mailSender;
    }
    @Bean

    @Override
    protected AuthenticationManager authenticationManager() throws Exception{
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(xodimService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}

