package com.tyrival.coder.entity;

import com.tyrival.coder.enums.PlaceholderEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 占位符处理类
 * @Author: Zhou Chenyu
 * @Date: 2017/7/19
 * @Version: V1.0
 */
public class Placeholder {

    private Map<String, String> rules;

    public Placeholder() {
        this.rules = new HashMap<String, String>();
    }

    /**
     * 增加一个占位符替换规则
     * @param placeholderEnum
     * @param targetWord
     * @return
     */
    public Placeholder add(PlaceholderEnum placeholderEnum, String targetWord) {
        this.rules.put(placeholderEnum.getRegex(), targetWord);
        return this;
    }

    /**
     * 删除一个占位符替换规则
     * @param placeholderEnum
     * @return
     */
    public Placeholder remove(PlaceholderEnum placeholderEnum) {
        this.rules.remove(placeholderEnum.getRegex());
        return this;
    }

    public Map<String, String> getRules() {
        return rules;
    }

    public void setRules(Map<String, String> rules) {
        this.rules = rules;
    }

}
