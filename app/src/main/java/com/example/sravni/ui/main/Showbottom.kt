package com.example.sravni.ui.main

import android.view.LayoutInflater
import android.view.View
import com.example.sravni.R
import com.google.android.material.bottomsheet.BottomSheetDialog


class Showbottom: View.OnClickListener{
    override fun onClick(v: View?) {
        val dialog = v.let { BottomSheetDialog(v!!.context) }
        val view = LayoutInflater.from(v?.context).inflate(R.layout.bottom_sheet, null)
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setContentView(view)
        dialog.show()
    }
}