/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.phystech.vkurse.VkurseME;

import javax.microedition.rms.*;


/**
 *
 * @author Lex
 */
public class Settings {
    RecordStore rs;
    int group;
    String groupName;
    final static int NOT_FOUND=-1;
    

    int getGroup()
    {
        return group;
    }
    void setGroup(int _group)
    {
        group = _group;
    }
    String getGroupName()
    {
        return groupName;
    }
    void setGroupName(String _groupName)
    {
        groupName = _groupName;
    }
    Settings()
    {
        //Значения по умолчанию - если не удастся загрузить из хранилища
        group = NOT_FOUND;
        groupName="";

        try{
            String str;
            str="";
            rs = RecordStore.openRecordStore("VKorseStorage",false);// открываем уже существующее хранилище записей

            if(rs!=null&&(rs.getRecordSize(1)>0))
            {
                str = new String(rs.getRecord(1));
                group = Integer.parseInt(str);
            }

            if(rs!=null&&(rs.getRecordSize(2)>0))
                groupName = new String(rs.getRecord(2));


            

            rs.closeRecordStore();
        }catch (Exception e) {
            String errmsg = e.toString();
            System.out.println("ERROR:"+errmsg);
	}
    }
    void Save()
    {
        try{
            rs = RecordStore.openRecordStore("VKorseStorage",true);
            String str = Integer.toString(group);

            if(rs.getNumRecords()==0)
                 rs.addRecord(str.getBytes(),0,str.length());
            else
                 rs.setRecord(0,str.getBytes(),0,str.length());

            if(rs.getNumRecords()==1)
                 rs.addRecord(groupName.getBytes(),0,groupName.length());
            else
                 rs.setRecord(1,groupName.getBytes(),0,groupName.length());
            
            rs.closeRecordStore();
        }catch (Exception e) {
            String errmsg = e.toString();
            System.out.println("ERROR:"+errmsg);
	}
    }
}
