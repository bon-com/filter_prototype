package com.example.prototype.web.filter.type01;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.filter.GenericFilterBean;

public class LoggingFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO 自動生成されたメソッド・スタブ
		
        HttpServletRequest req = (HttpServletRequest) request;
        String path = req.getRequestURI();

        logger.info("GenericFilterBeanを利用したログ出力 ⇒ リクエストURI: " + path);

        chain.doFilter(request, response); // 次のフィルター or Controllerへ
	}

}
