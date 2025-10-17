package com.github.zhoujiale.spring.boot.ai.constants;

/**
 * @classname: PromptConstant
 * @author: zhou
 * @description:
 * @date: 2025/9/8 14:33
 */
public interface PromptConstant {

    /**
     * 行业领域
     **/
    String SCOPE = "scope";

    static String promptWrapper(String text){
        return "<" + text + ">";
    }
}
