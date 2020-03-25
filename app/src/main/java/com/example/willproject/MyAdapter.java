package com.example.willproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
private  List<Listitem> listitems;
private Context context;
LayoutInflater layoutInflater;

    public MyAdapter(List<Listitem> listitems, Context context) {
        this.listitems = listitems;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        final ViewHolder viewHolder = new ViewHolder(v);
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DescActivity.class);

                i.putExtra("name",listitems.get(viewHolder.getAdapterPosition()).getHead() );
                i.putExtra("rating",listitems.get(viewHolder.getAdapterPosition()).getDes());
                i.putExtra("image",listitems.get(viewHolder.getAdapterPosition()).getImageUrl());
                context.startActivity(i);
            }
        });


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      Listitem  listitem = listitems.get(position);
      holder.textViewHead.setText(listitem.getHead());
        holder.textViewDes.setText(listitem.getDes());
       // holder.imageView.setImageDrawable(listitem.getImage());
        Picasso.with(context).load(listitem.getImageUrl()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return listitems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewHead;
        public TextView textViewDes;
        public ImageView imageView;
        LinearLayout view_container;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            view_container =itemView.findViewById(R.id.container);


            textViewHead = (TextView) itemView.findViewById(R.id.textviewHead);
            textViewDes = (TextView) itemView.findViewById(R.id.textViewDes);
            imageView = (ImageView) itemView.findViewById(R.id.list_img);

        }
    }
}
