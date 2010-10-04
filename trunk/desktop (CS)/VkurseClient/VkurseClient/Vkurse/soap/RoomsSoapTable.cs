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

    public class RoomsSoapTable : RoomsTable
    {

        public bool insert(Room item)
        {
            bool r = false;
            if (item != null)
            {
                RoomService.RoomService client = new RoomService.RoomServiceClient();
                try
                {
                    RoomService.insertRequest request = new RoomService.insertRequest();
                    request.room = item.ToString();
                    DebugHelper.AddLog("insert:  " + request.room);
                    RoomService.insertResponse response = client.insert(request);
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


        public bool update(Room item)
        {
            bool r = false;

            if (item != null)
            {
                RoomService.RoomService client = new RoomService.RoomServiceClient();
                try
                {
                    RoomService.updateRequest request = new RoomService.updateRequest();
                    request.room = item.ToString();
                    DebugHelper.AddLog("update:  " + request.room);
                    RoomService.updateResponse response = client.update(request);
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


        public Room get(int ID)
        {
            Room r = new Room();

            RoomService.RoomService client = new RoomService.RoomServiceClient();
            try
            {
                RoomService.getRequest request = new RoomService.getRequest();
                request.ID = ID;
                DebugHelper.AddLog("get:  " + request.ID);
                RoomService.getResponse response = client.get(request);
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

            RoomService.RoomService client = new RoomService.RoomServiceClient();
            try
            {
                RoomService.removeRequest request = new RoomService.removeRequest();
                request.ID = ID;
                DebugHelper.AddLog("remove:  " + request.ID);
                RoomService.removeResponse response = client.remove(request);
                DebugHelper.AddLog("Response: " + response.removeReturn);
                r = response.removeReturn;
            }
            catch (Exception ex)
            {
                DebugHelper.AddLog("Client exception: " + ex);
            }

            return r;
        }


        public List<Room> getAll()
        {
            List<Room> r = new List<Room>();

            RoomService.RoomService client = new RoomService.RoomServiceClient();
            try
            {
                RoomService.getAllRequest request = new RoomService.getAllRequest();
                //request.ID = ID;
                DebugHelper.AddLog("getAll:");
                RoomService.getAllResponse response = client.getAll(request);
                DebugHelper.AddLog("Response:");
                for (int i = 0; i < response.getAllReturn.Length; i++)
                {
                    if (response.getAllReturn[i] != "null")
                    {
                        Room l = new Room();
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

            RoomService.RoomService client = new RoomService.RoomServiceClient();
            try
            {
                RoomService.findFreeIDRequest request = new RoomService.findFreeIDRequest();
                DebugHelper.AddLog("findFreeID:  ");
                RoomService.findFreeIDResponse response = client.findFreeID(request);
                DebugHelper.AddLog("Response: " + response.findFreeIDReturn);
                r = response.findFreeIDReturn;
            }
            catch (Exception ex)
            {
                DebugHelper.AddLog("Client exception: " + ex);
            }

            return r;
        }


        public bool insertWithNewID(Room item)
        {
            bool r = false;
            if (item != null)
            {
                RoomService.RoomService client = new RoomService.RoomServiceClient();
                try
                {
                    RoomService.insertWithNewIDRequest request = new RoomService.insertWithNewIDRequest();
                    request.room = item.ToString();
                    DebugHelper.AddLog("insert:  " + request.room);
                    RoomService.insertWithNewIDResponse response = client.insertWithNewID(request);
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

