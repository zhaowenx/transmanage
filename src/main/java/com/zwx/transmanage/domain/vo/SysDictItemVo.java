package com.zwx.transmanage.domain.vo;

/**
 * Created by zhaowenx on 2018/10/10.
 */
public class SysDictItemVo {
    private String dict;
    private String itemKey;
    private String itemVal;

    public String getDict() {
        return dict;
    }

    public void setDict(String dict) {
        this.dict = dict;
    }

    public String getItemKey() {
        return itemKey;
    }

    public void setItemKey(String itemKey) {
        this.itemKey = itemKey;
    }

    public String getItemVal() {
        return itemVal;
    }

    public void setItemVal(String itemVal) {
        this.itemVal = itemVal;
    }

    @Override
    public String toString() {
        return "SysDictItemVo{" +
                "dict='" + dict + '\'' +
                ", itemKey='" + itemKey + '\'' +
                ", itemVal='" + itemVal + '\'' +
                '}';
    }
}
