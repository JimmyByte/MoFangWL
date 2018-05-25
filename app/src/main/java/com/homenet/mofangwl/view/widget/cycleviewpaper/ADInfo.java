package com.homenet.mofangwl.view.widget.cycleviewpaper;

import org.json.JSONObject;

/**
 * 描述：广告信息</br>
 */
public class ADInfo {

    String clickSpm = "";
    String target_type = "";
    String priority = "";
    String pvSpm = "";
    JSONObject params = null;
    String pic_url = "";
    String title = "";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getClickSpm() {
        return clickSpm;
    }

    public void setClickSpm(String clickSpm) {
        this.clickSpm = clickSpm;
    }

    public String getTarget_type() {
        return target_type;
    }

    public void setTarget_type(String target_type) {
        this.target_type = target_type;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getPvSpm() {
        return pvSpm;
    }

    public void setPvSpm(String pvSpm) {
        this.pvSpm = pvSpm;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public JSONObject getParams() {
        return params;
    }

    public void setParams(JSONObject params) {
        this.params = params;
    }
}
