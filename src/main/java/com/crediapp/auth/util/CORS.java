package com.crediapp.auth.util;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CORS implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		}
//	@Override
//	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
//			throws IOException, ServletException {
//		HttpServletResponse response = (HttpServletResponse) res;
//		HttpServletRequest request = (HttpServletRequest) req;
//
//		String origin = request.getHeader("Origin");
//		String method = request.getMethod();
//
//		if (!"http://localhost:4200".equals(origin)) {
//			response.sendError(HttpServletResponse.SC_FORBIDDEN, "Origin not allowed");
//			return;
//		}
//
//
//		response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
//		//response.setHeader("Access-Control-Allow-Methods", "DELETE, GET, OPTIONS, PATCH, POST, PUT");
//		response.setHeader("Access-Control-Allow-Methods", "GET");
//		response.setHeader("Access-Control-Max-Age", "3600");
//		response.setHeader("Access-Control-Allow-Headers",
//				"x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN");
//
//
//
//		if ("OPTIONS".equalsIgnoreCase(method)) {
//			response.setStatus(HttpServletResponse.SC_OK);
//		} else if (!"GET".equalsIgnoreCase(method)) {
//			// Rechazar explícitamente métodos distintos de POST
//			response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Only GET is allowed");
//		} else {
//			chain.doFilter(req, res);
//		}
//
//	}
@Override
public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
		throws IOException, ServletException {
	HttpServletResponse response = (HttpServletResponse) res;
	HttpServletRequest request = (HttpServletRequest) req;

	// 1. Definir el origen permitido (Angular)
	response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");

	// 2. Permitir todos los métodos necesarios para el simulador
	response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");

	// 3. Definir el tiempo de vida de la configuración en el navegador (1 hora)
	response.setHeader("Access-Control-Max-Age", "3600");

	// 4. Permitir cabeceras críticas (Content-Type es vital para enviar JSON)
	response.setHeader("Access-Control-Allow-Headers",
			"x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN");

	// 5. Manejo de peticiones de "pre-vuelo" (Preflight)
	if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
		response.setStatus(HttpServletResponse.SC_OK);
	} else {
		// Continuar con la cadena de filtros para peticiones reales (GET, POST, etc.)
		chain.doFilter(req, res);
	}
}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}
}