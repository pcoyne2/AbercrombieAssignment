package com.pcoyne.abercrombie.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.pcoyne.abercrombie.ExampleModel;
import com.pcoyne.abercrombie.R;
import com.pcoyne.abercrombie.WebViewActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.pcoyne.abercrombie.WebViewActivity.URL_EXTRA;

/**
 * Created by Patrick Coyne on 11/19/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    List<ExampleModel> items;
    Context context;

    public MyAdapter(Context context, List<ExampleModel> items) {
        this.items = items;
        this.context = context;
    }

    public void updateList(List<ExampleModel> newItems){
        items = newItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.card_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ExampleModel model = items.get(position);
        if(model.getBackgroundImage() != null){
            holder.backgroundImage.setVisibility(View.VISIBLE);
            Picasso.with(context).load(model.getBackgroundImage())
                    .into(holder.backgroundImage);
        }else{
            holder.backgroundImage.setVisibility(View.GONE);
        }
        holder.title.setText(model.getTitle());
        if(model.getTopDescription() != null){
            holder.topDescription.setVisibility(View.VISIBLE);
            holder.topDescription.setText(model.getTopDescription());
        }else{
            holder.topDescription.setVisibility(View.GONE);
        }
        if(model.getBottomDescription() != null){
            holder.bottomDescription.setVisibility(View.VISIBLE);
            holder.bottomDescription.setText(Html.fromHtml(model.getBottomDescription()));
            holder.bottomDescription.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String description = items.get(position).getBottomDescription();
                    if(description.contains("a href")){
                        Intent i = new Intent(context, WebViewActivity.class);
                        int startIndex = description.indexOf("http");
                        int endIndex = description.indexOf(".html")+5;
                        String url = description.substring(startIndex, endIndex);
//                        Intent i = new Intent(Intent.ACTION_VIEW, uri);
                        i.putExtra(URL_EXTRA, url);
                        context.getApplicationContext().startActivity(i);
                    }
                }
            });
        }else{
            holder.bottomDescription.setVisibility(View.GONE);
        }
        if(model.getPromoMessage() != null){
            holder.promoMessage.setVisibility(View.VISIBLE);
            holder.promoMessage.setText(model.getPromoMessage());
        }else{
            holder.promoMessage.setVisibility(View.GONE);
        }
        if(model.getContent() != null) {
            holder.content_title.setVisibility(View.VISIBLE);
            holder.content_title.setText(model.getContent().get(0).getTitle());
            holder.content_title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("TAG", items.get(position).getContent().get(0).getTarget());
                    Intent i = new Intent(context, WebViewActivity.class);
                    i.putExtra(URL_EXTRA, items.get(position).getContent().get(0).getTarget());
                    context.getApplicationContext().startActivity(i);
                }
            });
            if (model.getContent().size() >= 2) {
                holder.second_content_title.setVisibility(View.VISIBLE);
                holder.second_content_title.setText(model.getContent().get(1).getTitle());
                holder.second_content_title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("TAG", items.get(position).getContent().get(1).getTarget());
                        Intent i = new Intent(context, WebViewActivity.class);
                        i.putExtra(URL_EXTRA, items.get(position).getContent().get(1).getTarget());
                        context.getApplicationContext().startActivity(i);
                    }
                });

            } else {
                holder.second_content_title.setVisibility(View.GONE);
            }
        }else{
            holder.content_title.setVisibility(View.GONE);
            holder.second_content_title.setVisibility(View.GONE);
        }

    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // each data item is just a string in this case
        public View view;
        public ImageView backgroundImage;
        public TextView topDescription;
        public TextView title;
        public TextView promoMessage;
        public TextView bottomDescription;
        public Button content_title;
        public Button second_content_title;
        public ViewHolder(View v) {
            super(v);
            view = v;
            view.setOnClickListener(this);
            backgroundImage = (ImageView)view.findViewById(R.id.background_image);
            topDescription = (TextView)view.findViewById(R.id.topDescription);
            title = (TextView)view.findViewById(R.id.title);
            promoMessage = (TextView)view.findViewById(R.id.promoMessage);
            bottomDescription = (TextView)view.findViewById(R.id.bottomDescription);
            content_title = (Button)view.findViewById(R.id.first_content);
            second_content_title = (Button)view.findViewById(R.id.second_content);
        }

        @Override
        public void onClick(View view) {

        }
    }


}
