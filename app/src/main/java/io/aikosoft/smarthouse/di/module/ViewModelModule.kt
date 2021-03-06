package io.aikosoft.smarthouse.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.aikosoft.smarthouse.base.CommonViewModel
import io.aikosoft.smarthouse.base.ViewModelFactory
import io.aikosoft.smarthouse.di.util.ViewModelKey
import io.aikosoft.smarthouse.ui.detail.DetailViewModel
import io.aikosoft.smarthouse.ui.main.MainViewModel
import io.aikosoft.smarthouse.ui.mount.MountViewModel

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CommonViewModel::class)
    abstract fun bindCommonViewModel(commonViewModel: CommonViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MountViewModel::class)
    abstract fun bindMountViewModel(mountViewModel: MountViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(detailViewModel: DetailViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}