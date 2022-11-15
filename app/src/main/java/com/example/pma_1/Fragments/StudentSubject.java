package com.example.pma_1.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pma_1.R;
import com.example.pma_1.Subject;
import com.example.pma_1.SubjectInfoActivity;
import com.example.pma_1.ViewModel.StudentViewModel;
import com.example.pma_1.databinding.ActivityHomeBinding;
import com.google.android.material.textfield.TextInputLayout;

public class StudentSubject extends Fragment {


    View layoutView;
    StudentViewModel viewModel;
    TextView textView;

    private TextInputLayout tilName;
    private TextInputLayout tilSurname;
    private EditText tilYear;
    private EditText tilLectures;
    private EditText tilPractices;
    private Button btnSendSubject;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layoutView = inflater.inflate(R.layout.fragment_student_subject, container, false);



        return layoutView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textView = layoutView.findViewById(R.id.fragmentStudentSubject);
        viewModel = new ViewModelProvider(requireActivity()).get(StudentViewModel.class);
        textView.setText(viewModel.getSelectedItem().getValue());


        tilName = layoutView.findViewById(R.id.tvStudentName);
        tilSurname = layoutView.findViewById(R.id.tvStudentSurname);
        tilYear = layoutView.findViewById(R.id.etCollegeYear);
        tilLectures = layoutView.findViewById(R.id.etLectures);
        tilPractices = layoutView.findViewById(R.id.etPractices);
        btnSendSubject = layoutView.findViewById(R.id.btnSendProfessor);
        btnSendSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tilName.getEditText().getText().toString().equals("") ||
                        tilSurname.getEditText().getText().toString().equals("") ||
                        tilYear.getText().toString().equals("") ||
                        tilPractices.getText().toString().equals("") ||
                        tilLectures.getText().toString().equals(""))
                    Toast.makeText(getActivity(), "To proceed, fill all fields ", Toast.LENGTH_SHORT).show();
                else {
                    Subject subject = new Subject(tilName.getEditText().getText().toString(), tilSurname.getEditText().getText().toString(),
                            Integer.parseInt(tilYear.getText().toString()), Integer.parseInt(tilPractices.getText().toString()),
                            Integer.parseInt(tilLectures.getText().toString()));
                }
            }
        });
    }
}