package com.susy.dormitoryassistant.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.susy.dormitoryassistant.R;
import com.susy.dormitoryassistant.activity.AdminAddDisobeyActivity;
import com.susy.dormitoryassistant.adapter.StudentAdapter;
import com.susy.dormitoryassistant.app.DormitoryApplication;
import com.susy.dormitoryassistant.entity.DormStudents;
import com.susy.dormitoryassistant.entity.Dorms;
import com.susy.dormitoryassistant.entity.Student;
import com.susy.dormitoryassistant.http.AppClient;
import com.susy.dormitoryassistant.utils.UtilTools;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * 管理员端的主界面－4通讯录
 * Created by susy on 17/2/4.
 */
public class AdminContactFragment extends Fragment implements View.OnClickListener {
    private EditText etSearch;
    private Button btnSearch;
    private ListView lvStudent;

    private Dorms dorms = new Dorms();
    private List<String> dormList = new ArrayList<>();
    private List<Student> studentList = new ArrayList<>();
    private StudentAdapter mAdapter;

    private DormitoryApplication mApplication = null;
    private String userId = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mApplication = DormitoryApplication.getmInstance();
        View rootView = inflater.inflate(R.layout.fragment_admin_contact, container, false);
        etSearch = (EditText) rootView.findViewById(R.id.et_search);
        btnSearch = (Button) rootView.findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(this);

        if (mApplication.getGlobalUser() != null) {
            userId = mApplication.getGlobalUser().getData().getId();
            initDormList();
        }

        lvStudent = (ListView) rootView.findViewById(R.id.lv_student);
        mAdapter = new StudentAdapter(getActivity(), studentList, mListener);
        lvStudent.setAdapter(mAdapter);

        return rootView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_search:
                if (dormList != null) {
                    if (etSearch.getText().toString().length() == 8) {
                        //学号搜索
                        searchByStudentId(etSearch.getText().toString());
                    } else if (etSearch.getText().toString().length() == 3) {
                        //寝室号搜索 只能搜管辖范围
                        for (int i = 0; i < dormList.size(); i++) {
                            if (etSearch.getText().toString().equals(dormList.get(i).substring(4))) {
                                searchByDormCode(dormList.get(i));
                                break;
                            } else {
                                if (i == dormList.size() - 1) {
                                    UtilTools.showToast(getActivity(), "请输入学号或管辖寝室号");
                                }
                            }
                        }
                    } else {
                        UtilTools.showToast(getActivity(), "请输入学号或管辖寝室号");
                    }
                }
                break;
        }
    }

    private StudentAdapter.MyClickListener mListener = new StudentAdapter.MyClickListener() {
        @Override
        public void myOnClick(final int position, View v) {
            //UtilTools.showToast(getActivity(), "位置"+studentList.get(position).getStudentName());
            Intent intent = new Intent(getActivity(), AdminAddDisobeyActivity.class);
            intent.putExtra("studentId",studentList.get(position).getStudentId());
            intent.putExtra("studentName",studentList.get(position).getStudentName());
            startActivity(intent);
        }
    };

    private void searchByStudentId(final String studentId) {
        studentList.clear();
        AppClient.ApiStores apiStores = AppClient.retrofit().create(AppClient.ApiStores.class);
        Call<Student> call = apiStores.getStudent(studentId);
        call.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                if (response.body() != null) {
                    studentList.add(response.body());
                    mAdapter.refresh(studentList);
                } else {
                    UtilTools.showToast(getActivity(), "搜索失败");
                }
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                UtilTools.showToast(getActivity(), "搜索失败");
            }
        });
    }

    private void searchByDormCode(String dormitoryId) {
        studentList.clear();
        AppClient.ApiStores apiStores = AppClient.retrofit().create(AppClient.ApiStores.class);
        Call<DormStudents> call = apiStores.getStudentsByDormCode(dormitoryId);
        call.enqueue(new Callback<DormStudents>() {
            @Override
            public void onResponse(Call<DormStudents> call, Response<DormStudents> response) {
                if (response.body().getInfo().equals("查询成功")) {
                    for (Map.Entry<String, Student> entry : response.body().getData().getStudentMap().entrySet()) {
                        studentList.add(entry.getValue());
                    }
                    mAdapter.refresh(studentList);
                }
            }

            @Override
            public void onFailure(Call<DormStudents> call, Throwable t) {

            }
        });
    }

    public void initDormList() {
        dormList.clear();
        AppClient.ApiStores apiStores = AppClient.retrofit().create(AppClient.ApiStores.class);
        Call<Dorms> call = apiStores.adminGetDormList(userId);
        call.enqueue(new Callback<Dorms>() {
            @Override
            public void onResponse(Call<Dorms> call, Response<Dorms> response) {
                dorms = response.body();
                if (dorms.getData() != null) {
                    dormList = dorms.getData();
                }
            }

            @Override
            public void onFailure(Call<Dorms> call, Throwable t) {

            }
        });
    }
}
