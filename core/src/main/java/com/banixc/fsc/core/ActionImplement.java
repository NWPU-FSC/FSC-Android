package com.banixc.fsc.core;

import com.banixc.fsc.api.HttpEngine;
import com.banixc.fsc.model.Award;
import com.banixc.fsc.model.Class;
import com.banixc.fsc.model.Classact;
import com.banixc.fsc.model.Connect;
import com.banixc.fsc.model.Error;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.banixc.fsc.api.Api;
import com.banixc.fsc.api.ApiImplement;
import com.banixc.fsc.api.Receive;
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


public class ActionImplement implements Action {



    private Context context;
    private Api api;
    private SpHelp spHelp;


    public static ActionImplement newInstance(Context context) {
        return new ActionImplement(context);
    }

    public ActionImplement(Context context) {
        this.context = context;
        this.spHelp = new SpHelp(context);

        String token = spHelp.getToken();

        api = token.length() == 32 ? new ApiImplement(token) : new ApiImplement(null);
        HttpEngine.setServerUrl(spHelp.getUrl());
    }


    @Override
    public boolean is_login() {
        return spHelp.getToken().length() == 32;
    }

    @Override
    public boolean is_set_url_correct() {
        return HttpEngine.getServerUrl().length()>6;
    }

    @Override
    public int get_type() {
        return spHelp.getType();
    }

    @Override
    public void set_url(String url) {
        HttpEngine.setServerUrl(url);
    }

    @Override
    public String get_url() {
        return HttpEngine.getServerUrl();
    }

    @Override
    public User get_log_user() {
        return new User().setName(spHelp.getUserName());
    }

    @Override
    public Connect connect(ActionCallbackListener<Connect> listener) {
        new BaseAsyncTask<Connect>(listener){

            @Override
            protected Receive<Connect> doInBackground(Void... voids) {

                Receive<Connect> connectReceive =  api.connect();
                if(connectReceive.success())
                    spHelp.setUrl(HttpEngine.getServerUrl());
                return connectReceive;
            }
        }.execute();
        return null;
    }

    @Override
    public User login(final String username, final String password, final int type, ActionCallbackListener<User> listener) {
        new BaseAsyncTask<User>(listener) {

            @Override
            protected Receive<User> doInBackground(Void... voids) {
                Receive<User> userReceive = api.login(username, password, type);
                if (userReceive.success()) {
                    String token = userReceive.getResult().getToken();
                    spHelp.saveToken(token);
                    spHelp.saveType(type);
                    spHelp.setUserName(userReceive.getResult().getName());
                    api.setToken(token);
                }
                return userReceive;
            }
        }.execute();
        return null;
    }


    @Override
    public User register(final String name, final String username, final String password, ActionCallbackListener<User> listener) {
        new BaseAsyncTask<User>(listener) {

            @Override
            protected Receive<User> doInBackground(Void... voids) {
                return api.register(name, username, password);
            }
        }.execute();
        return null;
    }

    @Override
    public Void logout(ActionCallbackListener<Void> listener) {
        new BaseAsyncTask<Void>(listener) {

            @Override
            protected Receive<Void> doInBackground(Void... voids) {
                Receive<Void> logoutReceive = api.logout();
                if (logoutReceive.success()) {
                    spHelp.saveToken("");
                    spHelp.saveType(-1);
                    api.setToken(null);
                }
                return logoutReceive;
            }
        }.execute();
        return null;
    }

    @Override
    public Void add_student(final int student_id, final int relation_id,final String student_name, ActionCallbackListener<Void> listener) {
        new BaseAsyncTask<Void>(listener) {

            @Override
            protected Receive<Void> doInBackground(Void... voids) {
                return api.add_student(student_id, relation_id,student_name);
            }
        }.execute();
        return null;
    }


    @Override
    public List<News> news_list(final int page, final int status, final int sender, final int type, final ActionCallbackListener<List<News>> listener) {

        new BaseAsyncTask<List<News>>(listener) {

            @Override
            protected Receive<List<News>> doInBackground(Void... voids) {
                return api.news_list(page, status, sender, type);
            }
        }.execute();
        return null;
    }

    @Override
    public News news_detail(final int id, ActionCallbackListener<News> listener) {
        new BaseAsyncTask<News>(listener) {

            @Override
            protected Receive<News> doInBackground(Void... voids) {
                return api.news_detail(id);
            }
        }.execute();
        return null;
    }

    @Override
    public List<Message> get_message(final int message_id, ActionCallbackListener<List<Message>> listener) {
        new BaseAsyncTask<List<Message>>(listener) {

            @Override
            protected Receive<List<Message>> doInBackground(Void... voids) {
                return api.get_message(message_id);
            }
        }.execute();
        return null;
    }


