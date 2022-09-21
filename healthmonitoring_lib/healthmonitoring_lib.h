#pragma once                           // Inclui esse cabeçalho apenas uma vez

#include <fstream>                     // Classe ifstream
#include <android-base/properties.h>   // Função GetBoolProperty
#include <sys/stat.h>                  // Função e struct stat
#include <random>                      // Geração de números aleatórios (valores simulados)

using namespace std;                   // Permite usar string diretamente ao invés de std::string

namespace devtitans::healthmonitoring {       // Pacote HealthMonitoring

class HealthMonitoring {
    public:
        /**
         * Verifica se o diretório /sys/kernel/smartlamp existe. Se existir
         * o dispositivo SmartLamp está conectado via USB. Caso contrário,
         * verifica a propriedade devtitans.smartlamp.allow_simulated
         * para ver se valores simulados podem ser usados.
         *
         * Retorna:
         *      0: dispositivo não encontrado
         *      1: sucesso
         *      2: simulado (disp. não encontrado, mas usando valores simulados)
         */
        int connect();


        /**
         * Recebe a resistencia do GSR
         *
         * Retorna:
         *      ...
         */
        int getResistance();

    private:
        /**
         * Métodos para ler ou escrever nos arquivos gsr,
         * do diretório /sys/kernel/healthmonitoring.
         */
        int readFileValue(string file);
};

} // namespace
