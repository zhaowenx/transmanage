package com.zwx.transmanage.domain.vo;

/**
 * Created by zhaowenx on 2018/10/10.
 */
public class SysDictVo {
    private String dict;
    private String dictName;
    private String groupDict;

    public String getDict() {
        return dict;
    }

    public void setDict(String dict) {
        this.dict = dict;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getGroupDict() {
        return groupDict;
    }

    public void setGroupDict(String groupDict) {
        this.groupDict = groupDict;
    }

    @Override
    public String toString() {
        return "SysDictVo{" +
                "dict='" + dict + '\'' +
                ", dictName='" + dictName + '\'' +
                ", groupDict='" + groupDict + '\'' +
                '}';
    }
}
