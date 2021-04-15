package raum.muchbeer.datastorektx

import android.widget.CompoundButton
import androidx.databinding.BindingAdapter


@BindingAdapter("gender")
fun bindSwitch(button: CompoundButton, checkGender: Boolean) {
    button.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
        override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
          if (isChecked) checkGender else !checkGender
        }

    })

}
