package com.example.pma_1.Fragments.Student;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pma_1.Classes.Models.College;
import com.example.pma_1.Classes.Models.Courses;
import com.example.pma_1.Classes.Network.ApiManager;
import com.example.pma_1.Classes.Student.Subject;
import com.example.pma_1.Classes.StudentsRV.StudentRecyclerList;
import com.example.pma_1.CreateNewRecordActivity;
import com.example.pma_1.HomeActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pma_1.R;
import com.example.pma_1.SubjectInfoActivity;
import com.example.pma_1.ViewModel.StudentViewModel;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubjectInfo extends Fragment implements Callback<College> {

    private View layoutView;
    private TextView textView;
    private TextInputLayout tilName;
    private EditText tilYear;
    private EditText tilLectures;
    private EditText tilPractices;
    private Spinner subjectSpinner;
    private ArrayList<String> subjectSpinnerItems = new ArrayList<String>();


    private StudentViewModel studentViewModel;
    College college = new College();
    ArrayList<Courses> courses = new ArrayList<>();

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
        tilYear = layoutView.findViewById(R.id.etCollegeYear);
        tilLectures = layoutView.findViewById(R.id.etLectures);
        tilPractices = layoutView.findViewById(R.id.etPractices);
        subjectSpinner = layoutView.findViewById(R.id.subjectSpinner);






        ApiManager.getInstance().service().getCollege().enqueue(this);



        studentViewModel.getLiveCheckSubject().observe(getActivity(), observedCheckSubject -> {
            if(tilName.getEditText().getText().toString().equals("") ||
                    subjectSpinner.getSelectedItem().toString().equals("") ||  tilYear.getText().toString().equals("") ||
                    tilPractices.getText().toString().equals("") || tilLectures.getText().toString().equals(""))
                Toast.makeText(getActivity(), getActivity().getString(R.string.uncompleted_student), Toast.LENGTH_SHORT).show();
            else {
                Subject subject = new Subject(tilName.getEditText().getText().toString(), subjectSpinner.getSelectedItem().toString(),
                        Integer.parseInt(tilYear.getText().toString()), Integer.parseInt(tilPractices.getText().toString()),
                        Integer.parseInt(tilLectures.getText().toString()));
                studentViewModel.setSubject(subject);
            }
        });


        return layoutView;
    }

    @Override
    public void onResponse(Call<College> call, Response<College> response) {
        if(response.isSuccessful() && response.body() != null) {
            if(response.body().getCoursesList() != null)
            {
                courses.addAll(response.body().getCoursesList());
                for(int i = 0; i < courses.size(); i++) {
                    subjectSpinnerItems.add(courses.get(i).getName());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(layoutView.getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, subjectSpinnerItems);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                subjectSpinner.setAdapter(adapter);
            }
        }

    }

    @Override
    public void onFailure(Call<College> call, Throwable t) {
        Log.d("failure", "failure");
    }
}
