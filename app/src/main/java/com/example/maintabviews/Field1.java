package com.example.maintabviews;

import android.os.Bundle;
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

public class Field1 extends Fragment {

    private RecyclerView recyclerView;
    private List<Subject> subjectList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_field1, container, false);

        recyclerView = view.findViewById(R.id.field1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        SubjectAdapter adapter = new SubjectAdapter(subjectList);
        recyclerView.setAdapter(adapter);

        // Populate itemList with data

        // The icons need to change to the actual icons we want to use
        subjectList.add(new Subject(R.drawable.ic_launcher_foreground, "Item 1", "Description 1"));
        subjectList.add(new Subject(R.drawable.ic_launcher_foreground, "Item 2", "Description 2"));
        subjectList.add(new Subject(R.drawable.ic_launcher_foreground, "Item 3", "Description 3"));
        // Add all the courses in each field

        return view;
    }
}
