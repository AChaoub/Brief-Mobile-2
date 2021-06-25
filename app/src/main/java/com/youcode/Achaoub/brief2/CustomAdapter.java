package com.youcode.Achaoub.brief2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList staff_id, staff_nom, staff_prenom, staff_lieu;

    CustomAdapter(Activity activity, Context context, ArrayList staff_id, ArrayList staff_nom, ArrayList staff_prenom,
                  ArrayList  staff_lieu){
        this.activity = activity;
        this.context = context;
        this.staff_id = staff_id;
        this.staff_nom = staff_nom;
        this.staff_prenom = staff_prenom;
        this.staff_lieu = staff_lieu;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.staff_id_txt.setText(String.valueOf(staff_id.get(position)));
        holder.staff_nom_txt.setText(String.valueOf(staff_nom.get(position)));
        holder.staff_prenom_txt.setText(String.valueOf(staff_prenom.get(position)));
        holder.staff_lieu_txt.setText(String.valueOf(staff_lieu.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(staff_id.get(position)));
                intent.putExtra("nom", String.valueOf(staff_nom.get(position)));
                intent.putExtra("prenom", String.valueOf(staff_prenom.get(position)));
                intent.putExtra("lieu", String.valueOf(staff_lieu.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return staff_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView staff_id_txt, staff_nom_txt, staff_prenom_txt, staff_lieu_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            staff_id_txt = itemView.findViewById(R.id.staff_id_txt);
            staff_nom_txt = itemView.findViewById(R.id.staff_nom_txt);
            staff_prenom_txt = itemView.findViewById(R.id.staff_prenom_txt);
            staff_lieu_txt = itemView.findViewById(R.id.staff_lieuAffect_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
