package edvinasnew.app.source

import android.content.SharedPreferences
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import edvinasnew.app.main.MainViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SourceViewModelTest{
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val preferences = mock<SharedPreferences>()
    val editor = mock<SharedPreferences.Editor>()

    lateinit var viewModel: SourceViewModel

    @Before
    fun setUp() {
        given(preferences.edit()).willReturn(editor)
        given(editor.putBoolean(any(), any())).willReturn(editor)
    }

}