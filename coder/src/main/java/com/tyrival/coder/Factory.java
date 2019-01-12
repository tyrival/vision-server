package com.tyrival.coder;

import com.tyrival.coder.entity.Field;
import com.tyrival.coder.entity.JavaBean;
import com.tyrival.coder.entity.Placeholder;
import com.tyrival.coder.entity.Template;
import com.tyrival.coder.enums.PlaceholderEnum;
import com.tyrival.coder.enums.TemplateEnum;
import com.tyrival.coder.util.FileUtil;
import com.tyrival.coder.util.NameFormat;
import com.tyrival.coder.util.PathUtil;

import java.util.List;

/**
 * @Description:
 * @Author: Zhou Chenyu
 * @Date: 2018/10/31
 * @Version: V1.0
 */
public class Factory {

    /**
     * 代码生成工具示例
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        JavaBean javaBean = new JavaBean("datasource", "datasource", "Database", "数据库", "comm_datasource");
//        javaBean.addField(new Field("name", "String", "名称"))
//                .addField(new Field("host", "String", "域名"))
//                .addField(new Field("port", "String", "端口"))
//                .addField(new Field("db", "String", "数据库"))
//                .addField(new Field("userName", "String", "用户名"))
//                .addField(new Field("password", "String", "密码"))
//                .addField(new Field("sql", "String", "查询语句"))
//                .addField(new Field("type", "DataSourceTypeEnum", "类型"));
        Factory factory = new Factory(javaBean);
        factory.generate();

    }

    private final static String JAVA_EXT_NAME = ".java";
    private final static String SQL_EXT_NAME = ".sql";
    private final static String MAPPER_EXT_NAME = "Mapper.xml";

    private JavaBean javaBean;

    public Factory(JavaBean javaBean) {
        this.javaBean = javaBean;
    }

    public void generate() {
        this.generateEntity();
        this.generateDAO();
        this.generateService();
        this.generateServiceImpl();
        this.generateController();
        this.generateFeignController();
        this.generateFeignService();
        this.generateFeignHystrix();
        this.generateMybatis();
        this.generateSql();
    }

    private void generateEntity() {
        try {
            String path = PathUtil.getCommon(this.javaBean);
            String name = this.javaBean.getClassName() + JAVA_EXT_NAME;
            String content = Template.handler(TemplateEnum.ENTITY, this.getJavaBeanPlaceholder(this.javaBean));
            FileUtil.write(path, name, content);
            System.out.println("Finished " + path + name);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void generateDAO() {
        try {
            String path = PathUtil.getModule(this.javaBean) + "dao/";
            String name = this.javaBean.getClassName() + "DAO" + JAVA_EXT_NAME;
            String content = Template.handler(TemplateEnum.DAO, this.getMvcPlaceholder(this.javaBean));
            FileUtil.write(path, name, content);
            System.out.println("Finished " + path + name);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void generateService() {
        try {
            String path = PathUtil.getModule(this.javaBean) + "service/";
            String name = this.javaBean.getClassName() + "Service" + JAVA_EXT_NAME;
            String content = Template.handler(TemplateEnum.SERVICE, this.getMvcPlaceholder(this.javaBean));
            FileUtil.write(path, name, content);
            System.out.println("Finished " + path + name);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void generateServiceImpl() {
        try {
            String path = PathUtil.getModule(this.javaBean) + "service/impl/";
            String name = this.javaBean.getClassName() + "ServiceImpl" + JAVA_EXT_NAME;
            String content = Template.handler(TemplateEnum.SERVICE_IMPL, this.getMvcPlaceholder(this.javaBean));
            FileUtil.write(path, name, content);
            System.out.println("Finished " + path + name);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void generateController() {
        try {
            String path = PathUtil.getModule(this.javaBean) + "controller/";
            String name = this.javaBean.getClassName() + "Controller" + JAVA_EXT_NAME;
            String content = Template.handler(TemplateEnum.CONTROLLER, this.getMvcPlaceholder(this.javaBean));
            FileUtil.write(path, name, content);
            System.out.println("Finished " + path + name);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void generateFeignController() {
        try {
            String path = PathUtil.getFeign(this.javaBean) + "controller/";
            String name = this.javaBean.getClassName() + "Controller" + JAVA_EXT_NAME;
            String content = Template.handler(TemplateEnum.FEIGN_CONTROLLER, this.getMvcPlaceholder(this.javaBean));
            FileUtil.write(path, name, content);
            System.out.println("Finished " + path + name);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void generateFeignService() {
        try {
            String path = PathUtil.getFeign(this.javaBean) + "service/";
            String name = this.javaBean.getClassName() + "Service" + JAVA_EXT_NAME;
            String content = Template.handler(TemplateEnum.FEIGN_SERVICE, this.getMvcPlaceholder(this.javaBean));
            FileUtil.write(path, name, content);
            System.out.println("Finished " + path + name);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void generateFeignHystrix() {
        try {
            String path = PathUtil.getFeign(this.javaBean) + "service/impl/";
            String name = this.javaBean.getClassName() + "ServiceHystrix" + JAVA_EXT_NAME;
            String content = Template.handler(TemplateEnum.FEIGN_HYSTRIX, this.getMvcPlaceholder(this.javaBean));
            FileUtil.write(path, name, content);
            System.out.println("Finished " + path + name);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void generateMybatis() {
        try {
            String path = PathUtil.getMybatis(this.javaBean);
            String name = this.javaBean.getClassName() + MAPPER_EXT_NAME;
            String content = Template.handler(TemplateEnum.MYBATIS, this.getMybatisPlaceholder(this.javaBean));
            FileUtil.write(path, name, content);
            System.out.println("Finished " + path + name);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void generateSql() {
        try {
            String path = PathUtil.getSql(this.javaBean);
            String name = this.javaBean.getTableName() + SQL_EXT_NAME;
            String content = Template.handler(TemplateEnum.SQL, this.getSqlPlaceholder(this.javaBean));
            FileUtil.write(path, name, content);
            System.out.println("Finished " + path + name);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private Placeholder getSqlPlaceholder(JavaBean javaBean) {
        Placeholder placeholder = this.getMvcPlaceholder(javaBean);
        placeholder.add(PlaceholderEnum.TABLE, javaBean.getTableName());
        placeholder.add(PlaceholderEnum.COMMENT, javaBean.getComment());
        String contentColumn = this.getSqlColumn(this.javaBean.getFields());
        placeholder.add(PlaceholderEnum.SQL_COLUMNS, contentColumn);
        String contentComment = this.getSqlComment(this.javaBean);
        placeholder.add(PlaceholderEnum.SQL_COMMENTS, contentComment);
        return placeholder;
    }

    private String getSqlColumn(List<Field> fieldList) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fieldList.size(); i++) {
            Field field = fieldList.get(i);
            sb.append(Template.handler(TemplateEnum.SQL_COLUMN, getFieldPlaceholder(field)));
        }
        return sb.toString();
    }

    private String getSqlComment(JavaBean javaBean) {
        StringBuilder sb = new StringBuilder();
        List<Field> fieldList = javaBean.getFields();
        for (int i = 0; i < fieldList.size(); i++) {
            Field field = fieldList.get(i);
            Placeholder placeholder = getFieldPlaceholder(field);
            placeholder.add(PlaceholderEnum.TABLE, javaBean.getTableName());
            sb.append(Template.handler(TemplateEnum.SQL_COMMENT, placeholder));
        }
        return sb.toString();
    }

    private String getDeclaration(List<Field> fieldList) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fieldList.size(); i++) {
            Field field = fieldList.get(i);
            sb.append(Template.handler(TemplateEnum.FIELD, getFieldPlaceholder(field)));
        }
        return sb.toString();
    }

    private String getMethods(List<Field> fieldList) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fieldList.size(); i++) {
            Field field = fieldList.get(i);
            sb.append(Template.handler(TemplateEnum.METHOD, getFieldPlaceholder(field)));
        }
        return sb.toString();
    }

    private Placeholder getJavaBeanPlaceholder(JavaBean javaBean) {
        Placeholder placeholder = this.getMvcPlaceholder(javaBean);
        String declaration = this.getDeclaration(this.javaBean.getFields());
        placeholder.add(PlaceholderEnum.DECLARATION, declaration);
        String getterSetter = this.getMethods(this.javaBean.getFields());
        placeholder.add(PlaceholderEnum.GETTER_SETTER, getterSetter);
        return placeholder;
    }

    private Placeholder getFieldPlaceholder(Field field) {
        Placeholder placeholder = new Placeholder();
        placeholder.add(PlaceholderEnum.FIELD_NAME, field.getName());
        placeholder.add(PlaceholderEnum.UPPER_FIELD_NAME, NameFormat.toUpperCamelName(field.getName()));
        placeholder.add(PlaceholderEnum.FIELD_TYPE, field.getType());
        placeholder.add(PlaceholderEnum.FIELD_COLUMN, field.getColumnName());
        placeholder.add(PlaceholderEnum.FIELD_COMMENT, field.getComment());
        return placeholder;
    }

    private Placeholder getMvcPlaceholder(JavaBean javaBean) {
        Placeholder placeholder = new Placeholder();
        placeholder.add(PlaceholderEnum.MODULE, javaBean.getModuleName());
        placeholder.add(PlaceholderEnum.PACKAGE, javaBean.getPackageName());
        placeholder.add(PlaceholderEnum.CLASS, javaBean.getClassName());
        placeholder.add(PlaceholderEnum.INSTANCE, javaBean.getInstanceName());
        placeholder.add(PlaceholderEnum.COMMENT, javaBean.getComment());
        placeholder.add(PlaceholderEnum.URL, javaBean.getUrlName());
        return placeholder;
    }

    private Placeholder getMybatisPlaceholder(JavaBean javaBean) {
        Placeholder placeholder = this.getMvcPlaceholder(javaBean);
        String contentResult = this.getMybatisResult(javaBean.getFields());
        String contentUpdate = this.getMybatisUpdate(javaBean.getFields());
        placeholder.add(PlaceholderEnum.TABLE, javaBean.getTableName());
        placeholder.add(PlaceholderEnum.MYBATIS_RESULT_FIELD, contentResult);
        placeholder.add(PlaceholderEnum.MYBATIS_UPDATE_FIELD, contentUpdate);
        placeholder.add(PlaceholderEnum.MYBATIS_COLUMN, javaBean.getMybatisColumns());
        placeholder.add(PlaceholderEnum.MYBATIS_VALUE, javaBean.getMybatisValue());
        return placeholder;
    }

    private String getMybatisResult(List<Field> fieldList) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fieldList.size(); i++) {
            Field field = fieldList.get(i);
            sb.append(Template.handler(TemplateEnum.MYBATIS_RESULT_FEILD, this.getMybatisFieldPlaceholder(field)));
        }
        return sb.toString();
    }

    private String getMybatisUpdate(List<Field> fieldList) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fieldList.size(); i++) {
            Field field = fieldList.get(i);
            sb.append(Template.handler(TemplateEnum.MYBATIS_UPDATE_FIELD, this.getMybatisFieldPlaceholder(field)));
        }
        return sb.toString();
    }

    private Placeholder getMybatisFieldPlaceholder(Field field) {
        Placeholder placeholder = new Placeholder();
        placeholder.add(PlaceholderEnum.FIELD_NAME, field.getName());
        placeholder.add(PlaceholderEnum.FIELD_COLUMN, field.getColumnName());
        return placeholder;
    }


}
