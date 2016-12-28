package com.anubis.phlix.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.anubis.phlix.R;
import com.anubis.phlix.models.Photo;
import com.anubis.phlix.util.Util;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by sabine on 9/26/16.
 */

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.ViewHolder> {


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
        TextView tags;
        ImageView imageView;
        CardView cardView;

        public ViewHolder(final View itemView, final OnItemClickListener listener) {
            super(itemView);


            imageView = (ImageView) itemView.findViewById(R.id.ivPhoto);

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(itemView, position);
                    }
                }
            });
            tags = (TextView)itemView.findViewById(R.id.checkboxtags);
            cardView = (CardView)itemView.findViewById(R.id.cardView);
        }
    }

    private List<Photo> mPhotos;
    private Context mContext;
    private boolean mStaggered;

    public FriendsAdapter(Context context, List<Photo> photos, boolean staggered) {
        mStaggered = staggered;
        mPhotos = photos;
        mContext = context;

    }

    private Context getContext() {
        return mContext;
    }

    @Override
    public FriendsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View photosView = inflater.inflate(R.layout.photo_item_friends, parent, false);

        ViewHolder viewHolder = new ViewHolder(photosView, getListener());
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FriendsAdapter.ViewHolder viewHolder, int position) {
        Photo photo = mPhotos.get(position);

        CardView cv = viewHolder.cardView;
        cv.setUseCompatPadding(true);
        cv.setCardElevation(4.0f);

        GridLayoutManager.LayoutParams fp = (GridLayoutManager.LayoutParams) viewHolder.cardView.getLayoutParams();
        ImageView imageView = viewHolder.imageView;
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) viewHolder.imageView
                .getLayoutParams();

        TextView tags = viewHolder.tags;
        String username = Util.getCurrentUser();
        //
        boolean isMe = photo.getOwnername().equals(username);
        tags.setText((isMe ? mContext.getString(R.string.Me): photo.getOwnername()));


        if (mStaggered) {
            int aspectRatio = (null != photo.getWidth() && null != photo.getHeight()) ? Integer.parseInt(photo.getHeight()) / Integer.parseInt(photo.getWidth()) : 1;

            //Random rand = new Random();
            //int n = rand.nextInt(200) + 200;
            lp.height = 350; // photo.getPhotoHeight() * 2;
            //n = rand.nextInt(200) + 100;
            lp.width = aspectRatio > 0 ? 350 / aspectRatio : 350; // photo.getPhotoList//set the title, name, comments
            imageView.setLayoutParams(lp);
            fp.width = lp.width;
            fp.height = lp.height;
            cv.setLayoutParams(fp);

        } else {
            lp.height = Integer.parseInt(photo.getHeight());
            lp.width = Integer.parseInt(photo.getWidth());
        }
        Picasso.with(this.getContext()).load(photo.getUrl()).fit().centerCrop()
                .placeholder(android.R.drawable.btn_star)
                .error(android.R.drawable.btn_star)
                .into(imageView);

    }

    @Override
    public int getItemCount() {
        return mPhotos.size();
    }



}
