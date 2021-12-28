package cn.test.bookms.config;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

/**
 * 总配置文件
 * @author kai
 * @Description TODO
 * @Email kain.wong@foxmail.com
 * @Bolg  https://blog.csdn.net/qq_28631165
 */
public class ConfigUtil {
	
	/**
	 * 状态码：0
	 * 消息请求成功状态码，且得到正确响应结果
	 */
	public static final Integer SUCCESS_CODE = 0;
	public static final String SUCCESS_MSG = "ok";

	/**
	 * 状态码：1
	 * 消息请求成功，但未得到正确结果，或资源被禁止访问
	 */
	public static final Integer FAILED_CODE = 1;
	public static final String FAILED_MSG = "资源未找到";

	
	/**
	 * 状态码：5
	 * 消息请求失败，服务器运行时出现异常
	 */
	public static final Integer ERROR_CODE = 5;
	public static final String ERROR_MSG = "服务器内部错误";
	
	
	/**
	 * 图书实际上传的目录
	 */
	public static final String IMG_LOCAL_PATH = "D://images";
	
	/**
	 * 图书uri
	 */
	public static final String BOOKIMAGE_URI = "http://localhost:8080/images/";
	
	
	/**
	 * 时间格式化
	 */
	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	
	/**
	 * 获取上次后的新的文件名
	 * @param file 封装操作文件方法
	 * @param filePath  文件上传的本地的实际地址
	 * @return  新的文件名称，用来存入数据库
	 */
	public static String fileOperate(MultipartFile file,String filePath) {
		String originalFileName = file.getOriginalFilename();//获取原始图片的扩展名
		String newFileName = UUID.randomUUID()+originalFileName;  //新的文件名称
		File targetFile = new File(filePath,newFileName); //创建新文件
		try {
			//把本地文件上传到文件位置 , transferTo()是springmvc封装的方法，用于图片上传时，把内存中图片写入磁盘
			file.transferTo(targetFile); 
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}   
		return newFileName;
	}
	
}
