package com.example.maintabviews;

import static com.example.maintabviews.DatabaseHandler.COLUMN_CHAPTER_NAME;
import static com.example.maintabviews.DatabaseHandler.COLUMN_IS_COMPLETED;
import static com.example.maintabviews.DatabaseHandler.COLUMN_SUBJECT_NAME;
import static com.example.maintabviews.DatabaseHandler.TABLE_COMPLETED_CHAPTERS;
import static com.example.maintabviews.MainActivity.getDbHelper;

import android.content.ClipData;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ViewHolder> {

    private List<Chapter> chapterList;
    private int selectedPosition = -1;
    private final Context context;

    public ChapterAdapter(Context context, List<Chapter> chapterList) {
        this.context = context;
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
        holder.checkbox.setOnCheckedChangeListener(null); // Remove listener before updating state
        holder.checkbox.setText(chapter.getName());
        holder.checkbox.setChecked(chapter.isCompleted());
        holder.checkbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            int pos = holder.getAdapterPosition();
            chapter.setCompleted(isChecked);
            chapterList.get(pos).setCompleted(isChecked); // Update the data model
            updateCompleted(chapter, isChecked, position);

        });

    }

    @Override
    public int getItemCount() {
        return chapterList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkbox;

        public ViewHolder(View itemView) {
            super(itemView);
            checkbox = itemView.findViewById(R.id.checkbox);
        }
    }

    private void updateCompleted(Chapter chapter, boolean isChecked, int position) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Example database operation
                    SQLiteDatabase db = new DatabaseHandler(context).getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put(COLUMN_IS_COMPLETED, isChecked ? 1 : 0);
                    int rowsAffected = db.update(TABLE_COMPLETED_CHAPTERS, values, COLUMN_SUBJECT_NAME + " = ? AND " + COLUMN_CHAPTER_NAME + " = ?", new String[]{chapter.getCourseName(), chapter.getName()});
                    Log.d("DatabaseUpdate", "Rows affected: " + rowsAffected);

                    // Post UI update to the main thread
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            notifyItemChanged(position);
                        }
                    });
                } catch (Exception e) {
                    Log.e("DatabaseError", "Error updating database", e);
                }
            }
        }).start();
    }
}


