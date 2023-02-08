package me.kreal.attendance.config;

import me.kreal.attendance.security.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private JwtFilter jwtFilter;

    @Autowired
    public void setJwtFilter(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/zoom/**").permitAll()
//                .antMatchers("/content/test").permitAll()
//                .antMatchers("/content/getAll", "/content/get/*").hasAuthority("read")
//                .antMatchers("/content/create").hasAuthority("write")
//                .antMatchers("/content/update").hasAuthority("update")
//                .antMatchers("/content/delete/*").hasAuthority("delete")
                .anyRequest()
                .authenticated();
    }



}
