/*
Copyright 2016 StepStone Services

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

package com.stepstone.stepper.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.stepstone.stepper.StepperLayout
import com.stepstone.stepper.sample.adapter.DelayedTransitionFragmentStepAdapter

class DelayedTransitionStepperActivity : AppCompatActivity() {

    companion object {

        private const val CURRENT_STEP_POSITION_KEY = "position"
    }

    lateinit var stepperLayout: StepperLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Stepper sample"

        setContentView(R.layout.activity_delayed_transition)
        stepperLayout = findViewById(R.id.stepperLayout)

        val startingStepPosition = savedInstanceState?.getInt(CURRENT_STEP_POSITION_KEY) ?: 0
        stepperLayout.setAdapter(DelayedTransitionFragmentStepAdapter(supportFragmentManager, this), startingStepPosition)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(CURRENT_STEP_POSITION_KEY, stepperLayout.currentStepPosition)
        super.onSaveInstanceState(outState)
    }

    override fun onBackPressed() {
        val currentStepPosition = stepperLayout.currentStepPosition
        if (currentStepPosition > 0) {
            stepperLayout.onBackClicked()
        } else {
            finish()
        }
    }

}
