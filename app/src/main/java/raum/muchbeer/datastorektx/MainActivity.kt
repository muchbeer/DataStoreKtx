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
    //    setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        userPref = UserPref(this)
        viewModelFactory = UserPrefViewModelFactory(userPref)
        viewModel = ViewModelProvider(this, viewModelFactory).get(UserPrefViewModel::class.java)
      //  saveUser()
   binding.viewModelLayout = viewModel
        binding.lifecycleOwner= this

     /*   binding.switchGender.setOnCheckedChangeListener(object: CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(p0: CompoundButton?, isChecked: Boolean) {
               viewModel.checkGender(isChecked)
            }

        })*/
        observeUser()
    }

/*    private fun saveUser() {
      btn_save.setOnClickListener {
          userName = et_name.text.toString()
          userAge = et_age.text.toString().toInt()
          val gender = switch_gender.isChecked
         // viewModel.saveUserData(userName, userAge, gender)
       }
    }*/

    private fun observeUser() {
        viewModel.read_username.asLiveData().observe(this, { username->
            userName = username
            tv_name.text = username
        })

        viewModel.read_userAge.asLiveData().observe(this, { age ->
            userAge = age
            tv_age.text = age.toString()
        })

        viewModel.read_gender.asLiveData().observe(this, { gender ->
            userGender = if(gender) "Female" else "Male"
            tv_gender.text = userGender
        })
    }
}