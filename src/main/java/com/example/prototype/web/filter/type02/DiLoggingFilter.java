package com.example.prototype.web.filter.type02;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.GenericFilterBean;

import com.example.prototype.web.dto.Cart;

/**
 * サーブレットフィルタ内でDIコンテナが管理しているBeanを参照する
 */
public class DiLoggingFilter extends GenericFilterBean {

	@Autowired
	private Cart cart;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO 自動生成されたメソッド・スタブ
		
		HttpServletRequest req = (HttpServletRequest) request;
		// 一旦コメントアウト
//		logger.info("GenericFilterBeanを利用したログ出力\n⇒ リクエストURI: " + req.getRequestURI());
//		logger.info("\n⇒ カートの中身: " + cart);

		chain.doFilter(request, response); // 次のフィルター or Controllerへ
	}

}
