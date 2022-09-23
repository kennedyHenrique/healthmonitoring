package devtitans.healthmonitoringmanager;

import android.util.Log;
import android.os.ServiceManager;
import android.os.IBinder;
import android.os.RemoteException;

import devtitans.healthmonitoring.IHealthmonitoring;                      // Criado pelo AIDL

public class HealthmonitoringManager {
    private static final String TAG = "DevTITANS.HealthmonitoringManager";
    private IBinder binder;
    private IHealthmonitoring service;

    private static HealthmonitoringManager instance;

    // Construtor. Configura a "instância da classe" (objeto) recém-criada.
    // Note o "private" no construtor. Essa classe só pode ser instanciada dentro desse arquivo.
    private HealthmonitoringManager() {
        Log.d(TAG, "Nova (única) instância do HealthmonitoringManager ...");
        binder = ServiceManager.getService("devtitans.healthmonitoring.IHealthmonitoring/default");
        if (binder != null) {
            service = IHealthmonitoring.Stub.asInterface(binder);
            if (service != null)
                Log.d(TAG, "Serviço HealthMonitoring acessado com sucesso.");
            else
                Log.e(TAG, "Erro ao acessar o serviço HealthMonitoring!");
        }
        else
            Log.e(TAG, "Erro ao acessar o Binder!");
    }

    // Acessa a (única) instância dessa classe. Se ela não existir ainda, cria.
    // Note o "static" no método. Podemos executá-lo sem precisar instanciar um objeto.
    public static HealthmonitoringManager getInstance() {
        if (instance == null)
            instance = new HealthmonitoringManager();

        return instance;
    }

    public int connect() throws RemoteException {
        Log.d(TAG, "Executando método connect() ...");
        return service.connect();
    }

    public int getResistance() throws RemoteException {
        Log.d(TAG, "Executando método getResistance() ...");
        return service.getResistance();
    }
}
