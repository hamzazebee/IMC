package com.example.imc__app;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    // bch ndeclarie les var lihachti bihom
    private EditText PoidsInput;// win bch tektb lpoid
    private EditText TailleInput;// win bch tekteb el taille
    private Button CalculerButton;//button famech 3lech
    private TextView ResultText; // lmessage li bch yjik baad l calcule
    double T;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // atheya li bch norbtou beha bin lactivité wel layout (xml/UI)
        setContentView(R.layout.activity_main);

        // intialitation lkol wahda bel id mtaaha li bch torbt bih mbaad
        PoidsInput = findViewById(R.id.activity_main_poids_input);
        TailleInput = findViewById(R.id.activity_main_taille_input);
        CalculerButton = findViewById(R.id.activity_main_calculer_button);
        ResultText = findViewById(R.id.activity_main_result_text);
        //atheya bch ki yabda mazel ma that hata donnee l button ma yt3malch:
        CalculerButton.setEnabled(false);

        /*atheya method ecouteur 3al champs li bch nektb fih.
         parametre mtaaha interface najm nhot fi westha des ecouteur okhrin 3ala changement
         9bal w baad w fi west lektiba lezmni nimplimentihom lkol lama7ala bch ma taamlch error*/
        PoidsInput.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                enableCalculerButton();
            }
        });

        //nafs lahkeya mais 3al champs lakher mtaa taille
        TailleInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                enableCalculerButton();
            }

        });


        // ecouteur 3al boutton bch ki taaml click taayt ll methode calculateIMC()
        CalculerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateIMC(); // Lance le calcul
            }
        });
    }
    private void enableCalculerButton() {
        //atheya t5ali lboutton activé el ma yt3amrou les champs lkol option verification
        //w 3ayatnelha fel  afterTextChanged
        boolean isNotEmpty = !PoidsInput.getText().toString().isEmpty() &&
                !TailleInput.getText().toString().isEmpty() ;

        CalculerButton.setEnabled(isNotEmpty);
    }

    // Efectue le calcul
    private void calculateIMC() {
        try {
            //  convertit les valeurs des champs
            double poids = Double.parseDouble(PoidsInput.getText().toString());
            double taille = Double.parseDouble(TailleInput.getText().toString());


            // valeur negatif wala 0 ma tt3afech condition optionelle
            if (poids <= 0 || taille <= 0 ) {
                ResultText.setText("Veuillez entrer des valeurs positives."); // Message d'erreur
                return;
            }

            // formule l'IMC
            T = taille * taille; // Taille au carré
            double imc = poids / T; // Formule de l'IMC


            // lena 3malna intent jdida bch nhezou donnee mtaa lactivité hethi ll activité el thénya bel putExtra
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("IMC", imc); // hatina imc  fel putExtra

            // Lanci activité
            startActivity(intent);
        } catch (NumberFormatException e) {
            //valeur ghalet ykharjlk message d'erreur
            ResultText.setText("Veuillez entrer des valeurs valides.");
        }
    }
}
