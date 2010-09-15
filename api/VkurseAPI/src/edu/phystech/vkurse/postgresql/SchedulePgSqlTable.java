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
public class SchedulePgSqlTable implements ScheduleTable
{
    public boolean insert(Schedule item) throws TableException
    {
        Boolean r=false;

        if (item != null)
        {
            try
            {
                Class.forName(PgSqlSettings.getJdbcDriverClass()).newInstance();
            } catch (java.lang.Exception exc)
            {
                throw new TableException("Cannot load JDBC driver. It is porbably not installed correctly. Connection string is:  "+PgSqlSettings.getJdbcDriverClass(), exc);
            }

            try
            {
                Connection dbConn = DriverManager.getConnection(PgSqlSettings.getUrl(), PgSqlSettings.getUsername(), PgSqlSettings.getPassword());

                Statement st = dbConn.createStatement();

                /*
                System.out.println("insert into Schedule values(" +
                        item.getID() + ", '" + item.getName() + "'" +
                        ");");
                 */
                r = (st.executeUpdate("insert into Schedule values(" +
                        item.getID() + ", " +
                        item.getGroupID() + ", " +
                        item.getDay() + ", " +
                        item.getStartTime() + ", " +
                        item.getLength() + ", " +
                        item.getLectureID() + ", " +
                        item.getRoomID() + ", " +
                        item.getTeacherID() + ", " +
                        "'" + item.getComment() + "'" +
                        ");") > 0);

                st.close();
                dbConn.close();
            } catch (java.lang.Exception exc)
            {
                throw new TableException("An error occured while working with server: " + exc.toString(), exc);
            }
        }
        return r;
    }


    public boolean update(Schedule item) throws TableException
    {
        Boolean r=false;

        if (item != null)
        {
            try
            {
                Class.forName(PgSqlSettings.getJdbcDriverClass()).newInstance();
            } catch (java.lang.Exception exc)
            {
                throw new TableException("Cannot load JDBC driver. It is porbably not installed correctly. Connection string is:  "+PgSqlSettings.getJdbcDriverClass(), exc);
            }

            try
            {
                Connection dbConn = DriverManager.getConnection(PgSqlSettings.getUrl(), PgSqlSettings.getUsername(), PgSqlSettings.getPassword());

                Statement st = dbConn.createStatement();

                String cmd = "update Schedule set " +
                        "groupID = " + item.getGroupID() + ", " +
                        "day = " + item.getDay() + ", " +
                        "startTime = " + item.getStartTime() + ", " +
                        "length = " + item.getLength() + ", " +
                        "lectureID = " + item.getLectureID() + ", " +
                        "roomID = " + item.getRoomID() + ", " +
                        "teacherID = " + item.getTeacherID() + ", " +
                        "comment = '" + item.getComment() + "'" +
                        " where ID="+item.getID()+";";
                r = (st.executeUpdate(cmd) > 0);

                st.close();
                dbConn.close();
            } catch (java.lang.Exception exc)
            {
                throw new TableException("An error occured while working with server: " + exc.toString(), exc);
            }
        }
        return r;
    }


