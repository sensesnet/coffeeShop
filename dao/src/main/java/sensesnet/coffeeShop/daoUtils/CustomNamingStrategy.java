package sensesnet.coffeeShop.daoUtils;

import org.hibernate.cfg.DefaultNamingStrategy;

/**
 * Custom  strategy for columns and tables
 * column= F_COLUMN
 * table =T_TABLE
 */
public class CustomNamingStrategy extends DefaultNamingStrategy {

    public String classToTableName(String className) {
        return "T_" + super.classToTableName(className).toUpperCase();
    }

    public String propertyToColumnName(String propName) {
        return "F_" + super.propertyToColumnName(propName);
    }

    public String columnName(String columnName) {
        return columnName;
    }

    public String tableName(String tableName) {
        return tableName;
    }
}
