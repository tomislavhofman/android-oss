package com.kickstarter.viewmodels

import com.kickstarter.libs.ActivityViewModel
import com.kickstarter.libs.Environment
import com.kickstarter.libs.rx.transformers.Transformers.*
import com.kickstarter.libs.utils.ObjectUtils
import com.kickstarter.models.Category
import com.kickstarter.services.ApiClientType
import com.kickstarter.services.DiscoveryParams
import com.kickstarter.ui.IntentKey
import com.kickstarter.ui.activities.EditorialActivity
import com.kickstarter.ui.data.Editorial
import rx.Notification
import rx.Observable
import rx.subjects.BehaviorSubject
import rx.subjects.PublishSubject

interface EditorialViewModel {
    interface Inputs {
        /** Call when we get the user's current location. */
        fun location(latAndLong: android.util.Pair<Double, Double>)

        /** Call when the user has acknowledged the location heads up dialog. */
        fun locationDialogConfirmed()

        /** Call when the user clicks the retry container. */
        fun retryContainerClicked()
    }

    interface Outputs {
        /** Emits the @StringRes ID of the description of the [Editorial]. */
        fun description(): Observable<Int>

        /** Emits the [DiscoveryParams] for the tag of the [Editorial]. */
        fun discoveryParams(): Observable<DiscoveryParams>

        /** Emits the @DrawableRes of the graphic of the [Editorial]. */
        fun graphic(): Observable<Int>

        /** Emits when we should refresh the [com.kickstarter.ui.fragments.DiscoveryFragment]. */
        fun refreshDiscoveryFragment(): Observable<Void>

        /** Emits when we should request the user's location. */
        fun requestLocation(): Observable<Void>

        /** Emits a [Boolean] determining if the retry container should be visible. */
        fun retryContainerIsGone(): Observable<Boolean>

        /** Emits a list of root [Category]s. */
        fun rootCategories(): Observable<List<Category>>

        /** Emits the @StringRes ID of the title of the [Editorial]. */
        fun title(): Observable<Int>
    }

    class ViewModel(val environment: Environment) : ActivityViewModel<EditorialActivity>(environment), Inputs, Outputs {
        private val location: PublishSubject<android.util.Pair<Double, Double>> = PublishSubject.create()
        private val locationDialogConfirmed: PublishSubject<Void> = PublishSubject.create()
        private val retryContainerClicked: PublishSubject<Void> = PublishSubject.create()

        private val description: BehaviorSubject<Int> = BehaviorSubject.create()
        private val discoveryParams: BehaviorSubject<DiscoveryParams> = BehaviorSubject.create()
        private val graphic: BehaviorSubject<Int> = BehaviorSubject.create()
        private val refreshDiscoveryFragment: PublishSubject<Void> = PublishSubject.create()
        private val requestLocation: PublishSubject<Void> = PublishSubject.create()
        private val retryContainerIsGone: BehaviorSubject<Boolean> = BehaviorSubject.create()
        private val rootCategories: BehaviorSubject<List<Category>> = BehaviorSubject.create()
        private val title: BehaviorSubject<Int> = BehaviorSubject.create()

        private val apiClient: ApiClientType = environment.apiClient()

        val inputs: Inputs = this
        val outputs: Outputs = this

        init {
            val editorial = intent()
                    .map { it.getSerializableExtra(IntentKey.EDITORIAL) }
                    .filter { ObjectUtils.isNotNull(it) }
                    .ofType(Editorial::class.java)

            val categoriesNotification = Observable.merge(fetchCategories(), this.retryContainerClicked.switchMap { fetchCategories() })

            categoriesNotification
                    .compose(values())
                    .map { it.filter { category -> category.isRoot } }
                    .map { it.sorted() }
                    .compose(bindToLifecycle())
                    .subscribe { this.rootCategories.onNext(it) }

            categoriesNotification
                    .compose(errors())
                    .compose(bindToLifecycle())
                    .subscribe { this.retryContainerIsGone.onNext(false) }

            val discoveryParams = editorial
                    .map { discoveryParams(it) }

            discoveryParams
                    .compose(bindToLifecycle())
                    .subscribe(this.discoveryParams)

            discoveryParams
                    .compose<android.util.Pair<DiscoveryParams, String>>(takePairWhen(this.location
                            .map { arrayOf(it.first, it.second).joinToString(",") }))
                    .map { it.first.toBuilder().ll(it.second).build() }
                    .compose(bindToLifecycle())
                    .subscribe(this.discoveryParams)

            Observable.merge(discoveryParams,
                    discoveryParams.compose(takeWhen(this.locationDialogConfirmed)))
                    .filter { it.sort() == DiscoveryParams.Sort.DISTANCE }
                    .compose(bindToLifecycle())
                    .subscribe { this.requestLocation.onNext(null) }

            editorial
                    .map { it.graphic }
                    .compose(bindToLifecycle())
                    .subscribe(this.graphic)

            editorial
                    .map { it.title }
                    .compose(bindToLifecycle())
                    .subscribe(this.title)

            editorial
                    .map { it.description }
                    .compose(bindToLifecycle())
                    .subscribe(this.description)

            this.retryContainerClicked
                    .compose(bindToLifecycle())
                    .subscribe(this.refreshDiscoveryFragment)
        }

        private fun discoveryParams(editorial: Editorial): DiscoveryParams {
            val sort = when (editorial) {
                Editorial.LIGHTS_ON -> DiscoveryParams.Sort.DISTANCE
                else -> DiscoveryParams.Sort.MAGIC
            }
            return DiscoveryParams.builder().sort(sort).tagId(editorial.tagId).build()
        }

        private fun fetchCategories(): Observable<Notification<MutableList<Category>>>? {
            return this.apiClient.fetchCategories()
                    .doOnSubscribe { this.retryContainerIsGone.onNext(true) }
                    .materialize()
                    .share()
        }

        override fun location(latAndLong: android.util.Pair<Double, Double>) = this.location.onNext(latAndLong)

        override fun locationDialogConfirmed() = this.locationDialogConfirmed.onNext(null)

        override fun retryContainerClicked() = this.retryContainerClicked.onNext(null)

        override fun description(): Observable<Int> = this.description

        override fun discoveryParams(): Observable<DiscoveryParams> = this.discoveryParams

        override fun graphic(): Observable<Int> = this.graphic

        override fun refreshDiscoveryFragment(): Observable<Void> = this.refreshDiscoveryFragment

        override fun requestLocation(): Observable<Void> = this.requestLocation

        override fun retryContainerIsGone(): Observable<Boolean> = this.retryContainerIsGone

        override fun rootCategories(): Observable<List<Category>> = this.rootCategories

        override fun title(): Observable<Int> = this.title
    }
}
