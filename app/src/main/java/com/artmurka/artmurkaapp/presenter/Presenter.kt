package com.artmurka.artmurkaapp.presenter

import android.support.annotation.Nullable
import java.util.concurrent.CopyOnWriteArrayList

@Suppress("UNUSED_PARAMETER")
open class Presenter<View> {

    /**
     * Returns a current view attached to the presenter or null.

     * View is normally available between
     * [Activity.onResume] and [Activity.onPause],
     * [Fragment.onResume] and [Fragment.onPause],
     * [android.view.View.onAttachedToWindow] and [android.view.View.onDetachedFromWindow].

     * Calls outside of these ranges will return null.
     * Notice here that [Activity.onActivityResult] is called *before* [Activity.onResume]
     * so you can't use this method as a callback.

     * @return a current attached view.
     */
    @Nullable var view: View? = null
        private set

    private val onDestroyListeners = CopyOnWriteArrayList<OnDestroyListener>()

    /**
     * This method is being called when a user leaves view.

     * This method is intended for overriding.
     */
    protected open fun onDestroy() {
    }

    /**
     * This method is being called when a view gets attached to it.
     * Normally this happens during [Activity.onResume], [android.app.Fragment.onResume]
     * and [android.view.View.onAttachedToWindow].

     * This method is intended for overriding.

     * @param view a view that should be taken
     */
    protected open fun onTakeView(view: View) {
    }

    protected open fun onViewShown() {
    }

    protected open fun onViewHidden() {
    }

    /**
     * This method is being called when a view gets detached from the presenter.
     * Normally this happens during [Activity.onPause] ()}, [Fragment.onPause] ()}
     * and [android.view.View.onDetachedFromWindow].

     * This method is intended for overriding.
     */
    protected open fun onDropView() {
    }

    /**
     * A callback to be invoked when a presenter is about to be destroyed.
     */
    interface OnDestroyListener {
        /**
         * Called before [Presenter.onDestroy].
         */
        fun onDestroy()
    }

    /**
     * Adds a listener observing [.onDestroy].

     * @param listener a listener to add.
     */
    fun addOnDestroyListener(listener: OnDestroyListener) {
        onDestroyListeners.add(listener)
    }

    /**
     * Removed a listener observing [.onDestroy].

     * @param listener a listener to remove.
     */
    fun removeOnDestroyListener(listener: OnDestroyListener) {
        onDestroyListeners.remove(listener)
    }

    /**
     * Destroys the presenter, calling all [Presenter.OnDestroyListener] callbacks.
     */
    fun destroy() {
        for (listener in onDestroyListeners)
            listener.onDestroy()
        onDestroy()
    }


    /**
     * Attaches a view to the presenter.

     * @param view a view to attach.
     */
    fun takeView(view: View) {
        this.view = view
        onTakeView(view)
    }

    /**
     * Detaches the presenter from a view.
     */
    fun dropView() {
        onDropView()
        this.view = null
    }

    fun viewIsShown() {
        onViewShown()
    }

    fun viewIsHidden() {
        onViewHidden()
    }
}