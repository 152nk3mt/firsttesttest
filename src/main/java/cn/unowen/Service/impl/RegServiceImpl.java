package cn.unowen.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hutool.crypto.SecureUtil;
import cn.unowen.Service.RegService;
import cn.unowen.constant.SystemCon;
import cn.unowen.mapper.BookMapper;
import cn.unowen.pojo.Book;
import cn.unowen.utils.StrFilter;
import cn.unowen.vo.ResultBean;
import cn.unowen.vo.ResultBeanUtils;

@Service
@Transactional
public class RegServiceImpl implements RegService {
	@Autowired
	BookMapper bookmapper;
	@Autowired
	Book book;

	@Override
	public Book selectByBookNumber(String bookNumber) {
		return bookmapper.selectBybookNumber(bookNumber);
	}

	@Override
	public ResultBean registBook(Book book) {
		if (null == selectByBookNumber(book.getBooknumber())) {
			String md5 = SecureUtil.md5(book.getIdcode() + SystemCon.TOKENKEY + book.getBooknumber());
			String code = StrFilter.NotLetterOrNumber(md5).toUpperCase().replaceAll("O", "").replaceAll("I", "")
					.substring(0, 16);
			// 将注册码放进mysql
			return bookmapper.insertSelective(book) > 0 ? ResultBeanUtils.setOK("注册成功", code)
					: ResultBeanUtils.setError(SystemCon.RERROR2, "注册失败", null);
		} else {
			return ResultBeanUtils.setError(SystemCon.RERROR1, "已注册", null);
		}
	}

}