    @Override
    public List<Mark> student_mark(final int student_id, final int exam_id, ActionCallbackListener<List<Mark>> listener) {
        new BaseAsyncTask<List<Mark>>(listener) {

            @Override
            protected Receive<List<Mark>> doInBackground(Void... voids) {
                return api.student_mark(student_id, exam_id);
            }
        }.execute();
        return null;
    }


    @Override
    public List<User> student_teacher(final int student_id, ActionCallbackListener<List<User>> listener) {
        new BaseAsyncTask<List<User>>(listener) {

            @Override
            protected Receive<List<User>> doInBackground(Void... voids) {
                return api.student_teacher(student_id);
            }
        }.execute();
        return null;
    }

    @Override
    public List<Homework> student_homework(ActionCallbackListener<List<Homework>> listener) {
        new BaseAsyncTask<List<Homework>>(listener) {

            @Override
            protected Receive<List<Homework>> doInBackground(Void... voids) {
                return api.student_homework();
            }
        }.execute();
        return null;
    }


    @Override
    public List<Award> award_list(ActionCallbackListener<List<Award>> listener) {
        new BaseAsyncTask<List<Award>>(listener) {

            @Override
            protected Receive<List<Award>> doInBackground(Void... voids) {
                return api.award_list();
            }
        }.execute();
        return null;
    }


    @Override
    public List<ParentStudent> parent_student(ActionCallbackListener<List<ParentStudent>> listener) {
        new BaseAsyncTask<List<ParentStudent>>(listener) {

            @Override
            protected Receive<List<ParentStudent>> doInBackground(Void... voids) {
                return api.parent_student();
            }
        }.execute();
        return null;
    }

    @Override
    public User user_detail(final int user_id, ActionCallbackListener<User> listener) {
        new BaseAsyncTask<User>(listener) {

            @Override
            protected Receive<User> doInBackground(Void... voids) {
                return api.user_detail(user_id);
            }
        }.execute();
        return null;
    }


    @Override
    public Class student_class(final int student_id, ActionCallbackListener<Class> listener) {
        new BaseAsyncTask<Class>(listener) {

            @Override
            protected Receive<Class> doInBackground(Void... voids) {
                return api.student_class(student_id);
            }
        }.execute();
        return null;
    }

    @Override
    public List<Relation> get_relation_list(ActionCallbackListener<List<Relation>> listener) {
        new BaseAsyncTask<List<Relation>>(listener) {

            @Override
            protected Receive<List<Relation>> doInBackground(Void... voids) {
                return api.get_relation_list();
            }
        }.execute();
        return null;
    }


    @Override
    public Void update_current_student(final int student_id, ActionCallbackListener<Void> listener) {
        new BaseAsyncTask<Void>(listener) {

            @Override
            protected Receive<Void> doInBackground(Void... voids) {
                return api.update_current_student(student_id);
            }
        }.execute();
        return null;
    }


    @Override
    public List<Exam> get_exam_list(ActionCallbackListener<List<Exam>> listener) {
        new BaseAsyncTask<List<Exam>>(listener) {

            @Override
            protected Receive<List<Exam>> doInBackground(Void... voids) {
                return api.get_exam_list();
            }
        }.execute();
        return null;
    }

    @Override
    public List<Remark> get_remark(ActionCallbackListener<List<Remark>> listener) {
        new BaseAsyncTask<List<Remark>>(listener) {

            @Override
            protected Receive<List<Remark>> doInBackground(Void... voids) {
                return api.get_remark();
            }
        }.execute();
        return null;
    }

    @Override
    public List<Mark> get_mark(final int exam_id, ActionCallbackListener<List<Mark>> listener) {
        new BaseAsyncTask<List<Mark>>(listener) {

            @Override
            protected Receive<List<Mark>> doInBackground(Void... voids) {
                return api.get_mark(exam_id);
            }
        }.execute();
        return null;
    }

    @Override
    public List<Signin> student_signin(final Timestamp time, ActionCallbackListener<List<Signin>> listener) {
        new BaseAsyncTask<List<Signin>>(listener) {

            @Override
            protected Receive<List<Signin>> doInBackground(Void... voids) {
                return api.student_signin(time);
            }
        }.execute();
        return null;
    }


    @Override
    public List<Classact> get_activity(ActionCallbackListener<List<Classact>> listener) {
        new BaseAsyncTask<List<Classact>>(listener) {

            @Override
            protected Receive<List<Classact>> doInBackground(Void... voids) {
                return api.get_activity();
            }
        }.execute();
        return null;
    }

