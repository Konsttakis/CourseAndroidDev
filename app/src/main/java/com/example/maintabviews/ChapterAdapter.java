package com.example.maintabviews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ViewHolder> {
    private List<Chapter> chapterList;
    private int selectedPosition = -1;

    public ChapterAdapter(List<Chapter> chapterList) {
        this.chapterList = chapterList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Chapter chapter = chapterList.get(position);
        holder.checkBox.setText(chapter.getName());
        holder.checkBox.setChecked(chapter.isCompleted());
        holder.checkBox.setOnClickListener(v -> {
            boolean isChecked = holder.checkBox.isChecked();
            chapter.updateCompleted(isChecked);
            notifyItemChanged(position);
//          Needs to change the isCompleted value of the chapter to what is actually selected
        });
    }

    @Override
    public int getItemCount() {
        return chapterList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        String chapterName;
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            chapterName = itemView.findViewById(R.id.checkbox.text);
            checkBox = itemView.findViewById(R.id.checkbox);
        }
    }
}
