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
		public static final String PARAM_ERROR="10001";		//登录失败
		public static final String NOT_LOGIN="10002";		//未登录
		public static final String AUTH_CODE_ERROR="10003";		//验证码错误
	}

	public static final class Page {
		public static final String DEFAULT_PAGE_SIZE="10";
	}

	public static final String SUCCESS="success"; //返回成功
	public static final String ERROR="error"; // 返回失败
	
	public static final int NOT_DELETE=0;
	public static final int IS_DELETE=1;

	public static final class Api{
		public static final String SIGN="sign";
		public static final String APP_ID="appId";
		public static final String TIMESTAMP="timestamp";
		public static final long TIMEOUT_MINUTES=10;
	}

	public static final class ErrorCode{
		public static final String SUCCESS="0";
		public static final String SIGN_ERROR="40002";
		public static final String APP_ID_ERROR="40003";
		public static final String PARAM_ERROR="40035";
		public static final String PARAM_TIMEOUT_ERROR="42004";
	}

	public static final class Account{
		public static final int LOCKED=1;
		public static final int NOT_LOCKED=0;
		public static final int ERROR_COUNT_LOCK=5;
	}

	public static final class TabParam{
		public static final String TAB_MENU_ID="tabMenuId";
	}
}
