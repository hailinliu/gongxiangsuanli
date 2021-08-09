package it.mbkj.lib.utils;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.FlowableTransformer;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RxSchedulersHelper {
    public RxSchedulersHelper() {
    }

    public static <T> ObservableTransformer<T, T> io_main() {
        return (upstream) -> {
            return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        };
    }

    public static <T> FlowableTransformer<T, T> transformer() {
        return (upstream) -> {
            return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        };
    }
}