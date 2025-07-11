package com.example.prototype.web.filter.type03;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 	1リクエストに対して１回だけ処理が実行される
 */
public class OnceSimpleLoggingFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO 自動生成されたメソッド・スタブ
		
		
        HttpServletRequest req = (HttpServletRequest) request;
        String path = req.getRequestURI();
        // 一旦コメントアウト
        logger.info("OncePerRequestFilterを利用したログ出力 ⇒ リクエストURI: " + path);

        filterChain.doFilter(request, response); // 次のフィルター or Controllerへ
	}


}
