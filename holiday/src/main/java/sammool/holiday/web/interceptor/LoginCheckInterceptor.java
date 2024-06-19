package sammool.holiday.web.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import sammool.holiday.web.SessionConst;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        
            String requestURI = request.getRequestURI();
            log.info("로그인 인증 체크 인터셉터 실행:{}", requestURI);
            
            HttpSession session = request.getSession(false);
            
            if(session.getAttribute(SessionConst.LOGIN_LEADER)!=null){
                return true;
            }

            if(session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null ){
                log.info("미인증 사용자 요청");
                response.sendRedirect("https://shiny-barnacle-4pxgv5rp5q4c7pj6-8080.app.github.dev/login?redirectURI=" +requestURI);
                return false;
            }

            return true;
    }
    
}
