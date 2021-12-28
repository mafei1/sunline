package cn.test.bookms.service;

import java.util.List;

import cn.test.bookms.entity.BookInfo;
import cn.test.bookms.request.BookQuery;
import cn.test.bookms.result.BookResp;
import cn.test.bookms.result.PageResult;

public interface BookService {
	
	/**
	 * 新增图书
	 * @Description: 
	 * @param record
	 */
	void insertSelective(BookInfo record);
	
	/**
	 * 修改图书信息
	 * @Description: 
	 * @param record
	 */
	void updateBook(BookInfo record);
	
	/**
	 * 通过id查询图书
	 * @Description: 
	 * @param bid
	 * @return
	 */
	BookInfo selectByPrimaryKey(Integer bid);
	
	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	PageResult<List<BookResp>> selectByQueryCondition(BookQuery query);
	
	 /**
     * 下架图书
     */
    void downBook(Integer bid);
    
    /**
     * 重新上架图书
     */
    void upAgainBook(Integer bid);
    
    /**
     * 查询所有下架图书
     */
    List<BookResp> selectDownBook();
    
}
