package net.furkanakdemir.noticeboardsample.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.layout_toolbar.*

abstract class BaseToolbarActivity : AppCompatActivity() {

    @LayoutRes
    abstract fun layoutResId(): Int

    @StringRes
    abstract fun getToolbarTitle(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId())
        setupToolbar()
    }

    protected fun setupToolbar(homeEnabled: Boolean = true) {
        setSupportActionBar(baseToolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(homeEnabled)
        supportActionBar?.setDisplayShowHomeEnabled(homeEnabled)
        supportActionBar?.title = getString(getToolbarTitle())
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
