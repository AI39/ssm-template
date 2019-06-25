package com.moxiongfei.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * Created by little Stone
 * Date 2019/6/24 Time 17:07
 * AACDSI
 */
/*替代了web.xml*/
public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	/*加载Spring容器的配置类*/
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[]{SpringConfig.class};
	}

	/*加载springmvc容器的配置类*/
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[]{MvcConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}

	/*解决中文乱码问题*/
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("utf-8");
		characterEncodingFilter.setForceEncoding(true);
		return new Filter[]{characterEncodingFilter};
	}
}
