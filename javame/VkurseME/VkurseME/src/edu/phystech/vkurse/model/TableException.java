/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.phystech.vkurse.model;

/**
 *
 * @author Дима
 */

public class TableException extends Exception
{
    public TableException(String message){ super(message); }
    public TableException(String message, Exception cause) { super(message); }
    public TableException(Exception cause) { super(cause.toString()); }
}
