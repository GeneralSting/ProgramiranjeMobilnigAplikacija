package com.example.pma_1.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.pma_1.Classes.recyclerViewAdapter;
import com.example.pma_1.HomeActivity;
import com.example.pma_1.PersonalInfoActivity;
import com.example.pma_1.R;


public class Home extends Fragment {

    Button btnNext;


    RecyclerView recyclerView;
    String s1[], s2[];
    int images[] = {R.drawable.profile_image, R.drawable.profile_image, R.drawable.prof_profile_image, R.drawable.profile_image,
                    R.drawable.profile_image, R.drawable.profile_image, R.drawable.prof_profile_image, R.drawable.profile_image};
    View layoutView;
    private HomeActivity mainActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layoutView = inflater.inflate(R.layout.fragment_home, container, false);
        return layoutView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnNext = layoutView.findViewById(R.id.btnNext);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
                Intent intent = new Intent(getActivity(), PersonalInfoActivity.class);
                startActivity(intent);

            }
        });

        recyclerView = view.findViewById(R.id.studentsRecyclerView);

        mainActivity = (HomeActivity) getActivity();

        s1 = getResources().getStringArray(R.array.students_list);
        s2 = getResources().getStringArray(R.array.student_subject);



    }
}