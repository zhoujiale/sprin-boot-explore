package com.github.zhoujiale.spring.boot.web.enums;

/**
 * @author zhou
 * @className Type
 * @descrption TODO
 * @date 2022/5/31 16:10
 */
public enum Type {

    ALL("all"),
    ONLY("only")
    ;

    private String msg;

    Type(String msg) {
        this.msg = msg;
    }
}
