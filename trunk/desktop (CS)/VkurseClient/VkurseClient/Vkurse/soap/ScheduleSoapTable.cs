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

    public class ScheduleSoapTable : ScheduleTable
    {

        public bool insert(Schedule item)
        {
            bool r = false;
            if (item != null)
            {
                ScheduleService.ScheduleService client = new ScheduleService.ScheduleServiceClient();
                try
                {
                    ScheduleService.insertRequest request = new ScheduleService.insertRequest();
                    request.schedule = item.ToString();
                    DebugHelper.AddLog("insert:  " + request.schedule);
                    ScheduleService.insertResponse response = client.insert(request);
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


        public bool update(Schedule item)
        {
            bool r = false;

            if (item != null)
            {
                ScheduleService.ScheduleService client = new ScheduleService.ScheduleServiceClient();
                try
                {
                    ScheduleService.updateRequest request = new ScheduleService.updateRequest();
                    request.schedule = item.ToString();
                    DebugHelper.AddLog("update:  " + request.schedule);
                    ScheduleService.updateResponse response = client.update(request);
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


        public Schedule get(int ID)
        {
            Schedule r = new Schedule();

            ScheduleService.ScheduleService client = new ScheduleService.ScheduleServiceClient();
            try
            {
                ScheduleService.getRequest request = new ScheduleService.getRequest();
                request.ID = ID;
                DebugHelper.AddLog("get:  " + request.ID);
                ScheduleService.getResponse response = client.get(request);
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

            ScheduleService.ScheduleService client = new ScheduleService.ScheduleServiceClient();
            try
            {
                ScheduleService.removeRequest request = new ScheduleService.removeRequest();
                request.ID = ID;
                DebugHelper.AddLog("remove:  " + request.ID);
                ScheduleService.removeResponse response = client.remove(request);
                DebugHelper.AddLog("Response: " + response.removeReturn);
                r = response.removeReturn;
            }
            catch (Exception ex)
            {
                DebugHelper.AddLog("Client exception: " + ex);
            }

            return r;
        }


        public List<Schedule> getAll()
        {
            List<Schedule> r = new List<Schedule>();

            ScheduleService.ScheduleService client = new ScheduleService.ScheduleServiceClient();
            try
            {
                ScheduleService.getAllRequest request = new ScheduleService.getAllRequest();
                //request.ID = ID;
                DebugHelper.AddLog("getAll:");
                ScheduleService.getAllResponse response = client.getAll(request);
                DebugHelper.AddLog("Response:");
                for (int i = 0; i < response.getAllReturn.Length; i++)
                {
                    if (response.getAllReturn[i] != "null")
                    {
                        Schedule l = new Schedule();
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



        public List<Schedule> findByGroupDay(int groupID, byte day)
        {
            List<Schedule> r = new List<Schedule>();

            ScheduleService.ScheduleService client = new ScheduleService.ScheduleServiceClient();
            try
            {
                ScheduleService.findByGroupDayRequest request = new ScheduleService.findByGroupDayRequest();
                request.groupID = groupID;
                request.day = (sbyte)day;
                DebugHelper.AddLog("findByGroupDay:");
                ScheduleService.findByGroupDayResponse response = client.findByGroupDay(request);
                DebugHelper.AddLog("Response:");
                for (int i = 0; i < response.findByGroupDayReturn.Length; i++)
                {
                    if (response.findByGroupDayReturn[i] != "null")
                    {
                        Schedule l = new Schedule();
                        l.readData(response.findByGroupDayReturn[i]);
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

            ScheduleService.ScheduleService client = new ScheduleService.ScheduleServiceClient();
            try
            {
                ScheduleService.findFreeIDRequest request = new ScheduleService.findFreeIDRequest();
                DebugHelper.AddLog("findFreeID:  ");
                ScheduleService.findFreeIDResponse response = client.findFreeID(request);
                DebugHelper.AddLog("Response: " + response.findFreeIDReturn);
                r = response.findFreeIDReturn;
            }
            catch (Exception ex)
            {
                DebugHelper.AddLog("Client exception: " + ex);
            }

            return r;
        }


        public bool insertWithNewID(Schedule item)
        {
            bool r = false;
            if (item != null)
            {
                ScheduleService.ScheduleService client = new ScheduleService.ScheduleServiceClient();
                try
                {
                    ScheduleService.insertWithNewIDRequest request = new ScheduleService.insertWithNewIDRequest();
                    request.schedule = item.ToString();
                    DebugHelper.AddLog("insert:  " + request.schedule);
                    ScheduleService.insertWithNewIDResponse response = client.insertWithNewID(request);
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
