package cn.test.bookms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.test.bookms.config.ConfigUtil;
import cn.test.bookms.entity.BookType;
import cn.test.bookms.result.Result;
import cn.test.bookms.service.BookTypeService;

@RestController
@RequestMapping("/type")
public class TypeController {

	
	@Autowired
	private BookTypeService typeService;
	
	
	/**
	 * 图书类型列表
	 * @return
	 */
	@RequestMapping(value = "/list")
	public Result<List<BookType>> selectAllType() {
		Result<List<BookType>> result = new Result<List<BookType>>();
		try {
			List<BookType> list = typeService.selectAll();
			if (list.isEmpty()) {
				result.setCode(ConfigUtil.FAILED_CODE);
				result.setMsg("未找到任何数据");
			}
			result.setData(list);
		} catch (Exception e) {
			result.setCode(ConfigUtil.ERROR_CODE);
			result.setMsg(ConfigUtil.ERROR_MSG);
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 添加图书类型
	 * 
	 * @return
	 */
	@RequestMapping(value = "/add")
	public Result<String> addType(String tname) {
		Result<String> result = new Result<String>();
		try {
			BookType t = new BookType();
			t.setTname(tname);
			typeService.insertBookType(t);
		} catch (Exception e) {
			result.setCode(ConfigUtil.ERROR_CODE);
			result.setMsg(ConfigUtil.ERROR_MSG);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 修改类别信息
	 * @param id
	 * @param tname
	 * @return
	 */
	@RequestMapping(value = "/update")
	public Result<String> updateType(Integer id,String tname){
		Result<String> result = new Result<String>();
		try {
			BookType t = new BookType();
			t.setId(id);
			t.setTname(tname);
			typeService.updateBookType(t);
			result.setData("修改成功");
		} catch (Exception e) {
			result.setCode(ConfigUtil.ERROR_CODE);
			result.setMsg(ConfigUtil.ERROR_MSG);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 删除类别信息
	 * @param id
	 * @param tname
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public Result<String> deleteType(Integer id){
		Result<String> result = new Result<String>();
		try {
			typeService.deleteById(id);
		} catch (Exception e) {
			result.setCode(ConfigUtil.ERROR_CODE);
			result.setMsg(ConfigUtil.ERROR_MSG);
			e.printStackTrace();
		}
		return result;
	}
	
	
}
