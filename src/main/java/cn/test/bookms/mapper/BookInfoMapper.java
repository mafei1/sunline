package cn.test.bookms.mapper;

import java.util.List;

import cn.test.bookms.entity.BookInfo;
import cn.test.bookms.request.BookQuery;
import cn.test.bookms.result.BookResp;

public interface BookInfoMapper {
	
    int deleteByPrimaryKey(Integer bid);

    int insert(BookInfo record);

    int insertSelective(BookInfo record);

    BookInfo selectByPrimaryKey(Integer bid);

    int updateByPrimaryKeySelective(BookInfo record);

    int updateByPrimaryKey(BookInfo record);
    
    
    /**
     * 图书分页查询
     */
    List<BookResp> selectByQueryCondition(BookQuery query);
    
    /**
     * 图书分页查询总数
     */
    Integer selectTotalCount(BookQuery query);
    
    
    /**
     * 下架图书
     */
    int downBook(Integer bid);
    
    /**
     * 重新上架图书
     */
    int upAgainBook(Integer bid);
    
    /**
     * 查询所有下架图书
     */
    List<BookResp> selectDownBook();
    
    
}