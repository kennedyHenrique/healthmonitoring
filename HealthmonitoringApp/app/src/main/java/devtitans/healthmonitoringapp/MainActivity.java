package devtitans.healthmonitoringapp;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import android.os.RemoteException;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Timer;
import java.util.TimerTask;

import devtitans.healthmonitoringmanager.HealthmonitoringManager;                          // Biblioteca do Manager

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "DevTITANS.HealthMonitoring";

    private TextView textStatus, textResistance;
    private HealthmonitoringManager manager;                                        // Atributo para o Manager
    private int lastX = 0;
    private LineGraphSeries<DataPoint> series;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //textStatus =     findViewById(R.id.textStatus);
        textResistance =     findViewById(R.id.textResistance);                      // Acessa os componentes da tela

        manager = HealthmonitoringManager.getInstance();

        GraphView graph = (GraphView) findViewById(R.id.graph);
        series = new LineGraphSeries<DataPoint>();
        graph.addSeries(series);
        Viewport viewport = graph.getViewport();
        viewport.setYAxisBoundsManual(true);
        viewport.setMinY(0);
        viewport.setMaxY(2200);
        viewport.setScrollable(true);


        //updateAll(null);
        start(null);
    }

    public void start(View view){
        Timer timer = new Timer(false);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Log.d("TESTE","Serviço Rodando");
                int resistance = 0;
                try {
                    resistance = manager.getResistance();
                    Log.d("RESISTENCIA","Serviço Rodando"+resistance);
                    updateResistance(resistance);
                } catch (android.os.RemoteException e) {
                    Log.e(TAG, "Erro atualizando dados:", e);
                }

            }
        };
        timer.scheduleAtFixedRate(timerTask,0000,1000);
    }

    public void updateResistance(int resist) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                textResistance.setText(String.valueOf(resist));
                addEntry(resist);
            }
        };
        runOnUiThread(runnable);
    }

    public void addEntry(int resist){
        series.appendData(new DataPoint(lastX++, resist),false,5);
    }
/*

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

    }*/
}