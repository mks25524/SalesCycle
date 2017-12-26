package com.uuproject2.mks.salescycle.Comp.customer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uuproject2.mks.salescycle.Comp.model.NewSalesModel;
import com.uuproject2.mks.salescycle.R;

import java.util.List;

/**
 * Created by mks on 12/24/2017.
 */

public class PurchaseHistoryAdapterCustomer extends RecyclerView.Adapter<PurchaseHistoryAdapterCustomer.ViewHolder> {


    private Context context;
    private List<CustomerPurchaseModel> uploads;

    public PurchaseHistoryAdapterCustomer(Context context, List<CustomerPurchaseModel> uploads) {
        this.context=context;
        this.uploads=uploads;
    }


    @Override
    public PurchaseHistoryAdapterCustomer.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_sales_history, parent, false);
        PurchaseHistoryAdapterCustomer.ViewHolder viewHolder = new PurchaseHistoryAdapterCustomer.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PurchaseHistoryAdapterCustomer.ViewHolder holder, int position) {
        CustomerPurchaseModel upload = uploads.get(position);

        holder.textViewName.setText(upload.getDate());


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