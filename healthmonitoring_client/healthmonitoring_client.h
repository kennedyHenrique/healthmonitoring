#pragma once                      // Inclui esse cabeçalho apenas uma vez

#include <iostream>               // std::cout (char-out) e std::endl (end-line)
#include <string.h>               // Função strcmp
#include <stdlib.h>               // Função atoi

#include "healthmonitoring_lib.h"        // Classe healthmonitoring

namespace devtitans::healthmonitoring {  // Pacote que a classe abaixo pertence

class HealthMonitoringClient {           // Classe

    public:
        void start(int argc, char **argv);

};

} // namespace
