package com.banixc.fsc.api;


import android.view.MotionEvent;

import com.banixc.fsc.model.Award;
import com.banixc.fsc.model.Class;
import com.banixc.fsc.model.Classact;
import com.banixc.fsc.model.Connect;
import com.banixc.fsc.model.Error;
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
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiImplement implements Api {

    private final static int TIME_OUT_CODE = -20000;

    private final static String TIME_OUT_EVENT_MSG = "连接服务器失败";

    private final static String STRING_METHOD = "method";

    private final static String STRING_PARAMS = "params";

    private final static String STRING_TOKEN = "token";

    private Map<String, Object> params;

    private String method = null;
    private String token = null;

    public ApiImplement(String token) {
        this.token = token;
    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public void setToken(String token) {
        this.token = token;
    }




    private Receive send(Type type) {
        Map<String, Object> require = new HashMap<>();
        require.put(STRING_METHOD, method);
        require.put(STRING_PARAMS, params);
        if (null != getToken())
            require.put(STRING_TOKEN, getToken());
        try {
            return HttpEngine.getInstance().postHandle(require, type);
        } catch (IOException e) {
            return new Receive(null, new Error(TIME_OUT_CODE, TIME_OUT_EVENT_MSG));
        }
    }

    private void setMethod(String method) {
        init();

        this.method = method;
    }

    private void putParams(String param_name, Object param) {
        params.put(param_name, param);
    }

    private void init() {
        params = new HashMap<>();
        method = null;
    }

    @Override
    public Receive<Connect> connect() {
        setMethod("connect");

        return send(new TypeToken<Connect>() {
        }.getType());
    }


    @Override
    public Receive<List<News>> news_list(int page, int status, int sender, int type) {
        setMethod("news_list");
        if (0 != page) putParams("page", page);
        if (0 != status) putParams("status", status);
        if (0 != sender) putParams("sender", sender);
        if (0 != type) putParams("type", type);

        return send(new TypeToken<List<News>>() {
        }.getType());
    }

    @Override
    public Receive<News> news_detail(int id) {
        setMethod("news_detail");
        putParams("id", id);
        return send(new TypeToken<News>() {
        }.getType());
    }


    @Override
    public Receive<List<Message>> get_message(int message_id) {
        setMethod("get_message");
        putParams("message_id", message_id);
        return send(new TypeToken<List<Message>>() {
        }.getType());
    }


    @Override
    public Receive<List<Homework>> student_homework() {
        setMethod("student_homework");

        return send(new TypeToken<List<Homework>>() {
        }.getType());
    }

    @Override
    public Receive<List<Mark>> student_mark(int student_id, int exam_id) {
        setMethod("student_mark");
        putParams("student_id", student_id);
        putParams("exam_id", exam_id);
        return send(new TypeToken<List<Mark>>() {
        }.getType());

    }

    @Override
    public Receive<List<User>> student_teacher(int student_id) {
        setMethod("student_teacher");
        putParams("student_id", student_id);
        return send(new TypeToken<List<User>>() {
        }.getType());

    }

    @Override
    public Receive<User> login(String username, String password, int type) {
        setMethod("login");
        putParams("username", username);
        putParams("password", password);
        putParams("type", type);
        return send(new TypeToken<User>() {
        }.getType());
    }


    @Override
    public Receive<User> register(String name, String username, String password) {
        setMethod("register");
        putParams("name", name);
        putParams("username", username);
        putParams("password", password);
        return send(new TypeToken<User>() {
        }.getType());
    }


    @Override
    public Receive<Void> logout() {
        setMethod("logout");

        return send(new TypeToken<Void>() {
        }.getType());
    }


    @Override
    public Receive<Void> add_student(int student_id, int relation_id,String student_name) {
        setMethod("add_student");
        putParams("student_id", student_id);
        putParams("relation_id", relation_id);
        putParams("student_name", student_name);
        return send(new TypeToken<Void>() {
        }.getType());
    }

    @Override
    public Receive<List<Award>> award_list() {
        setMethod("award_list");

        return send(new TypeToken<List<Award>>() {
        }.getType());

    }


    @Override
    public Receive<List<ParentStudent>> parent_student() {
        setMethod("parent_student");
        return send(new TypeToken<List<ParentStudent>>() {
        }.getType());

    }

    @Override
    public Receive<User> user_detail(int user_id) {
        setMethod("user_detail");
        putParams("user_id", user_id);
        return send(new TypeToken<User>() {
        }.getType());
    }


    @Override
    public Receive<List<Relation>> get_relation_list() {
        setMethod("get_relation_list");
        return send(new TypeToken<List<Relation>>() {
        }.getType());
    }


    @Override
    public Receive<Class> student_class(int student_id) {
        setMethod("student_class");
        putParams("student_id", student_id);
        return send(new TypeToken<Class>() {
        }.getType());
    }

    @Override
    public Receive<List<Mark>> get_mark(int exam_id) {
        setMethod("get_mark");
        putParams("exam_id", exam_id);
        return send(new TypeToken<List<Mark>>() {
        }.getType());
    }

    @Override
    public Receive<Void> update_current_student(int student_id) {
        setMethod("update_current_student");
        putParams("student_id", student_id);
        return send(new TypeToken<Void>() {
        }.getType());
    }


    @Override
    public Receive<List<Exam>> get_exam_list() {
        setMethod("get_exam_list");

        return send(new TypeToken<List<Exam>>() {
        }.getType());

    }

    @Override
    public Receive<List<Remark>> get_remark() {
        setMethod("get_remark");

        return send(new TypeToken<List<Remark>>() {
        }.getType());

    }

    @Override
    public Receive<List<Signin>> student_signin(Timestamp time) {
        setMethod("student_signin");
        putParams("time", time);
        return send(new TypeToken<List<Signin>>() {
        }.getType());
    }

    @Override
    public Receive<List<Classact>> get_activity() {
        setMethod("get_activity");
        return send(new TypeToken<List<Classact>>() {
        }.getType());
    }

    @Override
    public Receive<List<TeacherParent>> teacher_parent() {
        setMethod("teacher_parent");
        return send(new TypeToken<List<TeacherParent>>() {
        }.getType());
    }

    @Override
    public Receive<List<Message>> get_message_list(int page, boolean as_send) {
        setMethod("get_message_list");
        if (as_send)
            putParams("as_send", true);
        putParams("page", page);
        return send(new TypeToken<List<Message>>() {
        }.getType());
    }

    @Override
    public Receive<List<Receiver>> get_message_receiver(int message_id, int page) {
        setMethod("get_message_receiver");
        putParams("message_id", message_id);
        putParams("page", page);
        return send(new TypeToken<List<Receiver>>() {
        }.getType());
    }


    @Override
    public Receive<List<Post>> get_post_list(int message_id, int page) {
        setMethod("get_post_list");
        putParams("message_id", message_id);
        putParams("page", page);
        return send(new TypeToken<List<Post>>() {
        }.getType());
    }

    @Override
    public Receive<Void> send_message(String title, String context, int receiver) {
        setMethod("send_message");
        putParams("title", title);
        putParams("content", context);
        putParams("receiver", receiver);
        return send(new TypeToken<Void>() {
        }.getType());
    }

    @Override
    public Receive<Void> send_message(String title, String context, List<Integer> receivers) {
        setMethod("send_message");
        putParams("title", title);
        putParams("content", context);
        putParams("receiver", receivers);
        return send(new TypeToken<Void>() {
        }.getType());
    }

    @Override
    public Receive<Void> post_message(int message_id, String context) {
        setMethod("post_message");
        putParams("message_id", message_id);
        putParams("content", context);

        return send(new TypeToken<Void>() {
        }.getType());
    }

    @Override
    public Receive<Void> update_parent_student_status(int parent_id, int student_id, int status) {
        setMethod("update_parent_student_status");
        putParams("parent_id", parent_id);
        putParams("student_id", student_id);
        putParams("status", status);

        return send(new TypeToken<Void>() {
        }.getType());
    }

    @Override
    public Receive<List<User>> get_contacts() {
        setMethod("get_contacts");
        return send(new TypeToken<List<User>>() {
        }.getType());
    }




    @Override
    public Receive<List<HomeworkDate>> get_homework_date_list() {
        setMethod("get_homework_dat_List");
        return send(new TypeToken<List<HomeworkDate>>() {
        }.getType());
    }
}

