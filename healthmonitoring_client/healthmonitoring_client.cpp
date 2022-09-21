#include "healthmonitoring_client.h"

using namespace std;                  // Permite usar o cout e endl diretamente ao invés de std::cout

namespace devtitans::healthmonitoring {      // Entra no pacote devtitans::healthmonitoring

void HealthMonitoringClient::start(int argc, char **argv) {
    cout << "Cliente HealthMonitoring!" << endl;

    if (argc < 2) {
        cout << "Sintaxe: " << argv[0] << "  " << endl;
        cout << "    Comandos: get-resistance" << endl;
        exit(1);
    }

    HealthMonitoring healthmonitoring;             // Classe da biblioteca HealthMonitoring

    // Comandos getResistance
    if (!strcmp(argv[1], "get-resistance")) {
        cout << "Valor da Resistencia: " << healthmonitoring.getResistance() << endl;
    }else {
        cout << "Comando inválido." << endl;
        exit(1);
    }
}

} // namespace



// MAIN

using namespace devtitans::healthmonitoring; // Permite usar HelloCpp diretamente ao invés de devtitans::hello::HelloCpp

int main(int argc, char **argv) {
    HealthMonitoringClient client;               // Variável hello, da classe HelloCpp, do pacote devtitans::hello
    client.start(argc, argv);             // Executa o método printHello
    return 0;
}
