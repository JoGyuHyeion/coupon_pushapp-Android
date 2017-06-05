package com.example.jo.pushapp.ListView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.jo.pushapp.DbConnection.JsonData;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by jo on 2017-04-29.
 */

public class CouponAdapter2 extends BaseAdapter {

    private Context mContext;
    private String photoUrl;
    private ArrayList<Coupon_item> items = new ArrayList<Coupon_item>();

    public CouponAdapter2(Context context) {
        mContext = context;
    }

    public CouponAdapter2(Context context, String photoUrl) {
        this.mContext = context;
        this.photoUrl = photoUrl;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    public void buildArrayList(String result) throws InterruptedException, ExecutionException, JSONException {
        JsonData jsonGpsData = new JsonData();
        this.items = jsonGpsData.getcouponArrayList(result);
    }

    public String addItem(Coupon_item item) {
        String add_mesg;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getNum() == (item.getNum())) {
                return add_mesg = "duplicate_items";
            }
        }
        items.add(item);
        return add_mesg = "sucess";
    }

    public void remove(int position) {
        items.remove(position);
    }

    public void remove(String num) {
        int index = 0;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getNum() == (num)) {
                index = i;
            }
        }
        items.remove(index);
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        CouponItemView2 view2 = new CouponItemView2(mContext);

        Coupon_item item = items.get(position);

        view2.setPhotoUrl(item.getPhotoUrl());
        view2.setplace(item.getPlace());
        view2.setcoupon(item.getContents());
        view2.setExpireation(item.getExpireation());

        return view2;
    }
}