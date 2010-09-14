/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.phystech.vkurse.postgresql;

/**
 *
 * @author Дима
 */
public class PgSqlSettings
{
    /*
     *   Дмитрий Исупов, 13 сентября 2010:
     * В данный момент на сервере используется база данных PostgreSQL, но
     * лично у меня на компьютере стоит MySQL и отлаживаю я на ней.
     * Смена сервера SQL производится переопределением констант в этом файле
     * настроек. Ниже две группы констант - для PostegreSQL и MySQL
     * соответственно. Владимир Никишкин говорил о возможности установки MySQL
     * на рабочий сервер, надеюсь, это будет осуществлено, т.к. с PostregreSQL
     * есть какие-то непонятные проблемы. Строка подключения указана верно
     * (судя по примерам в сети), но все равно приходилось танцевать с бубном,
     * чтобы все работало.
     * Я постараюсь проверять работоспособность на сервере PostgreSQL перед
     * каждым обновлением на google code, поэтому здесь должны быть
     * раскомментированны нужные константы, но все равно могу случайно выложить
     * версию с неправильными константами - если что, напомните - исправлю.
     */

    /*
    //  PostgreSQL
    private static String jdbcDriverClass = "org.postgresql.Driver";
    private static String url =
            "jdbc:postgresql://localhost/inno2010_vkurse;default_schema=true;";
    //jdbc:postgresql://localhost:5432/mydatabase?searchpath=myschema
    private static String dbName = "inno2010_vkurse";
    private static String username = "innocourse-dtb";
    private static String password = "innocourse-dtb";
    */
    
    //  MySQL
    private static String jdbcDriverClass = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/inno2010_vkurse";
    private static String dbName = "inno2010_vkurse";
    private static String username = "root";
    private static String password = "mysql";  // Поздравляю, теперь вы знаете
    // пароль рута на моем локальном сервере MySQL, только там нет ничего
    // инетересного.  :-P
    
    
    private PgSqlSettings(){}

    public static String getJdbcDriverClass()
    {
        return jdbcDriverClass;
    }

    public static String getUrl()
    {
        return url;
    }

    public static String getDbName()
    {
        return dbName;
    }

    public static String getUsername()
    {
        return username;
    }

    public static String getPassword()
    {
        return password;
    }

    public static void setJdbcDriverClass(String jdbcDriverClass)
    {
        PgSqlSettings.jdbcDriverClass = jdbcDriverClass;
    }

    public static void setUrl(String url)
    {
        PgSqlSettings.url = url;
    }

    public static void setDbName(String dbName)
    {
        PgSqlSettings.dbName = dbName;
    }

    public static void setUsername(String username)
    {
        PgSqlSettings.username = username;
    }

    public static void setPassword(String password)
    {
        PgSqlSettings.password = password;
    }
}
