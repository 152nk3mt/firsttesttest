package cn.unowen.Service;

import cn.unowen.vo.PageBean;
import cn.unowen.vo.SearchBookDate;

public interface SearchRegisteredBooksService {
	PageBean selectByBookNumber(String bookNumber);

	PageBean selectByDate(SearchBookDate searchBookDate);

	PageBean selectLogByDate(SearchBookDate searchBookDate);
}
