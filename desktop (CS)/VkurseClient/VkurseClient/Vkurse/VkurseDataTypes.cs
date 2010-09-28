using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;


namespace VkurseClient.edu.phystech.vkurse.model
{
    public static class DebugHelper
    {
        public static void AddLog(string s)
        {
            Console.WriteLine(s);
        }

    }


    /*
    public class TableException : Exception
    {
        public TableException(string message){ (message); }
        public TableException(string message, Exception cause) { super(message, cause); }
        public TableException(Exception cause) { super(cause); }
    }
    */


    public abstract class DbTableRecord
    {
        protected int id;

        public void setID(int newID)
        {
            id = newID;
        }

        public int getID()
        {
            return id;
        }


        public void readData(string data)
        {
            string p = "";
            bool inStr = false;
            int i;
            for (i = 0; i < data.Length; i++)
            {
                string c = data.Substring(i, 1);
                p += c;
                if ((!inStr) && (c.Equals(" ")))
                {
                    readParam(p);
                    p = "";
                }
                if (c.Equals("'")) inStr = !inStr;
            }
            if (!p.Equals("")) readParam(p);
        }

        private void readParam(string p)
        {
            string d = p;
            while (d.StartsWith(" ")) d = d.Substring(1, d.Length - 1);
            while (d.EndsWith(" ")) d = d.Substring(0, d.Length - 1);
            int a = d.IndexOf("=");
            if (a > 0)
            {
                string l = d.Substring(0, a);
                string r = d.Substring(a + 1, d.Length - a - 1);
                if (r.StartsWith("'")) r = r.Substring(1, r.Length - 1);
                if (r.EndsWith("'")) r = r.Substring(0, r.Length - 1);
                r = r.Replace("<apostrophe>", "'");
                setData(l, r);
            }
        }

        abstract public void setData(string n, string d);
    }


    public class ExamType : DbTableRecord
    {
        protected string name;

        public ExamType() { }
        public ExamType(int id, string name)
        {
            this.id = id;
            this.name = name;
        }

        public void setName(string newName)
        {
            name = newName;
        }
        public string getName()
        {
            return name;
        }

        override public string ToString()
        {
            string r = "ID=" + id + " ";
            r += "name='" + name.Replace("'", "<apostrophe>") + "' ";
            r = r.Trim();
            return r;
        }

        override public void setData(string n, string d)
        {
            if (n.Equals("ID")) this.id = Convert.ToInt32(d);
            if (n.Equals("name")) this.name = d;
        }
    }


    public class Group : DbTableRecord
    {
        protected string name;
        protected string course;

        public Group() { }
        public Group(int id, string name, string course)
        {
            this.id = id;
            this.name = name;
            this.course = course;
        }

        public string getName()
        {
            return name;
        }

        public void setName(string name)
        {
            this.name = name;
        }

        public string getCourse()
        {
            return course;
        }

        public void setCourse(string course)
        {
            this.course = course;
        }

        override public string ToString()
        {
            string r = "ID=" + id + " ";
            r += "name='" + name.Replace("'", "<apostrophe>") + "' ";
            r += "course='" + course.Replace("'", "<apostrophe>") + "' ";
            r = r.Trim();
            return r;
        }

        override public void setData(string n, string d)
        {
            if (n.Equals("ID")) this.id = Convert.ToInt32(d);
            if (n.Equals("name")) this.name = d;
            if (n.Equals("course")) this.course = d;
        }
    }


    public class Lecture : DbTableRecord
    {
        protected string name;
        protected int examTypeID;
        protected string comment;

        public Lecture() { }
        public Lecture(int id, string name, int examTypeID, string comment)
        {
            this.id = id;
            this.name = name;
            this.examTypeID = examTypeID;
            this.comment = comment;
        }

        public string getName()
        {
            return name;
        }

        public void setName(string name)
        {
            this.name = name;
        }

        public int getExamTypeID()
        {
            return examTypeID;
        }

        public void setExamTypeID(int examTypeID)
        {
            this.examTypeID = examTypeID;
        }

        public string getComment()
        {
            return comment;
        }

        public void setComment(string comment)
        {
            this.comment = comment;
        }

        override public string ToString()
        {
            string r = "ID=" + id + " ";
            r += "name='" + name.Replace("'", "<apostrophe>") + "' ";
            r += "examTypeID=" + examTypeID + " ";
            r += "comment='" + comment.Replace("'", "<apostrophe>") + "' ";
            r = r.Trim();
            return r;
        }

