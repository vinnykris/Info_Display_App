package com.example.vinodkrishnamurthy.info_display_app;

/**
 * Created by vinodkrishnamurthy on 2/4/18.
 */

public class Guides {

    private String guide_name;
    private String guide_city;
    private String guide_state;
    private String guide_end_date;
    private String guide_icon;

    // GUIDE GETTERS
    public String getGuide_name() {
        return guide_name;
    }

    public String getGuide_city() {
        return guide_city;
    }

    public String getGuide_state() {
        return guide_state;
    }

    public String getGuide_end_date() {
        return guide_end_date;
    }

    public String getGuide_icon() {
        return guide_icon;
    }

    public Guides(String guide_name, String guide_city, String guide_state, String guide_end_date, String guide_icon){
        this.guide_name = guide_name;
        this.guide_city = guide_city;
        this.guide_state = guide_state;
        this.guide_end_date = guide_end_date;
        this.guide_icon = guide_icon;
    }
}
