#pragma once

#include <android-base/logging.h>
#include <android/binder_process.h>
#include <android/binder_manager.h>

#include <aidl/devtitans/healthmonitoring/BnHealthmonitoring.h>            // Criado pelo AIDL automaticamente

#include "healthmonitoring_lib.h"                                   // Classe HealthMonitoring (biblioteca)

using namespace devtitans::healthmonitoring;

namespace aidl::devtitans::healthmonitoring {

class HealthMonitoringService : public BnHealthmonitoring {
    public:
        ndk::ScopedAStatus connect(int32_t* _aidl_return) override;
        ndk::ScopedAStatus getResistance(int32_t* _aidl_return) override;
    private:
        HealthMonitoring healthmonitoring;                                 // Biblioteca
};

}
