package com.aaa.mybatis.dao;

import com.aaa.mybatis.entity.News;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * fileName:NewsDao
 * description:
 * author:zz
 * createTime:2020/9/18 11:36
 * version:1.0.0
 */
public interface NewsDao {

    /**
     * 新闻列表
     * @return
     */
    @Select("<script>select news_id newsId,title,addtime from tb_news " +
                "<where>" +
                   "<if test=\"newsNo!=null and newsNo!=''\"> and news_id=#{newsNo}</if>"+
                   "<if test=\"title!=null and title!=''\"> and title like '%'||#{title}||'%'</if>"+
                "</where>"+
            "</script>")
    List<News>  list(Map map);

    /**
     * 根据编号获取对象
     * @param newsId
     * @return
     */
    @Select("select news_id newsId,title,addtime from tb_news where news_id=#{newsId}")
    News  getById(int newsId);

    /**
     * 新闻添加
     * @param news
     * @return
     */
    @Insert("insert into tb_news(news_id,title,addtime) values(#{newsId},#{title},#{addTime})")
    int  add(News news);
    /**
     * 新闻更新
     * @param news
     * @return
     */
    @Update("update tb_news set title=#{title},addTime = #{addTime} where news_id=#{newsId}")
    int update(News news);

    /**
     * 新闻删除
     * @param newsId
     * @return
     */
    @Delete("delete from tb_news where news_id=#{id}")
    int deleteById(int newsId);
}
