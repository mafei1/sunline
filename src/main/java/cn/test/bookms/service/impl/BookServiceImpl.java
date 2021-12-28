package cn.test.bookms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.test.bookms.config.ConfigUtil;
import cn.test.bookms.entity.BookInfo;
import cn.test.bookms.mapper.BookInfoMapper;
import cn.test.bookms.request.BookQuery;
import cn.test.bookms.result.BookResp;
import cn.test.bookms.result.PageResult;
import cn.test.bookms.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookInfoMapper bookMapper;
	
	public PageResult<List<BookResp>> selectByQueryCondition(BookQuery query) {
		query.setPage((query.getPage() - 1) * query.getLimit());
		List<BookResp> bookList = bookMapper.selectByQueryCondition(query);
		for(BookResp book: bookList) {
			if (book.getBriefIntr().length() >= 60) {
				book.setBriefIntr(book.getBriefIntr().substring(0, 60)); // 缩短图书简介
			}
			book.setImgName(ConfigUtil.BOOKIMAGE_URI + book.getImgName()); //设置图书封面路径
		}
		int totalCount = bookMapper.selectTotalCount(query); // 获取查询书籍的总条数
		PageResult<List<BookResp>> result = new PageResult<List<BookResp>>();
		result.setLimit(query.getLimit());
		result.setCount(totalCount);
		Double pageSize = Math.ceil(((double) totalCount / query.getLimit())); // 总页数
		result.setPageCount(pageSize.intValue());
		result.setData(bookList);
		return result;
	}

	public void downBook(Integer bid) {
		bookMapper.downBook(bid);
	}

	public void upAgainBook(Integer bid) {
		bookMapper.upAgainBook(bid);
	}


	public List<BookResp> selectDownBook() {
		return bookMapper.selectDownBook();
	}

	public void insertSelective(BookInfo record) {
		bookMapper.insertSelective(record);
	}

	public void updateBook(BookInfo record) {
		bookMapper.updateByPrimaryKeySelective(record);
	}
	
	
	public BookInfo selectByPrimaryKey(Integer bid) {
		BookInfo book = bookMapper.selectByPrimaryKey(bid);
		book.setImgName(ConfigUtil.BOOKIMAGE_URI+book.getImgName());
		return book;
	}

	
	
	
	
	
	
}
