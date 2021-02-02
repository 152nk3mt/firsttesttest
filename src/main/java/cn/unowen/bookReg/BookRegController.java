package cn.unowen.bookReg;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.unowen.Service.RegService;
import cn.unowen.pojo.Book;
import cn.unowen.vo.ResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "图书注册")
@CrossOrigin(allowCredentials = "true")
public class BookRegController {
	@Autowired
	RegService regService;
	@Value("${webUrlPath}")
	private String webUrl;
	@ApiOperation(value = "图书注册", notes = "通过识别码和图书编号注册,返回一个带有分页信息的列表", responseContainer = "ResultBean.class")
	@GetMapping("/reg")
	@ApiImplicitParams({ @ApiImplicitParam(name = "idcode", value = "识别码", required = true, paramType = "query"),
			@ApiImplicitParam(name = "booknumber", value = "图书编号", required = true, paramType = "query") })
	public ResultBean registBook(@Valid Book book,HttpServletRequest request) {
		book.setIp(request.getRemoteHost());
		return regService.registBook(book);

	}

}
