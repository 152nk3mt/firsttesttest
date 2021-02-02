package cn.unowen.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.unowen.pojo.Book;

public interface BookMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Book record);

	int insertSelective(Book record);

	Book selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Book record);

	int updateByPrimaryKey(Book record);

	Book selectBybookNumber(String booknumber);

	List<Book> selectByDate(@Param("startDate") String startDate, @Param("endDate") String endDate,
			@Param("offset") int offset, @Param("limit") int pageSize);

	int selectCountBetween(@Param("startDate") String startDate, @Param("endDate") String endDate);

	int selectCount();
}