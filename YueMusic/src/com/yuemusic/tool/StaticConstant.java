package com.yuemusic.tool;

public class StaticConstant {

	/**
	 * 异常代码
	 */
	public static final String REQUEST_EXCEPTION = "100";// 请求异常，参数错误
	public static final String REQUEST_CONTENT_NONE = "101";// 请求返回的内容为空
	public static final String REQUEST_PARAM_ERROR = "102";// 传值错误
	public static final String LOGIN_ERROR_PERMISSION = "200";// 登录权限错误
	public static final String LOGIN_ERROR_ACCOUNT = "201";// 登陆失败-账户错误
	public static final String LOGIN_REMOVE_ACCOUNT = "202";// 登陆失败-账户被封号
	public static final String LOGIN_SUCCESS_COMMONUSER = "203";// 普通用户登陆成功
	public static final String LOGIN_SUCCESS_DOCTOR = "204";// 医生用户登陆成功
	public static final String EXPIRED = "205";// 验证类的过期
	public static final String REQUEST_COUNT_FULLED = "105";// 请求次数已满
	public static final String PUSH_ERROR = "405";// 推送失败
	public static final String PUSH_REPEAT = "406";// 重复推送

	/**
	 * 歌曲路径
	 */
	public static final String PATH = "D:/Program Files/MyEclipse Professional 2014/xworkspace/YueMusic/WebRoot/";
	

}
