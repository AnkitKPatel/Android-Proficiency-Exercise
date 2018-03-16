package com.ankit.testappankit.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ankit.testappankit.GlideApp;
import com.ankit.testappankit.R;
import com.ankit.testappankit.adapters.model.Feed;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

/**
 * The type Feed adapter.
 */
public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /**
     * The Feed list.
     */
    public static ArrayList<Feed> feedList;
    /**
     * The constant mContext.
     */
    public static Context mContext;

    /**
     * Instantiates a new Feed adapter.
     *
     * @param feedList the feed list
     * @param mContext the m context
     */
    public FeedAdapter(ArrayList<Feed> feedList, Context mContext) {
        this.feedList = feedList;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_feed_recyclerview, parent, false);
        return new FeedAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder mainHolder, final int position) {
        final FeedAdapter.MyViewHolder holder = (FeedAdapter.MyViewHolder) mainHolder;
        final Feed feed = feedList.get(position);

        holder.tv_title.setText(feed.getTitle() == null ? "No title provided" : feed.getTitle());
        holder.tv_desc.setText(feed.getDescription() == null ? "No Description Provided" : feed.getDescription());
        String ConfigurableBG_URL = feed.getImageHref();
        if (ConfigurableBG_URL != null && ConfigurableBG_URL.length() > 0 && !ConfigurableBG_URL.equalsIgnoreCase("null")) {
            holder.tv_noImage.setVisibility(View.GONE);
            GlideApp.with(mContext)
                    .asBitmap().listener(new RequestListener<Bitmap>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                    holder.tv_noImage.setVisibility(View.VISIBLE);
                    String error = e.getMessage();
                    try {
                        holder.tv_noImage.setText("Failed to load image\n" + error.substring(error.indexOf("("), error.indexOf(")") + 1));
                    } catch (Exception E) {
                        holder.tv_noImage.setText("Failed to load image");
                    }
                    return false;
                }

                @Override
                public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                    return false;
                }
            })
                    .load(ConfigurableBG_URL)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .fitCenter()
                    .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .into(holder.iv_feed_image);
        } else {
            // make sure Glide doesn't load anything into this view until told otherwise
            GlideApp.with(mContext).clear(holder.iv_feed_image);
            // remove the placeholder (optional); read comments below
            holder.iv_feed_image.setImageDrawable(null);
            holder.tv_noImage.setVisibility(View.VISIBLE);
            holder.tv_noImage.setText("Image url not provided");
        }

        holder.itemView.setTag(feed);
    }

    @Override
    public int getItemCount() {
        return feedList.size();
    }

    /**
     * The type My view holder.
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        /**
         * The Tv title.
         */
        public TextView tv_title, /**
         * The Tv desc.
         */
        tv_desc, /**
         * The Tv no image.
         */
        tv_noImage;
        /**
         * The Iv feed image.
         */
        public ImageView iv_feed_image;

        /**
         * Instantiates a new My view holder.
         *
         * @param view the view
         */
        public MyViewHolder(View view) {
            super(view);
            tv_title = view.findViewById(R.id.tv_title);
            tv_desc = view.findViewById(R.id.tv_desc);
            tv_noImage = view.findViewById(R.id.tv_noImage);
            iv_feed_image = view.findViewById(R.id.iv_feed_image);
        }
    }
}
