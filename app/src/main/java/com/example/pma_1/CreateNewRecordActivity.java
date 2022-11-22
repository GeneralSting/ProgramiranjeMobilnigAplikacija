package com.example.pma_1;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.pma_1.Classes.Adapters.NewRecordAdapter;
import com.example.pma_1.Classes.Student.Student;
import com.example.pma_1.Classes.Student.Subject;
import com.example.pma_1.Classes.StudentsRV.StudentRecyclerList;
import com.example.pma_1.Fragments.Student.StudentInfo;
import com.example.pma_1.ViewModel.StudentViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class CreateNewRecordActivity extends FragmentActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private NewRecordAdapter newRecordAdapter;
    private StudentViewModel studentViewModel;
    private Student student;
    private Subject subject;

    Boolean recivedList = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_record);

        if(getIntent().hasExtra("studentsList")) {
            studentViewModel = new ViewModelProvider(CreateNewRecordActivity.this).get(StudentViewModel.class);
            studentViewModel.checkStudentRecyclerList.setValue(
            Boolean.FALSE.equals(studentViewModel.checkStudentRecyclerList.getValue()));

            studentViewModel.setStudentRecyclerList((List<StudentRecyclerList>) getIntent().getSerializableExtra("studentsList"));
        }


        tabLayout = findViewById(R.id.tlNewRecord);
        viewPager = findViewById(R.id.vpNewRecord);

        newRecordAdapter = new NewRecordAdapter(this);
        viewPager.setAdapter(newRecordAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch(tab.getPosition()) {
                    case 1:
                        studentViewModel = new ViewModelProvider(CreateNewRecordActivity.this).get(StudentViewModel.class);
                        studentViewModel.checkStudent.setValue(Boolean.FALSE.equals(studentViewModel.checkStudent.getValue()));
                        studentViewModel.getLiveDataStudent().observe(CreateNewRecordActivity.this, observedStudent -> {
                            student = observedStudent;
                        });
                        break;
                    case 2:
                        studentViewModel = new ViewModelProvider(CreateNewRecordActivity.this).get(StudentViewModel.class);
                        studentViewModel.checkSubject.setValue(Boolean.FALSE.equals(studentViewModel.checkSubject.getValue()));
                        studentViewModel.getLiveDataSubject().observe(CreateNewRecordActivity.this, observedSubject -> {
                            subject = observedSubject;
                        });
                        studentViewModel.checkSummary.setValue(Boolean.FALSE.equals(studentViewModel.checkSummary.getValue()));
                        break;
                    default: {
                        studentViewModel = new ViewModelProvider(CreateNewRecordActivity.this).get(StudentViewModel.class);
                        studentViewModel.getLiveDataStudent().observe(CreateNewRecordActivity.this, observedStudent -> {
                            student = observedStudent;
                        });
                    }
                }
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });
    }
}