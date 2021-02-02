package cn.unowen.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.unowen.mapper.BookLogMapper;
import cn.unowen.pojo.Book;
import cn.unowen.pojo.BookLog;
import cn.unowen.vo.ResultBean;

@Aspect
@Component
public class BookRegLog {
	@Autowired
	HttpServletRequest request;
	@Autowired
	BookLogMapper blMapper;
	@Autowired
	BookLog bl;
	@Autowired
	Book book;

	@Pointcut("execution(* cn.unowen.bookReg.BookRegController.registBook(..))")
	private void registBookAspect() {
	};

	@Before(value = "registBookAspect()")
	public void regBefore(JoinPoint joinPoint) {
		String ip = request.getRemoteHost();
		Object[] obj = joinPoint.getArgs();
		for (Object argItem : obj) {
			if (argItem instanceof Book) {
				book = (Book) argItem;
				bl.setBooknumber(book.getBooknumber());
				bl.setIp(ip);
				bl.setIdcode(book.getIdcode());
				break;
			}
		}

	}

	@AfterReturning(value = "registBookAspect()", returning = "object")
	public void regAfter(JoinPoint joinPoint, Object object) {
		ResultBean resultBean = (ResultBean) object;
		bl.setRegflag(resultBean.getMsg().equals("注册成功") ? 1 : -1);
		blMapper.insertSelective(bl);

	}
}
