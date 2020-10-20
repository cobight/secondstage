package cn.cobight.sb.entity;

/**
 * fileName:Greeting
 * description:
 * author:cobight
 * createTime:2020/10/12 16:22
 * version:1.0.0
 */
public class Greeting {
    private long id;
    private String content;

    public Greeting() {
    }

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
