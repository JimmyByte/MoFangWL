package com.homenet.mofangwl.model;

import java.util.List;

/**
 * Created by weijunpeng on 2018/5/22.
 */

public class ArticleListResult {

        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 1
             * name : 飞鱼与海鸟
             * author : 渔.舟.唱.晚
             * category : 经典美文
             * time : 2016-12-04 14:21:56
             * point : 1131
             * summary : 或许，它们属于同一种蓝，海面与天空。因为它们一起生长一个故事：海天相接！于是，深邃的蓝便和浅淡的蓝得到了融合。它们彼此接近着，仿佛情侣，着装共同的蓝色。海面上除了涌动的澎湃波浪，因为有海风，想要飞翔的还有一种鱼，它们想把飞翔的轨迹白描给天蓝，带着这样...
             * content : 或许，它们属于同一种蓝，海面与天空。
             * 因为它们一起生长一个故事：海天相接！
             */

            private int id;
            private String name;
            private String author;
            private String category;
            private String time;
            private int point;
            private String summary;
            private String content;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public int getPoint() {
                return point;
            }

            public void setPoint(int point) {
                this.point = point;
            }

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }

}
