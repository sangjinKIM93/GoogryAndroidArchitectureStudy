package com.jay.architecturestudy.ui.kin

import android.util.Log
import com.jay.architecturestudy.data.database.entity.ImageEntity
import com.jay.architecturestudy.data.database.entity.KinEntity
import com.jay.architecturestudy.data.repository.NaverSearchRepositoryImpl
import com.jay.architecturestudy.ui.BaseSearchPresenter
import com.jay.architecturestudy.util.addTo
import com.jay.architecturestudy.util.then
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class KinPresenter(
    override val view: KinContract.View,
    override val repository: NaverSearchRepositoryImpl
) : BaseSearchPresenter(view, repository), KinContract.Presenter {

    override fun subscribe() {
        super.subscribe()
        loadData()
    }

    private fun loadData() {
        val lastKeyword = repository.getLatestKinKeyword()
        if (lastKeyword.isBlank()) {
            view.updateUi(lastKeyword, emptyList())
        } else {
            repository.getLatestKinResult()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.updateUi(lastKeyword, it)
                }, { e ->
                    val message = e.message ?: return@subscribe
                    Log.e("kin", message)
                })
                .addTo(disposables)
        }
    }

    override fun search(keyword: String) {
        repository.getKin(
            keyword = keyword
        )
            .map {
                // 기존 결과 삭제
                updateKinResult { repository.clearKinResult() }
                it.kins.isNotEmpty().then {
                    val kinList = arrayListOf<KinEntity>()
                    it.kins.mapTo(kinList) { kin ->
                        KinEntity(
                            description = kin.description,
                            link = kin.link,
                            title = kin.title
                        )
                    }
                    updateKinResult {
                        // 최신 결과 저장
                        repository.saveKinResult(kinList)
                    }
                }
                repository.saveKinKeyword(keyword)
                it.kins
            }
            .subscribe({ kins ->
                if (kins.isEmpty()) {
                    view.hideResultListView()
                    view.showEmptyResultView()
                } else {
                    view.hideEmptyResultView()
                    view.showResultListView()
                }
                view.updateResult(kins)
            }, { e ->
                handleError(e)
            })
            .addTo(disposables)
    }

    private fun updateKinResult(func: () -> Unit) {
        Completable.fromCallable(func)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }
}