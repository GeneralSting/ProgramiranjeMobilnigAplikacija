package com.example.pma_1.Fragments.Student;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pma_1.Classes.Student.Student;
import com.example.pma_1.Classes.Student.Subject;
import com.example.pma_1.Classes.Student.Summary;
import com.example.pma_1.Classes.StudentsRV.StudentRecyclerList;
import com.example.pma_1.R;
import com.example.pma_1.SummaryActivity;
import com.example.pma_1.ViewModel.StudentViewModel;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class StudentSummary extends Fragment {


    View layoutView;
    StudentViewModel studentViewModel;
    Boolean studentAdded = false;
    Boolean subjectAdded = false;
    Student student;
    Subject subject;

    private EditText etStudentName;
    private EditText etStudentSurname;
    private EditText etStudentBirthDate;
    private EditText etProfessorName;
    private EditText etProfessorSurname;
    private EditText etsubjectYear;
    private EditText etSubjectLectures;
    private EditText etSubjectPractices;
    private Button btnExitSummary;

    private List<StudentRecyclerList> studentRecyclerList;
    Boolean recivedList = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layoutView = inflater.inflate(R.layout.fragment_student_summary, container, false);



        etStudentName = layoutView.findViewById(R.id.tvStudentName);
        etStudentSurname = layoutView.findViewById(R.id.tvStudentSurname);
        etStudentBirthDate = layoutView.findViewById(R.id.tvStudentBirthDate);
        etProfessorName = layoutView.findViewById(R.id.tvProfessorName);
        etProfessorSurname = layoutView.findViewById(R.id.tvProfessorSurname);
        etsubjectYear = (EditText) layoutView.findViewById(R.id.tvCollegeYear);
        etSubjectLectures = (EditText) layoutView.findViewById(R.id.tvLectures);
        etSubjectPractices = (EditText) layoutView.findViewById(R.id.tvPractices);

        studentViewModel = new ViewModelProvider(getActivity()).get(StudentViewModel.class);
        studentViewModel.getcheckStudentRecyclerList().observe(getActivity(), checkStudentsList -> {
            recivedList = true;
        });
        studentViewModel.getLiveDataStudentRecyclerList().observe(getActivity(), observedStudentList -> {
            studentRecyclerList = observedStudentList;
        });
        studentViewModel.getLiveDataStudent().observe(getActivity(), observedStudent -> {
            student = observedStudent;
            studentAdded = true;
        });

        studentViewModel.getLiveDataSubject().observe(getActivity(), observedSubject -> {
            subject = observedSubject;
            subjectAdded = true;
        });
        studentViewModel.getLiveCheckSummary().observe(getActivity(), observedCheckSummary -> {
            if(studentAdded && subjectAdded) {
                Summary summary = new Summary(student.getName(), student.getSurname(), student.getBirthDate(), subject.getName(),
                subject.getSubject(), subject.getYear(), subject.getLectures(), subject.getPractices());

                etStudentName.setText(summary.getStudentName());
                etStudentSurname.setText(summary.getStudentSurname());
                etStudentBirthDate.setText(summary.getStudentBirthDate());
                etProfessorName.setText(summary.getProfessorName());
                etProfessorSurname.setText(summary.getSubjectName());
                etsubjectYear.setText(""+ summary.getSubjectYear());
                etSubjectLectures.setText(""+ summary.getSubjectLectures());
                etSubjectPractices.setText(""+ summary.getSubjectPractices());

                btnExitSummary = layoutView.findViewById(R.id.btnExitSummary);
                btnExitSummary.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(recivedList)
                            summary.exitSummary2(getActivity(), student, subject, studentViewModel.getLiveDataStudentRecyclerList().getValue());
                        else
                            summary.exitSummary(getActivity(), student, subject);
                    }
                });
            }
        });


        return layoutView;
    }
}