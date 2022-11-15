package com.example.pma_1.Fragments;

import android.app.DatePickerDialog;
import android.content.ClipData;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.pma_1.HomeActivity;
import com.example.pma_1.databinding.ActivityHomeBinding;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.pma_1.R;
import com.example.pma_1.Student;
import com.example.pma_1.ViewModel.StudentViewModel;
import com.google.android.material.textfield.TextInputLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddStudent extends Fragment {


    private Button btnSendStudent;
    private TextInputLayout tilName;
    private TextInputLayout tilSurname;

    final Calendar myCalendar = Calendar.getInstance();
    private EditText birthDate;

    View layoutView;

    //
    StudentViewModel viewModel;
    private HomeActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layoutView = inflater.inflate(R.layout.fragment_add_student, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(StudentViewModel.class);

        mainActivity = (HomeActivity) getActivity();


        tilName = layoutView.findViewById(R.id.tvStudentName);
        tilName.getEditText().setText("pao");
        tilSurname = layoutView.findViewById(R.id.tvStudentSurname);
        btnSendStudent = layoutView.findViewById(R.id.btnSendProfessor);
        birthDate = (EditText) layoutView.findViewById(R.id.etBirthDate);
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };

        birthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getActivity(),date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btnSendStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (tilName.getEditText().getText().toString().equals("") || tilSurname.getEditText().getText().toString().equals("") || birthDate.getText().toString().equals(""))
                    Toast.makeText(getActivity(), "To proceed, fill all fields. ", Toast.LENGTH_SHORT).show();
                else {

                    TextInputLayout til = view.findViewById(R.id.tvStudentName);
                    viewModel.setData(tilName.getEditText().getText().toString());

                    TextView textView = (TextView)getActivity().findViewById(R.id.tvText);
                    textView.setText(tilName.getEditText().getText().toString());
                    Student student = new Student(tilName.getEditText().getText().toString(), tilSurname.getEditText().getText().toString(), birthDate.getText().toString());
                    StudentSubject studentSubject = new StudentSubject();
                    mainActivity.checkFragment = true;
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.mainFrameLayout, studentSubject, "findThisFragment")
                            .commit();
                }
            }
        });
        return layoutView;
    }

    private void updateLabel(){
        String myFormat="dd/MM/yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.GERMANY);
        birthDate.setText(dateFormat.format(myCalendar.getTime()));
    }

}
