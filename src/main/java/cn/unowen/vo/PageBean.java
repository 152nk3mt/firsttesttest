package cn.unowen.vo;

import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Scope("prototype")
@Component
public class PageBean {
	private int code;
	private String msg;
	private int totalpage;// 总页数
	private int totalcount;// 总条数
	private int currpage;// 当前页码
	private int limit;// 每页显示的数量
	private List<Object> data;// 数据源
}
