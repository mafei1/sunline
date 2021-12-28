package cn.test.bookms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.test.bookms.entity.BookType;
import cn.test.bookms.mapper.BookTypeMapper;
import cn.test.bookms.service.BookTypeService;

@Service
public class BookTypeServiceImpl implements BookTypeService {

	@Autowired
	private BookTypeMapper typeMapper;
	
	public void deleteById(Integer id) {
		typeMapper.deleteByPrimaryKey(id);
	}

	public void insertBookType(BookType record) {
		typeMapper.insertSelective(record);
	}

	public BookType selectById(Integer id) {
		return typeMapper.selectByPrimaryKey(id);
	}

	public void updateBookType(BookType record) {
		typeMapper.updateByPrimaryKeySelective(record);
	}

	public List<BookType> selectAll() {
		return typeMapper.selectAll();
	}

}
