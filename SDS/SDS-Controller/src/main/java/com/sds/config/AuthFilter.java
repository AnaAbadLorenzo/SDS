package com.sds.config;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sds.security.JWToken;
import com.sds.service.usuario.impl.UsuarioServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.io.IOException;

public class AuthFilter extends OncePerRequestFilter {

	public JWToken jwtToken;
	private final UsuarioServiceImpl usuarioService;

	public AuthFilter() {
		usuarioService = new UsuarioServiceImpl();
	}

	private static final String PREFIX_TOKEN = "Bearer ";
	private static final String HEADER = "Authorization";

	@Override
	protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
			final FilterChain filterChain) throws ServletException, IOException {

		String username = null;
		String token = null;

		token = extractToken(request);

		try {
			username = jwtToken.getUsername(token);

		} catch (final IllegalArgumentException e) {
			// TODO meter excepcion
		} catch (final ExpiredJwtException e) {
			// TODO meter excepcion
		} catch (final SignatureException e) {
			// TODO meter excepcion
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			final UserDetails userDetails = usuarioService.loadUserByUsername(username);

			if (jwtToken.validate(token, userDetails)) {
				final UsernamePasswordAuthenticationToken authentication = jwtToken.getAuthenticationToken(token,
						SecurityContextHolder.getContext().getAuthentication(), userDetails);
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				// TODO meter excepcion logger.info("authenticated user " + username + ",
				// setting security context");
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}

		try {
			filterChain.doFilter(request, response);
		} catch (final java.io.IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* Extraemos el token de la cabecera de la peticion */

	private String extractToken(final HttpServletRequest request) {
		String token = "";
		final String header = request.getHeader(HEADER);

		if (StringUtils.hasText(header)) {
			if (header.startsWith(PREFIX_TOKEN)) {
				token = header.substring(7, header.length());
			}
		} else {
			// TODO meter excepcion logger.warn("No se ha encontrado la cabecera
			// correspondiente");
		}

		return token;
	}

}