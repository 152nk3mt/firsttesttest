package cn.unowen.Service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.hutool.crypto.SecureUtil;
import cn.unowen.Service.UserService;
import cn.unowen.constant.SystemCon;
import cn.unowen.mapper.UserLogMapper;
import cn.unowen.mapper.UserMapper;
import cn.unowen.pojo.User;
import cn.unowen.pojo.UserLog;
import cn.unowen.vo.PageBean;
import cn.unowen.vo.PasswordForm;
import cn.unowen.vo.ResultBean;
import cn.unowen.vo.ResultBeanUtils;
import cn.unowen.vo.SearchBookDate;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserMapper uMapper;
	@Autowired
	UserLogMapper ulMapper;

	@Override
	public ResultBean login(User user, HttpSession session, HttpServletResponse response)
			throws UnsupportedEncodingException {
		User resultUser = uMapper.selectByName(user.getName());
		String pwdMD5 = SecureUtil.md5(user.getPassword());
		if (resultUser != null) {
			// 先判断用户锁定状态
			if (null != resultUser && resultUser.getLockflag() == -1) {
				// -1账号被锁定
				if (pwdMD5.equals(resultUser.getPassword())
						&& uMapper.lockedTime(user.getName()) >= (SystemCon.lockHour * 3600)) {
					// 解锁
					uMapper.lockOrNo(user.getName(), "1");
					uMapper.setTimesByName(user.getName());
					// 登录成功
					session.setAttribute("user", resultUser);
					response.addCookie(this.addCookie(resultUser.toString()));
					return ResultBeanUtils.setOK("登录成功", "登录成功");
				}
				return ResultBeanUtils.setError(SystemCon.RERROR4, "账号被锁定", null);
			} else {
				String pwd = resultUser.getPassword();
				if (pwdMD5.equals(pwd)) {
					uMapper.setTimesByName(user.getName());
					session.setAttribute("user", resultUser);
					// response.addCookie(this.addCookie(resultUser.toString()));
					return ResultBeanUtils.setOK("登录成功", "登录成功");
				} else {
					// 账号密码不正确
					uMapper.errorCount(user.getName(), 1);
					int times = uMapper.selectByName(user.getName()).getTimes();
					if (times == SystemCon.errorTimes) {
						uMapper.lockNow(user.getName());
					}
					if (times >= SystemCon.errorCount) {
						return ResultBeanUtils.setError(SystemCon.ERROR5, "账号或者密码不正确", null);
					}
					return ResultBeanUtils.setError(SystemCon.RERROR3, "账号或者密码不正确", null);

				}
			}
		} else {
			return ResultBeanUtils.setError(SystemCon.RERROR3, "账号或者密码不正确", null);
		}
	}

	private Cookie addCookie(String content) {
		Cookie cookie = new Cookie("user", content);
		cookie.setPath("/");
		return cookie;
	}

	@Override
	public PageBean getLogByDate(SearchBookDate searchBookDate) {
		List<UserLog> userLogs = ulMapper.selectByDate(searchBookDate.getStartDate().toLocaleString(),
				searchBookDate.getEndDate().toLocaleString(), searchBookDate.getOffset(), searchBookDate.getPageSize());
		int count = ulMapper.selectCountBetween(searchBookDate.getStartDate().toLocaleString(),
				searchBookDate.getEndDate().toLocaleString());
		List<Object> objects = (List) userLogs;
		return ResultBeanUtils.setPageOK(searchBookDate.getPageNum(), searchBookDate.getPageSize(), count, objects);

	}

	@Override
	public ResultBean updatePwd(HttpSession session, PasswordForm pwdForm) {
		User user = (User) session.getAttribute("user");
		if (user.getPassword().equals(SecureUtil.md5(pwdForm.getOldPassword()))) {

			int flag = uMapper.updatePasswordByName(user.getName(), SecureUtil.md5(pwdForm.getNewePassword()));
			if (flag > 0) {
				return ResultBeanUtils.setOK("修改成功", "修改成功");
			}
		}

		return ResultBeanUtils.setError(SystemCon.ERROR6, "修改失败", null);
	}

}
