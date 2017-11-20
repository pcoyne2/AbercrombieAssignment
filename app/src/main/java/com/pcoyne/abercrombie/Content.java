package com.pcoyne.abercrombie;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Created by Patrick Coyne on 11/17/2017.
 */

public class Content {

    @SerializedName("target")
    @Expose
    private String target;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("elementType")
    @Expose
    private String elementType;

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getElementType() {
        return elementType;
    }

    public void setElementType(String elementType) {
        this.elementType = elementType;
    }

}

