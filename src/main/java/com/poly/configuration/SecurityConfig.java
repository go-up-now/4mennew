package com.poly.configuration;

import com.poly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    UserService userService;

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(10);
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                                .requestMatchers("/4men/admin/api/**").permitAll()
//                    .requestMatchers("/admin/**").hasRole("ADMIN") // yêu cầu quyền admin để truy cập các trang admin
//                    .requestMatchers("/gio-hang").hasAnyRole("USER", "ADMIN") // yêu cầu quyền user để truy cập các trang user
//                    .requestMatchers("/assets/**", "/common/**", "/images/**", "/layout/**").permitAll() // Cho phép truy cập các tài nguyên tĩnh
                    .anyRequest().permitAll() // Mọi yêu cầu đều đều được truy cập
                );
                // Cấu hình trang đăng nhập
//                .formLogin(loginForm -> loginForm
//                        .loginPage("/dang-nhap") // trang đăng nhập tùy chỉnh
//                        .defaultSuccessUrl("/trang-chu", false) // trang đích sau khi đăng nhập thành công
//                        .permitAll() // Cho phép truy cập công khai trang đăng nhập
//                )
//                // Cấu hình trang đăng xuất
//                .logout(logout -> logout
//                        .logoutUrl("/logout") // url trang đăng xuất
//                        .logoutSuccessUrl("/dang-nhap") // trang đích sau khi đăng xuất thành công
//                        .permitAll() // cho phép truy cập công khai chức năng đăng xuất
//                );
        return http.build();
    }

//    public void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth.userDetailsService(username -> {
//           try {
//               com.poly.entity.User user = userService.getUserByUsername(username);
//               String password =
//           }
//           catch (Exception e) {
//
//           }
//        });
//    }

    // Định nghĩa một bean cho UserDetailsService để quản lý thông tin người dùng trong bộ nhớ
//    @Bean
//    public UserDetailsService userDetailsService(){
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("123456")
//                .roles("USER")
//                .build());
//        manager.createUser(User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("123456")
//                .roles("ADMIN")
//                .build());
//        return manager;
//    }

//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource) {
//        JdbcDaoImpl userDetailsService = new JdbcDaoImpl();
//        userDetailsService.setDataSource(dataSource);
//        userDetailsService.setUsersByUsernameQuery("SELECT username, password, state AS enabled FROM User WHERE username = ?");
//        userDetailsService.setAuthoritiesByUsernameQuery(
//                "SELECT u.username, r.name as authority " +
//                        "FROM User u " +
//                        "JOIN Role r ON u.role_id = r.id " +
//                        "WHERE u.username = ?");
//        return userDetailsService;
//    }



    // Định nghĩa một bean cho WebSecurityCustomizer để bỏ qua bảo mật cho các tài nguyên tĩnh
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().requestMatchers("/css/**", "/js/**", "/images/**");
//    }
}
