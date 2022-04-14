package com.example.momoweather.android.gson;

import java.io.Serializable;
import java.util.List;

public class AirNowResponse implements Serializable {

    /**
     * code : 200
     * updateTime : 2021-02-16T14:42+08:00
     * fxLink : http://hfx.link/2ax4
     * now : {"pubTime":"2021-02-16T14:00+08:00","aqi":"28","level":"1","category":"优","primary":"NA","pm10":"28","pm2p5":"5","no2":"3","so2":"2","co":"0.2","o3":"76"}
     * station : [{"pubTime":"2021-02-16T14:00+08:00","name":"密云镇","id":"CNA3697","aqi":"20","level":"1","category":"优","primary":"NA","pm10":"4","pm2p5":"4","no2":"4","so2":"3","co":"0.2","o3":"63"},{"pubTime":"2021-02-16T14:00+08:00","name":"丰台小屯","id":"CNA3696","aqi":"57","level":"2","category":"良","primary":"PM10","pm10":"63","pm2p5":"6","no2":"4","so2":"2","co":"0.2","o3":"73"},{"pubTime":"2021-02-16T14:00+08:00","name":"怀柔新城","id":"CNA3695","aqi":"25","level":"1","category":"优","primary":"NA","pm10":"7","pm2p5":"3","no2":"2","so2":"3","co":"0.1","o3":"78"},{"pubTime":"2021-02-16T14:00+08:00","name":"延庆石河营","id":"CNA3694","aqi":"26","level":"1","category":"优","primary":"NA","pm10":"15","pm2p5":"3","no2":"4","so2":"2","co":"0.4","o3":"83"},{"pubTime":"2021-02-16T14:00+08:00","name":"大兴旧宫","id":"CNA3675","aqi":"31","level":"1","category":"优","primary":"NA","pm10":"31","pm2p5":"5","no2":"2","so2":"1","co":"0.2","o3":"73"},{"pubTime":"2021-02-16T14:00+08:00","name":"房山燕山","id":"CNA3674","aqi":"26","level":"1","category":"优","primary":"NA","pm10":"19","pm2p5":"4","no2":"3","so2":"4","co":"0.2","o3":"83"},{"pubTime":"2021-02-16T14:00+08:00","name":"通州东关","id":"CNA3673","aqi":"22","level":"1","category":"优","primary":"NA","pm10":"15","pm2p5":"4","no2":"1","so2":"3","co":"0.3","o3":"70"},{"pubTime":"2021-02-16T14:00+08:00","name":"丰台云岗","id":"CNA3672","aqi":"45","level":"1","category":"优","primary":"NA","pm10":"45","pm2p5":"5","no2":"1","so2":"1","co":"0.2","o3":"82"},{"pubTime":"2021-02-16T14:00+08:00","name":"门头沟三家店","id":"CNA3671","aqi":"66","level":"2","category":"良","primary":"PM10","pm10":"82","pm2p5":"6","no2":"2","so2":"1","co":"0.2","o3":"76"},{"pubTime":"2021-02-16T14:00+08:00","name":"密云新城","id":"CNA3418","aqi":"23","level":"1","category":"优","primary":"NA","pm10":"11","pm2p5":"3","no2":"2","so2":"3","co":"0.2","o3":"73"},{"pubTime":"2021-02-16T14:00+08:00","name":"平谷新城","id":"CNA3417","aqi":"24","level":"1","category":"优","primary":"NA","pm10":"7","pm2p5":"3","no2":"1","so2":"2","co":"0.2","o3":"74"},{"pubTime":"2021-02-16T14:00+08:00","name":"延庆夏都","id":"CNA3281","aqi":"25","level":"1","category":"优","primary":"NA","pm10":"15","pm2p5":"3","no2":"2","so2":"1","co":"0.2","o3":"80"},{"pubTime":"2021-02-16T14:00+08:00","name":"古城","id":"CNA1012","aqi":"56","level":"2","category":"良","primary":"PM10","pm10":"61","pm2p5":"8","no2":"2","so2":"1","co":"0.2","o3":"76"},{"pubTime":"2021-02-16T14:00+08:00","name":"奥体中心","id":"CNA1011","aqi":"24","level":"1","category":"优","primary":"NA","pm10":"23","pm2p5":"3","no2":"4","so2":"2","co":"0.2","o3":"74"},{"pubTime":"2021-02-16T14:00+08:00","name":"昌平镇","id":"CNA1010","aqi":"24","level":"1","category":"优","primary":"NA","pm10":"17","pm2p5":"5","no2":"2","so2":"1","co":"0.2","o3":"75"},{"pubTime":"2021-02-16T14:00+08:00","name":"怀柔镇","id":"CNA1009","aqi":"25","level":"1","category":"优","primary":"NA","pm10":"10","pm2p5":"8","no2":"2","so2":"3","co":"0.2","o3":"77"},{"pubTime":"2021-02-16T14:00+08:00","name":"顺义新城","id":"CNA1008","aqi":"33","level":"1","category":"优","primary":"NA","pm10":"33","pm2p5":"5","no2":"1","so2":"3","co":"0.2","o3":"73"},{"pubTime":"2021-02-16T14:00+08:00","name":"海淀区万柳","id":"CNA1007","aqi":"34","level":"1","category":"优","primary":"NA","pm10":"34","pm2p5":"6","no2":"6","so2":"1","co":"0.2","o3":"75"},{"pubTime":"2021-02-16T14:00+08:00","name":"官园","id":"CNA1006","aqi":"25","level":"1","category":"优","primary":"NA","pm10":"25","pm2p5":"5","no2":"4","so2":"3","co":"0.2","o3":"78"},{"pubTime":"2021-02-16T14:00+08:00","name":"农展馆","id":"CNA1005","aqi":"28","level":"1","category":"优","primary":"NA","pm10":"28","pm2p5":"4","no2":"2","so2":"3","co":"0.2","o3":"85"},{"pubTime":"2021-02-16T14:00+08:00","name":"天坛","id":"CNA1004","aqi":"29","level":"1","category":"优","primary":"NA","pm10":"29","pm2p5":"10","no2":"2","so2":"1","co":"0.2","o3":"78"},{"pubTime":"2021-02-16T14:00+08:00","name":"东四","id":"CNA1003","aqi":"30","level":"1","category":"优","primary":"NA","pm10":"30","pm2p5":"7","no2":"2","so2":"3","co":"0.1","o3":"80"},{"pubTime":"2021-02-16T14:00+08:00","name":"定陵","id":"CNA1002","aqi":"23","level":"1","category":"优","primary":"NA","pm10":"22","pm2p5":"3","no2":"2","so2":"1","co":"0.2","o3":"73"},{"pubTime":"2021-02-16T14:00+08:00","name":"万寿西宫","id":"CNA1001","aqi":"29","level":"1","category":"优","primary":"NA","pm10":"29","pm2p5":"5","no2":"3","so2":"1","co":"0.3","o3":"75"}]
     * refer : {"sources":["cnemc"],"license":["commercial license"]}
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
    private NowBean now;
    private ReferBean refer;
    private List<StationBean> station;

    public static class NowBean implements Serializable {
        /**
         * pubTime : 2021-02-16T14:00+08:00
         * aqi : 28
         * level : 1
         * category : 优
         * primary : NA
         * pm10 : 28
         * pm2p5 : 5
         * no2 : 3
         * so2 : 2
         * co : 0.2
         * o3 : 76
         */

