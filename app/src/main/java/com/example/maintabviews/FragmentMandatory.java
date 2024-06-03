package com.example.maintabviews;

import android.content.Intent;
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

public class FragmentMandatory extends Fragment {

    SubjectsSingleton subjectsSingleton = SubjectsSingleton.getInstance();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_field1, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.field1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        SubjectsSingleton subjectsSingleton = SubjectsSingleton.getInstance();
        ArrayList<Subject> subjectList = subjectsSingleton.getMandatorySubjects();
        SubjectAdapter adapter = new SubjectAdapter(getContext(), subjectList);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new SubjectAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                Subject clickedSubject = subjectList.get(position);

                Intent intent = new Intent(getActivity(), ChaptersActivity.class);
                intent.putExtra("subjectName", clickedSubject.getName());
                startActivity(intent);
            }
        });

        return view;
    }
}
