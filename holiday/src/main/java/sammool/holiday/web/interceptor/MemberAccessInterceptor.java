package sammool.holiday.web.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import sammool.holiday.domain.Leader;
import sammool.holiday.domain.Member;
import sammool.holiday.web.SessionConst;

@Slf4j
public class MemberAccessInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        
            String requestURI = request.getRequestURI();
            HttpSession session = request.getSession(false);
            Member sessionMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
            Leader sessionLeader = (Leader) session.getAttribute(SessionConst.LOGIN_LEADER);

            if(sessionLeader != null){ return true;}

            if(sessionMember == null){
                log.info("로그인 페이지로 이동합니다");
                response.sendRedirect("https://shiny-barnacle-4pxgv5rp5q4c7pj6-8080.app.github.dev/login?redirectURI=" +requestURI);
                return false;
            }

            String[] parts = requestURI.split("/");
            String memberId = parts[2];

            if(!sessionMember.getMember_id().equals(memberId)){
                log.info("접근 권한이 없는 사용자입니다");
                log.info("sessionMember={}, loginMember={}", sessionMember.getMember_id(),memberId);
                response.sendRedirect("https://shiny-barnacle-4pxgv5rp5q4c7pj6-8080.app.github.dev/");
                return false;
            }
            return true;

    }
    
}
