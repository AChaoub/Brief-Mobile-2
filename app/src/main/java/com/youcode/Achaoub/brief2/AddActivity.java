package com.youcode.Achaoub.brief2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText nom_input, prenom_input, lieu_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        nom_input = findViewById(R.id.nomStaff);
        prenom_input = findViewById(R.id.prenomStaff);
        lieu_input = findViewById(R.id.lieuAffecStaff);
        add_button = findViewById(R.id.btnAjt);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addStaff(nom_input.getText().toString().trim(),
                        prenom_input.getText().toString().trim(),
                        lieu_input.getText().toString().trim());
            }
        });
    }
}
