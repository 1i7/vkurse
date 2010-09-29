using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

using VkurseClient.edu.phystech.vkurse.model;
using VkurseClient.edu.phystech.vkurse.soap;

namespace VkurseClient
{
    public partial class Form1 : Form
    {
        public TableFactory tableFactory;
        public ExamTypesTable examTypesTable;
        public GroupsTable groupsTable;
        public LecturesTable lecturesTable;
        public RoomsTable roomsTable;
        public ScheduleTable scheduleTable;
        public ScheduleChangesTable scheduleChangesTable;
        public TeachersTable teachersTable;

        private List<int> groupIDlist = new List<int>();
        private int currentGroupID = 2;

        private List<Panel> panelsShown = new List<Panel>();


        public Form1()
        {
            InitializeComponent();

            tableFactory = new SoapTableFactory();
            examTypesTable = tableFactory.getExamTypesTable();
            groupsTable = tableFactory.getGroupsTable();
            lecturesTable = tableFactory.getLecturesTable();
            roomsTable = tableFactory.getRoomsTable();
            scheduleTable = tableFactory.getScheduleTable();
            scheduleChangesTable = tableFactory.getScheduleChangesTable();
            teachersTable = tableFactory.getTeachersTable();

            monthCalendar1.SelectionRange.Start = DateTime.Now;
            monthCalendar1.SelectionRange.End = DateTime.Now;

            LoadingForm lf = new LoadingForm();
            lf.Show();
            Application.DoEvents();
            RefreshGroupsList();
            ShowSchedule();
            lf.Close();
        }


        public void AddLog(string s)
        {
            //textBox1.AppendText(s + "\r\n");
            Console.WriteLine(s);
        }


        public static string TimeFromInt(int tm)
        {
            string r = "";
            int h = tm / 60;
            int m = tm % 60;
            string hs = h.ToString(); if (hs.Length < 2) hs = "0" + hs;
            string ms = m.ToString(); if (ms.Length < 2) ms = "0" + ms;
            r = hs + ":" + ms;
            return r;
        }


        private Panel CreateElementPanel(int ID, string name, string room, string teacher, int start, int len)
        {
            Panel pn = new Panel();
            pn.Tag = ID;
            pn.Top = 5 + 40 * (start - 540) / 85;
            pn.Left = 5;
            pn.Width = 350;
            //pn.Height = 40;
            pn.Height = 40 * len / 85;
            //pn.BackgroundImage = Image.FromFile(@"D:\Photo\Эксперименты над флешкой телефона\100SSCAM\S3020099.JPG");
            pn.BackColor = Color.LightGray;
            //pn.BackColor = Color.DodgerBlue;
            pn.Click += new System.EventHandler(this.panel_Click);

            {
                Label l = new Label();
                l.Left = 5;
                l.Top = 20;
                //l.Font = new Font(l.Font, FontStyle.Bold);
                l.Width = 50 - l.Left;
                l.Text = TimeFromInt(start + len);
                l.BackColor = Color.FromArgb(0, Color.Black);
                l.Click += new System.EventHandler(this.label_Click);
                pn.Controls.Add(l);
            }

            {
                Label l = new Label();
                l.Left = 5;
                l.Top = 5;
                //l.Font = new Font(l.Font, FontStyle.Bold);
                l.Width = 50 - l.Left;
                l.Text = TimeFromInt(start);
                l.BackColor = Color.FromArgb(0, Color.Black);
                l.Click += new System.EventHandler(this.label_Click);
                pn.Controls.Add(l);
            }
            /*
            {
                Label l = new Label();
                l.Left = 50;
                l.Top = 35;
                //l.Font = new Font(l.Font, FontStyle.Bold);
                l.Width = pn.Width - l.Left - 5;
                l.Text = teacher;
                l.BackColor = Color.FromArgb(0, Color.Black);
                l.Click += new System.EventHandler(this.label_Click);
                pn.Controls.Add(l);
            }
            */
            {
                Label l = new Label();
                l.Left = 50;
                l.Top = 20;
                //l.Font = new Font(l.Font, FontStyle.Bold);
                l.Width = pn.Width - l.Left - 5;
                l.Text = teacher + "     " + room;
                l.BackColor = Color.FromArgb(0, Color.Black);
                l.Click += new System.EventHandler(this.label_Click);
                pn.Controls.Add(l);
            }
            
            {
                Label l = new Label();
                l.Left = 50;
                l.Top = 5;
                l.Font = new Font(l.Font, FontStyle.Bold);
                l.Width = pn.Width - l.Left - 5;
                l.Text = name;
                l.BackColor = Color.FromArgb(0, Color.Black);
                l.Click += new System.EventHandler(this.label_Click);
                pn.Controls.Add(l);
            }

            return pn;
        }


