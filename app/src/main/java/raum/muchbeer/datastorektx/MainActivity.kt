package raum.muchbeer.datastorektx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        userPref = UserPref(this)
        viewModelFactory = UserPrefViewModelFactory(userPref)
        viewModel = ViewModelProvider(this, viewModelFactory).get(UserPrefViewModel::class.java)

   binding.viewModelLayout = viewModel
        binding.lifecycleOwner= this

    }

}