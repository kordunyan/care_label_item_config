package com.itemconfiguration.controller.filter;

import com.itemconfiguration.config.TenantContext;
import com.itemconfiguration.utils.WebUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TenantFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		if (request.getHeader(WebUtils.HEAD_X_TENANT_ID) != null) {
			TenantContext.setCurrentTenant(request.getHeader(WebUtils.HEAD_X_TENANT_ID));
		}
		else if (request.getParameter(WebUtils.REQ_PARAM_TENANT_ID) != null) {
			TenantContext.setCurrentTenant(request.getParameter(WebUtils.REQ_PARAM_TENANT_ID));
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {

	}
}
