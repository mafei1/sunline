package cn.test.bookms.request;

import java.util.Date;

/**
 * 图书查询条件
 * @author kai
 * @Description TODO
 * @Email kain.wong@foxmail.com
 * @Bolg  https://blog.csdn.net/qq_28631165
 */
public class BookQuery {
	
	private String bookName;
	private String author;
	private String isbn;
	private Date publishDate1;
	private Date publishDate2;
	
	private Integer page = 1; // 当前页，初始默认第1页
	private Integer limit = 10; // 初始默认每页显示10条数据
	
	
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public Date getPublishDate1() {
		return publishDate1;
	}
	public void setPublishDate1(Date publishDate1) {
		this.publishDate1 = publishDate1;
	}
	public Date getPublishDate2() {
		return publishDate2;
	}
	public void setPublishDate2(Date publishDate2) {
		this.publishDate2 = publishDate2;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	@Override
	public String toString() {
		return "BookQuery [bookName=" + bookName + ", author=" + author + ", isbn=" + isbn + ", publishDate1="
				+ publishDate1 + ", publishDate2=" + publishDate2 + ", page=" + page + ", limit=" + limit + "]";
	}
	
	
}
