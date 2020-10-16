package com.jobc.j112kilo.ui.preview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.jobc.j112kilo.R
import com.jobc.j112kilo.ui.auth.registrationlogin.AuthActivity
import kotlinx.android.synthetic.main.activity_preview.*

class PreviewActivity : AppCompatActivity(R.layout.activity_preview) {
    private lateinit var adapter: PreviewPagerAdapter
    private lateinit var dots: Array<ImageView?>
    private var dotsCount = 5
    private val dP = 1F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }

    private fun initView() {
        adapter = PreviewPagerAdapter(supportFragmentManager, getFragmentPreview())
        previewPager.adapter = adapter
        previewPager.offscreenPageLimit = adapter.count

        initDots()
        initButtons()
    }

    private fun initDots() {
        dotsCount = adapter.count
        dots = Array(5){null}
        val oneDp = convertDpToPixel().toInt() * 5
        for(i in 0 until dotsCount) {
            dots[i] = ImageView(this)
            dots[i]!!.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.non_active_dot))

            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(oneDp, 0, oneDp, 0)
            sliderDotsContainer.addView(dots[i], params)
        }

        dots[0]!!.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.active_dot))
        previewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
                //
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                dots.forEach {
                    it!!.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.non_active_dot))
                }
                dots[position]!!.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.active_dot))
                checkText(position)
            }

            override fun onPageSelected(position: Int) {
                //
            }

        })
    }

    private fun checkText(position: Int) {
        if((dots.size - 1) == position) {
            tvNextStrep.setText(R.string.last_step)
        } else {
            tvNextStrep.setText(R.string.next_step)
        }
    }

    private fun initButtons() {
        tvSkip.setOnClickListener {
            startRegistrationActivity()
        }
        tvNextStrep.setOnClickListener {
            nextPreview()
        }
    }

    private fun nextPreview() {
        when(previewPager.currentItem == dots.size - 1) {
            true -> startRegistrationActivity()
            false -> previewPager.currentItem = previewPager.currentItem + 1
        }
    }

    private fun startRegistrationActivity() {
        val intent = Intent(this, AuthActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    private fun getFragmentPreview() : List<PreviewItemFragment> {
        val list: MutableList<PreviewItemFragment> = ArrayList()

        val fragment0 = PreviewItemFragment.newInstance(
            R.drawable.preview_motorway,
            getString(R.string.preview_title_0),
            getString(R.string.preview_text_0)
        )

        val fragment1 = PreviewItemFragment.newInstance(
            R.drawable.preview_quickly,
            getString(R.string.preview_title_1),
            getString(R.string.preview_text_1)
        )

        val fragment2 = PreviewItemFragment.newInstance(
            R.drawable.preview_animals,
            getString(R.string.preview_title_2),
            getString(R.string.preview_text_2)
        )

        val fragment3 = PreviewItemFragment.newInstance(
            R.drawable.preview_medicines,
            getString(R.string.preview_title_3),
            getString(R.string.preview_text_3)
        )

        val fragment4 = PreviewItemFragment.newInstance(
            R.drawable.preview_pianino,
            getString(R.string.preview_title_4),
            getString(R.string.preview_text_4)
        )

        list.add(fragment0)
        list.add(fragment1)
        list.add(fragment2)
        list.add(fragment3)
        list.add(fragment4)

        return list
    }

    private fun convertDpToPixel() : Float {
        val metrics = this.resources.displayMetrics
        return dP * (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT).toFloat()
    }
}
