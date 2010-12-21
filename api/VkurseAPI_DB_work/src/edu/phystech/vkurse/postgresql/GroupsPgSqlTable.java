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
public class GroupsPgSqlTable implements GroupsTable
{
    public int insert(Group item) throws TableException
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

                /*
                System.out.println("insert into Groups values(" +
                        item.getID() + ", '" + item.getName() + "'" +
                        ");");
                 */
                st.executeUpdate("insert into Groups(name, course) values(" +
                        "'" + item.getName().replace("'", "<apostrophe>") + "', '" + item.getCourse().replace("'", "<apostrophe>") + "'" +
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
                    ResultSet rsgfid = stgfid.executeQuery("SELECT last_value FROM groups_id_seq");
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


    public boolean update(Group item) throws TableException
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

                String cmd = "update Groups set " +
                        "name = '" + item.getName().replace("'", "<apostrophe>") + "', " +
                        "course = '" + item.getCourse().replace("'", "<apostrophe>") + "' " +
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


    public Group get(int ID) throws TableException
    {
        Group r = null;

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
                "select * from Groups where (id="+ID+")"
                //"select * from Groups"
                );

            if (rs.next())
            {
                r = new Group(rs.getInt("ID"), rs.getString("name").replace("'", "<apostrophe>"), rs.getString("course").replace("'", "<apostrophe>"));
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

            r = (st.executeUpdate("delete from Groups where id="+ID+";") > 0);

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

            ResultSet rs = st.executeQuery("select * from Groups");

            while (rs.next())
            {
                r.add(new Group(rs.getInt("ID"), rs.getString("name").replace("<apostrophe>", "'"), rs.getString("course").replace("<apostrophe>", "'")));
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
                ResultSet rs = st.executeQuery("select * from Groups" + cond);

                while (rs.next())
                {
                    r.add(new Group(rs.getInt("ID"), rs.getString("name").replace("<apostrophe>", "'"), rs.getString("course").replace("<apostrophe>", "'")));
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
                "select MAX(ID) as mx from Groups"
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

    public boolean insertWithNewID(Group item) throws TableException
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
