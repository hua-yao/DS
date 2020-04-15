package util;

import org.beetl.sql.core.*;
import org.beetl.sql.core.db.DBStyle;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.DebugInterceptor;

/**
 * 用于实体类生成
 */
public class EntityProducer {
    public static void main(String[] args) throws Exception {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/gmall?useUnicode=true&characterEncoding=UTF8&serverTimezone=Asia/Shanghai";
        String userName = "root";
        String password = "root";
        connectionBeet(driver, url, userName, password);

    }

    private static void connectionBeet(String driver, String url, String userName, String password) throws Exception {
        ConnectionSource source = ConnectionSourceHelper.getSimple(driver, url, userName, password);
        DBStyle mysql = new MySqlStyle();
        // sql语句放在classpagth的/sql 目录下
        SQLLoader loader = new ClasspathLoader("/sql");
        // 数据库命名跟java命名一样，所以采用DefaultNameConversion，还有一个是UnderlinedNameConversion，下划线风格的，
        UnderlinedNameConversion nc = new UnderlinedNameConversion();
        //创建一个SQLManager,DebugInterceptor （非必须），但可以通过它查看sql执行情况
        SQLManager sqlManager = new SQLManager(mysql, loader, source, nc, new Interceptor[]{new DebugInterceptor()});
        sqlManager.genPojoCodeToConsole("sku_attr_value");
        sqlManager.genSQLTemplateToConsole("sku_attr_value");
    }

}
