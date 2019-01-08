package com.tyrival.entity.base;

import com.tyrival.utils.StringUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/6/15
 * @Version: V1.0
 */
public class QueryParam implements Serializable {

    /**
     * 分隔符，使用半角逗号是因为如果文本中有逗号，
     */
    private static final String SEPARATOR = ",";

    private Parameter parameter;
    private List<Order> orders;
    private List<Filter> filters;

    public QueryParam(Parameter parameter) {
        this.parameter = parameter;
        this.initFilters();
        this.initOrders();
    }

    private void initOrders() {
        if (this.parameter == null || this.parameter.getOrder() == null) {
            return;
        }
        this.orders = new ArrayList<>();
        String[] array = this.parameter.getOrder().split(SEPARATOR);
        for (int i = 0; i < array.length / 2; i++) {
            Order o = new Order();
            o.setProperty(array[i * 2]);
            o.setType(OrderTypeEnum.getByCode(Integer.parseInt(array[i * 2 + 1])).getText());
            this.orders.add(o);
        }
    }

    private void initFilters() {
        if (this.parameter == null || this.parameter.getFilter() == null) {
            return;
        }
        this.filters = new ArrayList<>();
        String[] array = this.parameter.getFilter().split(SEPARATOR);
        for (int i = 0; i < array.length / 4; i++) {
            Filter f = new Filter();
            f.setProperty(array[i * 4]);
            SymbolEnum symbol = SymbolEnum.getByCode(Integer.parseInt(array[i * 4 + 1]));
            f.setSymbol(symbol.getText());
            String value = array[i * 4 + 2];
            if (symbol == SymbolEnum.LIKE) {
                value = "%" + value + "%";
            }
            f.setValue(value);
            f.setRelation(RelationEnum.getByCode(Integer.parseInt(array[i * 4 + 3])).getText());
            this.filters.add(f);
        }
    }

    public List<Order> getOrders() {
        return orders;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public Parameter getParameter() {
        return parameter;
    }

    /**
     * 排序条件
     */
    private class Order {
        private String property;
        private String type;

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = StringUtil.camelToUnderline(property);
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    /**
     * 关系枚举
     */
    private enum OrderTypeEnum {
        ASC(0, " ASC"), DESC(1, " DESC");

        OrderTypeEnum(Integer code, String text) {
            this.code = code;
            this.text = text;
        }

        private Integer code;

        public String getText() {
            return text;
        }

        private String text;

        public Integer getCode() {
            return code;
        }

        public static OrderTypeEnum getByCode(Integer code) {
            for (OrderTypeEnum orderTypeEnum : OrderTypeEnum.values()) {
                if (code == orderTypeEnum.getCode()) {
                    return orderTypeEnum;
                }
            }
            return null;
        }
    }


    /**
     * 查询条件
     */
    private class Filter {
        /**
         * 属性
         */
        private String property;
        /**
         * 值
         */
        private String value;
        /**
         * 运算符
         */
        private String symbol;
        /**
         * 关系
         */
        private String relation;

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = StringUtil.camelToUnderline(property);
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public String getRelation() {
            return relation;
        }

        public void setRelation(String relation) {
            this.relation = relation;
        }
    }

    /**
     * 运算符枚举
     */
    private enum SymbolEnum {
        EQUAL(0, "="),
        GREATER(1, ">"),
        LESS(2, "<"),
        GREATER_EQUAL(3, ">="),
        LESS_EQUAL(4, "<="),
        NOT_EQUAL(5, "!="),
        LIKE(6, " LIKE ");

        SymbolEnum(Integer code, String text) {
            this.code = code;
            this.text = text;
        }

        private Integer code;
        private String text;

        public Integer getCode() {
            return code;
        }

        public String getText() {
            return text;
        }

        public static SymbolEnum getByCode(Integer code) {
            for (SymbolEnum symbolEnum : SymbolEnum.values()) {
                if (code == symbolEnum.getCode()) {
                    return symbolEnum;
                }
            }
            return null;
        }
    }

    /**
     * 关系枚举
     */
    private enum RelationEnum {
        AND(0, " AND "), OR(1, " OR ");

        RelationEnum(Integer code, String text) {
            this.code = code;
            this.text = text;
        }

        private Integer code;

        public String getText() {
            return text;
        }

        private String text;


        public Integer getCode() {
            return code;
        }

        public static RelationEnum getByCode(Integer code) {
            for (RelationEnum relationEnum : RelationEnum.values()) {
                if (code == relationEnum.getCode()) {
                    return relationEnum;
                }
            }
            return null;
        }
    }

}
