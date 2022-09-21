#include "healthmonitoring_service.h"

using namespace aidl::devtitans::healthmonitoring;                // HealthmonitoringService (healthmonitoring_service.cpp)
using namespace std;                                       // std::shared_ptr
using namespace ndk;                                       // ndk::SharedRefBase

int main() {
    LOG(INFO) << "Iniciando HealthMonitoring AIDL Service ...";

    ABinderProcess_setThreadPoolMaxThreadCount(0);

    shared_ptr<HealthmonitoringService> healthmonitoring_service = SharedRefBase::make<HealthmonitoringService>();

    const string instance = std::string() + IHealthmonitoring::descriptor + "/default";   // devtitans.healthmonitoring.IHealthmonitoring/default
    binder_status_t status = AServiceManager_addService(healthmonitoring_service->asBinder().get(), instance.c_str());
    CHECK(status == STATUS_OK);

    LOG(INFO) << "HealthMonitoring AIDL Service iniciado com nome: " << instance;
    ABinderProcess_joinThreadPool();

    return EXIT_FAILURE;                                   // Não deve chegar nunca aqui
}
