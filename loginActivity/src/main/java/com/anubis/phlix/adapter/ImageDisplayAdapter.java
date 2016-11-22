package com.anubis.phlix.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anubis.phlix.R;
import com.anubis.phlix.models.Comment;

import java.util.List;

/**
 * Created by sabine on 9/26/16.
 */

public class ImageDisplayAdapter extends RecyclerView.Adapter<ImageDisplayAdapter.ViewHolder> {


    private OnItemClickListener listener;


    public OnItemClickListener getListener(){
        return this.listener;

    }
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView content;
        TextView author;


        public ViewHolder(final View itemView, final OnItemClickListener listener) {
            super(itemView);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (listener != null && position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(itemView, position);
                        }
                    }
                }
            });
             author = (TextView)itemView.findViewById(R.id.author);
             content= (TextView) itemView.findViewById(R.id.content);

        }
    }

    private List<Comment> mComments;
    private Context mContext;
    private boolean mStaggered;

    public ImageDisplayAdapter(Context context, List<Comment> comments, boolean staggered) {
        mStaggered = staggered;
        mComments = comments;
        mContext = context;


    }

    private Context getContext() {
        return mContext;
    }

    @Override
    public ImageDisplayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View photosView = inflater.inflate(R.layout.comment_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(photosView, getListener());
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ImageDisplayAdapter.ViewHolder viewHolder, int position) {
        Comment comment = mComments.get(position);

        TextView author = viewHolder.author;
        TextView content = viewHolder.content;
        author.setText(comment.getAuthorname());
        content.setText(Html.fromHtml(comment.getContent()));
    }

    @Override
    public int getItemCount() {
        return mComments.size();
    }



}
