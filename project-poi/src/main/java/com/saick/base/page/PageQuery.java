package com.saick.base.page;

import java.io.Serializable;
import java.util.List;


/**
 * 分页查询参数类，支持泛型结果集实体参数
 * @param <T>
 */
public class PageQuery<T> implements Serializable{
    private static final long serialVersionUID = -5268398935222873541L;
    
    @SuppressWarnings("unused")
    private static final int DEFAULT_DISPLAYPAGESIZE= 5;// 每页分页页码个数
    @SuppressWarnings("unused")
    private static final String PageQueryClassName = "pageQuery";
    
    private static final int DEFAULT_PAGESIZE = 10;// 默认每页显示记录数

    private int pageSize = DEFAULT_PAGESIZE;// 每页显示记录数
    
    private int totalRecordCount;// 总记录数
    private int currentPageNum;// 当前页码
    
    private int totalPageCount;// 总页数
    private int startPageIndex = 0;// 分页开始角标
    
    private List<T> recordList;//分页显示结果集List，里面是一个ResultInfo对象，在ResultInfo中封装结果和其他相关参数即可
    //分页显示参数计算，方便动态分页显示
    private int startPageNum;// 分页显示开始页码,可以使用currentPageNum替代
    private int endPageNum;// 分页显示结束页码
    
    /**
     * 根据当前页码currentPageNum，每页显示记录数pageSize和总记录条数totalRecordCount，
     * 计算得出总页数totalPageCount和开始页码角标startPageIndex
     */
    public PageQuery(int currentPageNum, int totalRecordCount) {
        this.currentPageNum = currentPageNum;
        this.totalRecordCount = totalRecordCount;
        totalPageCount = totalRecordCount % pageSize == 0 ? totalRecordCount / pageSize: totalRecordCount / pageSize + 1;
        startPageIndex = (currentPageNum - 1) * pageSize;
    }
    
    public PageQuery(int currentPageNum, int totalRecordCount,int pageSize) {
        this.pageSize = pageSize;
        this.currentPageNum = currentPageNum;
        this.totalRecordCount = totalRecordCount;
        totalPageCount = totalRecordCount % pageSize == 0 ? totalRecordCount / pageSize: totalRecordCount / pageSize + 1;
        startPageIndex = (currentPageNum - 1) * pageSize;
    }

    /**
     * 计算动态显示分页结束页码等信息，暂定分页显示5个页码 TODO
     */
    public void setPageDisplayParams(int totalRecordCount, int currentPageNum) {
        if (currentPageNum < 2) {
            this.startPageNum = 1;
            
        } else if (currentPageNum >= totalPageCount) {
            this.endPageNum=totalPageCount;
        } 
    }
    
    public List<T> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<T> recordList) {
        this.recordList = recordList;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public int getTotalRecordCount() {
        return totalRecordCount;
    }

    public void setTotalRecordCount(int totalRecordCount) {
        this.totalRecordCount = totalRecordCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPageNum() {
        return currentPageNum;
    }

    public void setCurrentPageNum(int currentPageNum) {
        this.currentPageNum = currentPageNum;
    }

    public int getStartPageIndex() {
        return startPageIndex;
    }

    public void setStartPageIndex(int startPageIndex) {
        this.startPageIndex = startPageIndex;
    }

    public int getStartPageNum() {
        return startPageNum;
    }

    public void setStartPageNum(int startPageNum) {
        this.startPageNum = startPageNum;
    }

    public int getEndPageNum() {
        return endPageNum;
    }

    public void setEndPageNum(int endPageNum) {
        this.endPageNum = endPageNum;
    }
    
}
