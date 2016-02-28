package com.arigoit.arigoit.adapter;

/**
 * Created by dhiraj on 27-02-2016.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.arigoit.arigoit.R;
import com.arigoit.arigoit.Utils.Constants;
import com.arigoit.arigoit.model.ProductInfo;

import java.util.List;

public class ProductAdapter extends BaseAdapter {

    private LayoutInflater layoutinflater;
    private List<ProductInfo> listStorage;
    private Context context;

    public ProductAdapter(Context context, List<ProductInfo> customizedListView) {
        this.context = context;
        layoutinflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listStorage = customizedListView;
    }

    @Override
    public int getCount() {
        return listStorage.size();
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

        ViewHolder listViewHolder;
        if(convertView == null){
            listViewHolder = new ViewHolder();
            convertView = layoutinflater.inflate(R.layout.productinfo, parent, false);
            listViewHolder.productImage = (ImageView)convertView.findViewById(R.id.product_image);
            listViewHolder.productName = (TextView)convertView.findViewById(R.id.product_name);
            listViewHolder.productPrice = (TextView)convertView.findViewById(R.id.product_price);

            convertView.setTag(listViewHolder);
        }else{
            listViewHolder = (ViewHolder)convertView.getTag();
        }
        if (position % 3 == 0) {
            listViewHolder.productImage.setImageResource(Constants.productDrawables[0]);
        } else if (position % 3 == 1){
            listViewHolder.productImage.setImageResource(Constants.productDrawables[1]);
        } else {
            listViewHolder.productImage.setImageResource(Constants.productDrawables[2]);
        }

        listViewHolder.productName.setText(listStorage.get(position).getName());
        String price = String.format("%.2f", listStorage.get(position).getPrice());
        listViewHolder.productPrice.setText(context.getResources().getString(R.string.Rs)
                                                + " "
                                                + price);

        return convertView;
    }

    static class ViewHolder{
        ImageView productImage;
        TextView productName;
        TextView productPrice;
    }
}
