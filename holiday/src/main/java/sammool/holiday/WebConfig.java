package sammool.holiday;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import sammool.holiday.web.interceptor.LeaderInterceptor;
import sammool.holiday.web.interceptor.LoginCheckInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer{

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(0)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/register", "/login", "/logout", "/leader-login", "/css/**", "/*.ico"); //홈, 로그인, 로그아웃 , 회원가입    
        
        registry.addInterceptor(new LeaderInterceptor())
                .order(1)
                .addPathPatterns("/members", "/leader-login");

    }
    
}
