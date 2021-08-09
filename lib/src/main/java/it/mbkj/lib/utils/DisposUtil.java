package it.mbkj.lib.utils;

import io.reactivex.rxjava3.disposables.Disposable;

public class DisposUtil {
    public DisposUtil() {
    }

    public static void close(Disposable disposable) {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
            disposable = null;
        }

    }
}

