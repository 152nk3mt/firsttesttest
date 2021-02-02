package cn.unowen.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.unowen.pojo.UserLog;

public interface UserLogMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(UserLog record);

	int insertSelective(UserLog record);

	UserLog selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(UserLog record);

	int updateByPrimaryKey(UserLog record);

	List<UserLog> selectByDate(@Param("startDate") String startDate, @Param("endDate") String endDate,
			@Param("offset") int offset, @Param("limit") int pageSize);

	int selectCountBetween(@Param("startDate") String startDate, @Param("endDate") String endDate);
	int selectCount();

}