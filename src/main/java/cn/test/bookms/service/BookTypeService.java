package cn.test.bookms.service;

import java.util.List;

import cn.test.bookms.entity.BookType;

public interface BookTypeService {
	
	 void deleteById(Integer id);
	 
	 void insertBookType(BookType record);
	 
	 BookType selectById(Integer id);
	 
	 void updateBookType(BookType record);
	 
	 List<BookType> selectAll();
	
}