        override public void setData(string n, string d)
        {
            if (n.Equals("ID")) this.id = Convert.ToInt32(d);
            if (n.Equals("name")) this.name = d;
            if (n.Equals("examTypeID")) this.examTypeID = Convert.ToInt32(d);
            if (n.Equals("comment")) this.comment = d;
        }
    }


    public class Room : DbTableRecord
    {
        protected string name;

        public Room() { }
        public Room(int id, string name)
        {
            this.id = id;
            this.name = name;
        }

        public string getName()
        {
            return name;
        }

        public void setName(string name)
        {
            this.name = name;
        }

        override public string ToString()
        {
            string r = "ID=" + id + " ";
            r += "name='" + name.Replace("'", "<apostrophe>") + "' ";
            r = r.Trim();
            return r;
        }

        override public void setData(string n, string d)
        {
            if (n.Equals("ID")) this.id = Convert.ToInt32(d);
            if (n.Equals("name")) this.name = d;
        }
    }


    public class Schedule : DbTableRecord
    {
        protected int groupID;
        protected byte day;
        protected int startTime;
        protected int length;
        protected int lectureID;
        protected int roomID;
        protected int teacherID;
        protected string comment;

        public Schedule() { }
        public Schedule(int id, int groupID, byte day, int startTime, int length,
                int lectureID, int roomID, int teacherID, string comment)
        {
            this.id = id;
            this.groupID = groupID;
            this.day = day;
            this.startTime = startTime;
            this.length = length;
            this.lectureID = lectureID;
            this.roomID = roomID;
            this.teacherID = teacherID;
            this.comment = comment;
        }

        public int getGroupID()
        {
            return groupID;
        }

        public void setGroupID(int groupID)
        {
            this.groupID = groupID;
        }

        public byte getDay()
        {
            return day;
        }

        public void setDay(byte day)
        {
            this.day = day;
        }

        public int getStartTime()
        {
            return startTime;
        }

        public void setStartTime(int startTime)
        {
            this.startTime = startTime;
        }

        public int getLength()
        {
            return length;
        }

        public void setLength(int length)
        {
            this.length = length;
        }

        public int getLectureID()
        {
            return lectureID;
        }

        public void setLectureID(int lectureID)
        {
            this.lectureID = lectureID;
        }

        public int getRoomID()
        {
            return roomID;
        }

        public void setRoomID(int roomID)
        {
            this.roomID = roomID;
        }

        public int getTeacherID()
        {
            return teacherID;
        }

        public void setTeacherID(int teacherID)
        {
            this.teacherID = teacherID;
        }

        public string getComment()
        {
            return comment;
        }

        public void setComment(string comment)
        {
            this.comment = comment;
        }

        override public string ToString()
        {
            string r = "ID=" + id + " ";
            r += "groupID=" + groupID + " ";
            r += "day=" + day + " ";
            r += "startTime=" + startTime + " ";
            r += "length=" + length + " ";
            r += "lectureID=" + lectureID + " ";
            r += "roomID=" + roomID + " ";
            r += "teacherID=" + teacherID + " ";
            r += "comment='" + comment.Replace("'", "<apostrophe>") + "' ";
            r = r.Trim();
            return r;
        }

        override public void setData(string n, string d)
        {
            if (n.Equals("ID")) this.id = Convert.ToInt32(d);
            if (n.Equals("groupID")) this.groupID = Convert.ToInt32(d);
            if (n.Equals("day")) this.day = (byte)Convert.ToInt32(d);
            if (n.Equals("startTime")) this.startTime = Convert.ToInt32(d);
            if (n.Equals("length")) this.length = Convert.ToInt32(d);
            if (n.Equals("lectureID")) this.lectureID = Convert.ToInt32(d);
            if (n.Equals("roomID")) this.roomID = Convert.ToInt32(d);
            if (n.Equals("teacherID")) this.teacherID = Convert.ToInt32(d);
            if (n.Equals("comment")) this.comment = d;
        }
    }


    public class ScheduleChange : DbTableRecord
    {
        protected int scheduleID;
        protected int week;
        protected int groupID;
        protected byte day;
        protected int startTime;
        protected int length;
        protected int lectureID;
        protected int roomID;
        protected int teacherID;
        protected string comment;

