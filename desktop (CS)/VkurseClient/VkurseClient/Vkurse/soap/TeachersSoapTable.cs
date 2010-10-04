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

    public class TeachersSoapTable : TeachersTable
    {

        public bool insert(Teacher item)
        {
            bool r = false;
            if (item != null)
            {
                TeacherService.TeacherService client = new TeacherService.TeacherServiceClient();
                try
                {
                    TeacherService.insertRequest request = new TeacherService.insertRequest();
                    request.teacher = item.ToString();
                    DebugHelper.AddLog("insert:  " + request.teacher);
                    TeacherService.insertResponse response = client.insert(request);
                    DebugHelper.AddLog("Response: " + response.insertReturn);
                    r = response.insertReturn;
                }
                catch (Exception ex)
                {
                    DebugHelper.AddLog("Client exception: " + ex);
                }
            }
            return r;
        }


        public bool update(Teacher item)
        {
            bool r = false;

            if (item != null)
            {
                TeacherService.TeacherService client = new TeacherService.TeacherServiceClient();
                try
                {
                    TeacherService.updateRequest request = new TeacherService.updateRequest();
                    request.teacher = item.ToString();
                    DebugHelper.AddLog("update:  " + request.teacher);
                    TeacherService.updateResponse response = client.update(request);
                    DebugHelper.AddLog("Response: " + response.updateReturn);
                    r = response.updateReturn;
                }
                catch (Exception ex)
                {
                    DebugHelper.AddLog("Client exception: " + ex);
                }
            }
            return r;
        }


        public Teacher get(int ID)
        {
            Teacher r = new Teacher();

            TeacherService.TeacherService client = new TeacherService.TeacherServiceClient();
            try
            {
                TeacherService.getRequest request = new TeacherService.getRequest();
                request.ID = ID;
                DebugHelper.AddLog("get:  " + request.ID);
                TeacherService.getResponse response = client.get(request);
                DebugHelper.AddLog("Response: " + response.getReturn);
                if (response.getReturn != "null")
                {
                    r.readData(response.getReturn);
                }
                else
                {
                    r = null;
                }
            }
            catch (Exception ex)
            {
                DebugHelper.AddLog("Client exception: " + ex);
            }

            return r;
        }


        public bool remove(int ID)
        {
            bool r = false;

            TeacherService.TeacherService client = new TeacherService.TeacherServiceClient();
            try
            {
                TeacherService.removeRequest request = new TeacherService.removeRequest();
                request.ID = ID;
                DebugHelper.AddLog("remove:  " + request.ID);
                TeacherService.removeResponse response = client.remove(request);
                DebugHelper.AddLog("Response: " + response.removeReturn);
                r = response.removeReturn;
            }
            catch (Exception ex)
            {
                DebugHelper.AddLog("Client exception: " + ex);
            }

            return r;
        }


        public List<Teacher> getAll()
        {
            List<Teacher> r = new List<Teacher>();

            TeacherService.TeacherService client = new TeacherService.TeacherServiceClient();
            try
            {
                TeacherService.getAllRequest request = new TeacherService.getAllRequest();
                //request.ID = ID;
                DebugHelper.AddLog("getAll:");
                TeacherService.getAllResponse response = client.getAll(request);
                DebugHelper.AddLog("Response:");
                for (int i = 0; i < response.getAllReturn.Length; i++)
                {
                    if (response.getAllReturn[i] != "null")
                    {
                        Teacher l = new Teacher();
                        l.readData(response.getAllReturn[i]);
                        r.Add(l);
                    }
                }
            }
            catch (Exception ex)
            {
                DebugHelper.AddLog("Client exception: " + ex);
            }

            return r;
        }


        public int findFreeID()
        {
            int r = 0;

            TeacherService.TeacherService client = new TeacherService.TeacherServiceClient();
            try
            {
                TeacherService.findFreeIDRequest request = new TeacherService.findFreeIDRequest();
                DebugHelper.AddLog("findFreeID:  ");
                TeacherService.findFreeIDResponse response = client.findFreeID(request);
                DebugHelper.AddLog("Response: " + response.findFreeIDReturn);
                r = response.findFreeIDReturn;
            }
            catch (Exception ex)
            {
                DebugHelper.AddLog("Client exception: " + ex);
            }

            return r;
        }


        public bool insertWithNewID(Teacher item)
        {
            bool r = false;
            if (item != null)
            {
                TeacherService.TeacherService client = new TeacherService.TeacherServiceClient();
                try
                {
                    TeacherService.insertWithNewIDRequest request = new TeacherService.insertWithNewIDRequest();
                    request.teacher = item.ToString();
                    DebugHelper.AddLog("insert:  " + request.teacher);
                    TeacherService.insertWithNewIDResponse response = client.insertWithNewID(request);
                    DebugHelper.AddLog("Response: " + response.insertWithNewIDReturn);
                    r = response.insertWithNewIDReturn;
                }
                catch (Exception ex)
                {
                    DebugHelper.AddLog("Client exception: " + ex);
                }
            }
            return r;
        }

    }


}

