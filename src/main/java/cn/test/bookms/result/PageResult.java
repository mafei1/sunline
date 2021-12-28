package cn.test.bookms.result;

/**
 * 统一分页格式
 * @author kai
 * @param <T>
 * @Description TODO
 * @Email kain.wong@foxmail.com
 * @Bolg  https://blog.csdn.net/qq_28631165
 */
public class PageResult<T> extends Result<T>{

	/**
	 * 当前页
	 */
	private Integer page;
	/**
	 * 每页显示条数
	 */
	private Integer limit;
	/**
	 * 总数据条数
	 */
	private Integer count;
	/**
	 * 总页数
	 */
	private Integer pageCount;
	
	
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
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	
	
	
	
}
