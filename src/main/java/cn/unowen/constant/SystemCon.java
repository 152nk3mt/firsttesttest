package cn.unowen.constant;

public class SystemCon {
	// 返回码
	public static final int ROK = 100000;
	// booknumber已注册
	public static final int RERROR1 = 100001;
	// 未注册但注册失败
	public static final int RERROR2 = 100002;
	// 账号或密码不正确
	public static final int RERROR3 = 100003;
	// 账号被锁定
	public static final int RERROR4 = 100004;
	// 生成验证码
	public static final int ERROR5 = 100005;
	// 修改密码失败
	public static final int ERROR6 = 100006;
	// 请求方式
	public static final String GET = "GET";
	public static final String POST = "POST";

	// 密钥
	public static final String TOKENKEY = "lanyuan_20181716";
	
	//cookie秘钥
	public static final String COOKIETOKENKEY = "CHANGYUAN";

	// 锁定时间
	public static final int lockHour = 5;

	// 错误次数==》验证码
	public static final int errorCount = 2;

	// 错误次数==》锁定
	public static final int errorTimes = 5;

	// 页面条数
	public static final int pageSize = 5;

	// 页面数
	public static final int pageNum = 1;

}
