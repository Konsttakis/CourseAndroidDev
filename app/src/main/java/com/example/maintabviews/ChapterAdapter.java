package com.example.maintabviews;

import static com.example.maintabviews.DatabaseHandler.COLUMN_CHAPTER_NAME;
import static com.example.maintabviews.DatabaseHandler.TABLE_COMPLETED_CHAPTERS;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ChapterViewHolder> {

    private List<Chapter> chapterList;
    private final Context context;

    public ChapterAdapter(Context context, List<Chapter> chapterList) {
        this.context = context;
        this.chapterList = chapterList;
    }

    @NonNull
    @Override
    public ChapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chapter_parent, parent, false);
        return new ChapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterViewHolder holder, int position) {
        Chapter chapter = chapterList.get(position);
        holder.chapterTitle.setText(chapter.getTitle());

        boolean isExpanded = chapter.isExpanded();
        holder.content.setVisibility(isExpanded ? View.VISIBLE : View.GONE);

        // Ensure each chapter has its own adapter
        if (isExpanded) {
            SubChapterAdapter subChapterAdapter = new SubChapterAdapter(context, chapter.getSubChapters());
            holder.subChapterRecyclerView.setAdapter(subChapterAdapter);
        }

        holder.itemView.setOnClickListener(v -> {
            chapter.setExpanded(!chapter.isExpanded());
            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return chapterList.size();
    }


    public static class ChapterViewHolder extends RecyclerView.ViewHolder {
        TextView chapterTitle;
        LinearLayout content;
        RecyclerView subChapterRecyclerView;

        public ChapterViewHolder(@NonNull View itemView) {
            super(itemView);
            chapterTitle = itemView.findViewById(R.id.header);
            content = itemView.findViewById(R.id.content);
            subChapterRecyclerView = itemView.findViewById(R.id.recyclerViewChild);
            subChapterRecyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
//            RecyclerView recyclerView = itemView.findViewById(R.id.recyclerViewChild);
//            recyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
//            SubChapterAdapter adapter = new SubChapterAdapter(this, chapterList);
//            recyclerView.setAdapter(adapter);
        }
    }
}
