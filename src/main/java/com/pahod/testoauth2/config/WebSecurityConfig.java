package com.pahod.testoauth2.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@EnableWebSecurity
//@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .mvcMatchers("/").permitAll()
//                .mvcMatchers("/").hasAnyRole("USER", "HR", "MANAGER")
                .mvcMatchers("/hr_info").hasRole("HR")
                .mvcMatchers("/manager_info").hasRole("MANAGER")
//                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
//                .and()
//                .csrf().disable()
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserBuilder builder = User.withDefaultPasswordEncoder();

        auth.inMemoryAuthentication()
                .withUser(builder.username("qwe").password("qwe").roles("USER"))
                .withUser(builder.username("asd").password("asd").roles("HR"))
                .withUser(builder.username("zxc").password("zxc").roles("HR", "MANAGER"));
    }

//    @Bean
//    public PrincipalExtractor principalExtractor(UserRepository userRepository) {
//        return map -> {
//            String id = (String) map.get("sub");
//
//            UserModel user = userRepository.findById(id).orElseGet(() -> {
//                UserModel newUser = new UserModel();
//
//                newUser.setId(id);
//                newUser.setName((String) map.get("name"));
//                newUser.setEmail((String) map.get("email"));
//                newUser.setGender((String) map.get("gender"));
//                newUser.setLocale((String) map.get("locale"));
//                newUser.setUserpic((String) map.get("picture"));
//
//                return newUser;
//            });
//
//            user.setLastVisit(LocalDateTime.now());
//
//            return userRepository.save(user);
//        };
//    }
}
