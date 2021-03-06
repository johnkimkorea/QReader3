package com.evgeniykim.qreader3.presentation.activities.history

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.evgeniykim.qreader3.R
import com.evgeniykim.qreader3.data.orm.HistoryORM
import com.evgeniykim.qreader3.domain.History
import com.evgeniykim.qreader3.presentation.activities.Favorite.FavoriteActivity
import com.evgeniykim.qreader3.presentation.activities.main.MainActivity
import com.evgeniykim.qreader3.presentation.activities.settings.SettingActivity
import com.evgeniykim.qreader3.presentation.adapters.HistoryAdapter
import com.evgeniykim.qreader3.presentation.mvp.BaseMvpActivity
import com.evgeniykim.qreader3.utils.ActionEnums
import com.evgeniykim.qreader3.utils.Constants.preUrl
import com.evgeniykim.qreader3.utils.UpperButtonsColors
import kotlinx.android.synthetic.main.activity_history.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import me.dm7.barcodescanner.zxing.ZXingScannerView

class HistoryActivity: BaseMvpActivity<HistoryActivityContract.View, HistoryActivityContract.Presenter>(),
HistoryActivityContract.View, View.OnClickListener {

    // variables
    private var mAdapter: HistoryAdapter? = null
    private lateinit var historyOrm: HistoryORM
    private var mScannerView: ZXingScannerView? = null
    private var flashState: Boolean = false
    var buttonsColors: UpperButtonsColors = UpperButtonsColors()
//    private val upperButtonsColors: UpperButtonsColors = UpperButtonsColors()

    override var mPresenter: HistoryActivityContract.Presenter = HistoryActivityPresenter()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        setUpRecyclerView()
        mPresenter.loadHistory(this)

//        btnBack.setOnClickListener {
//            onBackPressed()
//        }
//        btnClear.setOnClickListener {
//            historyOrm = HistoryORM()
//            historyOrm.clearAll(this)
//            Toast.makeText(this, "All cleared", Toast.LENGTH_SHORT).show()
//
//            this.recreate()
//        }

        setColors()
        initUI()
        btnLightBlocked.visibility = View.VISIBLE

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setColors() {
        val colors = UpperButtonsColors()
        colors.buttonPressed(this, btnHistory, txtHistory, viewLineHistory)
    }

    override fun showHistory(histories: MutableList<History>) {
        mAdapter?.addHistories(histories)
        mAdapter?.notifyDataSetChanged()
    }


    private fun setUpRecyclerView() {
        mAdapter = HistoryAdapter(ArrayList<History>()) {
            history, action ->
            when(action) {
                ActionEnums().ACTION_SEARCH -> {
                    searchInWWW(preUrl + history.context)
                }
                ActionEnums().ACTION_SHARE -> {
                    shareResultViewSharingIntent(history.context)
                }
                ActionEnums().ACTION_COPY -> {
                    copyToClipboard(history.context)
                }
            }
        }
        rvHistory.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvHistory.adapter = mAdapter
    }

    private fun initUI() {
        btnScan1.setOnClickListener(this)
        btnFav1.setOnClickListener(this)
        btnSet1.setOnClickListener(this)

        // Imagebuttons
        btnScan.setOnClickListener(this)
        btnFav.setOnClickListener(this)
        btnSet.setOnClickListener(this)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onClick(p0: View?) {
        when(p0?.id) {
            R.id.btnFav1 -> {
                buttonsColors.buttonPressed(this, btnFav, txtFav, lineViewFav)
                val intent = Intent(this, FavoriteActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit)
                finish()
            }
            R.id.btnScan1 -> {
                buttonsColors.buttonPressed(this, btnScan, txtScan, viewLineScan)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit)
                finish()
            }
            R.id.btnSet1 -> {
                buttonsColors.buttonPressed(this, btnSet, txtSet, viewLineSet)
                val intent = Intent(this, SettingActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit)
                finish()
            }

            // Imagebuttons
            R.id.btnFav -> {
                buttonsColors.buttonPressed(this, btnFav, txtFav, lineViewFav)
                val intent = Intent(this, FavoriteActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit)
                finish()
            }
            R.id.btnScan -> {
                buttonsColors.buttonPressed(this, btnScan, txtScan, viewLineScan)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit)
                finish()
            }
            R.id.btnSet -> {
                buttonsColors.buttonPressed(this, btnSet, txtSet, viewLineSet)
                val intent = Intent(this, SettingActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit)
                finish()
            }

        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setColorsPressed(){
        buttonsColors.buttonPressed(this, btnHistory, txtHistory, viewLineHistory)
    }
    @RequiresApi(Build.VERSION_CODES.M)
    private fun rootBtnColors(){
        buttonsColors.buttonUnpressed(this, btnHistory, txtHistory, viewLineHistory)
    }


}