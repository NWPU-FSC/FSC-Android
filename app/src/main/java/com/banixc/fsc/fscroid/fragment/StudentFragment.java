package com.banixc.fsc.fscroid.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.banixc.fsc.fscroid.R;
import com.banixc.fsc.fscroid.activity.AddStudentActivity;

import java.util.Date;


/**
 * Created by TianshuiGong on 2016/8/24
 */
public class StudentFragment extends Fragment {



    private Button button;

    public static StudentFragment newInstance() {

        Bundle args = new Bundle();
        StudentFragment fragment = new StudentFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student, container, false);

        button = (Button) view.findViewById(R.id.button_student_fragment_add);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddStudentActivity.strart(getActivity());
            }
        });
    }
}

