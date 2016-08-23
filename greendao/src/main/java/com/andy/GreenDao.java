package com.andy;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class GreenDao {
    public static void main(String[] args) throws Exception {
        // 正如你所见的，你创建了一个用于添加实体（Entity）的模式（Schema）对象。
        // 两个参数分别代表：数据库版本号与自动生成代码的包路径。
        Schema schema = new Schema(1, "com.andy.greendao");

        //新建记录表
        Entity record = schema.addEntity("Record");
        record.addIdProperty().primaryKey();
        record.addDoubleProperty("num");
        record.addDateProperty("date");
        record.addStringProperty("desc");
        record.addLongProperty("category_id");
        record.addStringProperty("category_name");
        record.addLongProperty("funds_id");
        record.addStringProperty("funds_name");
        record.addIntProperty("type");

        //类目表
        Entity category = schema.addEntity("Category");
        category.addIdProperty().primaryKey();
        category.addStringProperty("category_name");
        //类目的类型： 0为支出、1为收入、2为收借款、3为转账
        category.addIntProperty("type");

        //资金项表
        Entity funds = schema.addEntity("Funds");
        funds.addIdProperty();
        funds.addStringProperty("funds_name");
        funds.addDoubleProperty("num");

        //收借款人表
        Entity person = schema.addEntity("Person");
        person.addIdProperty();
        person.addStringProperty("person_name");
        person.addDoubleProperty("num");

        new DaoGenerator().generateAll(schema, "../MengZhu/app/src/main/java-gen");
    }

}
