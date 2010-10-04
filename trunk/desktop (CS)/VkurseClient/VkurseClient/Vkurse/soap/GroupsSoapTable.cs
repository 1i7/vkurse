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

    public class GroupsSoapTable : GroupsTable
    {

        public bool insert(Group item)
        {
            bool r = false;
            if (item != null)
            {
                GroupService.GroupService client = new GroupService.GroupServiceClient();
                try
                {
                    GroupService.insertRequest request = new GroupService.insertRequest();
                    request.group = item.ToString();
                    DebugHelper.AddLog("insert:  " + request.group);
                    GroupService.insertResponse response = client.insert(request);
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


        public bool update(Group item)
        {
            bool r = false;

            if (item != null)
            {
                GroupService.GroupService client = new GroupService.GroupServiceClient();
                try
                {
                    GroupService.updateRequest request = new GroupService.updateRequest();
                    request.group = item.ToString();
                    DebugHelper.AddLog("update:  " + request.group);
                    GroupService.updateResponse response = client.update(request);
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


        public Group get(int ID)
        {
            Group r = new Group();

            GroupService.GroupService client = new GroupService.GroupServiceClient();
            try
            {
                GroupService.getRequest request = new GroupService.getRequest();
                request.ID = ID;
                DebugHelper.AddLog("get:  " + request.ID);
                GroupService.getResponse response = client.get(request);
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

            GroupService.GroupService client = new GroupService.GroupServiceClient();
            try
            {
                GroupService.removeRequest request = new GroupService.removeRequest();
                request.ID = ID;
                DebugHelper.AddLog("remove:  " + request.ID);
                GroupService.removeResponse response = client.remove(request);
                DebugHelper.AddLog("Response: " + response.removeReturn);
                r = response.removeReturn;
            }
            catch (Exception ex)
            {
                DebugHelper.AddLog("Client exception: " + ex);
            }

            return r;
        }


        public List<Group> getAll()
        {
            List<Group> r = new List<Group>();

            GroupService.GroupService client = new GroupService.GroupServiceClient();
            try
            {
                GroupService.getAllRequest request = new GroupService.getAllRequest();
                //request.ID = ID;
                DebugHelper.AddLog("getAll:");
                GroupService.getAllResponse response = client.getAll(request);
                DebugHelper.AddLog("Response:");
                for (int i = 0; i < response.getAllReturn.Length; i++)
                {
                    if (response.getAllReturn[i] != "null")
                    {
                        Group l = new Group();
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

            GroupService.GroupService client = new GroupService.GroupServiceClient();
            try
            {
                GroupService.findFreeIDRequest request = new GroupService.findFreeIDRequest();
                DebugHelper.AddLog("findFreeID:  ");
                GroupService.findFreeIDResponse response = client.findFreeID(request);
                DebugHelper.AddLog("Response: " + response.findFreeIDReturn);
                r = response.findFreeIDReturn;
            }
            catch (Exception ex)
            {
                DebugHelper.AddLog("Client exception: " + ex);
            }

            return r;
        }


        public bool insertWithNewID(Group item)
        {
            bool r = false;
            if (item != null)
            {
                GroupService.GroupService client = new GroupService.GroupServiceClient();
                try
                {
                    GroupService.insertWithNewIDRequest request = new GroupService.insertWithNewIDRequest();
                    request.group = item.ToString();
                    DebugHelper.AddLog("insert:  " + request.group);
                    GroupService.insertWithNewIDResponse response = client.insertWithNewID(request);
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

