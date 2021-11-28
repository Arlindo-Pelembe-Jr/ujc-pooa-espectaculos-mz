package com.example.espetaculos_mz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomBaseAdapter extends BaseAdapter {
        Context context;
        String espe[];
        int [] imgs;
        LayoutInflater layoutInflater;
        ArrayList<String> espects;
        int img;
        ArrayList<Espectaculos_Model> espectaculos_models;
    public CustomBaseAdapter(Context context, String[] espe, int[] imgs) {
        this.context = context;
        this.espe = espe;
        this.imgs = imgs;
        this.layoutInflater = LayoutInflater.from(context);
    }
//    public CustomBaseAdapter (Context context,ArrayList<String> espes,int img){
//        this.context=context;
//        this.espects=espes;
//        this.img=img;
//        this.layoutInflater = LayoutInflater.from(context);
//
//    }
    public CustomBaseAdapter (Context context,ArrayList<Espectaculos_Model> espes,int img){
        this.context=context;
        this.espectaculos_models=espes;
        this.img=img;
        this.layoutInflater = LayoutInflater.from(context);

    }
    @Override
    public int getCount() {
        return espectaculos_models.size();
    }

    @Override
    public Object getItem(int position) {
        return this.espectaculos_models.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            convertView = layoutInflater.inflate(R.layout.activity_custom__list__view,null);
        TextView textView = (TextView) convertView.findViewById(R.id.textList);


        ImageView imgL=(ImageView)convertView.findViewById(R.id.imageIcon);
//        textView.setText(espe[position]);
        textView.setText("Nome:"+espectaculos_models.get(position).getNome() + "\n    Promotor:"+espectaculos_models.get(position).getPromotor()+"\nLocal:"+espectaculos_models.get(position).getLocal()+"\n  Preco:"+espectaculos_models.get(position).getPreco());



        imgL.setImageResource(img);
         return convertView;
    }
}
