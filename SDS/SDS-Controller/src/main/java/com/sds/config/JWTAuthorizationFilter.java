package com.sds.config;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sds.service.util.CodeMessageErrors;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

	private final static String HEADER = "Authorization";
	private final static String PREFIX = "Bearer ";
	private final static String SECRET = "mySecretKey";

	@Override
	protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
			final FilterChain chain) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Origin", "http://193.147.87.205:8090");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers",
				"Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
		try {
			if (existeJWTToken(request, response)) {
				final Claims claims = validateToken(request);
				if (claims.get("authorities") != null) {
					setUpSpringAuthentication(claims);
				} else {
					SecurityContextHolder.clearContext();
				}
			} else {
				SecurityContextHolder.clearContext();
			}
			chain.doFilter(request, response);
		} catch (final ExpiredJwtException expiredJwtException) {
			response.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
			response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, expiredJwtException.getMessage());
			throw new ServletException(CodeMessageErrors.JWT_EXPIRADO_EXCEPTION.getCodigo());
		} catch (final UnsupportedJwtException unsupportedJwtException) {
			response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
			response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, unsupportedJwtException.getMessage());
			throw new ServletException(CodeMessageErrors.JWT_NO_SOPORTADO_EXCEPTION.getCodigo());
		} catch (final MalformedJwtException malformedJwtException) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, malformedJwtException.getMessage());
			throw new ServletException(CodeMessageErrors.JWT_MALFORMADO_EXCEPTION.getCodigo());
		}
	}

	private Claims validateToken(final HttpServletRequest request) {
		final String jwtToken = request.getHeader(HEADER).replace(PREFIX, "");
		return Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(jwtToken).getBody();
	}

	/**
	 * Metodo para autenticarnos dentro del flujo de Spring
	 *
	 * @param claims
	 */
	private void setUpSpringAuthentication(final Claims claims) {
		final List<String> authorities = (List) claims.get("authorities");

		final UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(),
				null, authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
		SecurityContextHolder.getContext().setAuthentication(auth);

	}

	private boolean existeJWTToken(final HttpServletRequest request, final HttpServletResponse res) {
		final String authenticationHeader = request.getHeader(HEADER);
		if (authenticationHeader == null || !authenticationHeader.startsWith(PREFIX)) {
			return false;
		}

		return true;
	}

}
