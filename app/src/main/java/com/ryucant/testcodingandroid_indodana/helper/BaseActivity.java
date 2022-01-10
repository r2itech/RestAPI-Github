package com.ryucant.testcodingandroid_indodana.helper;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;

import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;

public class BaseActivity extends AppCompatActivity {

    @VisibleForTesting
    public ProgressBar progressBar;
    private ACProgressFlower dialog;

    public void setProgressBar(ProgressBar progressBar) {
        progressBar = progressBar;
    }

    public void showProgressBar() {
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    public void hideProgressBar() {
        if (progressBar != null) {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    public void ShowLoading(){
        dialog = new ACProgressFlower.Builder(this)
                .direction(ACProgressConstant.DIRECT_ANTI_CLOCKWISE)
                .sizeRatio((float) 0.1)
                .centerPadding((float) 0.6)
                .themeColor(Color.WHITE)
                .build();
        dialog.show();
    }

    public void HideLoading(){
        if (this.dialog != null && this.dialog.isShowing()) {
            this.dialog.dismiss();
        }
    }

    public void hideKeyboard(View view) {
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        hideProgressBar();
    }

}
