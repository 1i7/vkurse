/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.phystech.vkurse.model;

/**
 *
 * @author Дима
 */
public interface ExamTypesTable
{
    public boolean insert(ExamType item) throws TableException;
    public boolean update(ExamType item) throws TableException;
    public ExamType get(int ID) throws TableException;
    public boolean remove(int ID) throws TableException;
    public java.util.Vector getAll() throws TableException;
}
