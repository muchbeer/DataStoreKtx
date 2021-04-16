package raum.muchbeer.datastorektx

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import raum.muchbeer.datastorektx.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel : UserPrefViewModel
    private lateinit var viewModelFactory : UserPrefViewModelFactory
    private lateinit var userPref: UserPref
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

      //  binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userPref = UserPref(this)
        viewModelFactory = UserPrefViewModelFactory(userPref)
        viewModel = ViewModelProvider(this, viewModelFactory).get(UserPrefViewModel::class.java)

   binding.viewModelLayout = viewModel
        binding.lifecycleOwner= this

    }


    private fun randomMessage() {
        val listOfMessage = listOf("Gadiel my boy", "Gianna my babe girl", "My Love", "Jacob", "Dada")

        val index = (0..4).random()
        val newMessage = listOfMessage[index]
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.etAge.windowToken, 0)
    }
}