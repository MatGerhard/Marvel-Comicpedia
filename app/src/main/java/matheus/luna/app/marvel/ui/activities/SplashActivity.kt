package matheus.luna.app.marvel.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import matheus.luna.app.marvel.R
import matheus.luna.app.marvel.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar!!.hide()

        setupSplash()
    }

    private fun setupSplash() = with(binding) {

        tvSplashTitle.alpha = 0f
        tvSplashTitle.animate().setDuration(4800).alpha(1f)

        tvSplashAutor.alpha = 0f
        tvSplashAutor.animate().setDuration(4800).alpha(1f)

        ivSplash.alpha = 0f
        ivSplash.animate().setDuration(3800).alpha(1f).withEndAction {
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}