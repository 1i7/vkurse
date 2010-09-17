package vkurseconsoleclient;

//import edu.phystech.vkurse.model.*;      //  Модель
//import edu.phystech.vkurse.test.*;       //  Тестовая реализация модели
//import edu.phystech.vkurse.postgresql.*; //  Реализация для сервера PostgreSQL
//import java.util.*;
import java.io.*;

/**
 *
 * @author Дмитрий ApX Исупов
 */
public class Main {

    public static void main(String[] args)
    {
        try
        {
            BufferedReader is = new BufferedReader(
                new InputStreamReader(System.in));

            System.out.println("     Please choose data source:");
            System.out.println("0: Test data");
            System.out.println("1: PostgreSQL server");
            int b = Integer.parseInt(is.readLine());
            System.out.println("");
            
            VkurseClient client = new VkurseClient(b);
            if (client.IsInited())
            {
                client.Run();
            }
        }
        catch (Exception ex) { }
    }

}
