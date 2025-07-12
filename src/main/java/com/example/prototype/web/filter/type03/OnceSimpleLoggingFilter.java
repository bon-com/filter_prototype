package com.example.prototype.web.filter.type03;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 	1リクエストに対して１回だけ処理が実行される
 */
public class OnceSimpleLoggingFilter extends OncePerRequestFilter {

	private static final Logger logger = LoggerFactory.getLogger(OnceSimpleLoggingFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// 処理開始
		long start = System.currentTimeMillis();

		try {
			filterChain.doFilter(request, response);
		} finally {
			// 処理終了
			long duration = System.currentTimeMillis() - start;
			logger.info("Request [{} {}] completed in {} ms with status {}",
					request.getMethod(),
					request.getRequestURI(),
					duration,
					response.getStatus());
		}
	}

}
