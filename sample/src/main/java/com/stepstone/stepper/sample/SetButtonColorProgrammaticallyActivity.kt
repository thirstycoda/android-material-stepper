package com.stepstone.stepper.sample

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.annotation.ColorInt
import android.view.View
import androidx.core.content.ContextCompat

/**
 * @author Piotr Zawadzki
 */
class SetButtonColorProgrammaticallyActivity : AbstractStepperActivity() {

    lateinit var customButtonColor: ColorStateList

    @ColorInt
    @JvmField
    var blackColorInt: Int = 0

    override val layoutResId: Int
        get() = R.layout.activity_set_button_color_programmatically

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        customButtonColor = ContextCompat.getColorStateList(this, R.color.ms_custom_button_text_color)!!
        blackColorInt = ContextCompat.getColor(this, com.stepstone.stepper.R.color.ms_black)
        stepperLayout.setNextButtonColor(customButtonColor)
        stepperLayout.setBackButtonColor(customButtonColor)
        stepperLayout.setCompleteButtonColor(customButtonColor)
    }

    override fun onStepSelected(newStepPosition: Int) {
        super.onStepSelected(newStepPosition)
        when (newStepPosition) {
            1 -> stepperLayout.setNextButtonColor(blackColorInt)
            2 -> stepperLayout.setBackButtonColor(blackColorInt)
        }
    }

    override fun onCompleted(completeButton: View) {
        super.onCompleted(completeButton)
        stepperLayout.setCompleteButtonColor(blackColorInt)
    }
}
