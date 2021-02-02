package cn.unowen.Service;

import cn.unowen.pojo.Book;
import cn.unowen.vo.ResultBean;

public interface RegService {
	//判断是否注册过
	public Book selectByBookNumber(String bookNumber);

	//注册
	public ResultBean registBook(Book book);
}
