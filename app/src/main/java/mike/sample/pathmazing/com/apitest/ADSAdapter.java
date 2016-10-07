package mike.sample.pathmazing.com.apitest;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by user on 9/27/16.
 */

public class ADSAdapter extends BaseAdapter {
    ArrayList<MainActivity.ADS> mArrayList;
    private Context mContext;
    Activity mActivity;
    LayoutInflater mLayoutInflater;


    public ADSAdapter(ArrayList<MainActivity.ADS>mArrayList, Context context){

        this.mArrayList=mArrayList;
        this.mContext=context;
        //mActivity=(Activity)context;

        //mLayoutInflater=mActivity.getLayoutInflater();
        mLayoutInflater=LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return mArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view= mLayoutInflater.inflate(R.layout.item_list, null);
        TextView name=(TextView)view.findViewById(R.id.txtName);
        TextView date=(TextView)view.findViewById(R.id.txtDate);
        ImageView imageView=(ImageView)view.findViewById(R.id.image);
        Glide
                .with(mContext)
                .load(mArrayList.get(position).getAds_url())
                .into(imageView);
        //imageView.setImageResource(R.mipmap.ic_launcher);
        name.setText(""+this.mArrayList.get(position).getAds_name());
        date.setText(""+this.mArrayList.get(position).getCreated_date());

        return view;
    }
}
