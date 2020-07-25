package com.ntec.pokelearn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MimeRecyclerViewAdapter extends RecyclerView.Adapter<MimeRecyclerViewAdapter.MyViewHolder>{

    private static final String TAG = "MimeRecyclerViewAdapter";
    private Context mContext;
    private ArrayList<String> mCard;

    public MimeRecyclerViewAdapter(Context mContext, ArrayList<String> mCard) {
        this.mContext = mContext;
        this.mCard = mCard;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.answer_slot,parent,false);

        return new MimeRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.ans.setText(mCard.get(position));

    }

    @Override
    public int getItemCount() {
        return mCard.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView ans;
        ImageView iv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ans = itemView.findViewById(R.id.ans);
            iv = itemView.findViewById(R.id.iv);
        }
    }
}
