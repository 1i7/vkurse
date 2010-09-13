package vkurseapi_test;

/**
 * Автор: Дмитрий Исупов, гр. 793 (группа базы данных)
 *
 * Проект, демонстрирующий работу с классами модели.
 * В проекте показано, как подключить jar-библиотеку VkurseAPI, как объявлять
 * классы, какие именно использовать, и как с ними работать. Демонстрируется
 * использование фабрики классов, на примере классов Lecture и LecturesTable
 * демонстрируется получение, добавление, изменение и удаление данных.
 */



//  Подключаем пакеты
import edu.phystech.vkurse.model.*;      //  Модель
import edu.phystech.vkurse.test.*;       //  Тестовая реализация модели
import edu.phystech.vkurse.postgresql.*; //  Реализация для сервера PostgreSQL
import java.util.*;

/**
 *
 * @author Дима
 */
public class Main
{
    public static void main(String[] args) throws edu.phystech.vkurse.model.TableException
    {
        //  Эта команда выводит текст в стандарный поток вывода. Обычно это
        //  консоль, но если запускать из под NetBeans 6.8, то текст выводится
        //  внизу под текстом программы
        System.out.println("Welcome to VkurseAPI test app");
        System.out.println("");

        
        // От одного объявления (и только от него) зависит, с чем на самом деле
        // мы будем работать. Первый закомментированный вариант - сервер БД PostgreSQL,
        // второй вариант - тестовые данные
        // TableFactory factory = new PgSqlTableFactory();
        // TableFactory factory = new TestTableFactory();

        // Этот код был написан мною при отладке своего модуля, поэтому создаем
        // экземпляр фабрики, работающей с сервером БД
        //TableFactory factory = new PgSqlTableFactory();
        TableFactory factory = new PgSqlTableFactory();
        Lecture l = null;  //  В этой переменной храним данные об одной лекции
        //  А этот класс уже получает данные о лекции с сервера/XML/тестовых
        //  данных Обратите внимание на то, как он создается! Мы используем
        //  не new LecturesTable(), а создаем с помощью фабрики. Благодаря
        //  этому достигается такая гибкость с тем, откуда на самом деле брать
        //  данные. Если мы до этого создали фабрику, работающую с тестовыми
        //  данными, то и реализацию LecturesTable мы получим именно ту, которая
        //  работает с тестовыми данными; если же фабрика работает с сервером,
        //  то и полученная реализация класса LecturesTable тоже будет работать
        //  с сервером.
        LecturesTable lt = factory.getLecturesTable();

        //  Попробуем получить информацию о предмете с ID=0
        System.out.println("Getting lecture with ID=0");
        l = lt.get(0);
        //  Если мы что-то получили, выводим информацию
        if (l != null)
        {
            System.out.println("  Lecture:");
            System.out.println("id:         " + l.getID());
            System.out.println("name:       '" + l.getName()+"'");
            System.out.println("examTypeID: " + l.getExamTypeID());
            System.out.println("comment:    '" + l.getComment()+"'");
        }
        //  Иначе - говорим, что ничего не получили
        else
        {
            System.out.println("No data about lecture");
        }
        System.out.println("");

        //  Повторяем операцию уже с ID=12321, которого нет
        System.out.println("Getting lecture with ID=12321");
        l = lt.get(12321);
        if (l != null)
        {
            System.out.println("  Lecture:");
            System.out.println("id:         " + l.getID());
            System.out.println("name:       '" + l.getName()+"'");
            System.out.println("examTypeID: " + l.getExamTypeID());
            System.out.println("comment:    '" + l.getComment()+"'");
        }
        else
        {
            System.out.println("No data about lecture");
        }
        System.out.println("");
        System.out.println("");



        int i;
        System.out.println("        All:");
        //  А теперь получим данные обо всех занятиях
        java.util.Vector lst = lt.getAll();  //  Класс Vector находится в пакете
                    // java.util, указано его полное имя, но т.к. мы подключили
                    // этот пакет в самом верху, можно было объявить переменную
                    // так: Vector lst = lt.getAll();

        //  Выведем информацию о занятиях
        for (i=0; i<lst.size(); ++i)
        {
            System.out.println("  Lecture "+i+":");
            //  Т.к. класс Vector хранит в себе только тип Object, являющийся
            //  прародителем для всех классов в java, нужно сделать явное
            //  приведение типов к тому, что там на самом деле хранится
            l = (Lecture)lst.get(i);
            if (l != null)
            {
                System.out.println("id:         " + l.getID());
                System.out.println("name:       '" + l.getName()+"'");
                System.out.println("examTypeID: " + l.getExamTypeID());
                System.out.println("comment:    '" + l.getComment()+"'");
            }
            else
            {
                System.out.println("No data about lecture");
            }
        }
        System.out.println("");
        System.out.println("");



        Boolean r;
        //  Попробуем удалить несуществующую запись с ID=100
        r = lt.remove(100);
        if (r)
        {
            System.out.println("Record with ID=100 was deleted");
        }
        else
        {
            System.out.println("Record with ID=100 was not deleted");
        }



        //  Теперь мы добавим новую запись
        //  Для этого мы сначала создаем переменную типа Lecure, хранящую в себе
        //  все информацию о лекции. Данные можно указать в конструкторе (а можно
        //  и не указывать)
        Lecture l2 = new Lecture(100, "New lecture", 0, "");
        //  Можно изменить какую-либо информацию
        l2.setComment("Comment of new lecrute");
        //  Ну и в конце концов мы все-таки добавляем новую запись
        r = lt.insert(l2);
        if (r)
        {
            System.out.println("Record with ID=100 was added");
        }
        else
        {
            //  Но запись может и не добавиться, например, если такой ID уже есть
            System.out.println("Record with ID=100 was not added");
        }
        System.out.println("");


        //  Выведем все записи, чтобы убедиться, что она добавилась
        lst = lt.getAll();
        for (i=0; i<lst.size(); ++i)
        {
            System.out.println("  Lecture "+i+":");
            l = (Lecture)lst.get(i);
            if (l != null)
            {
                System.out.println("id:         " + l.getID());
                System.out.println("name:       '" + l.getName()+"'");
                System.out.println("examTypeID: " + l.getExamTypeID());
                System.out.println("comment:    '" + l.getComment()+"'");
            }
            else
            {
                System.out.println("No data about lecture");
            }
        }
        System.out.println("");


        //  Что ж, новую запись добавлять научились - отлично! Но вот досада,
        //  уже добавив запись, мы обнаружили, что допустили опечатку.
        //  Нужно эту запись изменить. Сначала мы получим данные, потом
        //  внесем в переменную класса Lecture все изменения, а потом уже
        //  внесем эти изменения на сервер.
        Lecture l3 = lt.get(100);  //  Получаем данные
        if (l3 != null)
        {
            //  Корректируем данные
            l3.setName("New name of lecture");
            l3.setComment("New comment");

            //  И посылаем новые данные на... на сервер =)
            r = lt.update(l3);
            //  И проверяем, получилось, али нет
            if (r)
            {
                System.out.println("Record was updated");
            }
            else
            {
                System.out.println("Record was not updated");
            }
        }
        else
        {
            //  Ой, изменять-то нечего =( Хотя странно, кто успел удалить запись,
            //  которую мы только что добавили
            System.out.println("No data about lecture to change");
        }
        System.out.println("");

        //  Нам же сказали, что получилось! Но, как говорится, доверяй, но
        //  проверяй! Поэтому мы снова посмотрим, что там хранится в БД
        //  в записи под номером 100
        l = lt.get(100);
        if (l != null)
        {
            System.out.println("  Lecture:");
            System.out.println("id:         " + l.getID());
            System.out.println("name:       '" + l.getName()+"'");
            System.out.println("examTypeID: " + l.getExamTypeID());
            System.out.println("comment:    '" + l.getComment()+"'");
        }
        else
        {
            System.out.println("No data about lecture");
        }
        System.out.println("");

        
        //  А теперь попробуем удалить существующую запись с ID=100
        r = lt.remove(100);
        if (r)
        {
            System.out.println("Record with ID=100 was deleted");
        }
        else
        {
            System.out.println("Record with ID=100 was not deleted");
        }
        System.out.println("");
        
        
        //  И снова выведем все записи
        lst = lt.getAll();
        for (i=0; i<lst.size(); ++i)
        {
            System.out.println("  Lecture "+i+":");
            l = (Lecture)lst.get(i);
            if (l != null)
            {
                System.out.println("id:         " + l.getID());
                System.out.println("name:       '" + l.getName()+"'");
                System.out.println("examTypeID: " + l.getExamTypeID());
                System.out.println("comment:    '" + l.getComment()+"'");
            }
            else
            {
                System.out.println("No data about lecture");
            }
        }
        System.out.println("");
        System.out.println("");
         
        
    }

}