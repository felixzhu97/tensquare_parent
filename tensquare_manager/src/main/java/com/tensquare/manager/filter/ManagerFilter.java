package com.tensquare.manager.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;

@Component
public class ManagerFilter extends ZuulFilter {

	@Autowired
	private JwtUtil jwtUtil;

	/**
	 * 在请求的pre或者后post执行
	 * @return
	 */
	@Override
	public String filterType() {
		return "pre";
	}

	/**
	 * 多个过滤器的执行顺序，数字越小，表示越先执行
	 * @return
	 */
	@Override
	public int filterOrder() {
		return 0;
	}

	/**
	 * 当前过滤器是否开启true表示开启
	 * @return
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	/**
	 * 过滤器内执行的操作 return 任何Object的值都表示继续执行
	 * setSendZuulResponese(false)表示不再继续执行
	 * @return
	 * @throws ZuulException
	 */
	@Override
	public Object run() throws ZuulException {
		System.out.println("经过后台过滤器了");
		//获得request上下文
		RequestContext requestContext = RequestContext.getCurrentContext();
		//request域
		HttpServletRequest request = requestContext.getRequest();

		if (request.getMethod().equals("OPTIONS")) {
			return null;
		}

		if (request.getRequestURI().indexOf("login")>0) {
			return null;
		}

		//得到头信息
		String header = request.getHeader("Authorization");
		if (!StringUtils.isEmpty(header)) {
			if (header.startsWith("Bearer ")) {
				String token = header.substring(7);
				try {
					Claims claims = jwtUtil.parseJWT(token);
					String roles = (String) claims.get("roles");
					if (roles.equals("admin")) {
						requestContext.addZuulRequestHeader("Authorization",header);
						return null;
					}
				}catch (Exception e) {
					e.printStackTrace();
					requestContext.setSendZuulResponse(false);//终止运行
				}
			}
		}
		requestContext.setSendZuulResponse(false);//终止运行
		requestContext.setResponseStatusCode(403);
		requestContext.setResponseBody("权限不足");
		requestContext.getResponse().setContentType("text/html;charset=utf-8");
		return null;
	}
}
