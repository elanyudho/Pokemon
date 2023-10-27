package com.elanyudho.pokemon.ui.splash

import android.content.Intent
import android.view.LayoutInflater
import com.elanyudho.core.abstraction.BaseActivityBinding
import com.elanyudho.pokemon.databinding.ActivitySplashBinding
import com.elanyudho.pokemon.ui.main.MainActivity

class SplashActivity : BaseActivityBinding<ActivitySplashBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivitySplashBinding
        get() = { ActivitySplashBinding.inflate(layoutInflater) }

    override fun setupView() {
        setAction()
    }

    private fun setAction() {
        with(binding) {
            btnStart.setOnClickListener {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finishAffinity()
            }
        }
    }

}