package com.example.maintabviews;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Looper;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.ItemViewHolder> {

    private ArrayList<Subject> subjectList;
    private OnItemClickListener listener;
    private final Context context;

    public SubjectAdapter(Context context, ArrayList<Subject> subjectList) {

        this.subjectList = subjectList;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subject, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Subject subject = subjectList.get(holder.getAdapterPosition());
        holder.nameTextView.setText(subject.getName());

        // Running this code every second. Would be better to run it when we return to this
        // activity(maybe with onResume). In onResume method we can't pass the holder(as a param)
        final Handler handler = new Handler(Looper.getMainLooper());
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                double progress = findCompletedChapters(holder, subject);

                holder.progressBar.post(() -> holder.progressBar.setProgress((int) progress));


                handler.postDelayed(this, 1000);
            }
        };
        handler.post(runnable);


        switch (subject.getName()) {
            case "Αρχαία Ελληνικά":
                holder.iconImageView.setImageResource(R.drawable.ancient_greek);
                break;
            case "Ιστορία":
                holder.iconImageView.setImageResource(R.drawable.history);
                break;
            case "Λατινικά":
                holder.iconImageView.setImageResource(R.drawable.latin);
                break;
            case "Νεοελληνική Γλώσσα και Λογοτεχνία":
                holder.iconImageView.setImageResource(R.drawable.literature);
                break;
            case "Μαθηματικά":
                holder.iconImageView.setImageResource(R.drawable.math);
                break;
            case "Φυσική":
                holder.iconImageView.setImageResource(R.drawable.science);
                break;
            case "Βιολογία":
                holder.iconImageView.setImageResource(R.drawable.biology);
                break;
            case "Χημεία":
                holder.iconImageView.setImageResource(R.drawable.chemistry);
                break;
            case "ΑΟΘ":
                holder.iconImageView.setImageResource(R.drawable.aoth);
                break;
            case "ΑΕΠΠ":
                holder.iconImageView.setImageResource(R.drawable.aepp);
                break;
            case "Αγγλικά":
                holder.iconImageView.setImageResource(R.drawable.english);
                break;
            case "Γαλλικά":
                holder.iconImageView.setImageResource(R.drawable.french);
                break;
            case "Γερμανικά":
                holder.iconImageView.setImageResource(R.drawable.german);
                break;

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(holder.getAdapterPosition());
                }
            }
        });
    }

    private double findCompletedChapters(ItemViewHolder holder, Subject subject) {
        int completedChapters = 0;
        int totalChapters = 0;

        // Count the chapters that are completed from the database isCompleted field
        try {
            Log.d("Database", "Before opening");
            SQLiteDatabase db = new DatabaseHandler(context).getReadableDatabase();
            Log.d("Database", "After opening");
            String selectQuery = "SELECT " + DatabaseHandler.COLUMN_SUBCHAPTER_NAME + " , " + DatabaseHandler.COLUMN_IS_COMPLETED +
                    " FROM " + DatabaseHandler.TABLE_COMPLETED_CHAPTERS +
                    " WHERE " + DatabaseHandler.COLUMN_SUBJECT_NAME + " = ?";

            Log.d("Database", "Before cursor");
            Cursor cursor = db.rawQuery(selectQuery, new String[]{subject.getName()});
            if (cursor.moveToFirst()) {
                do {
                    if (cursor.getInt(1) == 1) {
                        Log.d("Database", "Chapter " + cursor.getString(0) + " is completed");
                        completedChapters++;
                    }
                    totalChapters++;
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("Database Error", e.getMessage());
        }

        Log.d("Database", "Completed Chapters: " + completedChapters);
        Log.d("Database", "Total Chapters: " + totalChapters);

        double progress = (completedChapters * 100.0) / totalChapters;
        // Set the progress bar for each subject based on the completed chapters of the subject
        holder.progressBar.post(() -> holder.progressBar.setProgress((int) progress));
        return progress;

    }

    @Override
    public int getItemCount() {
        return subjectList.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView iconImageView;
        TextView nameTextView;
        ProgressBar progressBar;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            iconImageView = itemView.findViewById(R.id.item_icon);
            nameTextView = itemView.findViewById(R.id.item_name);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


}