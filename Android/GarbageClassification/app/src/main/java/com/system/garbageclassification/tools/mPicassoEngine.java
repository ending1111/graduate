package com.system.garbageclassification.tools;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.zhihu.matisse.engine.impl.PicassoEngine;

public class mPicassoEngine extends PicassoEngine {
//缩略图
    @Override
    public void loadThumbnail(Context context, int resize, Drawable placeholder, ImageView imageView, Uri uri) {
        Picasso.get().load(uri).placeholder(placeholder)
                .resize(resize, resize)
                .centerCrop()
                .into(imageView);
    }
//图片
    @Override
    public void loadImage(Context context, int resizeX, int resizeY, ImageView imageView, Uri uri) {
        Picasso.get().load(uri).resize(resizeX, resizeY).priority(Picasso.Priority.HIGH)
                .centerInside().into(imageView);
    }
}
