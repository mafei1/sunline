package cn.test.bookms.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.test.bookms.config.ConfigUtil;
import cn.test.bookms.entity.AdminInfo;
import cn.test.bookms.entity.BookInfo;
import cn.test.bookms.request.BookQuery;
import cn.test.bookms.result.BookResp;
import cn.test.bookms.result.PageResult;
import cn.test.bookms.result.Result;
import cn.test.bookms.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private BookService bookService;

	@RequestMapping(value="/query")
	public PageResult<List<BookResp>> selectByQueryCondition(BookQuery query,String date1,String date2) {
		int page = query.getPage();
		PageResult<List<BookResp>> result = new PageResult<List<BookResp>>();
		try {
			if(date1!=null&&!"".equals(date1)) {
				query.setPublishDate1(ConfigUtil.sdf.parse(date1));
			}
			if(date2!=null&&!"".equals(date2)) {
				query.setPublishDate2(ConfigUtil.sdf.parse(date2));
			}
			System.out.println(query+", "+date1+", "+date2);
			result = bookService.selectByQueryCondition(query);
			result.setPage(page);
			if(result.getData() == null) {
				result.setCode(ConfigUtil.FAILED_CODE);
				result.setMsg("未找到任何数据");
			}
		} catch (Exception e) {
			result.setCode(ConfigUtil.ERROR_CODE);
			result.setMsg(ConfigUtil.ERROR_MSG);
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 
	 * @Description: 图书封面上传
	 * @return   图书封面文件名
	 */
	@RequestMapping(value = "/imageName")
	public Result<String> selectImgName(MultipartFile file) {
		Result<String> result = new Result<String>();
		try {
			String newFileName = ConfigUtil.fileOperate(file, ConfigUtil.IMG_LOCAL_PATH);
			result.setData(newFileName);
		} catch (Exception e) {
			result.setCode(ConfigUtil.ERROR_CODE);
			result.setMsg(ConfigUtil.ERROR_MSG);
			e.printStackTrace();
		}
		return result;
	}

	
	/**
	 * 添加图书
	 * @Description: 
	 * @param book
	 * @param publishDate1
	 * @return
	 */
	@RequestMapping(value = "/add")
	public Result<String> addBook(BookInfo book,String publishDate1,HttpSession session) {
		Result<String> result = new Result<String>();
		try {
		    AdminInfo admin = (AdminInfo) session.getAttribute("admin");
		    int aid = 0;
			if(admin!=null) {
				aid = admin.getAid();
			}else {
				result.setCode(ConfigUtil.FAILED_CODE);
				result.setMsg("登录失效，请重新登录");
				result.setData(null);
				return result;
			}
			book.setOnDate(new Date()); //上架时间为当前时间
			book.setOnAdminId(aid);
			book.setOffFlag(0); 
			book.setPublishDate(ConfigUtil.sdf.parse(publishDate1));
			bookService.insertSelective(book);  //调用service层插入数据
			result.setData("添加成功");
		} catch (Exception e) {
			result.setCode(ConfigUtil.ERROR_CODE);
			result.setMsg(ConfigUtil.ERROR_MSG);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 下架图书
	 * @Description: 
	 * @param bid
	 * @return
	 */
	@RequestMapping(value = "/down")
	public Result<String> downBook(Integer bid) {
		Result<String> result = new Result<String>();
		try {
			bookService.downBook(bid);
			result.setData("下架成功");
		} catch (Exception e) {
			result.setCode(ConfigUtil.ERROR_CODE);
			result.setMsg(ConfigUtil.ERROR_MSG);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 下架图书列表
	 * @Description: 
	 * @return
	 */
	@RequestMapping(value="/downList")
	public Result<List<BookResp>> selectDownList(){
		Result<List<BookResp>> result = new Result<List<BookResp>>();
		try {
			List<BookResp> list = bookService.selectDownBook();
			result.setData(list);
		} catch (Exception e) {
			result.setCode(ConfigUtil.ERROR_CODE);
			result.setMsg(ConfigUtil.ERROR_MSG);
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 重新上架
	 * @Description: 
	 * @param bid
	 * @return
	 */
	@RequestMapping(value = "/up")
	public Result<String> upBook(Integer bid) {
		Result<String> result = new Result<String>();
		try {
			bookService.upAgainBook(bid);
			result.setData("上架成功");
		} catch (Exception e) {
			result.setCode(ConfigUtil.ERROR_CODE);
			result.setMsg(ConfigUtil.ERROR_MSG);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 通过id查询书籍信息
	 * @Description: 
	 * @param bid
	 * @return
	 */
	@RequestMapping(value="/one")
	public Result<BookInfo> selectById(Integer bid){
		Result<BookInfo> result = new Result<BookInfo>();
		try {
			BookInfo b = bookService.selectByPrimaryKey(bid);
			if(b==null) {
				result.setCode(ConfigUtil.FAILED_CODE);
				result.setMsg("未找到该资源");
			}
			result.setData(b);
		} catch (Exception e) {
			result.setCode(ConfigUtil.ERROR_CODE);
			result.setMsg(ConfigUtil.ERROR_MSG);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 修改图书
	 * @Description: 
	 * @param book
	 * @param publishDate1
	 * @return
	 */
	@RequestMapping(value = "/update")
	public Result<String> updateBook(BookInfo book,String publishDate1) {
		Result<String> result = new Result<String>();
		try {
			book.setPublishDate(ConfigUtil.sdf.parse(publishDate1));
			book.setImgName(book.getImgName().substring(book.getImgName().lastIndexOf("/")+1));
			System.out.println("修改成功： "+book);
			bookService.updateBook(book);
			result.setData("修改成功");
		} catch (Exception e) {
			result.setCode(ConfigUtil.ERROR_CODE);
			result.setMsg(ConfigUtil.ERROR_MSG);
			e.printStackTrace();
		}
		return result;
	}
}
