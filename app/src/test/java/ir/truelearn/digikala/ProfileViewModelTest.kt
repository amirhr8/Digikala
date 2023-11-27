package ir.truelearn.digikala

import ir.truelearn.digikala.data.model.prfile.LoginRequest
import ir.truelearn.digikala.data.model.prfile.LoginResponse
import ir.truelearn.digikala.data.remote.NetworkResult
import ir.truelearn.digikala.repository.ProfileRepository
import ir.truelearn.digikala.viewmodel.ProfileViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import net.bytebuddy.matcher.ElementMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ProfileViewModelTest {



    // Mock ProfileRepository
    private val mockRepository: ProfileRepository = mock()

    // Create an instance of ProfileViewModel using the mocked ProfileRepository
    private val viewModel = ProfileViewModel(mockRepository)

    @Test
    fun `login() should update loginResponse on successful login`() = runBlocking {
        // Prepare test data
        val loginRequest = LoginRequest("test_phone", "test_password")
        val loginResponse = LoginResponse("test_phone", "test_id", "test_role", "test_token")
        val successResult = NetworkResult.Success("success_message", loginResponse)

        // Mock the repository response
        whenever(mockRepository.login(loginRequest)).thenReturn(successResult)

        // Update ViewModel input states
        viewModel.inputPhoneState = "test_phone"
        viewModel.inputPasswordState = "test_password"

        // Perform the login() call
        viewModel.login()

        // Verify the ViewModel state updates
        assertThat(viewModel.loadingState, `is`(true))

        // Advance the coroutine to execute pending actions
        coroutineRule.advanceTimeBy(1000)

        // Verify the ViewModel state updates after the API call
        assertThat(viewModel.loadingState, `is`(false))
        assertThat(viewModel.loginResponse.value, `is`(successResult))
    }
}
