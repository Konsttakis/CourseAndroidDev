package com.example.maintabviews;

import static com.example.maintabviews.DatabaseHandler.COLUMN_CHAPTER_NAME;
import static com.example.maintabviews.DatabaseHandler.COLUMN_IS_COMPLETED;
import static com.example.maintabviews.DatabaseHandler.COLUMN_SUBCHAPTER_NAME;
import static com.example.maintabviews.DatabaseHandler.COLUMN_SUBJECT_NAME;
import static com.example.maintabviews.DatabaseHandler.TABLE_COMPLETED_CHAPTERS;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SubChapterAdapter extends RecyclerView.Adapter<SubChapterAdapter.ViewHolder> {

    private List<SubChapter> subChapterList;
    private final Context context;

    public SubChapterAdapter(Context context, List<SubChapter> subChapterList) {
        this.context = context;
        this.subChapterList = subChapterList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_chapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SubChapter subChapter = subChapterList.get(position);
        holder.checkbox.setOnCheckedChangeListener(null); // Remove listener before updating state
        holder.checkboxText.setText(subChapter.getName());
        holder.checkbox.setChecked(subChapter.isCompleted());

        holder.expandableContent.setVisibility(subChapter.isExpanded() ? View.VISIBLE : View.GONE);

        holder.checkboxContainer.setOnClickListener(v -> {
            if (!holder.checkbox.isPressed()) {
                subChapter.setExpanded(!subChapter.isExpanded());
                notifyItemChanged(position);
            }
        });
        holder.checkbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            int pos = holder.getAdapterPosition();
            subChapter.setCompleted(isChecked);
            subChapterList.get(pos).setCompleted(isChecked); // Update the data model
            updateCompleted(subChapter, isChecked, position);
        });
        holder.button.setOnClickListener(v -> {
            String text = holder.editText.getText().toString();
            // Handle save action
        });
    }

    @Override
    public int getItemCount() {
        return subChapterList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkbox;
        TextView checkboxText;
        LinearLayout checkboxContainer;
        LinearLayout expandableContent;
        EditText editText;
        Button button;
        public ViewHolder(View itemView) {
            super(itemView);
            checkbox = itemView.findViewById(R.id.checkbox);
            checkboxText = itemView.findViewById(R.id.textView);
            checkboxContainer = itemView.findViewById(R.id.checkboxContainer);
            expandableContent = itemView.findViewById(R.id.expandableContent);
            editText = itemView.findViewById(R.id.editTextText);
            button = itemView.findViewById(R.id.button);
        }
    }

    private void updateCompleted(SubChapter subChapter, boolean isChecked, int position) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SQLiteDatabase db = new DatabaseHandler(context).getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put(COLUMN_IS_COMPLETED, isChecked ? 1 : 0);
                    int rowsAffected = db.update(TABLE_COMPLETED_CHAPTERS, values,
                            COLUMN_SUBJECT_NAME + " = ? AND " + COLUMN_CHAPTER_NAME + " = ? AND " + COLUMN_SUBCHAPTER_NAME + " = ?",
                            new String[]{subChapter.getCourseName(), subChapter.getChapterName(), subChapter.getName()});
                    Log.d("DatabaseUpdate", "Rows affected: " + rowsAffected);
                    Log.d("DatabaseUpdate", "SubChapter: " + subChapter.getName() + ", Completed: " + subChapter.isCompleted());

                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            notifyItemChanged(position);
                        }
                    });
                } catch (Exception e) {
                    Log.e("DatabaseError", "Error updating database", e);
                }
                // Ensure the database connection is closed
            }

        }).start();
    }
}


