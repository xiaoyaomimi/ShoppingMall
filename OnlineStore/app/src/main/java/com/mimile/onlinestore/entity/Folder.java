package com.mimile.onlinestore.entity;


import java.util.List;

/**
 * Created by caidongdong on 2016/12/1 14:36
 * email : mircaidong@163.com
 * 文件夹
 */
public class Folder {
    public String name;
    public String path;
    public Image cover;
    public List<Image> images;

    @Override
    public boolean equals(Object o) {
        try {
            Folder other = (Folder) o;
            return this.path.equalsIgnoreCase(other.path);
        }catch (ClassCastException e){
            e.printStackTrace();
        }
        return super.equals(o);
    }
}
