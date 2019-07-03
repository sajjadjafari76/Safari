package com.fanava.rally.adapter;

public class AdapterListeners {
    public interface CarListener {
        void onClick(int position);

        void onRemove(int position);

    }
}