        public ScheduleChange() { }
        public ScheduleChange(int id, int scheduleID, int week, int groupID, byte day, int startTime, int length, int lectureID, int roomID, int teacherID, string comment)
        {
            this.id = id;
            this.scheduleID = scheduleID;
            this.week = week;
            this.groupID = groupID;
            this.day = day;
            this.startTime = startTime;
            this.length = length;
            this.lectureID = lectureID;
            this.roomID = roomID;
            this.teacherID = teacherID;
            this.comment = comment;
        }

        public int getScheduleID()
        {
            return scheduleID;
        }

        public void setScheduleID(int scheduleID)
        {
            this.scheduleID = scheduleID;
        }

        public int getWeek()
        {
            return week;
        }

        public void setWeek(int week)
        {
            this.week = week;
        }

        public int getGroupID()
        {
            return groupID;
        }

        public void setGroupID(int groupID)
        {
            this.groupID = groupID;
        }

        public byte getDay()
        {
            return day;
        }

        public void setDay(byte day)
        {
            this.day = day;
        }

        public int getStartTime()
        {
            return startTime;
        }

        public void setStartTime(int startTime)
        {
            this.startTime = startTime;
        }

        public int getLength()
        {
            return length;
        }

        public void setLength(int length)
        {
            this.length = length;
        }

        public int getLectureID()
        {
            return lectureID;
        }

        public void setLectureID(int lectureID)
        {
            this.lectureID = lectureID;
        }

        public int getRoomID()
        {
            return roomID;
        }

        public void setRoomID(int roomID)
        {
            this.roomID = roomID;
        }

        public int getTeacherID()
        {
            return teacherID;
        }

        public void setTeacherID(int teacherID)
        {
            this.teacherID = teacherID;
        }

        public string getComment()
        {
            return comment;
        }

        public void setComment(string comment)
        {
            this.comment = comment;
        }

        override public string ToString()
        {
            string r = "ID=" + id + " ";
            r += "scheduleID=" + scheduleID + " ";
            r += "week=" + week + " ";
            r += "groupID=" + groupID + " ";
            r += "day=" + day + " ";
            r += "startTime=" + startTime + " ";
            r += "length=" + length + " ";
            r += "lectureID=" + lectureID + " ";
            r += "roomID=" + roomID + " ";
            r += "teacherID=" + teacherID + " ";
            r += "comment='" + comment.Replace("'", "<apostrophe>") + "' ";
            r = r.Trim();
            return r;
        }

        override public void setData(string n, string d)
        {
            if (n.Equals("ID")) this.id = Convert.ToInt32(d);
            if (n.Equals("scheduleID")) this.scheduleID = Convert.ToInt32(d);
            if (n.Equals("week")) this.week = Convert.ToInt32(d);
            if (n.Equals("groupID")) this.groupID = Convert.ToInt32(d);
            if (n.Equals("day")) this.day = (byte)Convert.ToInt32(d);
            if (n.Equals("startTime")) this.startTime = Convert.ToInt32(d);
            if (n.Equals("length")) this.length = Convert.ToInt32(d);
            if (n.Equals("lectureID")) this.lectureID = Convert.ToInt32(d);
            if (n.Equals("roomID")) this.roomID = Convert.ToInt32(d);
            if (n.Equals("teacherID")) this.teacherID = Convert.ToInt32(d);
            if (n.Equals("comment")) this.comment = d;
        }
    }


    public class Teacher : DbTableRecord
    {
        protected string name;
        protected string degree;

        public Teacher() { }
        public Teacher(int id, string name, string degree)
        {
            this.id = id;
            this.name = name;
            this.degree = degree;
        }

        public string getName()
        {
            return name;
        }

        public void setName(string name)
        {
            this.name = name;
        }

        public string getDegree()
        {
            return degree;
        }

        public void setDegree(string degree)
        {
            this.degree = degree;
        }

        override public string ToString()
        {
            string r = "ID=" + id + " ";
            r += "name='" + name.Replace("'", "<apostrophe>") + "' ";
            r += "degree='" + degree.Replace("'", "<apostrophe>") + "' ";
            r = r.Trim();
            return r;
        }

        override public void setData(string n, string d)
        {
            if (n.Equals("ID")) this.id = Convert.ToInt32(d);
            if (n.Equals("name")) this.name = d;
            if (n.Equals("degree")) this.degree = d;
        }
    }

}
