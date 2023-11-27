package ir.truelearn.digikala

import ir.truelearn.digikala.data.model.ResponseResult
import ir.truelearn.digikala.data.model.prfile.LoginRequest
import ir.truelearn.digikala.data.model.prfile.LoginResponse
import ir.truelearn.digikala.data.remote.NetworkResult
import ir.truelearn.digikala.data.remote.ProfileApiInterface
import ir.truelearn.digikala.repository.ProfileRepository
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import retrofit2.Response

class ProfileRepositoryTest {

    // Mock ProfileApiInterface
    private val mockApiInterface: ProfileApiInterface = mock()

    // Create an instance of ProfileRepository using the mocked ProfileApiInterface
    private val repository = ProfileRepository(mockApiInterface)

    @Test
    fun `login() should return Success when API call is successful`() = runBlocking {
        // Prepare test data
        val loginRequest = LoginRequest("test_phone", "test_password")
        val loginResponse = LoginResponse("test_phone", "test_id", "test_role", "test_token")
        val responseResult = ResponseResult("success_message", loginResponse, true)
        val successResponse = Response.success(responseResult)

        // Mock the API call
        whenever(mockApiInterface.login(loginRequest)).thenReturn(successResponse)

        // Perform the actual login() call
        val result = repository.login(loginRequest)

        // Verify the result
        assertTrue(result is NetworkResult.Success)
        assertEquals("success_message", result.message)
        assertEquals(loginResponse, result.data)
    }

    @Test
    fun `login() should return Error when API call is not successful`() = runBlocking {
        // Prepare test data
        val loginRequest = LoginRequest("test_phone", "test_password")
        val errorResponse =
            Response.error<ResponseResult<LoginResponse>>(400, ResponseBody.create(null, "Response.error()"))

        // Mock the API call
        whenever(mockApiInterface.login(loginRequest)).thenReturn(errorResponse)

        // Perform the actual login() call
        val result = repository.login(loginRequest)

        // Verify the result
        assertTrue(result is NetworkResult.Error)
        assertEquals("Api call failed : code : 400 , message : Error", result.message)
    }
}
