package it.mbkj.lib.utils;

import android.view.View;

import com.jakewharton.rxbinding4.view.RxView;

import java.util.concurrent.TimeUnit;

public class ClickUtil {
    public ClickUtil() {
    }

    public static void click(View view, ClickUtil.Click click) {
        RxView.clicks(view).throttleFirst(800L, TimeUnit.MILLISECONDS).subscribe((aVoid) -> {
            click.click();
        });
    }

    public static void click(long time, View view, ClickUtil.Click click) {
        RxView.clicks(view).throttleFirst(time, TimeUnit.MILLISECONDS).subscribe((aVoid) -> {
            click.click();
        });
    }

    public interface Click {
        void click();
    }
}


