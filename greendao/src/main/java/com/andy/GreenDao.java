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
        record.addIdProperty().primaryKey();                        //ID
        record.addDoubleProperty("num");                            //金额
        record.addDateProperty("date");                             //日期
        record.addStringProperty("desc");                           //描述
        record.addLongProperty("category_id");                      //类目ID
        record.addStringProperty("category_name");                  //类目名
        record.addLongProperty("funds_id");                         //资金ID
        record.addStringProperty("funds_name");                     //资金名
        record.addIntProperty("type");                              //记录类型
        record.addLongProperty("person_id");                        //收借款人ID
        record.addStringProperty("person_name");                    //人名
        record.addLongProperty("in_funds_id");                      //收款资金ID(转账)
        record.addStringProperty("in_funds_name");                  //收款资金名(转账)

        //类目表
        Entity category = schema.addEntity("Category");
        category.addIdProperty().primaryKey();
        category.addStringProperty("category_name");
        //类目的类型： 0为支出、1为收入、2为收借款、3为转账
        category.addIntProperty("type");

        //资金项表
        Entity funds = schema.addEntity("Funds");
        funds.addIdProperty().primaryKey();
        funds.addStringProperty("funds_name");
        funds.addDoubleProperty("num");

        //收借款人表
        Entity person = schema.addEntity("Person");
        person.addIdProperty().primaryKey();
        person.addStringProperty("person_name");
        person.addDoubleProperty("num");

        new DaoGenerator().generateAll(schema, "../MengZhu/app/src/main/java-gen");
    }

}
