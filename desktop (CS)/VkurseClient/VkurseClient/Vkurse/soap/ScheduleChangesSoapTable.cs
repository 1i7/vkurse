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

    public class ScheduleChangesSoapTable : ScheduleChangesTable
    {

        public bool insert(ScheduleChange item)
        {
            bool r = false;
            if (item != null)
            {
                ScheduleChangeService.ScheduleChangeService client = new ScheduleChangeService.ScheduleChangeServiceClient();
                try
                {
                    ScheduleChangeService.insertRequest request = new ScheduleChangeService.insertRequest();
                    request.scheduleChange = item.ToString();
                    DebugHelper.AddLog("insert:  " + request.scheduleChange);
                    ScheduleChangeService.insertResponse response = client.insert(request);
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


        public bool update(ScheduleChange item)
        {
            bool r = false;

            if (item != null)
            {
                ScheduleChangeService.ScheduleChangeService client = new ScheduleChangeService.ScheduleChangeServiceClient();
                try
                {
                    ScheduleChangeService.updateRequest request = new ScheduleChangeService.updateRequest();
                    request.scheduleChange = item.ToString();
                    DebugHelper.AddLog("update:  " + request.scheduleChange);
                    ScheduleChangeService.updateResponse response = client.update(request);
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


        public ScheduleChange get(int ID)
        {
            ScheduleChange r = new ScheduleChange();

            ScheduleChangeService.ScheduleChangeService client = new ScheduleChangeService.ScheduleChangeServiceClient();
            try
            {
                ScheduleChangeService.getRequest request = new ScheduleChangeService.getRequest();
                request.ID = ID;
                DebugHelper.AddLog("get:  " + request.ID);
                ScheduleChangeService.getResponse response = client.get(request);
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

            ScheduleChangeService.ScheduleChangeService client = new ScheduleChangeService.ScheduleChangeServiceClient();
            try
            {
                ScheduleChangeService.removeRequest request = new ScheduleChangeService.removeRequest();
                request.ID = ID;
                DebugHelper.AddLog("remove:  " + request.ID);
                ScheduleChangeService.removeResponse response = client.remove(request);
                DebugHelper.AddLog("Response: " + response.removeReturn);
                r = response.removeReturn;
            }
            catch (Exception ex)
            {
                DebugHelper.AddLog("Client exception: " + ex);
            }

            return r;
        }


        public List<ScheduleChange> getAll()
        {
            List<ScheduleChange> r = new List<ScheduleChange>();

            ScheduleChangeService.ScheduleChangeService client = new ScheduleChangeService.ScheduleChangeServiceClient();
            try
            {
                ScheduleChangeService.getAllRequest request = new ScheduleChangeService.getAllRequest();
                //request.ID = ID;
                DebugHelper.AddLog("getAll:");
                ScheduleChangeService.getAllResponse response = client.getAll(request);
                DebugHelper.AddLog("Response:");
                for (int i = 0; i < response.getAllReturn.Length; i++)
                {
                    if (response.getAllReturn[i] != "null")
                    {
                        ScheduleChange l = new ScheduleChange();
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



        public List<ScheduleChange> findByGroupWeekDay(int groupID, int week, byte day)
        {
            List<ScheduleChange> r = new List<ScheduleChange>();

            ScheduleChangeService.ScheduleChangeService client = new ScheduleChangeService.ScheduleChangeServiceClient();
            try
            {
                ScheduleChangeService.findByGroupWeekDayRequest request = new ScheduleChangeService.findByGroupWeekDayRequest();
                request.groupID = groupID;
                request.week = week;
                request.day = (sbyte)day;
                DebugHelper.AddLog("findByGroupWeekDay:");
                ScheduleChangeService.findByGroupWeekDayResponse response = client.findByGroupWeekDay(request);
                DebugHelper.AddLog("Response:");
                for (int i = 0; i < response.findByGroupWeekDayReturn.Length; i++)
                {
                    if (response.findByGroupWeekDayReturn[i] != "null")
                    {
                        ScheduleChange l = new ScheduleChange();
                        l.readData(response.findByGroupWeekDayReturn[i]);
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


        public List<ScheduleChange> findByScheduleID(int scheduleID)
        {
            List<ScheduleChange> r = new List<ScheduleChange>();

            ScheduleChangeService.ScheduleChangeService client = new ScheduleChangeService.ScheduleChangeServiceClient();
            try
            {
                ScheduleChangeService.findByScheduleIDRequest request = new ScheduleChangeService.findByScheduleIDRequest();
                request.scheduleID = scheduleID;
                DebugHelper.AddLog("findByScheduleID:");
                ScheduleChangeService.findByScheduleIDResponse response = client.findByScheduleID(request);
                DebugHelper.AddLog("Response:");
                for (int i = 0; i < response.findByScheduleIDReturn.Length; i++)
                {
                    if (response.findByScheduleIDReturn[i] != "null")
                    {
                        ScheduleChange l = new ScheduleChange();
                        l.readData(response.findByScheduleIDReturn[i]);
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

            ScheduleChangeService.ScheduleChangeService client = new ScheduleChangeService.ScheduleChangeServiceClient();
            try
            {
                ScheduleChangeService.findFreeIDRequest request = new ScheduleChangeService.findFreeIDRequest();
                DebugHelper.AddLog("findFreeID:  ");
                ScheduleChangeService.findFreeIDResponse response = client.findFreeID(request);
                DebugHelper.AddLog("Response: " + response.findFreeIDReturn);
                r = response.findFreeIDReturn;
            }
            catch (Exception ex)
            {
                DebugHelper.AddLog("Client exception: " + ex);
            }

            return r;
        }


        public bool insertWithNewID(ScheduleChange item)
        {
            bool r = false;
            if (item != null)
            {
                ScheduleChangeService.ScheduleChangeService client = new ScheduleChangeService.ScheduleChangeServiceClient();
                try
                {
                    ScheduleChangeService.insertWithNewIDRequest request = new ScheduleChangeService.insertWithNewIDRequest();
                    request.scheduleChange = item.ToString();
                    DebugHelper.AddLog("insert:  " + request.scheduleChange);
                    ScheduleChangeService.insertWithNewIDResponse response = client.insertWithNewID(request);
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
