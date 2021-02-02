package cn.unowen.Service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.unowen.Service.SearchRegisteredBooksService;
import cn.unowen.mapper.BookLogMapper;
import cn.unowen.mapper.BookMapper;
import cn.unowen.pojo.Book;
import cn.unowen.pojo.BookLog;
import cn.unowen.vo.PageBean;
import cn.unowen.vo.ResultBeanUtils;
import cn.unowen.vo.SearchBookDate;

@Service
public class SearchRegisteredBooksServiceImpl implements SearchRegisteredBooksService {
	@Autowired
	BookMapper bkmapper;
	@Autowired
	BookLogMapper blMapper;

	@Override
	public PageBean selectByBookNumber(String bookNumber) {
		List<Object> books = new ArrayList<>();
		books.add(bkmapper.selectBybookNumber(bookNumber));
		return ResultBeanUtils.setPageOK(1, 1, 1, books);
		// return ResultBeanUtils.setOK("查询成功",
		// bkmapper.selectBybookNumber(bookNumber));
	}

	@Override
	public PageBean selectByDate(SearchBookDate searchBookDate) {
		List<Book> booklist = bkmapper.selectByDate(searchBookDate.getStartDate().toLocaleString(),
				searchBookDate.getEndDate().toLocaleString(), searchBookDate.getOffset(), searchBookDate.getPageSize());
		int count = bkmapper.selectCountBetween(searchBookDate.getStartDate().toLocaleString(),
				searchBookDate.getEndDate().toLocaleString());
		List<Object> objects = (List) booklist;
		return ResultBeanUtils.setPageOK(searchBookDate.getPageNum(), searchBookDate.getPageSize(), count, objects);
	}

	@Override
	public PageBean selectLogByDate(SearchBookDate searchBookDate) {
		List<BookLog> booklist = blMapper.selectByDate(searchBookDate.getStartDate().toLocaleString(),
				searchBookDate.getEndDate().toLocaleString(), searchBookDate.getOffset(), searchBookDate.getPageSize());
		int count = blMapper.selectCountBetween(searchBookDate.getStartDate().toLocaleString(),
				searchBookDate.getEndDate().toLocaleString());
		List<Object> objects = (List) booklist;
		return ResultBeanUtils.setPageOK(searchBookDate.getPageNum(), searchBookDate.getPageSize(), count, objects);
	}
}
