package com.example.bootcamppractice.historyViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bootcamppractice.R;

public class HistoryViewHolder extends RecyclerView.ViewHolder {
    public TextView tvHistory, tvResult;
    public HistoryViewHolder(@NonNull View itemView) {
        super(itemView);
        tvHistory = itemView.findViewById(R.id.tvHistory);
        tvResult = itemView.findViewById(R.id.tvResult);
    }
}
