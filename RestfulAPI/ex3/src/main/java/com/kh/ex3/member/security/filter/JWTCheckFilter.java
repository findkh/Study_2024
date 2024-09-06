package com.kh.ex3.member.security.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.kh.ex3.member.security.auth.CustomUserPrincipal;
import com.kh.ex3.member.security.util.JWTUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Component
@RequiredArgsConstructor
@Log4j2
public class JWTCheckFilter extends OncePerRequestFilter {
	
	private final JWTUtil jwtUtil;
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		if(request.getServletPath().startsWith("/api/v1/token/")) {
			return true;
		}
		return false;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		log.info("JWTCheckFilter doFilter...");
		log.info("requestURI: " + request.getRequestURI());
		
		String headerStr = request.getHeader("Authorization");
		
		log.info("headerStr: " + headerStr);
		
		// 토큰이 없는 경우
		if(headerStr == null || !headerStr.startsWith("Bearer")) {
			handleException(response, new Exception("ACCESS TOKEN NOT FOUND"));
			return;
		}
		
		String accessToken = headerStr.substring(7);
		try {
			Map<String, Object> tokenMap = jwtUtil.validateToken(accessToken);
			// 정상 처리인 경우
			log.info("tokenMap: " + tokenMap);
			
			String mid = tokenMap.get("mid").toString();
			String[] roles = tokenMap.get("role").toString().split(",");
			
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					new CustomUserPrincipal(mid),
					null,
					Arrays.stream(roles).map(role -> new SimpleGrantedAuthority("ROLE_" + role)).collect(Collectors.toList())
					);
			
			SecurityContext context = SecurityContextHolder.getContext();
			context.setAuthentication(authenticationToken);
			
			filterChain.doFilter(request, response);
		} catch (Exception e) {
			handleException(response, e);
		}
		
	}
	
	private void handleException(HttpServletResponse response, Exception e) throws IOException {
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		response.setContentType("application/json");
		response.getWriter().println("{\"error\":\"" + e.getMessage() + "\"}");
	}
	
}
