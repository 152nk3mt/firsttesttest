package cn.unowen.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.unowen.constant.SystemCon;
import cn.unowen.vo.SearchBookDate;

@Aspect
@Component
public class PageAop {
	@Autowired
	HttpServletRequest request;

	@Pointcut("execution(* *WithPage(..))")
	private void param() {
	};

	@Before(value = "param()")
	public void regBefore(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		SearchBookDate pageParam = (SearchBookDate) args[0];
		if (pageParam.getPageSize() < 1) {
			pageParam.setPageSize(SystemCon.pageSize);
		}
		if (pageParam.getPageSize() < 1) {
			pageParam.setPageSize(SystemCon.pageNum);
		}
		pageParam.setOffset((pageParam.getPageNum() - 1) * pageParam.getPageSize());
	}
}
