package com.tyrival.coder.enums;


/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/10/31
 * @Version: V1.0
 */
public enum PlaceholderEnum {

    MODULE, PACKAGE, CLASS, INSTANCE, URL, COMMENT, DECLARATION, GETTER_SETTER,
    FIELD_NAME, UPPER_FIELD_NAME, FIELD_TYPE, FIELD_COMMENT, FIELD_COLUMN,
    TABLE, MYBATIS_RESULT_FIELD, MYBATIS_UPDATE_FIELD, MYBATIS_COLUMN, MYBATIS_VALUE,
    SQL_COLUMNS, SQL_COMMENTS;

    public String getRegex() {
        StringBuilder sb = new StringBuilder()
                .append("\\$\\{").append(this.name()).append("\\}");
        return sb.toString();
    }
}
