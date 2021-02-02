package cn.unowen.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.unowen.mapper.UserLogMapper;
import cn.unowen.pojo.User;
import cn.unowen.pojo.UserLog;
import cn.unowen.vo.ResultBean;

@Aspect
@Component
public class UserLogAop {
	@Autowired
	HttpServletRequest request;
	@Autowired
	User user;
	@Autowired
	UserLog ul;
	@Autowired
	UserLogMapper ulMapper;

	@Pointcut("execution(* cn.unowen.controller.UserController.login(..))")
	private void userLoginAspect() {
	};

	@Before(value = "userLoginAspect()")
	public void loginBefore(JoinPoint joinPoint) {
		Object[] obj = joinPoint.getArgs();
		for (Object argItem : obj) {
			if (argItem instanceof User) {
				user = (User) argItem;
				ul.setName(user.getName());
				ul.setIp(request.getRemoteHost());
				break;
			}
		}
	}

	@AfterReturning(value = "userLoginAspect()", returning = "object")
	public void loginAfter(JoinPoint joinPoint, Object object) {
		ResultBean resultBean = (ResultBean) object;
		boolean flag = resultBean.getMsg().equals("登录成功");
		ul.setLoginflag(flag ? "1" : "-1");
		ulMapper.insertSelective(ul);
		
	}
}