    public Schedule get(int ID) throws TableException
    {
        Schedule r = null;

        try
        {
            Class.forName(PgSqlSettings.getJdbcDriverClass()).newInstance();
        } catch (java.lang.Exception exc)
        {
            throw new TableException("Cannot load JDBC driver. It is porbably not installed correctly. Connection string is:  "+PgSqlSettings.getJdbcDriverClass(), exc);
        }

        try
        {
            Connection dbConn = DriverManager.getConnection(PgSqlSettings.getUrl(), PgSqlSettings.getUsername(), PgSqlSettings.getPassword());

            Statement st = dbConn.createStatement();

            ResultSet rs = st.executeQuery(
                "select * from Schedule where (id="+ID+")"
                //"select * from Schedule"
                );

            if (rs.next())
            {
                r = new Schedule(
                        rs.getInt("ID"),
                        rs.getInt("groupID"),
                        (byte)rs.getInt("day"),
                        rs.getInt("startTime"),
                        rs.getInt("length"),
                        rs.getInt("lectureID"),
                        rs.getInt("roomID"),
                        rs.getInt("teacherID"),
                        rs.getString("comment")
                        );
            }
                        /*
                        item.getID() + ", " +
                        item.getDay() + ", " +
                        item.getStartTime() + ", " +
                        item.getLength() + ", " +
                        item.getLectureID() + ", " +
                        item.getRoomID() + ", " +
                        item.getTeacherID() + ", " +
                        "'" + item.getComment() + "'" +
                         */
            rs.close();
            st.close();
            dbConn.close();
        } catch (java.lang.Exception exc)
        {
            throw new TableException("An error occured while working with server: " + exc.toString(), exc);
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
            throw new TableException("Cannot load JDBC driver. It is porbably not installed correctly. Connection string is:  "+PgSqlSettings.getJdbcDriverClass(), exc);
        }

        try
        {
            Connection dbConn = DriverManager.getConnection(PgSqlSettings.getUrl(), PgSqlSettings.getUsername(), PgSqlSettings.getPassword());

            Statement st = dbConn.createStatement();

            r = (st.executeUpdate("delete from Schedule where id="+ID+";") > 0);

            st.close();
            dbConn.close();
        } catch (java.lang.Exception exc)
        {
            throw new TableException("An error occured while working with server: " + exc.toString(), exc);
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
            throw new TableException("Cannot load JDBC driver. It is porbably not installed correctly. Connection string is:  "+PgSqlSettings.getJdbcDriverClass(), exc);
        }

        try
        {
            Connection dbConn = DriverManager.getConnection(PgSqlSettings.getUrl(), PgSqlSettings.getUsername(), PgSqlSettings.getPassword());

            Statement st = dbConn.createStatement();

            ResultSet rs = st.executeQuery("select * from Schedule");

            while (rs.next())
            {
                Schedule s = new Schedule(
                        rs.getInt("ID"),
                        rs.getInt("groupID"),
                        (byte)rs.getInt("day"),
                        rs.getInt("startTime"),
                        rs.getInt("length"),
                        rs.getInt("lectureID"),
                        rs.getInt("roomID"),
                        rs.getInt("teacherID"),
                        rs.getString("comment")
                        );

                r.add(s);
            }
            rs.close();
            st.close();
            dbConn.close();
        } catch (java.lang.Exception exc)
        {
            throw new TableException("An error occured while working with server: " + exc.toString(), exc);
        }

        return r;
    }


    public java.util.Vector findByGroupDay(int groupID, byte day) throws TableException
    {
        java.util.Vector r = new java.util.Vector();

        try
        {
            Class.forName(PgSqlSettings.getJdbcDriverClass()).newInstance();
        } catch (java.lang.Exception exc)
        {
            throw new TableException("Cannot load JDBC driver. It is porbably not installed correctly. Connection string is:  "+PgSqlSettings.getJdbcDriverClass(), exc);
        }

        try
        {
            Connection dbConn = DriverManager.getConnection(PgSqlSettings.getUrl(), PgSqlSettings.getUsername(), PgSqlSettings.getPassword());

            Statement st = dbConn.createStatement();

            ResultSet rs = st.executeQuery(
                    "select * from " + PgSqlSettings.getDbName() + ".Schedule" +
                    " where day=" + day + " and groupID=" + groupID
                    );

            while (rs.next())
            {
                Schedule s = new Schedule(
                        rs.getInt("ID"),
                        rs.getInt("groupID"),
                        (byte)rs.getInt("day"),
                        rs.getInt("startTime"),
                        rs.getInt("length"),
                        rs.getInt("lectureID"),
                        rs.getInt("roomID"),
                        rs.getInt("teacherID"),
                        rs.getString("comment")
                        );

                r.add(s);
            }
            rs.close();
            st.close();
            dbConn.close();
        } catch (java.lang.Exception exc)
        {
            throw new TableException("An error occured while working with server: " + exc.toString(), exc);
        }

        return r;
    }
}
