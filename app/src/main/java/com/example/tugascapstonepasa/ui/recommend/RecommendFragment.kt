package com.example.tugascapstonepasa.ui.recommend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tugascapstonepasa.databinding.FragmentRecommendBinding

class RecommendFragment : Fragment() {

    private var _binding: FragmentRecommendBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val recommendViewModel = ViewModelProvider(this).get(RecommendViewModel::class.java)

        _binding = FragmentRecommendBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Mengatur judul
        recommendViewModel.title.observe(viewLifecycleOwner) { title ->
            binding.textRecommend.text = title
        }

        // Mengapa password kuat
        recommendViewModel.whyStrongPassword.observe(viewLifecycleOwner) { whyText ->
            binding.textWhyStrongPasswordContent.text = whyText
        }

        // Tips membuat password kuat
        recommendViewModel.tips.observe(viewLifecycleOwner) { tips ->
            binding.textTips.text = tips.joinToString(separator = "\n• ", prefix = "• ")
        }

        // Contoh kesalahan pembuatan password
        recommendViewModel.examples.observe(viewLifecycleOwner) { examples ->
            binding.textExamples.text = examples.joinToString(separator = "\n\n") { example ->
                "✘ ${example.first}\n→ ${example.second}"
            }
        }

        // Saran pembuatan password
        recommendViewModel.recommendation.observe(viewLifecycleOwner) { recommendation ->
            binding.textRecommendation.text = recommendation
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
