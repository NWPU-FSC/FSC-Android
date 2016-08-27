package com.banixc.fsc.api;

import android.content.Intent;

import com.banixc.fsc.model.Award;
import com.banixc.fsc.model.Class;
import com.banixc.fsc.model.Classact;
import com.banixc.fsc.model.Connect;
import com.banixc.fsc.model.Mark;
import com.banixc.fsc.model.Message;
import com.banixc.fsc.model.News;
import com.banixc.fsc.model.ParentStudent;
import com.banixc.fsc.model.User;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import dalvik.annotation.TestTarget;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    public  Api api ;



    @Test
    public void test_news_list() throws Exception{
        //获取一个列表
        List<News> news = api.news_list(0,0,0,0).getResult();
        assertEquals(true,news.size()>0);
    }


//    @Test
//    public void test_news_detail() throws Exception{
//        News news = api.news_detail(9).getResult();
//        Receive<News> receive = api.news_detail(2);
//        //检测API的正常功能
//        assertEquals(9,news.getId());
//        //检测找不到的功能
//        assertEquals(null,receive.getResult());
//        assertEquals(-10002,receive.getError().getStatus());
//
//    }

    @Test
    public void test_mark() throws Exception{

        List<Mark> mark = api.student_mark(123,456).getResult();

        assertEquals(2,mark.size());
    }
    @Test
    public void test_user() throws Exception{

        List<User> user = api.student_teacher(123).getResult();

        assertEquals(2,user.size());
    }

    @Test
    public void test_award() throws Exception{

        List<Award> award = api.award_list().getResult();

        assertEquals(1,award.size());
    }



    @Test
    public void test_parent_student() throws Exception{

        List<ParentStudent> parentstudent = api.parent_student().getResult();

        assertEquals(1,parentstudent.size());
    }

//    @Test
//    public void test_message() throws Exception{
//        List<Message> message = api.get_message(1).getResult();
//
//
//    }

   // @Test
    //public void test_homework() throws Exception{
    //    List<Homework> homework = api.student_homework().getResult();


   // }

    @Test
    public void test_class() throws Exception{
        Class cl = api.student_class(1).getResult();

    }

    @Test
    public void test_get_mark() throws Exception{
       List <Mark> mark = api.get_mark(1).getResult();

    }
    @Test
    public void test_get_activity() throws Exception{
        List <Classact> activity = api.get_activity().getResult();

    }

    @Test
    public void test_login() throws Exception{
        api.setToken(null);
        Receive<User> receive = api.login("pass","pass",1);
        assertEquals(true,receive.success());
        assertEquals(true,receive.getResult().getToken().length() == 32);

    }

    @Test
    public void test_message_list() throws Exception{
        Receive<List<Message>> receive1 = api.get_message_list(0,true);
        Receive<List<Message>> receive2 = api.get_message_list(0,false);
        assertEquals(true, receive1.success());
        assertEquals(true, receive2.success());

    }

    @Test
    public void test_register() throws Exception{
        api.setToken(null);
        Receive<User> receive = api.register("pas1s21","pa1ss211","pass11");
        assertEquals(false,receive.success());
        assertEquals(-12007,receive.getError().getStatus());
    }



    @Test
    public void test_get_cont() throws Exception{
        api.get_contacts();
        List<User> users = api.get_contacts().getResult();;
        users.size();

    }

    @Test
    public void test_send_message() throws Exception{
        ArrayList<Integer> ints = new ArrayList<>();
        ints.add(1);
        ints.add(87);

        assertEquals (true,api.send_message("123","456",ints).success());
    }


    @Test
    public void test_connect() throws Exception{
        HttpEngine.setServerUrl("http://fsc.banixc.com/api");
        Connect connect = api.connect().getResult();
        assertEquals(true,connect.is_token_correct());
    }





    @Before
    public void start() throws Exception{
        if(api==null)
        api = new ApiImplement("2bc750e3050e3351bffa1dd3f9d2a531");
    }

}