package com.example.momoweather.android.gson;

import java.io.Serializable;
import java.util.List;

public class LifestyleResponse implements Serializable {

    /**
     * code : 200
     * updateTime : 2021-02-06T16:36+08:00
     * fxLink : http://hfx.link/2ax2
     * daily : [{"date":"2021-02-06","type":"2","name":"洗车指数","level":"2","category":"较适宜","text":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"},{"date":"2021-02-06","type":"1","name":"运动指数","level":"3","category":"较不宜","text":"天气较好，但考虑天气寒冷，推荐您进行室内运动，户外运动时请注意保暖并做好准备活动。"}]
     * refer : {"sources":["Weather China"],"license":["commercial license"]}
     */

    private String cityId;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    private String code;
    private String updateTime;
    private String fxLink;
    private ReferBean refer;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getFxLink() {
        return fxLink;
    }

    public void setFxLink(String fxLink) {
        this.fxLink = fxLink;
    }

    public ReferBean getRefer() {
        return refer;
    }

    public void setRefer(ReferBean refer) {
        this.refer = refer;
    }

    public List<DailyBean> getDaily() {
        return daily;
    }

    public void setDaily(List<DailyBean> daily) {
        this.daily = daily;
    }

    private List<DailyBean> daily;

    public static class ReferBean implements Serializable {
        private List<String> sources;
        private List<String> license;

        public List<String> getSources() {
            return sources;
        }

        public void setSources(List<String> sources) {
            this.sources = sources;
        }

        public List<String> getLicense() {
            return license;
        }

        public void setLicense(List<String> license) {
            this.license = license;
        }
    }

    public static class DailyBean implements Serializable {
        /**
         * date : 2021-02-06
         * type : 2
         * name : 洗车指数
         * level : 2
         * category : 较适宜
         * text : 较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。
         */

        private String date;
        private String type;
        private String name;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        private String level;
        private String category;
        private String text;
    }
}
