package cn.unowen.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.unowen.pojo.BookLog;

public interface BookLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BookLog record);

    int insertSelective(BookLog record);

    BookLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BookLog record);

    int updateByPrimaryKey(BookLog record);
    
    List<BookLog> selectByDate(@Param("startDate") String startDate, @Param("endDate") String endDate,
			@Param("offset") int offset, @Param("limit") int pageSize);
    
    int selectCountBetween(@Param("startDate") String startDate, @Param("endDate") String endDate);
    
    int selectCount();
}