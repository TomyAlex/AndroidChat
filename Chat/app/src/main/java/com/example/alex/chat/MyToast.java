package com.example.alex.chat;

import android.content.Context;
import android.widget.Toast;

public class MyToast extends Toast {
    private Context context;
    public MyToast(Context context) {
        super(context);
        this.context = context;
    }

    public void showMessageWithDuration(CharSequence message, int duration) {
        // Display for a short duration of time a message Toast
        Toast toast = Toast.makeText(this.context, message, duration);
        toast.show();
    }
}