    @Override
    public List<Message> get_receive_message(final int page, ActionCallbackListener<List<Message>> listener) {
        new BaseAsyncTask<List<Message>>(listener) {

            @Override
            protected Receive<List<Message>> doInBackground(Void... voids) {
                return api.get_message_list(page, false);
            }
        }.execute();
        return null;
    }

    @Override
    public List<Message> get_send_message(final int page, ActionCallbackListener<List<Message>> listener) {
        new BaseAsyncTask<List<Message>>(listener) {

            @Override
            protected Receive<List<Message>> doInBackground(Void... voids) {
                return api.get_message_list(page, true);
            }
        }.execute();
        return null;
    }


    @Override
    public List<TeacherParent> teacher_parent(ActionCallbackListener<List<TeacherParent>> listener) {
        new BaseAsyncTask<List<TeacherParent>>(listener) {

            @Override
            protected Receive<List<TeacherParent>> doInBackground(Void... voids) {
                return api.teacher_parent();
            }
        }.execute();
        return null;
    }


    @Override
    public List<Receiver> get_message_receiver(final int message_id, final int page, ActionCallbackListener<List<Receiver>> listener) {
        new BaseAsyncTask<List<Receiver>>(listener) {

            @Override
            protected Receive<List<Receiver>> doInBackground(Void... voids) {
                return api.get_message_receiver(message_id,page);
            }
        }.execute();
        return null;
    }


    @Override
    public List<Post> get_post_list(final int message_id, final int page, ActionCallbackListener<List<Post>> listener) {
        new BaseAsyncTask<List<Post>>(listener) {

            @Override
            protected Receive<List<Post>> doInBackground(Void... voids) {
                return api.get_post_list(message_id, page);
            }
        }.execute();
        return null;
    }

    @Override
    public Void send_message(final String title, final String context, final int receiver, ActionCallbackListener<Void> listener) {
        new BaseAsyncTask<Void>(listener) {

            @Override
            protected Receive<Void> doInBackground(Void... voids) {
                return api.send_message(title, context, receiver);
            }
        }.execute();
        return null;
    }
    @Override
    public Void send_message(final String title, final String context, final List<Integer> receivers, ActionCallbackListener<Void> listener) {
        new BaseAsyncTask<Void>(listener) {

            @Override
            protected Receive<Void> doInBackground(Void... voids) {
                return api.send_message(title, context, receivers);
            }
        }.execute();
        return null;
    }

    @Override
    public Void post_message(final int message_id, final String context, ActionCallbackListener<Void> listener) {
        new BaseAsyncTask<Void>(listener) {

            @Override
            protected Receive<Void> doInBackground(Void... voids) {
                return api.post_message(message_id, context);
            }
        }.execute();
        return null;
    }

    @Override
    public Void update_parent_student_status(final int parent_id, final int student_id, final int status, ActionCallbackListener<Void> listener) {
        new BaseAsyncTask<Void>(listener) {

            @Override
            protected Receive<Void> doInBackground(Void... voids) {
                return api.update_parent_student_status(parent_id, student_id,status);
            }
        }.execute();
        return null;    }



    @Override
    public List<User> get_contacts(ActionCallbackListener<List<User>> listener) {
        new BaseAsyncTask<List<User>>(listener) {

            @Override
            protected Receive<List<User>> doInBackground(Void... voids) {
                return api.get_contacts();
            }
        }.execute();
        return null;
    }

    @Override
    public List<HomeworkDate> get_homework_date_list( ActionCallbackListener<List<HomeworkDate>> listener) {
        new BaseAsyncTask<List<HomeworkDate>>(listener) {

            @Override
            protected Receive<List<HomeworkDate>> doInBackground(Void... voids) {
                return api.get_homework_date_list();
            }
        }.execute();
        return null;
    }










    public void ToastError(Error error) {
        if (error != null)
            Toast.makeText(context, error.getStatus() + " " + error.getMessage(), Toast.LENGTH_LONG).show();
        else
            Toast.makeText(context, "未知错误@" + context.toString(), Toast.LENGTH_LONG).show();
    }


    abstract class BaseAsyncTask<T> extends AsyncTask<Void, Void, Receive<T>> {

        ActionCallbackListener<T> listener;

        public BaseAsyncTask(ActionCallbackListener<T> listener) {
            this.listener = listener;
        }

        @Override
        protected void onPostExecute(Receive<T> receive) {
            if (listener != null && receive != null) {
                if (null == receive.getError()) {
                    listener.onSuccess(receive.getResult());
                } else {
                    ToastError(receive.getError());
                    listener.onFailure(receive.getError());
                }
            }
        }
    }

}
