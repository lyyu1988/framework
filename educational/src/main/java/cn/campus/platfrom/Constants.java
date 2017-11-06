package cn.campus.platfrom;

/**
 * 
 * @author: 	wangLin
 * @date: 		2014-4-8下午04:52:25
 * @comment:	<静态类,定义常用静态变量>
 */
public class Constants {

	public static final class LoginCode{
		public static final boolean FLAG_SUCCESS=true;
		public static final boolean FLAG_FAIL=false;
		public static final String OK="10000";				//正常状态
		public static final String NO_DATA="10001";			//账号不存在
		public static final String PARAM_ERROR="10002";		//登录失败
		public static final String NOT_LOGIN="10003";		//未登录
		public static final String AUTH_CODE_ERROR="10004";		//验证码错误
	}
	public static final class Cookie {
		public static final int FIVE_YEARS=5*365*24*60*60;
	}
	public static final class Page {
		public static final String DEFAULT_PAGE_SIZE="10";
	}

	public static final String SUCCESS="success"; //返回成功
	public static final String ERROR="error"; // 返回失败
	
	public static final int NOT_DELETE=0;
	public static final int IS_DELETE=1;
	
}