        private String pubTime;
        private String aqi;
        private String level;
        private String category;
        private String primary;
        private String pm10;
        private String pm2p5;

        public String getPubTime() {
            return pubTime;
        }

        public void setPubTime(String pubTime) {
            this.pubTime = pubTime;
        }

        public String getAqi() {
            return aqi;
        }

        public void setAqi(String aqi) {
            this.aqi = aqi;
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

        public String getPrimary() {
            return primary;
        }

        public void setPrimary(String primary) {
            this.primary = primary;
        }

        public String getPm10() {
            return pm10;
        }

        public void setPm10(String pm10) {
            this.pm10 = pm10;
        }

        public String getPm2p5() {
            return pm2p5;
        }

        public void setPm2p5(String pm2p5) {
            this.pm2p5 = pm2p5;
        }

        public String getNo2() {
            return no2;
        }

        public void setNo2(String no2) {
            this.no2 = no2;
        }

        public String getSo2() {
            return so2;
        }

        public void setSo2(String so2) {
            this.so2 = so2;
        }

        public String getCo() {
            return co;
        }

        public void setCo(String co) {
            this.co = co;
        }

        public String getO3() {
            return o3;
        }

        public void setO3(String o3) {
            this.o3 = o3;
        }

        private String no2;
        private String so2;
        private String co;
        private String o3;
    }

    public static class ReferBean implements Serializable {
        private List<String> sources;

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

        private List<String> license;
    }

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

    public NowBean getNow() {
        return now;
    }

    public void setNow(NowBean now) {
        this.now = now;
    }

    public ReferBean getRefer() {
        return refer;
    }

    public void setRefer(ReferBean refer) {
        this.refer = refer;
    }

    public List<StationBean> getStation() {
        return station;
    }

    public void setStation(List<StationBean> station) {
        this.station = station;
    }

    public static class StationBean implements Serializable {
        /**
         * pubTime : 2021-02-16T14:00+08:00
         * name : 密云镇
         * id : CNA3697
         * aqi : 20
         * level : 1
         * category : 优
         * primary : NA
         * pm10 : 4
         * pm2p5 : 4
         * no2 : 4
         * so2 : 3
         * co : 0.2
         * o3 : 63
         */

        private String pubTime;
        private String name;
        private String id;

        public String getPubTime() {
            return pubTime;
        }

        public void setPubTime(String pubTime) {
            this.pubTime = pubTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAqi() {
            return aqi;
        }

        public void setAqi(String aqi) {
            this.aqi = aqi;
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

        public String getPrimary() {
            return primary;
        }

        public void setPrimary(String primary) {
            this.primary = primary;
        }

        public String getPm10() {
            return pm10;
        }

        public void setPm10(String pm10) {
            this.pm10 = pm10;
        }

        public String getPm2p5() {
            return pm2p5;
        }

        public void setPm2p5(String pm2p5) {
            this.pm2p5 = pm2p5;
        }

        public String getNo2() {
            return no2;
        }

        public void setNo2(String no2) {
            this.no2 = no2;
        }

        public String getSo2() {
            return so2;
        }

        public void setSo2(String so2) {
            this.so2 = so2;
        }

        public String getCo() {
            return co;
        }

        public void setCo(String co) {
            this.co = co;
        }

        public String getO3() {
            return o3;
        }

        public void setO3(String o3) {
            this.o3 = o3;
        }

        private String aqi;
        private String level;
        private String category;
        private String primary;
        private String pm10;
        private String pm2p5;
        private String no2;
        private String so2;
        private String co;
        private String o3;
    }
}
