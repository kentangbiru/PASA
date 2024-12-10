package com.example.tugascapstonepasa.ui.home

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tugascapstonepasa.R
import com.example.tugascapstonepasa.data.model.UpdateStrengthBar
import com.example.tugascapstonepasa.data.respone.PasswordResponse
import com.example.tugascapstonepasa.data.retrofit.ApiConfig
import com.example.tugascapstonepasa.data.retrofit.ApiService
import com.example.tugascapstonepasa.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Observe LiveData from ViewModel
        homeViewModel.text.observe(viewLifecycleOwner) { newText ->
            binding.title.text = newText
        }

        // Toggle password visibility
        binding.passwordToggle.setOnClickListener {
            if (binding.passwordInput.transformationMethod is PasswordTransformationMethod) {
                // Show Password
                binding.passwordInput.transformationMethod = HideReturnsTransformationMethod.getInstance()
                binding.passwordToggle.setImageResource(R.drawable.ic_off_visibility_icon)
            } else {
                // Hide Password
                binding.passwordInput.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.passwordToggle.setImageResource(R.drawable.ic_visibility_icon)
            }
            // Reset cursor position
            binding.passwordInput.setSelection(binding.passwordInput.text?.length ?: 0)
            Log.d("HomeFragment", "Password visibility toggled")
        }

        // Handle analyze button click
        binding.analyzeButton.setOnClickListener {
            val password = binding.passwordInput.text.toString()

            if (password.isEmpty()) {
                binding.strengthText.text = getString(R.string.error_empty_password)
            } else if (password.length < 8) {
                binding.strengthText.text = getString(R.string.error_password_length)
            } else if (isNetworkAvailable()) {
                analyzePassword(password)
            } else {
                binding.strengthText.text = getString(R.string.error_no_internet)
            }
        }

        return root
    }

    private fun analyzePassword(password: String) {
        val apiService = ApiConfig.getApiService()
        val call = apiService.analyzePassword(ApiService.PasswordRequest(password))

        call.enqueue(object : Callback<PasswordResponse> {
            override fun onResponse(call: Call<PasswordResponse>, response: retrofit2.Response<PasswordResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val responseBody = response.body()!!

                    val strengthName = responseBody.data?.strengthName // Gunakan `strength` dari `PasswordResponse`
                    val recommendations = responseBody.data?.recommendations  // Gunakan `suggestions` dari `PasswordResponse`

                    Log.d("HomeFragment", "Strength: $strengthName")
                    Log.d("HomeFragment", "Recommendations: ${recommendations?.joinToString("\n")}")

                    // Update UI with the strength and recommendations
                    val updateStrengthBar = UpdateStrengthBar(binding, requireContext())
                    updateStrengthBar.update(strengthName)

                    val recommendationsText = recommendations?.joinToString("\n") ?: "Tidak ada rekomendasi."

                    binding.strengthText.text = getString(R.string.strength_text, strengthName, recommendationsText)                } else {
                    binding.strengthText.text = getString(R.string.error_failed_response)
                }
            }

            override fun onFailure(call: Call<PasswordResponse>, t: Throwable) {
                binding.strengthText.text = getString(R.string.error_api_failure, t.message)
            }
        })
    }


    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo
        return activeNetwork?.isConnected == true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
