package com.example.arief.belajarandroidretrofit6twopanefragment.tabfragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.arief.belajarandroidretrofit6twopanefragment.R;
import com.example.arief.belajarandroidretrofit6twopanefragment.retrofitHandler.RetrofitClient;

/**
 * Created by Arief on 8/20/2017.
 */

public class TabOneFragment extends Fragment  {

    private EditText id,nama;
    private Button insert;

    private boolean b;

    public interface insertListener {
        void isInsert(boolean b, Context con);

    }
    private insertListener listener;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_one,container,false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        id= (EditText)getActivity().findViewById(R.id.editId);
        nama = (EditText)getActivity().findViewById(R.id.editNama);
        insert = (Button)getActivity().findViewById(R.id.kirim);


        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if(nama.getText().toString().trim().equals("")){
                        listener.isInsert(false,getActivity());
                    }else{
                        int xId = Integer.parseInt(String.valueOf(id.getText().toString()));
                        String xNama = nama.getText().toString().trim();
                        new RetrofitClient(getActivity()).insertData(xId,xNama);
                        listener.isInsert(true,getActivity());
                    }


            }
        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (insertListener)new TabTwoFragment();
        Log.e("OnAttach","Attached");
    }
}
