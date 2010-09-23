/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.phystech.vkurse.model;

/**
 *
 * @author Дима
 */
public class ScheduleChange extends DbTableRecord
{
    protected int scheduleID;
    protected int week;
    protected int groupID;
    protected byte day;
    protected int startTime;
    protected int length;
    protected int lectureID;
    protected int roomID;
    protected int teacherID;
    protected String comment;

    public ScheduleChange() {}
    public ScheduleChange(int id, int scheduleID, int week, byte day, int startTime, int length, int lectureID, int roomID, int teacherID, String comment)
    {
        this.id = id;
        this.lectureID = lectureID;
        this.week = week;
        this.day = day;
        this.startTime = startTime;
        this.length = length;
        this.lectureID = lectureID;
        this.roomID = roomID;
        this.teacherID = teacherID;
        this.comment = comment;
    }

    public int getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(int scheduleID) {
        this.scheduleID = scheduleID;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
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
}