using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

using VkurseClient.edu.phystech.vkurse.model;


namespace VkurseClient.edu.phystech.vkurse.soap
{

    public class SoapTableFactory: TableFactory
    {
        public ExamTypesTable getExamTypesTable()
        {
            return new ExamTypesSoapTable();
        }

        public GroupsTable getGroupsTable()
        {
            return new GroupsSoapTable();
        }

        public LecturesTable getLecturesTable()
        {
            return new LecturesSoapTable();
        }

        public RoomsTable getRoomsTable()
        {
            return new RoomsSoapTable();
        }

        public ScheduleTable getScheduleTable()
        {
            return new ScheduleSoapTable();
        }

        public ScheduleChangesTable getScheduleChangesTable()
        {
            return new ScheduleChangesSoapTable();
        }

        public TeachersTable getTeachersTable()
        {
            return new TeachersSoapTable();
        }

        public string getSourceName()
        {
            
            return "SOAP";
        }
    }

}

