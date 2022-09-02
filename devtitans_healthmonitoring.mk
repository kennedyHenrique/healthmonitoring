# Herda as configurações do emulador (produto sdk_phone_x86_64)
$(call inherit-product, $(SRC_TARGET_DIR)/product/sdk_phone_x86_64.mk)

# Sobrescreve algumas variáveis com os dados do novo produto
PRODUCT_NAME := devtitans_healthmonitoring
PRODUCT_DEVICE := healthmonitoring
PRODUCT_BRAND := HealthmonitoringBrand
PRODUCT_MODEL := HealthmonitoringModel

# Copia o arquivo devtitans.txt para o /system/etc da imagem do Android
PRODUCT_COPY_FILES += \
    device/devtitans/healthmonitoring/devtitans.txt:system/etc/devtitans.txt\
    device/devtitans/healthmonitoring/healthmonitoring.rc:vendor/etc/init/healthmonitoring.rc

PRODUCT_SYSTEM_PROPERTIES += \
    ro.devtitans.name=Healthmonitoring

PRODUCT_PRODUCT_PROPERTIES += \
    ro.product.devtitans.version=1.0

PRODUCT_VENDOR_PROPERTIES += \
    ro.vendor.devtitans.hardware=ModelB

# Seta o diretório de overlays
PRODUCT_PACKAGE_OVERLAYS = device/devtitans/kraken/overlay
