package com.example.maintabviews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
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
        holder.chapterName.setText(chapter.getName());
        holder.radioButton.setChecked(position == selectedPosition);
        holder.radioButton.setOnClickListener(v -> {
            selectedPosition = holder.getAdapterPosition();
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return chapterList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView chapterName;
        RadioButton radioButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            chapterName = itemView.findViewById(R.id.chapterName);
            radioButton = itemView.findViewById(R.id.radioButton);
        }
    }
}
