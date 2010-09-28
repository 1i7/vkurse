using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

using VkurseClient.edu.phystech.vkurse.model;

namespace VkurseClient
{
    public partial class ScheduleInfoForm : Form
    {
        private int posY = 5;
        private const int posX = 120;

        public ScheduleInfoForm()
        {
            InitializeComponent();
        }

        private string WeekDayName(byte d)
        {
            string r = "";
            if (1 == d) r = "понедельник";
            if (2 == d) r = "вторник";
            if (3 == d) r = "среда";
            if (4 == d) r = "четверг";
            if (5 == d) r = "пятница";
            if (6 == d) r = "суббота";
            if (7 == d) r = "воскресенье";
            return r;
        }

        private void AddParam(string name, string val)
        {
            {
                Label l = new Label();
                l.Left = 5;
                l.Top = posY;
                l.Font = new Font(l.Font, FontStyle.Bold);
                l.Width = posX - l.Left - 5;
                l.Height = 15;
                l.Text = name;
                l.BackColor = Color.FromArgb(0, Color.Black);
                this.Controls.Add(l);
            }
            {
                Label l = new Label();
                l.Left = posX;
                l.Top = posY;
                //l.Font = new Font(l.Font, FontStyle.Bold);
                l.Width = this.Width - l.Left - 5;
                l.Height = 15;
                l.Text = val;
                l.BackColor = Color.FromArgb(0, Color.Black);
                this.Controls.Add(l);
            }
            posY += 15;
        }

        private void AddParamLn(string name, string val)
        {
            {
                Label l = new Label();
                l.Left = 5;
                l.Top = posY;
                l.Font = new Font(l.Font, FontStyle.Bold);
                l.Width = posX - l.Left - 5;
                l.Height = 15;
                l.Text = name;
                l.BackColor = Color.FromArgb(0, Color.Black);
                this.Controls.Add(l);
            }
            posY += 15;
            {
                Label l = new Label();
                l.Left = 5;
                l.Top = posY;
                //l.Font = new Font(l.Font, FontStyle.Bold);
                l.Width = this.Width - l.Left - 5;
                //l.Height = 15;
                l.Text = val;
                l.BackColor = Color.FromArgb(0, Color.Black);
                this.Controls.Add(l);
                posY += l.Height;
            }
        }

        public void SetData(int ID, TableFactory tableFactory)
        {
            ExamTypesTable examTypesTable;
            GroupsTable groupsTable;
            LecturesTable lecturesTable;
            RoomsTable roomsTable;
            ScheduleTable scheduleTable;
            // ScheduleChangesTable scheduleChangesTable;
            TeachersTable teachersTable;
            examTypesTable = tableFactory.getExamTypesTable();
            groupsTable = tableFactory.getGroupsTable();
            lecturesTable = tableFactory.getLecturesTable();
            roomsTable = tableFactory.getRoomsTable();
            scheduleTable = tableFactory.getScheduleTable();
            //scheduleChangesTable = tableFactory.getScheduleChangesTable();
            teachersTable = tableFactory.getTeachersTable();

            Schedule schedule = scheduleTable.get(ID);
            if (schedule != null)
            {
                Lecture lecture = lecturesTable.get(schedule.getLectureID());
                Group group = groupsTable.get(schedule.getGroupID());
                Room room = roomsTable.get(schedule.getRoomID());
                Teacher teacher = teachersTable.get(schedule.getTeacherID());
                ExamType examType = null;
                if (lecture != null) examType = examTypesTable.get(lecture.getExamTypeID());

                AddParam("Время:", WeekDayName(schedule.getDay()) + ", " +
                    Form1.TimeFromInt(schedule.getStartTime()) +
                    "  -  " + Form1.TimeFromInt(schedule.getStartTime() + schedule.getLength()) +
                    "  (" + schedule.getLength() + " минут)");

                string gn = "-"; if (group != null) gn = group.getName() + "  (курс: " + group.getCourse() + ")";
                string ln = "-"; if (lecture != null) ln = lecture.getName();
                string rn = "-"; if (room != null) rn = room.getName();
                string tn = "-"; if (teacher != null) tn = teacher.getName();
                string en = "-"; if (examType != null) en = examType.getName();

                AddParam("Предмет:", ln);
                AddParam("Аудитория:", rn);
                AddParam("Группа:", gn);
                AddParam("Преподаватель:", tn);
                AddParam("Отчетность:", en);
                AddParamLn("Комментарий:", schedule.getComment());
            }
        }

    }
}
