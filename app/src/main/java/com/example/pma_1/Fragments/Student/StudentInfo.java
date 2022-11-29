package com.example.pma_1.Fragments.Student;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pma_1.Classes.Models.College;
import com.example.pma_1.Classes.Student.Student;
import com.example.pma_1.CreateNewRecordActivity;
import com.example.pma_1.R;
import com.example.pma_1.ViewModel.StudentViewModel;
import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


public class StudentInfo extends Fragment {

    public static final int CAMERA_PERM_CODE = 101;
    public static final int CAMERA_REQUEST_CODE = 102;
    private TextInputLayout tilName;
    private TextInputLayout tilSurname;
    private ImageView ivStudent;
    final Calendar myCalendar = Calendar.getInstance();
    private EditText birthDate;
    private StudentViewModel studentViewModel;
    private Bitmap image;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_student_info, container, false);

        studentViewModel = new ViewModelProvider(requireActivity()).get(StudentViewModel.class);
        tilName = rootView.findViewById(R.id.tvStudentName);
        tilSurname = rootView.findViewById(R.id.tvStudentSurname);
        birthDate = (EditText) rootView.findViewById(R.id.etBirthDate);
        ivStudent = rootView.findViewById(R.id.profileImageStudent);

        ivStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                askCameraPermission();
            }
        });

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
                new DatePickerDialog(getActivity(), date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        studentViewModel = new ViewModelProvider(getActivity()).get(StudentViewModel.class);
        studentViewModel.getLiveCheckStudent().observe(getActivity(), observedCheckStudent -> {
            if (tilName.getEditText().getText().toString().equals("") || tilSurname.getEditText().getText().toString().equals("")
            || birthDate.getText().toString().equals("") || image == null)
                Toast.makeText(getActivity(), getActivity().getString(R.string.uncompleted_student), Toast.LENGTH_SHORT).show();
            else {
                Student student = new Student(tilName.getEditText().getText().toString(),
                tilSurname.getEditText().getText().toString(), birthDate.getText().toString(), image);
                studentViewModel.setStudent(student);
            }
        });
        return rootView;
    }

    private void askCameraPermission() {
        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.CAMERA}, CAMERA_PERM_CODE);
        else
            openCamera();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == CAMERA_REQUEST_CODE) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            }
            else
                Toast.makeText(getActivity(), "DENIED", Toast.LENGTH_SHORT).show();
        }
    }

    private void openCamera() {
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera, CAMERA_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == CAMERA_REQUEST_CODE) {
            image = (Bitmap) data.getExtras().get("data");
            ivStudent.setImageBitmap(image);
        }
    }

    private void updateLabel(){
        String myFormat="dd/MM/yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.GERMANY);
        birthDate.setText(dateFormat.format(myCalendar.getTime()));
    }
}