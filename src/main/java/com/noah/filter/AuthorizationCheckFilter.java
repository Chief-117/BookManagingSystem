package com.noah.filter;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.noah.api.model.AjaxResultType;
import com.noah.api.model.BaseResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class AuthorizationCheckFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws ServletException, IOException {
		// 如果不是登入就攔截
		if (!req.getServletPath().equals("/admin/login")) {
			String requestTokenHeader = req.getHeader(AUTHORIZATION);
			String Bearer = "Bearer ";
			// 以jjwt驗證token，只要驗證成功就放行
			// 驗證失敗會拋exception，直接將錯誤訊息傳回
			if (requestTokenHeader != null && requestTokenHeader.startsWith(Bearer)) {
				try {
					String token = requestTokenHeader.substring(7);
					Claims claims = Jwts.parser().setSigningKey("MySecret").parseClaimsJws(token).getBody();
					System.out.println("JWT payload:" + claims.toString());
					chain.doFilter(req, res);
				} catch (Exception e) {
					System.err.println("Error : " + e);
					res.setStatus(HttpServletResponse.SC_FORBIDDEN);
					Map<String, String> err = new HashMap<>();
					err.put("jwt_err", e.getMessage());
					res.setContentType(MediaType.APPLICATION_JSON_VALUE);
					new ObjectMapper().writeValue(res.getOutputStream(), err);
				}
			} else {
				Map<String, String> empty = new HashMap<>();
				empty.put("status", AjaxResultType.UNAUTHORIZED.getCode());
				empty.put("msg", AjaxResultType.UNAUTHORIZED.getMsg());
				empty.put("data", "");
				res.setContentType(MediaType.APPLICATION_JSON_VALUE);
				res.setStatus(UNAUTHORIZED.value());
				new ObjectMapper().writeValue(res.getOutputStream(), empty);
			}
		} else {
			chain.doFilter(req, res);
		}
	}

}
