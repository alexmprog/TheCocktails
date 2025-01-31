package com.alexmprog.thecocktails.categories.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexmprog.thecocktails.core.common.model.Resource
import com.alexmprog.thecocktails.core.domain.usecase.GetCategoriesUseCase
import com.alexmprog.thecocktails.core.domain.model.Category
import com.alexmprog.thecocktails.core.ui.R
import com.alexmprog.thecocktails.core.ui.state.ErrorText
import com.alexmprog.thecocktails.core.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.CONFLATED
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.sync.withPermit
import kotlinx.coroutines.withContext
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject

@HiltViewModel
internal class CategoriesListViewModel @Inject constructor(
    getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val refreshAction = Channel<Boolean>(CONFLATED)
    val uiState: StateFlow<UiState<List<Category>>> = refreshAction.receiveAsFlow()
        .flatMapLatest { getCategoriesUseCase() }
        .map {
            when (it) {
                is Resource.Success -> UiState.Success(it.data)
                is Resource.Error -> UiState.Error(ErrorText.StringResource(R.string.core_ui_network_error))
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UiState.Loading,
        )

    init {
        refresh()
        viewModelScope.launch {
            //val mutex = Mutex()
            Log.i("linset", "viewModelScope start")
            //var i = AtomicInteger()
            var i = 0
            withContext(Dispatchers.Main) {

                val job1 = launch(Dispatchers.Default) {
                    repeat(10_000_000) {
//                    mutex.withLock {
//                        i++
//                    }
                        //i.incrementAndGet()
//                        withContext(Dispatchers.Main) {
//                            i++
//                        }
                        i++
                    }
                }
                val job2 = launch(Dispatchers.Default) {
                    repeat(10_000_000) {
//                    mutex.withLock {
//                        i++
//                    }
                        //i.incrementAndGet()
//                        withContext(Dispatchers.Main) {
//                            i++
//                        }
                        i++
                    }
                }
                job1.join()
                job2.join()
            }
            Log.i("linset", "viewModelScope i=$i")
        }
    }

    fun refresh() {
        refreshAction.trySend(true)
    }

    override fun onCleared() {
        super.onCleared()
    }

    fun repeatedString(s: String, n: Long): Long {
        val repeatCount = n / s.length
        val remainIndex = n % s.length
        var result = 0L
        val array = s.toCharArray()
        if (repeatCount > 0) {
            array.forEach {
                if (it == 'a') result++
            }
            result *= repeatCount
        }
        if (remainIndex > 0) {
            for (i in 0 until remainIndex.toInt()) {
                if (array[i] == 'a') result++
            }
        }
        return result
    }

    fun kangoo(x1: Int, v1: Int, x2: Int, v2: Int): String {
        val vDiff = v1 - v2
        val xDiff = x2 - x1
        if (vDiff <= 0 || vDiff > xDiff ) return "NO"
        return if (xDiff % vDiff == 0) "YES" else "NO"
//        var pos1 = x1
//        var pos2 = x2
//        while (pos1<=pos2){
//            pos1+=x1
//            pos2+=x2
//        }
//        return if(pos1==pos2)"YES" else "NO"
    }
}
