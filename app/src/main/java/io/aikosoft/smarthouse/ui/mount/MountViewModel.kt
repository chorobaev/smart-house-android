package io.aikosoft.smarthouse.ui.mount

import androidx.lifecycle.LiveData
import io.aikosoft.smarthouse.base.BaseViewModel
import io.aikosoft.smarthouse.data.models.ModuleSmartHouseLiz
import io.aikosoft.smarthouse.data.repositories.ModuleRepository
import io.aikosoft.smarthouse.utility.SingleLiveEvent
import javax.inject.Inject

class MountViewModel @Inject constructor(
    private val moduleRepository: ModuleRepository
) : BaseViewModel() {

    private var module = ModuleSmartHouseLiz()
    private val _permissionsGranted = SingleLiveEvent<Unit>()
    private val _moduleName = SingleLiveEvent<String>()
    private val _disconnectFromWiFi = SingleLiveEvent<Unit>()
    private val _moduleIsSetUp = SingleLiveEvent<Unit>()
    private val _moduleMounted = SingleLiveEvent<Unit>()

    val permissionsGranted: LiveData<Unit> get() = _permissionsGranted
    val shouldConnectToWiFi: LiveData<String> get() = _moduleName
    val shouldDisconnectFromWiFi: LiveData<Unit> get() = _disconnectFromWiFi
    val moduleIsConfigured: LiveData<Unit> get() = _moduleIsSetUp
    val moduleMounted: LiveData<Unit> get() = _moduleMounted

    fun enableFeatures() {
        _permissionsGranted.call()
    }

    fun connectToModule(moduleName: String) {
        module = module.copy(id = moduleName)
        _moduleName.value = moduleName
    }

    fun disconnectFromWiFi() {
        _disconnectFromWiFi.call()
    }

    fun setLoading(loading: Boolean) {
        mLoading.value = loading
    }

    fun sendSsidAndPassword(ssid: String, password: String) {
        moduleRepository.setSsidAndPassword(ssid, password).request {
            _moduleIsSetUp.call()
        }
    }

    fun mountModule(name: String) {
        module = module.copy(name = name)
        log("Putting module: $module")
        moduleRepository.mountModule(module).request {
            _moduleMounted.call()
        }
    }
}