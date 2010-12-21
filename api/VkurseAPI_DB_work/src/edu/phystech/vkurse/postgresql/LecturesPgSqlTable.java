/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.phystech.vkurse.postgresql;
import java.sql.*;
import edu.phystech.vkurse.model.*;
import java.util.Vector;
//import java.util.*;

/**
 *
 * @author Дима
 */
public class LecturesPgSqlTable implements LecturesTable
{
    public int insert(Lecture item) throws TableException
    {
        int r=-1;

        if (item != null)
        {
            try
            {
                Class.forName(PgSqlSettings.getJdbcDriverClass()).newInstance();
            } catch (java.lang.Exception exc)
            {
                throw new TableException("Cannot load JDBC driver. It is porbably not installed correctly. Connection string is:  " + PgSqlSettings.getJdbcDriverClass() + "        " + exc.toString());
            }

            try
            {
                Connection dbConn = DriverManager.getConnection(PgSqlSettings.getUrl(), PgSqlSettings.getUsername(), PgSqlSettings.getPassword());

                Statement st = dbConn.createStatement();

                st.executeUpdate("insert into Lectures(name, examtypeid, comment) values(" +
                        "'" + item.getName().replace("'", "<apostrophe>") + "', " +
                        item.getExamTypeID() + ", '" + item.getComment().replace("'", "<apostrophe>") + "'" +
                        ");");
                /*
                ResultSet rs = st.getGeneratedKeys();
                if (rs.first())
                {
                    r = rs.getInt(1);
                }
                 */
                {
                    Statement stgfid = dbConn.createStatement();
                    ResultSet rsgfid = stgfid.executeQuery("SELECT last_value FROM lectures_id_seq");
                    rsgfid.next();
                    r = rsgfid.getInt(1);
                }

                st.close();
                dbConn.close();
            } catch (java.lang.Exception exc)
            {
                throw new TableException("An error occured while working with server: " + exc.toString());
            }
        }
        return r;
    }


    public boolean update(Lecture item) throws TableException
    {
        Boolean r=false;

        if (item != null)
        {
            try
            {
                Class.forName(PgSqlSettings.getJdbcDriverClass()).newInstance();
            } catch (java.lang.Exception exc)
            {
                throw new TableException("Cannot load JDBC driver. It is porbably not installed correctly. Connection string is:  " + PgSqlSettings.getJdbcDriverClass() + "        " + exc.toString());
            }

            try
            {
                Connection dbConn = DriverManager.getConnection(PgSqlSettings.getUrl(), PgSqlSettings.getUsername(), PgSqlSettings.getPassword());

                Statement st = dbConn.createStatement();

                String cmd = "update Lectures set " +
                        "name = '" + item.getName().replace("'", "<apostrophe>") + "', " +
                        "examTypeID = " + item.getExamTypeID() + ", " +
                        "comment = '" + item.getComment().replace("'", "<apostrophe>") + "' " +
                        " where ID="+item.getID()+";";
                r = (st.executeUpdate(cmd) > 0);

                st.close();
                dbConn.close();
            } catch (java.lang.Exception exc)
            {
                throw new TableException("An error occured while working with server: " + exc.toString());
            }
        }
        return r;
    }


    public Lecture get(int ID) throws TableException
    {
        Lecture r = null;
                
        try
        {
            Class.forName(PgSqlSettings.getJdbcDriverClass()).newInstance();
        } catch (java.lang.Exception exc)
        {
            throw new TableException("Cannot load JDBC driver. It is porbably not installed correctly. Connection string is:  " + PgSqlSettings.getJdbcDriverClass() + "        " + exc.toString());
        }

        try
        {
            Connection dbConn = DriverManager.getConnection(PgSqlSettings.getUrl(), PgSqlSettings.getUsername(), PgSqlSettings.getPassword());

            Statement st = dbConn.createStatement();

            ResultSet rs = st.executeQuery(
                "select * from Lectures where (id="+ID+")"
                //"select * from Lectures"
                );

            if (rs.next())
            {
                r = new Lecture(rs.getInt("ID"), rs.getString("name").replace("<apostrophe>", "'"),
                        rs.getInt("examTypeID"), rs.getString("comment").replace("<apostrophe>", "'"));
            }
            rs.close();
            st.close();
            dbConn.close();
        } catch (java.lang.Exception exc)
        {
            throw new TableException("An error occured while working with server: " + exc.toString());
        }

        return r;
    }


