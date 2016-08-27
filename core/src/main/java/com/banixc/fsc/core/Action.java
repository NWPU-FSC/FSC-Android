package com.banixc.fsc.core;

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


public interface Action {

    boolean is_login();

    boolean is_set_url_correct();

    int get_type();

    void set_url(String url);

    String get_url();

    User get_log_user();

    Connect connect(ActionCallbackListener<Connect> listener);

    User login(String username, String password, int type, ActionCallbackListener<User> listener);

    User register(String name, String username, String password, ActionCallbackListener<User> listener);

    User user_detail(int user_id, ActionCallbackListener<User> listener);

    Void logout(ActionCallbackListener<Void> listener);

    Void add_student(int student_id, int relation_id, String student_name,ActionCallbackListener<Void> listener);

    List<News> news_list(int page, int status, int sender, int type, ActionCallbackListener<List<News>> listener);

    List<Message> get_message(int message_id, ActionCallbackListener<List<Message>> listener);

    News news_detail(int id, ActionCallbackListener<News> listener);

    Class student_class(int student_id, ActionCallbackListener<Class> listener);

    List<Mark> student_mark(int student_id, int exam_id, ActionCallbackListener<List<Mark>> listener);

    List<Homework> student_homework(ActionCallbackListener<List<Homework>> listener);

    List<User> student_teacher(int student_id, ActionCallbackListener<List<User>> listener);

    List<Award> award_list(ActionCallbackListener<List<Award>> listener);

    List<ParentStudent> parent_student(ActionCallbackListener<List<ParentStudent>> listener);

    List<Relation> get_relation_list(ActionCallbackListener<List<Relation>> listener);

    Void update_current_student(int student_id, ActionCallbackListener<Void> listener);

    List<Exam> get_exam_list(ActionCallbackListener<List<Exam>> listener);

    List<Remark> get_remark(ActionCallbackListener<List<Remark>> listener);

    List<Mark> get_mark(int exam_id, ActionCallbackListener<List<Mark>> listener);

    List<Signin> student_signin(Timestamp time, ActionCallbackListener<List<Signin>> listener);

    List<Classact> get_activity(ActionCallbackListener<List<Classact>> listener);

    List<TeacherParent> teacher_parent(ActionCallbackListener<List<TeacherParent>> listener);

    List<Message> get_receive_message(int page, ActionCallbackListener<List<Message>> listener);

    List<Message> get_send_message(int page, ActionCallbackListener<List<Message>> listener);

    List<Receiver> get_message_receiver(int message_id, int page, ActionCallbackListener<List<Receiver>> listener);

    List<Post> get_post_list(int message_id, int page, ActionCallbackListener<List<Post>> listener);

    Void send_message(String title, String context, int receiver, ActionCallbackListener<Void> listener);

    Void send_message(String title, String context, List<Integer> receivers, ActionCallbackListener<Void> listener);

    Void post_message(int message_id, String context, ActionCallbackListener<Void> listener);

    Void update_parent_student_status(int parent_id, int student_id, int status, ActionCallbackListener<Void> listener);

    List<User> get_contacts(ActionCallbackListener<List<User>> listener);

    List<HomeworkDate> get_homework_date_list(ActionCallbackListener<List<HomeworkDate>> listener);

}
