package com.zdnf.bbs.domain;

import javax.validation.constraints.NotNull;

/**
 * Created by ZDNF on 2017/5/12.
 * 板块类的实体类
 */
public class Plate {
    private int id;
    @NotNull
    private String name;
    @NotNull
    private String type;
    @NotNull
    private String BelongTo;

    public void setId(int id){this.id=id;}
    public int getId(){return id;}
    public void setName(String name){this.name=name;}
    public String getName(){return name;}
    public void setType(String type){this.type=type;}
    public String getType(){return type;}
    //public void setColor(String color){this.color=color;}
    //public String getColor(){return color;}

}
