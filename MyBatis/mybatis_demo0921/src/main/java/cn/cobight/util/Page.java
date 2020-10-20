package cn.cobight.util;

/**
 * fileName:Page
 * description:
 * author:cobight
 * createTime:2020/9/21 20:09
 * version:1.0.0
 */
public class Page {

    //第几页
    private Integer pageNo;
    //每页显示数量
    private Integer pageSize;
    // 总条数
    private Integer  total;
    // 总页数  (一直点下一页时，最大页)
    private Integer pageCount;

    /**
     * 构造（当前页和每页显示数量） 方便输入参数设置
     * @param pageNo
     * @param pageSize
     */
    public Page(Integer pageNo, Integer pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", total=" + total +
                ", pageCount=" + pageCount +
                '}';
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }
}