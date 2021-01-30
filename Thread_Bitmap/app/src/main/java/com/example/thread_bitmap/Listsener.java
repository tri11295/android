package com.example.thread_bitmap;

import android.graphics.Bitmap;

public interface Listsener {
    void onImageDownLoad(Bitmap bitmap);
    void onError();
}
