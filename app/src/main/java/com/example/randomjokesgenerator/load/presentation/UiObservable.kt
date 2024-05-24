package com.example.randomjokesgenerator.load.presentation

interface UpdateUi {
    fun updateUiState(uiState: LoadUiState)
}

interface UpdateObserver {
    fun updateObserver(observer: UpdateUiCallback)

    fun clearObserver()
}

interface UiObservable : UpdateObserver, UpdateUi {

    class Base : UiObservable {

        private var showUi: UpdateUiCallback? = null
        private var cache: LoadUiState? = null

        override fun updateUiState(uiState: LoadUiState) {
            if (showUi != null) {
                showUi!!.updateUi(uiState)
            } else {
                cache = uiState
            }
        }

        override fun updateObserver(observer: UpdateUiCallback) {
            showUi = observer
            if (cache != null) {
                showUi!!.updateUi(cache!!)
                cache = null
            }
        }

        override fun clearObserver() {
            showUi = null
        }
    }
}