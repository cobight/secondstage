package cn.cobight.mybatis.entity;

/**
 * fileName:NewsType
 * description:
 * author:cobight
 * createTime:2020/9/17 21:24
 * version:1.0.0
 */
public class NewsType {
    private Integer typeId;
    private String tname;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }
}
