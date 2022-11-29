package com.example.pma_1.Classes.StudentsRV;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pma_1.R;

import java.util.List;

public class recyclerViewAdapter extends RecyclerView.Adapter<recyclerViewAdapter.myViewHolder> {

    Context ctx;
    private List<StudentRecyclerList> postsList;

    public recyclerViewAdapter(Context ctx, List<StudentRecyclerList> postsList) {
        this.ctx = ctx;
        this.postsList = postsList;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.students_recycler_row, parent, false);
        return new myViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.studentName.setText(postsList.get(position).getName() + " " + postsList.get(position).getSurname());
        holder.studentSubject.setText(postsList.get(position).getSubject());
        holder.studentImage.setImageResource(postsList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        TextView studentName, studentSubject;
        ImageView studentImage;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            studentName = itemView.findViewById(R.id.studentName);
            studentSubject = itemView.findViewById(R.id.studentSubject);
            studentImage = itemView.findViewById(R.id.studentImage);
        }
    }

}
