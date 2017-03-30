package com.taeksukim.android.dayback;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.taeksukim.android.daybacklogin.R;

import java.util.ArrayList;

/**
 * Created by TaeksuKim on 2017. 3. 30..
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CustomViewHolder>{

    ArrayList<User> datas;
    Context context;

    public CardAdapter(ArrayList<User> datas, Context context){
        this.datas = datas;
        this.context = context;
    }
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_main_item,parent,false);
        CustomViewHolder cvh = new CustomViewHolder(view);
        return cvh;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        final User user = datas.get(position);

        holder.textDate.setText(user.date + "");
        holder.textTitle.setText(user.title + "");
        holder.textContent.setText(user.name + "");
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView textDate, textTitle, textContent;
        CardView cardView;
        public CustomViewHolder(View itemView) {
            super(itemView);

            textDate = (TextView) itemView.findViewById(R.id.textDate);
            textTitle = (TextView) itemView.findViewById(R.id.textTitle);
            textContent = (TextView) itemView.findViewById(R.id.textContent);

            cardView = (CardView) itemView.findViewById(R.id.cardView);

        }
    }
}
