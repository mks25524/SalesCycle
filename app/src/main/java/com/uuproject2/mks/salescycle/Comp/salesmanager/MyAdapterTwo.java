package com.uuproject2.mks.salescycle.Comp.salesmanager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.uuproject2.mks.salescycle.R;

import java.util.List;

/**
 * Created by mks on 10/26/2017.
 */

public class MyAdapterTwo extends RecyclerView.Adapter<MyAdapterTwo.ViewHolder> {


    private  Context context;
    private List<TransactionGetingDataModel> uploads;

    public MyAdapterTwo(Context context, List<TransactionGetingDataModel> uploads) {
       this.context=context;
        this.uploads=uploads;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_transaction, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TransactionGetingDataModel upload = uploads.get(position);

        holder.textViewName.setText(upload.getId());


    }

    @Override
    public int getItemCount() {
        return uploads.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewName;


        public ViewHolder(View itemView) {
            super(itemView);

            textViewName = (TextView) itemView.findViewById(R.id.textViewName);

          //  itemView.setTag(textViewName);

        }



    }
}