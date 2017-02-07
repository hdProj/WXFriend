package com.example.donghe.wxfriend.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.donghe.wxfriend.MultiImageView;
import com.example.donghe.wxfriend.R;
import com.example.donghe.wxfriend.activity.GalleryActivity;
import com.example.donghe.wxfriend.bean.ListItemModle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dong.he on 2017/2/7.
 */

public class MyRecycleViewAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<ListItemModle> list;
    private LayoutInflater mInflater;
    private ArrayList<String> images;

    public MyRecycleViewAdapter(Context mContext) {

        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    public void setList(List<ListItemModle> list) {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int pos) {

        final ViewHolder viewHolder = (ViewHolder) holder;
        final ListItemModle itemModle = list.get(pos);

        holder.itemView.setTag(pos);
        viewHolder.tvContent.setText(itemModle.getContent());
        Glide.with(mContext).load(itemModle.getHeadImg())
                .asBitmap()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(new BitmapImageViewTarget(viewHolder.ivHead) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable rounde = RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                        rounde.setCircular(true);
                        //要实现圆角，只需要加上这句
                        rounde.setCornerRadius(100L);
                        viewHolder.ivHead.setImageDrawable(rounde);
                    }
                });

        viewHolder.tvName.setText(itemModle.getName());
        if (itemModle != null && itemModle.getUrls().size() > 0) {
            viewHolder.gridView.setVisibility(View.VISIBLE);
            viewHolder.gridView.setList(itemModle.getUrls());

            viewHolder.gridView.setOnItemClickListener(new MultiImageView.OnItemClickListener() {
                @Override
                public void onItemClick(View view, String position) {
                    addPic(itemModle);
                    Intent intent = new Intent(mContext, GalleryActivity.class);
                    intent.putStringArrayListExtra("imagePath", images);
                    intent.putExtra("position", position);
                    mContext.startActivity(intent);
                }
            });
        } else {
            viewHolder.gridView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        ImageView ivHead;
        TextView tvContent;
        MultiImageView gridView;

        public ViewHolder(View itemView) {
            super(itemView);
            ivHead = (ImageView) itemView.findViewById(R.id.iv_head);
            tvName = (TextView) itemView.findViewById(R.id.tv_username);
            tvContent = (TextView) itemView.findViewById(R.id.tv_content);
            gridView = (MultiImageView) itemView.findViewById(R.id.gridview);
        }
    }

    private void addPic(final ListItemModle item) {
        if (images == null) {
            images = new ArrayList<>();
        } else {
            images.clear();
        }
        images.addAll(item.getUrls());
    }

}
