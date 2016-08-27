package com.banixc.fsc.fscroid.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.banixc.fsc.core.ActionCallbackListener;
import com.banixc.fsc.fscroid.R;
import com.banixc.fsc.fscroid.base.BaseActivity;
import com.banixc.fsc.model.Error;
import com.banixc.fsc.model.Relation;

import java.util.ArrayList;
import java.util.List;

public class AddStudentActivity extends BaseActivity implements View.OnClickListener {
    private EditText editAddStudentId;
    private EditText editAddStudentName;
    private Button editAddStudentRelation;
    private Button buttonAddStudentConfirm;

    List<Relation> relation = new ArrayList<>();
    private String[] relationname;
    int relationchoise;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("学生管理");
        actionBar.setHomeButtonEnabled(true);

        initView();
        initData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home: //对用户按home icon的处理，本例只需关闭activity，就可返回上一activity，即主activity。
                finish();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initView() {

        editAddStudentId = (EditText) findViewById(R.id.edit_add_student_id);
        editAddStudentName = (EditText) findViewById(R.id.edit_add_student_name);
        editAddStudentRelation = (Button) findViewById(R.id.edit_add_student_relation);
        editAddStudentRelation.setOnClickListener(this);
        buttonAddStudentConfirm = (Button) findViewById(R.id.button_add_student_confirm);
        buttonAddStudentConfirm.setOnClickListener(this);
    }

    @Override
    protected void initData() {


        getAction().get_relation_list(new ActionCallbackListener<List<Relation>>() {
            @Override
            public void onSuccess(List<Relation> data) {

                relationname = new String[data.size()];
                for (int m = 0; m < data.size(); m++) {
                    relation.add(data.get(m));
                    relationname[m] = data.get(m).getName();
                }
            }

            @Override
            public void onFailure(Error error) {

            }
        });

    }

    public static void strart(Activity activity) {
        Intent intent = new Intent(activity, AddStudentActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_add_student_confirm:
                if(editAddStudentId.getText().length()>0&&relationchoise>0){
                    int id = Integer.parseInt(editAddStudentId.getText().toString());
                    String name =editAddStudentName.getText().toString();

                    getAction().add_student(id, relationchoise,name, new ActionCallbackListener<Void>() {
                        @Override
                        public void onSuccess(Void data) {
                            new AlertDialog.Builder(AddStudentActivity.this).setTitle("温馨提示")//设置对话框标题
                                    .setMessage("添加学生成功，刷新关联学生列表查看")//设置显示的内容
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.dismiss();
                                            finish();
                                        }
                                    }).show();
                        }

                        @Override
                        public void onFailure(Error error) {

                        }
                    });
                }else
                    Toast.makeText(this, "请完成所以输入或选择", Toast.LENGTH_SHORT).show();

                break;
            case R.id.edit_add_student_relation:
                final AlertDialog.Builder builder = new AlertDialog.Builder(AddStudentActivity.this);
                builder.setTitle("选择关系");
                //    指定下拉列表的显示数据
                builder.setItems(relationname, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        relationchoise=which;
                        editAddStudentRelation.setText(relationname[which]);
                    }
                });
                builder.show();
        }
    }

}

