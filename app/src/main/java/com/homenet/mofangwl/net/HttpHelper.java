package com.homenet.mofangwl.net;

import com.homenet.mofangwl.model.ArticleResult;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by weijunpeng on 2018/5/23.
 */

public class HttpHelper {

    public static <T> Observable<T> request(final CompositeDisposable subscriptions,
                                            final Observable<ArticleResult<T>> observable) {
        final RequestDisposable disposableWrap = new RequestDisposable();
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<T> e) throws Exception {
                disposableWrap.disposable = observable
                        .map(ResultTransform.getInstance())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .onErrorReturn(ErrorReturnHolder.ERROR_RETURN)
                        .subscribe(
                                new Consumer<Object>() {
                                    @Override
                                    public void accept(Object o) throws Exception {
                                        if (o instanceof Throwable) {
                                            e.onError((Throwable) o);
                                            Timber.tag("HttpHelper.request() ==> onNext()").w((Throwable) o);
                                        } else {
                                            e.onNext((T) o);
                                            e.onComplete();
                                        }
                                    }
                                },
                                new Consumer<Throwable>() {
                                    @Override
                                    public void accept(Throwable throwable) throws Exception {
                                        if (null != disposableWrap.disposable) {
                                            subscriptions.remove(disposableWrap.disposable);
                                        }
                                        Timber.tag("HttpHelper.request() ==> onError()").w(throwable);
                                        e.onError(throwable);
                                    }
                                },
                                new Action() {
                                    @Override
                                    public void run() throws Exception {
                                        if (null != disposableWrap.disposable) {
                                            subscriptions.remove(disposableWrap.disposable);
                                        }
                                    }
                                });
                subscriptions.add(disposableWrap.disposable);
            }
        });
    }

    private static class RequestDisposable {
        Disposable disposable;
    }

    private static final class ErrorReturnHolder {

        private static final Function ERROR_RETURN = new Function<Throwable, Throwable>() {
            @Override
            public Throwable apply(@NonNull Throwable throwable) throws Exception {
                return throwable;
            }
        };
    }

}
