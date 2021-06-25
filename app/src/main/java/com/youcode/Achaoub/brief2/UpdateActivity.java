package com.youcode.Achaoub.brief2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText nomMod_input, prenomMod_input, lieuMod_input;
    Button modification_boutton, suppression_boutton;

    String id, nom, prenom, lieu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        nomMod_input = findViewById(R.id.nomStaff_input2);
        prenomMod_input = findViewById(R.id.prenomStaff_input2);
        lieuMod_input = findViewById(R.id.lieuAffecStaff_input2);
        modification_boutton = findViewById(R.id.btnMdf);
        suppression_boutton = findViewById(R.id.btnSupp);

        //First we call this
        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(nom);
        }

        modification_boutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                nom = nomMod_input.getText().toString().trim();
                prenom = prenomMod_input.getText().toString().trim();
                lieu = lieuMod_input.getText().toString().trim();
                myDB.ModicationDonnees(id, nom, prenom,lieu);
            }
        });
        suppression_boutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("nom") &&
                getIntent().hasExtra("prenom") && getIntent().hasExtra("lieu")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            nom = getIntent().getStringExtra("nom");
            prenom= getIntent().getStringExtra("prenom");
            lieu = getIntent().getStringExtra("lieu");

            //Setting Intent Data
            nomMod_input.setText(nom);
            prenomMod_input.setText(prenom);
            lieuMod_input.setText(lieu);
            Log.d("Achaoub ", nom+" "+prenom+" "+lieu);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + nom + " ?");
        builder.setMessage("Vous êtes sûr de vouloir supprimer " + nom + " ?");
        builder.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.SupprimerEnregistrement(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
