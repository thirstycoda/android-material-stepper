/*
Copyright 2017 StepStone Services

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package com.stepstone.stepper.sample.step.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import androidx.annotation.UiThread
import com.stepstone.stepper.BlockingStep
import com.stepstone.stepper.StepperLayout
import com.stepstone.stepper.VerificationError
import com.stepstone.stepper.sample.R

internal class StepperFeedbackStepFragment : ButterKnifeFragment(), BlockingStep {

    companion object {

        private const val STEP_POSITION = "step_position"

        fun newInstance(stepPosition: Int): StepperFeedbackStepFragment {
            val fragment = StepperFeedbackStepFragment()
            val arguments = Bundle()
            arguments.putInt(STEP_POSITION, stepPosition)
            fragment.arguments = arguments
            return fragment
        }
    }

    lateinit var stepContent: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        stepContent = view.findViewById(R.id.stepContent)
        stepContent.text = "Step content #${arguments!!.getInt(STEP_POSITION)} \n ${getString(R.string.lorem_ipsum)}"
    }

    override fun verifyStep(): VerificationError? {
        return null
    }

    override fun onSelected() {}

    override fun onError(error: VerificationError) {}

    @UiThread
    override fun onNextClicked(callback: StepperLayout.OnNextClickedCallback) {
        callback.stepperLayout.showProgress("Operation in progress, please wait...")
        Handler(Looper.getMainLooper()).postDelayed({
            callback.stepperLayout.updateProgressMessage("wait a little longer...")
        }, 5000L)
        Handler(Looper.getMainLooper()).postDelayed({
            callback.goToNextStep()
            callback.stepperLayout.hideProgress()
        }, 10000L)
    }

    override fun onCompleteClicked(callback: StepperLayout.OnCompleteClickedCallback) {
        callback.complete()
    }

    @UiThread
    override fun onBackClicked(callback: StepperLayout.OnBackClickedCallback) {
        callback.goToPrevStep()
    }

    override val layoutResId: Int
        get() = R.layout.fragment_with_text_content

}
