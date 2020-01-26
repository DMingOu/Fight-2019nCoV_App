package com.odm.fight_2019ncov.model.entity;

/**
 * @description: 1
 * @author: ODM
 * @date: 2020/1/27
 */
public class test {


    /**
     * id : 356
     * pubDate : 1580047711000
     * pubDateStr : 22分钟前
     * title : 武汉新型肺炎死亡病例已有45人，已经治愈出院40例
     * summary : 武汉市市长周先旺：截至今天凌晨，武汉累计报告618例，已经治愈出院40例，死亡45例，目前在院治疗533例，重症87例，危重53例，都在定点医院接受隔离治疗。
     * infoSource : 人民日报
     * sourceUrl : http://m.weibo.cn/2803301701/4465106090076043
     * provinceId : 42
     * provinceName : 湖北省
     * createTime : 1580048835000
     * modifyTime : 1580048835000
     */

    private int id;
    private long pubDate;
    private String pubDateStr;
    private String title;
    private String summary;
    private String infoSource;
    private String sourceUrl;
    private String provinceId;
    private String provinceName;
    private long createTime;
    private long modifyTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getPubDate() {
        return pubDate;
    }

    public void setPubDate(long pubDate) {
        this.pubDate = pubDate;
    }

    public String getPubDateStr() {
        return pubDateStr;
    }

    public void setPubDateStr(String pubDateStr) {
        this.pubDateStr = pubDateStr;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getInfoSource() {
        return infoSource;
    }

    public void setInfoSource(String infoSource) {
        this.infoSource = infoSource;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(long modifyTime) {
        this.modifyTime = modifyTime;
    }
}
