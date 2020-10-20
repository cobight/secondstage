package cn.cobight.ssm.entity;

/**
 * fileName:NewsFile
 * description:
 * author:cobight
 * createTime:2020/9/26 9:07
 * version:1.0.0
 */
public class NewsFile {
    private Integer id;
    private String fileName;
    private String filePath;
    private Integer newsId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }
}
