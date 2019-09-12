package net.furkanakdemir.noticeboard.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import net.furkanakdemir.noticeboard.ui.NoticeBoardViewModel

@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NoticeBoardViewModel::class)
    abstract fun bindRepoViewModel(noticeBoardViewModel: NoticeBoardViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: NoticeBoardViewModelFactory): ViewModelProvider.Factory
}
