package ru.vdv.jm.spring_boot_jm.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final LoginSuccessHandler loginSuccessHandler;

    public SecurityConfig(UserDetailsService userDetailsService, LoginSuccessHandler loginSuccessHandler) {
        this.userDetailsService = userDetailsService;
        this.loginSuccessHandler = loginSuccessHandler;
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userDetailsService);
        return authenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                //указываем страницу с формой логина
                .loginPage("/login")

                //указываем логику обработки при логине
                .successHandler(new LoginSuccessHandler())

                //указываем action с формы логина
                .loginProcessingUrl("/login")

                //Указываем параметры логина и пароля с формы логина
                .usernameParameter("username")
                .passwordParameter("password")

                //даем доступ к форме логина всем
                .permitAll();
        http.logout()

                //разрешаем делать логаут всем
                .permitAll()

                //указываем URL логаута
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))

                //указываем URL при удачном логауте
                .logoutSuccessUrl("/login")

                //выключаем кроссдоменную секьюрность (на этапе обучения неважна)
                .and()
                .csrf()
                .disable();

        http
                .formLogin()
                .successHandler(loginSuccessHandler);

        http
                // делаем страницу регистрации недоступной для авторизированных пользователей
                //то есть запрашиваем авторизацию для определённых url
                .authorizeRequests()

                // защищенные URL. То есть даём разрешение для конкретного url, конкретным ролям
                // .antMatchers(Http.Method.GET, "/user").hasAnyRole(Role.ADMIN.name(), Role.USER.name())
                .antMatchers("/admin/**")
                .hasAuthority("ADMIN")
                .antMatchers("/user")
                .hasAnyAuthority("USER", "ADMIN")

                //страницы аутентификаци доступна всем
                .antMatchers("/login")
                .anonymous()
                .and()
                .
                        formLogin()
                .permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}