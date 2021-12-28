package cn.test.bookms.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BookInfo implements Serializable {
    private Integer bid;

    private String bookName;

    private String author;

    private String publishHouse;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date publishDate;

    private String isbn;

    private Double price;

    private String briefIntr;

    private String imgName;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date onDate;

    private Integer onAdminId;

    private Integer typeId;

    private Integer offFlag;

    private static final long serialVersionUID = 1L;

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName == null ? null : bookName.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getPublishHouse() {
        return publishHouse;
    }

    public void setPublishHouse(String publishHouse) {
        this.publishHouse = publishHouse == null ? null : publishHouse.trim();
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn == null ? null : isbn.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getBriefIntr() {
        return briefIntr;
    }

    public void setBriefIntr(String briefIntr) {
        this.briefIntr = briefIntr == null ? null : briefIntr.trim();
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName == null ? null : imgName.trim();
    }

    public Date getOnDate() {
        return onDate;
    }

    public void setOnDate(Date onDate) {
        this.onDate = onDate;
    }

    public Integer getOnAdminId() {
        return onAdminId;
    }

    public void setOnAdminId(Integer onAdminId) {
        this.onAdminId = onAdminId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getOffFlag() {
        return offFlag;
    }

    public void setOffFlag(Integer offFlag) {
        this.offFlag = offFlag;
    }

	@Override
	public String toString() {
		return "BookInfo [bid=" + bid + ", bookName=" + bookName + ", author=" + author + ", publishHouse="
				+ publishHouse + ", publishDate=" + publishDate + ", isbn=" + isbn + ", price=" + price + ", briefIntr="
				+ briefIntr + ", imgName=" + imgName + ", onDate=" + onDate + ", onAdminId=" + onAdminId + ", typeId="
				+ typeId + ", offFlag=" + offFlag + "]";
	}
    
    
}