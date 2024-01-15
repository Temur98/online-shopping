package config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity(debug = true)
public class SecurityConfig {

    private JWTValidatorFilter jwtValidatorFilter;
    private JWTSecurityGeneratorFilter jwtSecurityGeneratorFilter;
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(

                        (requests) ->requests

                                .requestMatchers("/notices/**", "/contacts").permitAll()

                                .requestMatchers(HttpMethod.POST,"/accounts").permitAll()

                                .requestMatchers(
                                        HttpMethod.GET,
                                        "api/v1/products/**"
                                        ).hasAuthority("user")
                                .requestMatchers(
                                        HttpMethod.PUT,
                                        "api/v1/products/**"
                                        ).hasAuthority("user")

                                .requestMatchers(HttpMethod.GET,"api/v1/products/**").hasAnyAuthority("super-admin","admin")
                                .requestMatchers(HttpMethod.POST,"api/v1/products/**").hasAnyAuthority("super-admin","admin")
                                .requestMatchers(HttpMethod.DELETE,"api/v1/products/**").hasAuthority("super-admin")

                                .anyRequest().permitAll()

                );

        http.addFilterBefore(jwtValidatorFilter, BasicAuthenticationFilter.class);
        http.addFilterAfter(jwtSecurityGeneratorFilter, BasicAuthenticationFilter.class);

        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }


    @Bean
    public PasswordEncoder myPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
