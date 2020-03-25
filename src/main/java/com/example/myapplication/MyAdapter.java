package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {


    private LayoutInflater layoutInflater;
    private Context mContext;
    private List<Student> students;


    public MyAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
        mContext = context;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.row_layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (students != null) {
            Student student = students.get(position);
            String mark = Integer.toString((int) student.getMark());
            String data = student.getName() +" - " + mark;
            holder.setData(data, position);

        }
    }

    @Override
    public int getItemCount() {
        if (students != null) {
            return students.size();
        }
        return 0;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView userItemView;
        public Button remove;
        private int position;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userItemView = itemView.findViewById(R.id.user_view);
            remove= itemView.findViewById(R.id.button2);
        }

        public void setData(String userText, int position) {
            userItemView.setText(userText);
            this.position = position;
        }
    }
}

