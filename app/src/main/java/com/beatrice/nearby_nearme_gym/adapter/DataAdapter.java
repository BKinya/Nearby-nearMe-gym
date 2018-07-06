package com.beatrice.nearby_nearme_gym.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.beatrice.nearby_nearme_gym.R;
import com.beatrice.nearby_nearme_gym.model.Work_out_session_model;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder>{

    private ArrayList<Work_out_session_model> workout_sessions;

    public DataAdapter(ArrayList<Work_out_session_model> workout_sessions) {
        this.workout_sessions = workout_sessions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sessions_list_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.session_textview.setText(workout_sessions.get(position).getExercise_type_name());
    }

    @Override
    public int getItemCount() {
        return workout_sessions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView session_textview;
        public ViewHolder(View itemView) {
            super(itemView);

            session_textview = itemView.findViewById(R.id.list_item);
        }
    }
}
