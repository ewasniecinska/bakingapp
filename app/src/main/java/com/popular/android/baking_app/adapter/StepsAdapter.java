package com.popular.android.baking_app.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.popular.android.baking_app.R;
import com.popular.android.baking_app.StepDetailActivity;
import com.popular.android.baking_app.models.Step;

import java.util.List;

    /**
     * Created by ewasniecinska on 30.06.2018.
     */

    public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.ViewHolder>{

        private List<Step> list;


        public StepsAdapter(List<Step> list){
            this.list = list;

        }



        public static class ViewHolder extends RecyclerView.ViewHolder {
            TextView textView;
            CardView cardView;

            public ViewHolder(View v) {
                super(v);
                textView = v.findViewById(R.id.textField);
                cardView = v.findViewById(R.id.cardView);

            }
        }

        @Override
        public StepsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_steps_item, parent, false);
            return new StepsAdapter.ViewHolder(view);

        }

        @Override
        public void onBindViewHolder(StepsAdapter.ViewHolder holder, final int position) {
            String textField = list.get(position).getShortDescription();
            final StepsAdapter.ViewHolder viewHolder = holder;
            viewHolder.textView.setText(textField);

            viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Context context = viewHolder.textView.getContext();
                    Intent intent = new Intent(context, StepDetailActivity.class);
                    intent.putExtra("STEP", list.get(position));

                    context.startActivity(intent);


                }

            });
        }


        @Override
        public int getItemCount() {
            return list.size();
        }


    }

