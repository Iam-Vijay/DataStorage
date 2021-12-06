package `in`.spiegel.datastorage

import `in`.spiegel.datastorage.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var sessionManager: SessionManager
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                sessionManager.savetodataStore(binding.name.text.toString(),binding.age.text.toString().toInt(),true)
            }
        }
        sessionManager.exampleCounterFlow.asLiveData().observe(this,{
            binding.textView.setText(it)
        })
    }
}