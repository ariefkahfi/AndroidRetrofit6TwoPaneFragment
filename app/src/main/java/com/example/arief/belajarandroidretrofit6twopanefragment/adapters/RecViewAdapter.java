package com.example.arief.belajarandroidretrofit6twopanefragment.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.arief.belajarandroidretrofit6twopanefragment.R;
import com.example.arief.belajarandroidretrofit6twopanefragment.model.Person;

import java.util.ArrayList;

/**
 * Created by Arief on 8/20/2017.
 */

public class RecViewAdapter  extends RecyclerView.Adapter<RecViewAdapter.RecViewHolder>{

    private ArrayList<Person> data;

    public RecViewAdapter(ArrayList<Person> data){
        this.data=data;
    }

    public ArrayList<Person> getData() {
        return data;
    }

    @Override
    public RecViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_ui,parent,false);

        return new RecViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecViewHolder holder, int position) {

        Person p = data.get(position);

        holder.tId.setText(String.valueOf(p.getId()));
        holder.tNama.setText(p.getNama());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class RecViewHolder extends RecyclerView.ViewHolder{

        private TextView tId,tNama;

        public RecViewHolder(View itemView) {
            super(itemView);
            tId = (TextView)itemView.findViewById(R.id.tampilId);
            tNama = (TextView)itemView.findViewById(R.id.tampilNama);
        }
    }
}
