package cn.unowen.Service;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.unowen.pojo.User;
import cn.unowen.vo.PageBean;
import cn.unowen.vo.PasswordForm;
import cn.unowen.vo.ResultBean;
import cn.unowen.vo.SearchBookDate;

public interface UserService {
	public ResultBean login(User user, HttpSession session, HttpServletResponse response) throws UnsupportedEncodingException;

	public PageBean getLogByDate(SearchBookDate searchBookDate);
	
	public ResultBean updatePwd(HttpSession session, PasswordForm pwdForm);
	
	
}
