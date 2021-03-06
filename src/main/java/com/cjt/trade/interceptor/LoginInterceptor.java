package com.cjt.trade.interceptor;

import com.cjt.trade.constant.GlobalConfig;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author caojiantao
 */
public class LoginInterceptor implements HandlerInterceptor {

  /**
   * 请求处理之前调用，返回false代表请求被拦截，不会向下执行
   */
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
          throws Exception {
    // 登陆判断逻辑
    Object loginSession = request.getSession().getAttribute(GlobalConfig.ACCOUNT_SESSION);
    if (loginSession == null) {
      response.sendRedirect("/backend/login.action");
      return false;
    }
    return true;
  }

  /**
   * 请求处理后，且视图渲染前调用，用于modelAndView的操作
   */
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                         ModelAndView modelAndView) throws Exception {

  }

  /**
   * 请求处理后，且视图渲染已经完成，用于资源清理工作
   */
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
          throws Exception {

  }
}
