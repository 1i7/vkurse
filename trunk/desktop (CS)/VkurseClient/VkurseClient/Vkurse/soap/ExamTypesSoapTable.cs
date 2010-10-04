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

    public class ExamTypesSoapTable : ExamTypesTable
    {

        public bool insert(ExamType item)
        {
            bool r = false;
            if (item != null)
            {
                ExamTypeService.ExamTypeService client = new ExamTypeService.ExamTypeServiceClient();
                try
                {
                    ExamTypeService.insertRequest request = new ExamTypeService.insertRequest();
                    request.examType = item.ToString();
                    DebugHelper.AddLog("insert:  " + request.examType);
                    ExamTypeService.insertResponse response = client.insert(request);
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


        public bool update(ExamType item)
        {
            bool r = false;

            if (item != null)
            {
                ExamTypeService.ExamTypeService client = new ExamTypeService.ExamTypeServiceClient();
                try
                {
                    ExamTypeService.updateRequest request = new ExamTypeService.updateRequest();
                    request.examType = item.ToString();
                    DebugHelper.AddLog("update:  " + request.examType);
                    ExamTypeService.updateResponse response = client.update(request);
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


        public ExamType get(int ID)
        {
            ExamType r = new ExamType();

            ExamTypeService.ExamTypeService client = new ExamTypeService.ExamTypeServiceClient();
            try
            {
                ExamTypeService.getRequest request = new ExamTypeService.getRequest();
                request.ID = ID;
                DebugHelper.AddLog("get:  " + request.ID);
                ExamTypeService.getResponse response = client.get(request);
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

            ExamTypeService.ExamTypeService client = new ExamTypeService.ExamTypeServiceClient();
            try
            {
                ExamTypeService.removeRequest request = new ExamTypeService.removeRequest();
                request.ID = ID;
                DebugHelper.AddLog("remove:  " + request.ID);
                ExamTypeService.removeResponse response = client.remove(request);
                DebugHelper.AddLog("Response: " + response.removeReturn);
                r = response.removeReturn;
            }
            catch (Exception ex)
            {
                DebugHelper.AddLog("Client exception: " + ex);
            }

            return r;
        }


        public List<ExamType> getAll()
        {
            List<ExamType> r = new List<ExamType>();

            ExamTypeService.ExamTypeService client = new ExamTypeService.ExamTypeServiceClient();
            try
            {
                ExamTypeService.getAllRequest request = new ExamTypeService.getAllRequest();
                //request.ID = ID;
                DebugHelper.AddLog("getAll:");
                ExamTypeService.getAllResponse response = client.getAll(request);
                DebugHelper.AddLog("Response:");
                for (int i = 0; i < response.getAllReturn.Length; i++)
                {
                    if (response.getAllReturn[i] != "null")
                    {
                        ExamType l = new ExamType();
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

            ExamTypeService.ExamTypeService client = new ExamTypeService.ExamTypeServiceClient();
            try
            {
                ExamTypeService.findFreeIDRequest request = new ExamTypeService.findFreeIDRequest();
                DebugHelper.AddLog("findFreeID:  ");
                ExamTypeService.findFreeIDResponse response = client.findFreeID(request);
                DebugHelper.AddLog("Response: " + response.findFreeIDReturn);
                r = response.findFreeIDReturn;
            }
            catch (Exception ex)
            {
                DebugHelper.AddLog("Client exception: " + ex);
            }

            return r;
        }


        public bool insertWithNewID(ExamType item)
        {
            bool r = false;
            if (item != null)
            {
                ExamTypeService.ExamTypeService client = new ExamTypeService.ExamTypeServiceClient();
                try
                {
                    ExamTypeService.insertWithNewIDRequest request = new ExamTypeService.insertWithNewIDRequest();
                    request.examType = item.ToString();
                    DebugHelper.AddLog("insert:  " + request.examType);
                    ExamTypeService.insertWithNewIDResponse response = client.insertWithNewID(request);
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

