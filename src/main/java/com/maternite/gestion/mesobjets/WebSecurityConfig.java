package com.maternite.gestion.mesobjets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class WebSecurityConfig implements WebMvcConfigurer {

    @Configuration
    @EnableWebSecurity
    protected static class ApplicationSecurity extends WebSecurityConfigurerAdapter {

        @Bean
        public UserDetailsService userDetailsService() {
            return new UserDetailsServiceImp();
        };

        @Value("${cors.allowedOrigins}")
        private String allowedOrigins;

        @Override
        public void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService()).passwordEncoder(new PasswordEncoder() {
                @Override
                public String encode(CharSequence charSequence) {
                    return charSequence.toString();
                }

                @Override
                public boolean matches(CharSequence charSequence, String s) {
                    return true;
                }
            });
        }


        @Override
        protected void configure(HttpSecurity http) throws Exception {
            //
            //http.cors();

            //super.configure(http);
            http.csrf().disable();
            /*http.requiresChannel()
                    .anyRequest()
                    .requiresSecure();*/
            http.authorizeRequests().antMatchers("/**/createconsultation",
                    "/**/autorisation","/**/dashboard","/**/accueilpatient","/**/afficherhistpatient**",
                    "/**/genererfiche**","/**/modifierconsultation**"
                    //,"/**/apps"
                    ).authenticated()
                    .and().
                    formLogin().loginPage("/login").defaultSuccessUrl("/connexpatient").
                    failureUrl("/login/1").permitAll();
            http.authorizeRequests().anyRequest().permitAll();
            http.logout().logoutRequestMatcher(
                    new AntPathRequestMatcher("/deconnexion")).
                    logoutSuccessUrl("/login");
            http.sessionManagement().maximumSessions(3).expiredUrl("/login");
            http.sessionManagement().invalidSessionUrl("/login");
        }

        /*
        @Override
        public void addCorsMappings(CorsRegistry registry){
            registry.addMapping("/**")
                    .allowedOrigins(allowedOrigins)
                    .allowedMethods("*")
                    .allowedHeaders("*");
        }
        */
    }

}