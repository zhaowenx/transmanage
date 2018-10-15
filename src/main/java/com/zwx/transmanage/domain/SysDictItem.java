package com.zwx.transmanage.domain;

/**
 * Created by zhaowenx on 2018/10/10.
 */
public class SysDictItem {
    private String dict;
    private String itemKey;
    private String itemVal;
    private String oldItemKey;

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

    public String getOldItemKey() {
        return oldItemKey;
    }

    public void setOldItemKey(String oldItemKey) {
        this.oldItemKey = oldItemKey;
    }

    @Override
    public String toString() {
        return "SysDictItem{" +
                "dict='" + dict + '\'' +
                ", itemKey='" + itemKey + '\'' +
                ", itemVal='" + itemVal + '\'' +
                ", oldItemKey='" + oldItemKey + '\'' +
                '}';
    }
}
