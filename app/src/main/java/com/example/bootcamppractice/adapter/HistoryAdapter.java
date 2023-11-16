package com.example.bootcamppractice.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bootcamppractice.R;
import com.example.bootcamppractice.historyViewHolder.HistoryViewHolder;
import com.example.bootcamppractice.objects.History;

import java.util.ArrayList;
import java.util.Collections;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryViewHolder> {

    private Context context;
    ArrayList<History> histories;
    ArrayList<String> resultHistories;

    public HistoryAdapter(Context context, ArrayList<History> histories, ArrayList<String> resultHistories) {
        this.context = context;
        this.histories = histories;
        this.resultHistories = resultHistories;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.history_layout, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        holder.tvHistory.setText(histories.get(position).toString());
        holder.tvResult.setText(resultHistories.get(position));
    }

    @Override
    public int getItemCount() {
        if (histories == null) {
            return 0;
        }
        else {
            return histories.size();
        }
    }

    public void setData(ArrayList<History> updatedHistories, ArrayList<String> updatedResults) {
        Collections.reverse(updatedHistories);
        Collections.reverse(updatedResults);
        this.histories = updatedHistories;
        this.resultHistories = updatedResults;
        notifyDataSetChanged();
    }

}
