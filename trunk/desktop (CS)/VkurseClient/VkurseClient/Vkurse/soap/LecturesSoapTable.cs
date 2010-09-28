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

    public class LecturesSoapTable : LecturesTable
    {

        public bool insert(Lecture item)
        {
            bool r = false;
            if (item != null)
            {
                LectureService.LectureService client = new LectureService.LectureServiceClient();
                try
                {
                    LectureService.insertRequest request = new LectureService.insertRequest();
                    request.lecture = item.ToString();
                    DebugHelper.AddLog("insert:  " + request.lecture);
                    LectureService.insertResponse response = client.insert(request);
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


        public bool update(Lecture item)
        {
            bool r = false;

            if (item != null)
            {
                LectureService.LectureService client = new LectureService.LectureServiceClient();
                try
                {
                    LectureService.updateRequest request = new LectureService.updateRequest();
                    request.lecture = item.ToString();
                    DebugHelper.AddLog("update:  " + request.lecture);
                    LectureService.updateResponse response = client.update(request);
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


        public Lecture get(int ID)
        {
            Lecture r = new Lecture();

            LectureService.LectureService client = new LectureService.LectureServiceClient();
            try
            {
                LectureService.getRequest request = new LectureService.getRequest();
                request.ID = ID;
                DebugHelper.AddLog("get:  " + request.ID);
                LectureService.getResponse response = client.get(request);
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

            LectureService.LectureService client = new LectureService.LectureServiceClient();
            try
            {
                LectureService.removeRequest request = new LectureService.removeRequest();
                request.ID = ID;
                DebugHelper.AddLog("remove:  " + request.ID);
                LectureService.removeResponse response = client.remove(request);
                DebugHelper.AddLog("Response: " + response.removeReturn);
                r = response.removeReturn;
            }
            catch (Exception ex)
            {
                DebugHelper.AddLog("Client exception: " + ex);
            }

            return r;
        }


        public List<Lecture> getAll()
        {
            List<Lecture> r = new List<Lecture>();

            LectureService.LectureService client = new LectureService.LectureServiceClient();
            try
            {
                LectureService.getAllRequest request = new LectureService.getAllRequest();
                //request.ID = ID;
                DebugHelper.AddLog("getAll:");
                LectureService.getAllResponse response = client.getAll(request);
                DebugHelper.AddLog("Response:");
                for (int i = 0; i < response.getAllReturn.Length; i++)
                {
                    if (response.getAllReturn[i] != "null")
                    {
                        Lecture l = new Lecture();
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

    }


}

