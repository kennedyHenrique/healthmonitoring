#include "healthmonitoring_service.h"

namespace aidl::devtitans::healthmonitoring {
    ndk::ScopedAStatus HealthmonitoringService::connect(int32_t* _aidl_return) {
        *_aidl_return = this->healthmonitoring.connect();
        LOG(INFO) << "connect(): " << *_aidl_return;
        return ndk::ScopedAStatus::ok();
    }

    ndk::ScopedAStatus HealthMonitoringService::getResistance(int32_t* _aidl_return) {
        *_aidl_return = this->healthmonitoring.getResistance();
        LOG(INFO) << "getResistance(): " << *_aidl_return;
        return ndk::ScopedAStatus::ok();
    }
}
