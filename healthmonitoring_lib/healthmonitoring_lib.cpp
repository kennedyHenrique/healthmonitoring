#include "healthmonitoring_lib.h"

using namespace std;                                   // Permite usar string, ifstream diretamente ao invés de std::string
using namespace android::base;                         // Permite usar GetBoolProperty ao invés de android::base::GetBoolProperty

namespace devtitans::healthmonitoring {                       // Entra no pacote devtitans::healthmonitoring

int HealthMonitoring::connect() {
    char dirPath[] = "/sys/kernel/healthmonitoring";
    struct stat dirStat;
    if (stat(dirPath, &dirStat) == 0)
        if (S_ISDIR(dirStat.st_mode))
            return 1;                                  // Se o diretório existir, retorna 1
    // Diretório não existe
    return 0;// Dispositivo não encontrado
}

int HealthMonitoring::readFileValue(string file) {
    int connected = this->connect();

    if (connected == 1) {                          // Conectado. Vamos solicitar o valor ao dispositivo
        int value;
        string filename = string("/sys/kernel/healthmonitoring/") + file;
        ifstream file(filename);                        // Abre o arquivo do módulo do kernel

        if (file.is_open()) {                           // Verifica se o arquivo foi aberto com sucesso
            file >> value;                              // Lê um inteiro do arquivo
            file.close();
            return value;
        }
    }
    return -1;
}

int HealthMonitoring::getResistance() {
    return this->readFileValue("gsr");
}


} // namespace