        private Panel AddLecturePanel(int ID, string name, string room, string teacher, int start, int len)
        {
            //Panel pn = CreateElementPanel("Математический анализ", "123 ГК", "Знаменская Л.Н.", 540, 540 + 30);
            Panel pn = CreateElementPanel(ID, name, room, teacher, start, len);
            panelsShown.Add(pn);
            this.Controls.Add(pn);
            return pn;
        }


        private void ClearLecturePanels()
        {
            for (int i = 0; i < panelsShown.Count; i++)
            {
                this.Controls.Remove(panelsShown[i]);
            }
            panelsShown.Clear();
        }


        public void RefreshGroupsList()
        {
            this.groupComboBox.Items.Clear();
            groupIDlist.Clear();

            List<Group> grs = groupsTable.getAll();
            for (int i = 0; i < grs.Count; i++)
            {
                Group gr = grs[i];
                groupIDlist.Add(gr.getID());
                groupComboBox.Items.Add(gr.getName() + "  (курс: " + gr.getCourse() + ")");
                if (gr.getID() == currentGroupID) groupComboBox.SelectedIndex = i;
            }
        }

        private void monthCalendar1_DateSelected(object sender, DateRangeEventArgs e)
        {
            ShowSchedule();
        }

        private void ShowSchedule()
        {
            ClearLecturePanels();
            DateTime d = monthCalendar1.SelectionRange.Start;
            byte dw = (byte)d.DayOfWeek;
            //Console.WriteLine(d.DayOfWeek.ToString());
            //Console.WriteLine(dw);

            List<Schedule> scs = scheduleTable.findByGroupDay(currentGroupID, dw);

            for (int i = 0; i < scs.Count; i++)
            {
                Schedule sc = scs[i];
                Lecture l = lecturesTable.get(sc.getLectureID());
                Teacher t = teachersTable.get(sc.getTeacherID());
                Room r    = roomsTable.get(sc.getRoomID());
                string ls = ""; if (l != null) ls = l.getName();
                string ts = ""; if (t != null) ts = t.getName();
                string rs = ""; if (r != null) rs = r.getName();
                //AddLecturePanel("" + sc.getID() + "   " + ls, rs, ts, sc.getStartTime(), sc.getLength());
                AddLecturePanel(sc.getID(), ls, rs, ts, sc.getStartTime(), sc.getLength());
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            int a = groupComboBox.SelectedIndex;
            if (a >= 0)
            {
                currentGroupID = groupIDlist[a];
            }

            ShowSchedule();
        }

        private void panel_Click(object sender, EventArgs e)
        {
            Panel pn = (Panel)sender;
            ProcessPanelClick((int)pn.Tag);
        }

        private void label_Click(object sender, EventArgs e)
        {
            Label lb = (Label)sender;
            Panel pn = (Panel)lb.Parent;
            ProcessPanelClick((int)pn.Tag);
        }

        private void ProcessPanelClick(int ID)
        {
            Console.WriteLine(ID);
            ScheduleInfoForm infoForm = new ScheduleInfoForm();
            infoForm.SetData(ID, tableFactory);
            infoForm.Show();
        }
        
        
        
        
        /*
        private void button1_Click(object sender, EventArgs e)
        {
            TableFactory tf = new SoapTableFactory();
            ScheduleTable tb = tf.getScheduleTable();
            Schedule obj;

            obj = tb.get(666);
            if (obj != null)
            {
                AddLog("   Schedule:");
                AddLog("ID = " + obj.getID());
                //AddLog("scheduleID = " + obj.getScheduleID());
                //AddLog("week = " + obj.getWeek());
                AddLog("groupID = " + obj.getGroupID());
                AddLog("day = " + obj.getDay());
                AddLog("startTime = " + obj.getStartTime());
                AddLog("length = " + obj.getLength());
                AddLog("lectureID = " + obj.getLectureID());
                AddLog("roomID = " + obj.getRoomID());
                AddLog("teacherID = " + obj.getTeacherID());
                AddLog("comment = " + obj.getComment());
                //AddLog("scheduleID = " + obj.getScheduleID());
                //AddLog("comment = " + obj.getComment());
            }
            else
            {
                AddLog("   Schedule:  null");
            }
        }

        private void button2_Click(object sender, EventArgs e)
        {
            TableFactory tf = new SoapTableFactory();
            ScheduleTable tb = tf.getScheduleTable();
            Schedule obj;

            //obj = new Schedule(666, "This is name", 13, "this is comment");
            obj = new Schedule(666, 0, (byte)1, 540, 85, 0, 0, 0, "it is 'comment' of change");
            AddLog("Insert: " + obj);
            bool r = tb.insert(obj);
            AddLog("Result: " + r);
        }

        private void button3_Click(object sender, EventArgs e)
        {
            TableFactory tf = new SoapTableFactory();
            ScheduleTable tb = tf.getScheduleTable();
            Schedule obj;

            //obj = new Schedule(666, "This is 'updated' name", 13, "this is 'updated' comment");
            obj = new Schedule(666, 3, (byte)4, 340, 185, 10, 10, 10, "it is 'updated' comment of change");
            AddLog("update: " + obj);
            bool r = tb.update(obj);
            AddLog("Result: " + r);
        }

        private void button4_Click(object sender, EventArgs e)
        {
            TableFactory tf = new SoapTableFactory();
            ScheduleTable tb = tf.getScheduleTable();

            int ID = 666;
            AddLog("Insert: " + ID);
            bool r = tb.remove(ID);
            AddLog("Result: " + r);
        }

        private void button5_Click(object sender, EventArgs e)
        {
            TableFactory tf = new SoapTableFactory();
            ScheduleTable tb = tf.getScheduleTable();

            List<Schedule> all = tb.getAll();
            for (int i = 0; i<all.Count; i++)
            {
                Schedule obj = all[i];
                AddLog("   Schedule(" + i + "):");
                AddLog("ID = " + obj.getID());
                //AddLog("scheduleID = " + obj.getScheduleID());
                //AddLog("week = " + obj.getWeek());
                AddLog("groupID = " + obj.getGroupID());
                AddLog("day = " + obj.getDay());
                AddLog("startTime = " + obj.getStartTime());
                AddLog("length = " + obj.getLength());
                AddLog("lectureID = " + obj.getLectureID());
                AddLog("roomID = " + obj.getRoomID());
                AddLog("teacherID = " + obj.getTeacherID());
                AddLog("comment = " + obj.getComment());
                //AddLog("course = " + obj.getCourse());
                //AddLog("scheduleID = " + obj.getScheduleID());
                //AddLog("comment = " + obj.getComment());
            }
        }

        private void button6_Click(object sender, EventArgs e)
        {
            TableFactory tf = new SoapTableFactory();
            ScheduleTable tb = tf.getScheduleTable();

            List<Schedule> all = tb.findByGroupDay(0, 1);
            for (int i = 0; i < all.Count; i++)
            {
                Schedule obj = all[i];
                AddLog("   Schedule(" + i + "):");
                AddLog("ID = " + obj.getID());
                //AddLog("scheduleID = " + obj.getScheduleID());
                //ddLog("week = " + obj.getWeek());
                AddLog("groupID = " + obj.getGroupID());
                AddLog("day = " + obj.getDay());
                AddLog("startTime = " + obj.getStartTime());
                AddLog("length = " + obj.getLength());
                AddLog("lectureID = " + obj.getLectureID());
                AddLog("roomID = " + obj.getRoomID());
                AddLog("teacherID = " + obj.getTeacherID());
                AddLog("comment = " + obj.getComment());
                //AddLog("course = " + obj.getCourse());
                //AddLog("scheduleID = " + obj.getScheduleID());
                //AddLog("comment = " + obj.getComment());
            }
        }
        */

    }
}
