package com.example.pma_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.pma_1.Classes.CustomSpinnerAdapter;
import com.example.pma_1.Classes.CustomSpinnerItem;
import com.example.pma_1.Classes.LanguageManager;
import com.example.pma_1.Fragments.AddStudent;
import com.example.pma_1.Fragments.Home;
import com.example.pma_1.Fragments.StudentSubject;
import com.example.pma_1.ViewModel.StudentViewModel;
import com.example.pma_1.databinding.ActivityHomeBinding;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ActivityHomeBinding binding;
    private Spinner customSpinner;
    LanguageManager languageManager = new LanguageManager(this);
    private int focusedFragment;
    public boolean checkFragment = false;

    //


    TextView tvText;
    StudentViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);




        tvText = findViewById(R.id.tvText);
        viewModel = new ViewModelProvider(this).get(StudentViewModel.class);
        viewModel.getSelectedItem().observe(this, item -> {
            tvText.setText(item);
            Toast.makeText(this, "item", Toast.LENGTH_SHORT).show();
        });

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView((binding.getRoot()));


        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            switch(item.getItemId()) {
                case R.id.home:
                    focusedFragment = R.id.home;
                    replaceFragment(new Home());
                    this.setTitle("PMA-Home");
                    break;
                case R.id.addStudent:
                    focusedFragment = R.id.addStudent;
                    replaceFragment(new AddStudent());
                    this.setTitle("PMA-Add Student");
                    break;
                case R.id.studentSubject:
                    focusedFragment = R.id.studentSubject;
                    replaceFragment(new StudentSubject());
                    this.setTitle("PMA-Student Subject");
                    break;
            }
            return true;
        });
        funkcija();
    }

    private void renderFragment() {
        switch(focusedFragment) {
            case R.id.home:
                replaceFragment(new Home());
                this.setTitle("PMA-Home");
                break;
            case R.id.addStudent:
                replaceFragment(new AddStudent());
                this.setTitle("PMA-Add Student");
                break;
            case R.id.studentSubject:
                replaceFragment(new StudentSubject());
                this.setTitle("PMA-Student Subject");
                break;
        }
    }

    private void funkcija() {
        customSpinner = findViewById(R.id.languageSpinner);

        ArrayList<CustomSpinnerItem> customSpinnerItemsList = new ArrayList<>();
        customSpinnerItemsList.add(new CustomSpinnerItem("ENG", "en", R.drawable.great_britain));
        customSpinnerItemsList.add(new CustomSpinnerItem("HR", "hr", R.drawable.cro));
        customSpinnerItemsList.add(new CustomSpinnerItem("HU", "hu", R.drawable.hungary));

        CustomSpinnerAdapter customSpinnerAdapter = new CustomSpinnerAdapter(this, customSpinnerItemsList);

        if(customSpinner != null) {
            customSpinner.setAdapter((customSpinnerAdapter));
            customSpinner.setOnItemSelectedListener(this);
        }
    }

    private void replaceFragment (Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(checkFragment) {
            fragment = new StudentSubject();
        }
        fragmentTransaction.replace(R.id.mainFrameLayout, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        CustomSpinnerItem items = (CustomSpinnerItem) adapterView.getSelectedItem();
        Toast.makeText(this, items.getSpinnerText(), Toast.LENGTH_SHORT).show();
        languageManager.updateResource(items.getLangCode());
        renderFragment();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}