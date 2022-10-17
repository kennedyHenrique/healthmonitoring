package devtitans.healthmonitoringapp;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import android.os.RemoteException;

import devtitans.healthmonitoringmanager.HealthmonitoringManager;                          // Biblioteca do Manager

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "DevTITANS.SmartlampApp";

    private TextView textStatus, textResistance;
    private HealthmonitoringManager manager;                                        // Atributo para o Manager

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textStatus =     findViewById(R.id.textStatus);
        textResistance =     findViewById(R.id.textResistance);                      // Acessa os componentes da tela


        manager = HealthmonitoringManager.getInstance();

        updateAll(null);
    }

    public void updateAll(View view) {
        Log.d(TAG, "Atualizando dados do dispositivo ...");

        textStatus.setText("Atualizando ...");
        textStatus.setTextColor(Color.parseColor("#c47e00"));

        try {
            int resistance = manager.getResistance();                        // Executa o método getResistance via manager
            textResistance.setText(String.valueOf(resistance));

            int status = manager.connect();                                  // Executa o método connect via manager
            if (status == 0) {
                textStatus.setText("Desconectado");
                textStatus.setTextColor(Color.parseColor("#73312f"));
            }
            else if (status == 1) {
                textStatus.setText("Conectado");
                textStatus.setTextColor(Color.parseColor("#6d790c"));
            }
            else {
                textStatus.setText("Simulado");
                textStatus.setTextColor(Color.parseColor("#207fb5"));
            }

        } catch (android.os.RemoteException e) {
            Toast.makeText(this, "Erro ao acessar o Binder!", Toast.LENGTH_LONG).show();
            Log.e(TAG, "Erro atualizando dados:", e);
        }

    }
}