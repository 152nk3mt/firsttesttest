package cn.unowen.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.unowen.Service.UserService;
import cn.unowen.pojo.User;
import cn.unowen.vo.PageBean;
import cn.unowen.vo.PasswordForm;
import cn.unowen.vo.ResultBean;
import cn.unowen.vo.SearchBookDate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/api/v1/user")
@Api(tags = "用户类信息查询")
@CrossOrigin(allowCredentials = "true")
public class UserController {
	@Autowired
	UserService uservice;
	@Value("${webUrlPath}")
	private String webUrl;
	@PostMapping("/login")
	@ApiOperation(value = "使用用户名密码登录", responseContainer = "ResultBean.class")
	@ApiImplicitParams({ @ApiImplicitParam(name = "name", value = "用户名", required = true),
			@ApiImplicitParam(name = "password", value = "密码", required = true), })
	public @ResponseBody ResultBean login(User user, HttpSession session, HttpServletResponse response) throws IOException {
		return uservice.login(user, session, response);
		
	}

	@ApiOperation(value = "根据注册开始时间、注册结束时间查看用户的登录日志信息", notes = "通过开始日期和结束日期,返回一个带有分页信息的列表，pageCount代表总数量", responseContainer = "PageBean.class")
	@ApiImplicitParams({ @ApiImplicitParam(name = "startDate", value = "开始时间", required = true, paramType = "query"),
			@ApiImplicitParam(name = "endDate", value = "结束时间", required = true),
			@ApiImplicitParam(name = "pageNum", value = "需要查询的页码", required = false),
			@ApiImplicitParam(name = "pageSize", value = "每页展示的数量", required = false) })
	@PostMapping("/searchUserLog/byDate")
	public @ResponseBody PageBean userLogByDateWithPage(@Valid SearchBookDate searchBookDate) {

		return uservice.getLogByDate(searchBookDate);
	}

	@ApiOperation(value = "根据旧密码修改新密码", notes = "根据用户名修改，用户名从session获取，无需前端传递", responseContainer = "ResultBean.class")
	@ApiImplicitParams({ @ApiImplicitParam(name = "oldPassword", value = "旧密码", required = true, paramType = "query"),
			@ApiImplicitParam(name = "newePassword", value = "新密码", required = true), })
	@PostMapping("/updatePassword")
	public @ResponseBody ResultBean updatePWD(@Valid PasswordForm pwdForm, HttpSession session) {
		return uservice.updatePwd(session, pwdForm);

	}
	
	@GetMapping("/logout")
	public void out(HttpSession session,HttpServletResponse response) {
		session.removeAttribute("user");
		try {
			response.sendRedirect(webUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

	
}
