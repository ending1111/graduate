package com.system.garbageclassification.tools;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.youth.banner.loader.ImageLoader;
//图片加载器
public class mLocalGlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {

        int sourceId = Integer.parseInt(String.valueOf(path));
        Picasso.get().load(sourceId).into(imageView);

    }
}
