package com.nico.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class DispatcherServletConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() { // Config de la app
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() { // Config DispatcherServlet
		return new Class[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() { // Mapeo del DispatcherServlet a las URL
		return new String[] { "/" };
	}

}
