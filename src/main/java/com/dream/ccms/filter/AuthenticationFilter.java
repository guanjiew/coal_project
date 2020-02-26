package com.dream.ccms.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dream.ccms.authorization.TokenManager;


@Order(1)
@WebFilter(filterName = "authenticationFilter",urlPatterns = "/*")
public class AuthenticationFilter extends OncePerRequestFilter {
	  private static final PathMatcher pathMatcher = new AntPathMatcher();

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		 try {
	            if(isProtectedUrl(request)) {
	                String token = request.getHeader("SECRET");
	                //检查jwt令牌, 如果令牌不合法或者过期, 里面会直接抛出异常, 下面的catch部分会直接返回
	                TokenManager.parseJWT(token);
	              
	            }
	        } catch (Exception e) {
	            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
	            return;
	        }
	        //如果jwt令牌通过了检测, 那么就把request传递给后面的RESTful api
	        filterChain.doFilter(request, response);
		
	}
	 //只对地址 /api 开头的api检查jwt. 不然的话登录/login也需要jwt
    private boolean isProtectedUrl(HttpServletRequest request) {
        return pathMatcher.match("/api/**", request.getServletPath());
    }

	

}
