package com.ntec.pokelearn;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;
import com.wajahatkarim3.easyflipview.EasyFlipView;
import java.util.ArrayList;

public class RecyclerViewCardAdapter extends RecyclerView.Adapter<RecyclerViewCardAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<String> mNum;
    private int cardFlipCount = 0;
    private boolean cardBothFlipped = false;
    private Intent intent;

    //Get context and card number
    public RecyclerViewCardAdapter(Context mContext, ArrayList<String> mNum) {
        this.mContext = mContext;
        this.mNum = mNum;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;

        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.card_layout,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        intent = new Intent("flippedValue");

        //If card is click
        View.OnClickListener mClickListener = new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final String cardNum = holder.mCard_num.getText().toString();

                //check whether the both card is flipped
                //NO
                if(cardFlipCount <= 2 && !cardBothFlipped){
                    //check whether the card clicked is facing front
                    if(holder.easyFlipView.isFrontSide()){
                        //allow player to flip the card and record the number of card flipped
                        holder.easyFlipView.flipTheView();
                        cardFlipCount++;
                        intent.putExtra("cardNum", cardNum);
                        //if this is the second card flipped up, set to true
                        if(cardFlipCount == 2){
                            cardBothFlipped = true;
                        }
                        //update card number to activity
                        intent.putExtra("cardFold", false);
                        LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
                    }
                    //Yes
                } else if(cardFlipCount <= 2 && cardBothFlipped){
                    //check whether the card clicked is facing back
                    if(holder.easyFlipView.isBackSide()){
                        //allow player to flip the card and record reduce the count of card flip
                        holder.easyFlipView.flipTheView();
                        cardFlipCount--;
                        //if this is there is no card facing back, set to false
                        if(cardFlipCount == 0){
                            cardBothFlipped = false;
                            intent.putExtra("cardFold", true);
                        }
                        //update to activity
                        LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
                    }
                }
                //prevent spamming of flipping the card
                view.setClickable(false);

                view.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        view.setClickable(true);
                    }
                }, 500);
            }
        };
        //flip card properties
        holder.easyFlipView.setAutoFlipBackTime(100);
        holder.easyFlipView.setOnClickListener(mClickListener);
        //set card num
        holder.mCard_num.setText(mNum.get(position));

    }

    @Override
    public int getItemCount() { return mNum.size(); }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mCard_num;
        EasyFlipView easyFlipView;
        public MyViewHolder(View itemView){
            super(itemView);

            mCard_num = itemView.findViewById(R.id.card_layout_back);
            easyFlipView = itemView.findViewById(R.id.card_layout_easyflipview);
        }
    }
}
