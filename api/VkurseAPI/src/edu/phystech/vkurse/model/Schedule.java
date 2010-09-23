/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.phystech.vkurse.model;

/**
 *
 * @author Дима
 */
public class Schedule extends DbTableRecord
{
    protected int groupID;
    protected byte day;
    protected int startTime;
    protected int length;
    protected int lectureID;
    protected int roomID;
    protected int teacherID;
    protected String comment;

    public Schedule() {}
    public Schedule(int id, int groupID, byte day, int startTime, int length,
            int lectureID, int roomID, int teacherID, String comment)
    {
        this.id = id;
        this.groupID = groupID;
        this.day = day;
        this.startTime = startTime;
        this.length = length;
        this.lectureID = lectureID;
        this.roomID = roomID;
        this.teacherID = teacherID;
        this.comment = comment;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public byte getDay() {
        return day;
    }

    public void setDay(byte day) {
        this.day = day;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLectureID() {
        return lectureID;
    }

    public void setLectureID(int lectureID) {
        this.lectureID = lectureID;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override public String toString()
    {
        String r = "ID=" + id + " ";
        r += "groupID=" + groupID + " ";
        r += "day=" + day + " ";
        r += "startTime=" + startTime + " ";
        r += "length=" + length + " ";
        r += "lectureID=" + lectureID + " ";
        r += "roomID=" + roomID + " ";
        r += "teacherID=" + teacherID + " ";
        r += "comment='" + comment + "' ";
        r = r.trim();
        return r;
    }
}
