package com.example.pma_1.Classes.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.pma_1.Fragments.Student.SubjectInfo;
import com.example.pma_1.Fragments.Student.StudentInfo;
import com.example.pma_1.Fragments.Student.StudentSummary;

public class NewRecordAdapter extends FragmentStateAdapter {


    public NewRecordAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position) {
            case 1: return new SubjectInfo();
            case 2: return new StudentSummary();
            default: return new StudentInfo();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