    public boolean remove(int ID) throws TableException
    {
        Boolean r=false;

        try
        {
            Class.forName(PgSqlSettings.getJdbcDriverClass()).newInstance();
        } catch (java.lang.Exception exc)
        {
            throw new TableException("Cannot load JDBC driver. It is porbably not installed correctly. Connection string is:  " + PgSqlSettings.getJdbcDriverClass() + "        " + exc.toString());
        }

        try
        {
            Connection dbConn = DriverManager.getConnection(PgSqlSettings.getUrl(), PgSqlSettings.getUsername(), PgSqlSettings.getPassword());

            Statement st = dbConn.createStatement();

            r = (st.executeUpdate("delete from Lectures where id="+ID+";") > 0);

            st.close();
            dbConn.close();
        } catch (java.lang.Exception exc)
        {
            throw new TableException("An error occured while working with server: " + exc.toString());
        }
        
        return r;
    }


    public java.util.Vector getAll() throws TableException
    {
        java.util.Vector r = new java.util.Vector();

        try
        {
            Class.forName(PgSqlSettings.getJdbcDriverClass()).newInstance();
        } catch (java.lang.Exception exc)
        {
            throw new TableException("Cannot load JDBC driver. It is porbably not installed correctly. Connection string is:  " + PgSqlSettings.getJdbcDriverClass() + "        " + exc.toString());
        }

        try
        {
            Connection dbConn = DriverManager.getConnection(PgSqlSettings.getUrl(), PgSqlSettings.getUsername(), PgSqlSettings.getPassword());

            Statement st = dbConn.createStatement();

            ResultSet rs = st.executeQuery("select * from Lectures");

            while (rs.next())
            {
                
                r.add(new Lecture(rs.getInt("ID"), rs.getString("name").replace("<apostrophe>", "'"),
                          rs.getInt("examTypeID"), rs.getString("comment").replace("<apostrophe>", "'")));
            }
            rs.close();
            st.close();
            dbConn.close();
        } catch (java.lang.Exception exc)
        {
            throw new TableException("An error occured while working with server: " + exc.toString());
        }

        return r;
    }
    

    public java.util.Vector get(int[] ids) throws TableException
    {
        java.util.Vector r = new java.util.Vector();

        try
        {
            Class.forName(PgSqlSettings.getJdbcDriverClass()).newInstance();
        } catch (java.lang.Exception exc)
        {
            throw new TableException("Cannot load JDBC driver. It is porbably not installed correctly. Connection string is:  " + PgSqlSettings.getJdbcDriverClass() + "        " + exc.toString());
        }

        if (ids.length > 0)
        {
            try
            {
                String cond = " where ";
                for (int i = 0; i < ids.length; i++)
                {
                    if (i > 0) cond += " or ";
                    cond += "(id="+ids[i]+")";
                }
                Connection dbConn = DriverManager.getConnection(PgSqlSettings.getUrl(), PgSqlSettings.getUsername(), PgSqlSettings.getPassword());
                Statement st = dbConn.createStatement();
                ResultSet rs = st.executeQuery("select * from Lectures" + cond);

                while (rs.next())
                {

                    r.add(new Lecture(rs.getInt("ID"), rs.getString("name").replace("<apostrophe>", "'"),
                              rs.getInt("examTypeID"), rs.getString("comment").replace("<apostrophe>", "'")));
                }
                rs.close();
                st.close();
                dbConn.close();
            } catch (java.lang.Exception exc)
            {
                throw new TableException("An error occured while working with server: " + exc.toString());
            }
        }

        return r;
    }


    /*
    public int findFreeID() throws TableException
    {
        int r = 0;

        try
        {
            Class.forName(PgSqlSettings.getJdbcDriverClass()).newInstance();
        } catch (java.lang.Exception exc)
        {
            throw new TableException("Cannot load JDBC driver. It is porbably not installed correctly. Connection string is:  " + PgSqlSettings.getJdbcDriverClass() + "        " + exc.toString());
        }

        try
        {
            Connection dbConn = DriverManager.getConnection(PgSqlSettings.getUrl(), PgSqlSettings.getUsername(), PgSqlSettings.getPassword());

            Statement st = dbConn.createStatement();

            ResultSet rs = st.executeQuery(
                "select MAX(ID) as mx from Lectures"
                //"select * from ExamTypes"
                );

            if (rs.next())
            {
                r = rs.getInt("mx");
            }
            rs.close();
            st.close();
            dbConn.close();
        } catch (java.lang.Exception exc)
        {
            throw new TableException("An error occured while working with server: " + exc.toString());
        }

        r++;
        return r;
    }

    public boolean insertWithNewID(Lecture item) throws TableException
    {
        boolean r = false;
        if (item != null)
        {
            item.setID(this.findFreeID());
            r = this.insert(item);
        }
        return r;
    }
     * 
     */
}
