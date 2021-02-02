package cn.unowen.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.unowen.Service.SearchRegisteredBooksService;
import cn.unowen.vo.PageBean;
import cn.unowen.vo.SearchBookDate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "图书类信息查询")
@CrossOrigin(allowCredentials = "true")
public class SearchRegisteredBooksController {
	@Autowired
	SearchRegisteredBooksService searchRegisteredBooksService;
	@Value("${webUrlPath}")
	private String webUrl;
	@ApiOperation(value = "通过16位图书编号查询", responseContainer = "ResultBean.class")
	@ApiImplicitParams({ @ApiImplicitParam(name = "bookNumber", value = "图书编号", required = true, paramType = "query") })
	@PostMapping("/searchRegBook/byNum")
	public PageBean regedBookByNum(String bookNumber) {
		return searchRegisteredBooksService.selectByBookNumber(bookNumber);
	}

	@ApiOperation(value = "根据注册开始时间、注册结束时间查询图书明细以及图书数量", notes = "通过开始日期和结束日期,返回一个带有分页信息的列表，pageCount代表总数量", responseContainer = "PageBean.class")
	@ApiImplicitParams({ @ApiImplicitParam(name = "startDate", value = "开始时间", required = true, paramType = "query"),
			@ApiImplicitParam(name = "endDate", value = "结束时间", required = true),
			@ApiImplicitParam(name = "pageNum", value = "需要查询的页码", required = false),
			@ApiImplicitParam(name = "pageSize", value = "每页展示的数量", required = false) })
	@PostMapping("/searchRegBook/byDate")
	public PageBean regedBookByDateWithPage(@Valid SearchBookDate searchBookDate) {
		return searchRegisteredBooksService.selectByDate(searchBookDate);
	}

	@ApiOperation(value = "根据注册开始时间、注册结束时间查询注册日志信息", notes = "通过开始日期和结束日期,返回一个带有分页信息的列表，pageCount代表总数量", responseContainer = "PageBean.class")
	@ApiImplicitParams({ @ApiImplicitParam(name = "startDate", value = "开始时间", required = true, paramType = "query"),
			@ApiImplicitParam(name = "endDate", value = "结束时间", required = true),
			@ApiImplicitParam(name = "pageNum", value = "需要查询的页码", required = false),
			@ApiImplicitParam(name = "pageSize", value = "每页展示的数量", required = false) })
	@PostMapping("/searchRegBookLog/byDate")
	public PageBean regedBookLogByDateWithPage(@Valid SearchBookDate searchBookDate) {
		return searchRegisteredBooksService.selectLogByDate(searchBookDate);
	}

}
