package com.example.imc__app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity  extends AppCompatActivity {
    private TextView IMCText;// variable lwahid bch naaktbou fih el message de réesulta

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //n'imlimentiw  l methode parent
        setContentView(R.layout.activity_second);//naaytou ll fichier xml mtaa lactivité hethi (layout)

  // resulta eli fi  textveiws jebneha ml fichier xml w hatineha fel variable imcText
        IMCText = findViewById(R.id.activity_second_imc_text);

//  nesta9blou l intent li baathneya fel activité loula w nchoufou  chfeha bel getIntent() w nhotoha fi object intent
        Intent intent = getIntent();
        //affichage mesage selon l résulta (4 condition)+verification valeur not null
        if (intent != null) {
            double imc = intent.getDoubleExtra("IMC", 0.0);

             if (imc < 18.5) {

                IMCText.setText("Votre IMC est " + imc + "Insuffisance pondérale ");
            } else if (18.5 < imc || imc  < 24.9) {
                IMCText.setText("Votre IMC est " + imc + "Poids normal ");
            } else if (imc>25 || imc < 30  ) {
                IMCText.setText("Votre IMC est " + imc + "Surpoids ");
            } else {
                IMCText.setText("Votre IMC est " + imc + "Obésité ");
            }
        }
    }
}

