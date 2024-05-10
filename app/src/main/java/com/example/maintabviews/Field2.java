package com.example.maintabviews;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class Field2 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_field2, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.field2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        SubjectManager subjectManager = SubjectManager.getInstance();
        List<Subject> subjectList = subjectManager.getField2();
        SubjectAdapter adapter = new SubjectAdapter(subjectList);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new SubjectAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.d("Field", "onItemClick: Clicked");
            }
        });


        return view;
    }
}
