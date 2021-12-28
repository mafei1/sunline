package cn.test.bookms.mapper;

import java.util.List;

import cn.test.bookms.entity.BookType;

public interface BookTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BookType record);

    int insertSelective(BookType record);

    BookType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BookType record);

    int updateByPrimaryKey(BookType record);
    
    /**
     * 查询所有的图书类型
     * @return
     */
    List<BookType> selectAll();
}