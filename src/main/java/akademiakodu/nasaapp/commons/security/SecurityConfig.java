package akademiakodu.nasaapp.commons.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //prepost do kazdej metody kto ma dostep
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private CustomUserService customUserService;
    private PasswordEncoder passwordEncoder;


    public SecurityConfig(CustomUserService customUserService, PasswordEncoder passwordEncoder) {
        this.customUserService = customUserService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(customUserService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()  //wlacza autoryzacje
                .antMatchers("/login**","/").permitAll() //umozliwia logowani ewszystkim PermitAll   //** autoryzacja z wartosciami **
                .and()
                .formLogin()  //odpalamy mozliwosc dodawania formularzy // wlaczam
                .loginPage("/login") //pod tym linkiem chce miec log in     //ustawiam /\
                .loginProcessingUrl("/signin")   // form action link
                .usernameParameter("username")    //input name w formularzu
                .passwordParameter("password") //input password
                .successHandler(
                        (req, res, auth) -> {

                            for (GrantedAuthority g : auth.getAuthorities()) {
                                System.out.println(g.getAuthority());
                            }
                            res.sendRedirect("/");     //przekierowuje podczas logowania np strone glowna

                        })
                .failureHandler(
                        (req, res, exp) -> {
                            String errorMessage;
                            if (exp.getClass().isAssignableFrom(BadCredentialsException.class)) {
                                errorMessage = "Invalid username or password";
                            } else {
                                errorMessage = "unknown error " + exp.getMessage();
                            }
                            req.getSession().setAttribute("message", errorMessage);
                            res.sendRedirect("/login");  //jesli zle to wyrzuca na strone logowania
                        }
                )
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(   //po statusach lapie i przekierowuje
                        (req, res, auth) -> {
                            res.sendRedirect("/");
                        }
                ).permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/login");  //blad 403 error handler


        http
                .csrf().disable(); //odpornosc na ataki ddos
        http.headers().frameOptions().disable();


    }
}
