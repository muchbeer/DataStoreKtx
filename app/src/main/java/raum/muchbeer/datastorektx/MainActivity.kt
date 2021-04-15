package raum.muchbeer.datastorektx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import raum.muchbeer.datastorektx.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel : UserPrefViewModel
    private lateinit var viewModelFactory : UserPrefViewModelFactory
    private lateinit var userPref: UserPref
    private lateinit var binding: ActivityMainBinding

 //  private val viewModelPref :  UserPrefViewModel by viewModels()
    var userName = ""
    var userAge = 0
    var userGender = ""
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