package com.example.pma_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.pma_1.Classes.LanguageSpinner.LanguageManager;
import com.example.pma_1.ViewModel.StudentViewModel;

public class HomeActivity extends AppCompatActivity {

    //


    TextView tvText;
    StudentViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


    }
}