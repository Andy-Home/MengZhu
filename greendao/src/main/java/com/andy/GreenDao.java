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
        record.addLongProperty("funds_id");
        record.addStringProperty("category_name");
        record.addBooleanProperty("is_pay");
        record.addStringProperty("funds_name");

        //类目表
        Entity category = schema.addEntity("Category");
        category.addIdProperty().primaryKey();
        category.addStringProperty("category_name");
        category.addBooleanProperty("is_pay");

        //资金项表
        Entity funds = schema.addEntity("Funds");
        funds.addIdProperty();
        funds.addStringProperty("funds_name");

        new DaoGenerator().generateAll(schema, "../MengZhu/app/src/main/java-gen");
    }

}
