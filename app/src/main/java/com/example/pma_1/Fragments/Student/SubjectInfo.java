package com.example.pma_1.Fragments.Student;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pma_1.Classes.Student.Subject;
import com.example.pma_1.Classes.StudentsRV.StudentRecyclerList;
import com.example.pma_1.CreateNewRecordActivity;
import com.example.pma_1.HomeActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pma_1.R;
import com.example.pma_1.SubjectInfoActivity;
import com.example.pma_1.ViewModel.StudentViewModel;
import com.google.android.material.textfield.TextInputLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class SubjectInfo extends Fragment {

    private View layoutView;
    private TextView textView;
    private TextInputLayout tilName;
    private TextInputLayout tilSubject;
    private EditText tilYear;
    private EditText tilLectures;
    private EditText tilPractices;

    private StudentViewModel studentViewModel;

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layoutView = inflater.inflate(R.layout.fragment_subject_info, container, false);

        studentViewModel = new ViewModelProvider(getActivity()).get(StudentViewModel.class);
        studentViewModel.getLiveDataStudent().observe(getActivity(), observedStudent -> {
            textView = layoutView.findViewById(R.id.subjectForStudent);
            textView.setText(observedStudent.getName() + " " + observedStudent.getSurname());
        });

        tilName = layoutView.findViewById(R.id.tvStudentName);
        tilSubject = layoutView.findViewById(R.id.tvStudentSurname);
        tilYear = layoutView.findViewById(R.id.etCollegeYear);
        tilLectures = layoutView.findViewById(R.id.etLectures);
        tilPractices = layoutView.findViewById(R.id.etPractices);

        studentViewModel.getLiveCheckSubject().observe(getActivity(), observedCheckSubject -> {
            if(tilName.getEditText().getText().toString().equals("") ||
                    tilSubject.getEditText().getText().toString().equals("") || tilYear.getText().toString().equals("") ||
                    tilPractices.getText().toString().equals("") || tilLectures.getText().toString().equals(""))
                Toast.makeText(getActivity(), getActivity().getString(R.string.uncompleted_student), Toast.LENGTH_SHORT).show();
            else {
                Subject subject = new Subject(tilName.getEditText().getText().toString(), tilSubject.getEditText().getText().toString(),
                        Integer.parseInt(tilYear.getText().toString()), Integer.parseInt(tilPractices.getText().toString()),
                        Integer.parseInt(tilLectures.getText().toString()));
                studentViewModel.setSubject(subject);
            }
        });


        return layoutView;
    }
}
