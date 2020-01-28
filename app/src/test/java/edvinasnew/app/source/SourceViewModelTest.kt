package edvinasnew.app.source

import android.content.SharedPreferences
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import org.junit.Before
import org.junit.Rule

class SourceViewModelTest {
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