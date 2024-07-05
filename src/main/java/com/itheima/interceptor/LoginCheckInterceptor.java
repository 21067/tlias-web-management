package com.itheima.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component//交给IOC容器管理
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override//目标资源方法运行前运行，返回true放行，false不放行
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {

        //1.获取请求url。
        String url = req.getRequestURL().toString();//获取请求的url地址
        log.info("请求的url：{}",url);

        //判断是否为登陆注册
        if(url.contains("login")){
            log.info("登陆操作，放行...");
            return true;
        }
        if (url.contains("register")){
            log.info("注册操作，放行...");
            return true;
        }

        //2.判断请求url中是否包含login，如果包含，说明是登录操作，放行。
        //3.获取请求头中的令牌（token）。
        String jwt = req.getHeader("token");//拿到请求头里边的token信息（jwt令牌）,以字符串的形式存在jwt里
        //4.判断令牌是否存在，如果不存在，返回错误结果（未登录）。
        if(!StringUtils.hasLength(jwt)){//判断是否有令牌，字符长度为空则返回未登录错误
            log.info("请求头token为空，返回未登录信息");//日志输出
            Result error = Result.error("NOT_LOGIN");//创建一个result对象，存错误信息
            //手动将对象转为json返回给前端,此时需要阿里巴巴包
            String notLogin = JSONObject.toJSONString(error);//把error对象转换成json字符串
            resp.getWriter().write(notLogin);//响应对象将结果返回给前端
            return false;
        }//如果不为空则进行jwt令牌解析


        //5.解析token，如果解析失败，返回错误结果（未登录）。
        try {//捕获异常
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析令牌失败，返回未登录的错误信息");

            Result error = Result.error("NOT_LOGIN");//创建一个result对象，存错误信息
            //手动将对象转为json返回给前端,此时需要阿里巴巴包
            String notLogin = JSONObject.toJSONString(error);//把error对象转换成json字符串
            resp.getWriter().write(notLogin);//响应对象将结果返回给前端
            return false;
        }



        //6.放行。
        log.info("令牌合法，放行");
         return true;
    }

    @Override//标资源运行后运行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle...");    }

    @Override//视图渲染完后运行，最后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion...");    }
}
