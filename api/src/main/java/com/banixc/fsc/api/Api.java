package com.banixc.fsc.api;


import com.banixc.fsc.model.Award;
import com.banixc.fsc.model.Class;
import com.banixc.fsc.model.Classact;
import com.banixc.fsc.model.Connect;
import com.banixc.fsc.model.Exam;
import com.banixc.fsc.model.Homework;
import com.banixc.fsc.model.Mark;
import com.banixc.fsc.model.Message;
import com.banixc.fsc.model.News;
import com.banixc.fsc.model.ParentStudent;
import com.banixc.fsc.model.Post;
import com.banixc.fsc.model.Receiver;
import com.banixc.fsc.model.Relation;
import com.banixc.fsc.model.Remark;
import com.banixc.fsc.model.Signin;
import com.banixc.fsc.model.TeacherParent;
import com.banixc.fsc.model.User;
import com.banixc.fsc.model.base.HomeworkDate;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface Api {

    String getToken();

    void setToken(String token);

    Receive<Connect> connect();

    Receive<List<Mark>> get_mark(int exam_id);

    Receive<List<News>> news_list(int page, int status, int sender, int type);

    Receive<List<Award>> award_list();

    Receive<News> news_detail(int id);

    Receive<List<Message>> get_message(int message_id);

    Receive<List<Homework>> student_homework();

    Receive<List<Mark>> student_mark(int student_id, int exam_id);

    Receive<List<User>> student_teacher(int student_id);

    Receive<User> user_detail(int user_id);

    Receive<List<ParentStudent>> parent_student();

    Receive<User> login(String username, String password, int type);

    Receive<Void> logout();

    Receive<User> register(String name, String username, String password);

    Receive<Void> add_student(int student_id, int relation_id,String student_name);
    Receive<List<Relation>> get_relation_list();

    Receive<Class> student_class(int student_id);

    Receive<Void> update_current_student(int student_id);

    Receive<List<Exam>> get_exam_list();

    Receive<List<Remark>> get_remark();

    Receive<List<Signin>> student_signin(Timestamp time);

    Receive<List<Classact>> get_activity();

    Receive<List<TeacherParent>> teacher_parent();

    Receive<List<Message>> get_message_list(int page, boolean as_send);

    Receive<List<Receiver>> get_message_receiver(int message_id, int page);

    Receive<List<Post>> get_post_list(int message_id, int page);

    Receive<Void> send_message(String title, String context, int receiver);

    Receive<Void> send_message(String title, String context, List<Integer> receivers);

    Receive<Void> post_message(int message_id, String context);

    Receive<Void> update_parent_student_status(int parent_id, int student_id, int status);

    Receive<List<User>> get_contacts();

    Receive<List<HomeworkDate>> get_homework_date_list();
}
