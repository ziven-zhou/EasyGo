package com.ziven.easygo.util;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Ziven
 * @date 2021/5/28
 */
@Keep
public final class ThreadUtils {

    private ThreadUtils() {
    }

    public static final Handler MAIN_HANDLER = new Handler(Looper.getMainLooper());

    public static final EasyThreadFactory THREAD_FACTORY = new EasyThreadFactory();

    public static final ExecutorService EASY_EXECUTOR = newEasyThreadPool();

    public static boolean isOnWorkerThread() {
        return !isOnMainThread();
    }

    public static boolean isOnMainThread() {
        return Condition
                .ofFalse()
                .ofJustEqual(Thread.currentThread(), Looper.getMainLooper().getThread())
                .isTrue();
    }

    public static void runOnMainThread(@NonNull final Runnable runnable) {
        Condition
                .ofFalse()
                .ofJustEqual(Thread.currentThread(), Looper.getMainLooper().getThread())
                .doTrue(runnable::run)
                .doFalse(() -> MAIN_HANDLER.post(runnable));
    }

    public static void postMainThread(@NonNull final Runnable runnable) {
        MAIN_HANDLER.post(runnable);
    }

    public static void postDelayedMainThread(@NonNull final Runnable runnable, long delayMillis) {
        MAIN_HANDLER.postDelayed(runnable, delayMillis);
    }

    public static void runOnWorkerThread(@NonNull final Runnable runnable) {
        Condition
                .ofFalse()
                .ofJustNotEqual(Thread.currentThread(), Looper.getMainLooper().getThread())
                .doTrue(runnable::run)
                .doFalse(() -> EASY_EXECUTOR.execute(runnable));
    }

    public static void postOnWorkerThread(@NonNull final Runnable runnable) {
        EASY_EXECUTOR.execute(runnable);
    }

    public static ExecutorService newEasyThreadPool() {
        return new ThreadPoolExecutor(
                Runtime.getRuntime().availableProcessors(),
                Integer.MAX_VALUE,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                THREAD_FACTORY);
    }

    @Keep
    private static class EasyThreadFactory implements ThreadFactory {
        private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);
        private final AtomicInteger mThreadNumber;
        private final ThreadGroup mGroup;
        private final String mNamePrefix;

        EasyThreadFactory() {
            mThreadNumber = new AtomicInteger(1);
            SecurityManager sm = System.getSecurityManager();
            mGroup = sm == null ? Thread.currentThread().getThreadGroup() : sm.getThreadGroup();
            mNamePrefix = "easy-go-pool-" + POOL_NUMBER.getAndIncrement() + "-thread-";
        }

        @Override
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(mGroup, runnable, mNamePrefix + mThreadNumber.getAndIncrement(), 0);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            if (thread.getPriority() != Thread.NORM_PRIORITY) {
                thread.setPriority(Thread.NORM_PRIORITY);
            }
            return thread;
        }
    }

    public static void workerToMain(@NonNull Runnable worker,
                                    @NonNull Runnable main) {
        newThreadEasyGo().workerThread(worker).mainThread(main).serial();
    }

    public static void workerToMain(@NonNull Runnable main,
                                    @NonNull Runnable... workers) {
        ThreadEasyGo threadEasyGo = ThreadEasyGo.of();
        EasyUtils.forEach(workers, threadEasyGo::workerThread);
        threadEasyGo.mainThread(main).serial();
    }

    public static <R> void workerToMain(@NonNull Runnable main,
                                        @NonNull Obtain<R> worker,
                                        @NonNull Runnable... workers) {
        ThreadEasyGo threadEasyGo = ThreadEasyGo.of();
        EasyUtils.forEach(workers, threadEasyGo::workerThread);
        threadEasyGo
                .workerThread(worker)
                .mainThread(main)
                .serial();
    }

    public static <R, V> void workerToMain(@NonNull Obtain<R> worker,
                                           @NonNull Carry<V> main) {
        newThreadEasyGo()
                .workerThread(worker)
                .mainThread(main)
                .serial();
    }

    public static <R, V> void workerToMain(@NonNull Carry<V> main,
                                           @NonNull Obtain<R>... workers) {
        ThreadEasyGo threadEasyGo = ThreadEasyGo.of();
        EasyUtils.forEach(workers, threadEasyGo::workerThread);
        threadEasyGo.mainThread(main).serial();
    }

    public static <R> void workerToMain(@NonNull Runnable main,
                                        @NonNull Obtain<R>... workers) {
        ThreadEasyGo threadEasyGo = ThreadEasyGo.of();
        EasyUtils.forEach(workers, threadEasyGo::workerThread);
        threadEasyGo.mainThread(main).serial();
    }

    public static <R1, R2, V> void workerToMain(@NonNull Obtain<R1> worker1,
                                                @NonNull Obtain<R2> worker2,
                                                @NonNull Carry<V> main) {
        newThreadEasyGo()
                .workerThread(worker1)
                .workerThread(worker2)
                .mainThread(main)
                .serial();
    }

    public static <R1, R2, R3, V> void workerToMain(@NonNull Obtain<R1> worker1,
                                                    @NonNull Obtain<R2> worker2,
                                                    @NonNull Obtain<R3> worker3,
                                                    @NonNull Carry<V> main) {
        newThreadEasyGo()
                .workerThread(worker1)
                .workerThread(worker2)
                .workerThread(worker3)
                .mainThread(main)
                .serial();
    }

    public static ThreadEasyGo newThreadEasyGo() {
        return ThreadEasyGo.of();
    }

    @Keep
    public static class ThreadEasyGo {

        private int mCount = 0;
        private ThreadEasyGoTransfer mHead;
        private ThreadEasyGoTransfer mTrail;
        private ThreadEasyGoTransfer mMultipleTransfer;
        private CopyOnWriteArrayList<Object> mParams;

        private ThreadEasyGo() {
        }

        public static ThreadEasyGo of() {
            return new ThreadEasyGo();
        }

        public ThreadEasyGo workerThread(@NonNull Runnable runnable) {
            setTransfer(value -> {
                runnable.run();
                return null;
            }, ThreadEasyGoTransfer.THREAD_MODE_WORKER);
            return this;
        }

        public <R> ThreadEasyGo workerThread(@NonNull Obtain<R> obtain) {
            setTransfer(value -> obtain.obtain(), ThreadEasyGoTransfer.THREAD_MODE_WORKER);
            return this;
        }

        public <R, V> ThreadEasyGo workerThread(@NonNull Carry<V> obtain) {
            setTransfer((Transfer<R, V>) value -> {
                obtain.carry(value);
                return null;
            }, ThreadEasyGoTransfer.THREAD_MODE_WORKER);
            return this;
        }

        public <R, V> ThreadEasyGo workerThread(@NonNull Transfer<R, V> transfer) {
            setTransfer(transfer, ThreadEasyGoTransfer.THREAD_MODE_WORKER);
            return this;
        }

        public ThreadEasyGo mainThread(@NonNull Runnable runnable) {
            setTransfer(value -> {
                runnable.run();
                return null;
            }, ThreadEasyGoTransfer.THREAD_MODE_MAIN);
            return this;
        }

        public <R> ThreadEasyGo mainThread(@NonNull Obtain<R> obtain) {
            setTransfer(value -> obtain.obtain(), ThreadEasyGoTransfer.THREAD_MODE_MAIN);
            return this;
        }

        public <R, V> ThreadEasyGo mainThread(@NonNull Carry<V> obtain) {
            setTransfer((Transfer<R, V>) value -> {
                obtain.carry(value);
                return null;
            }, ThreadEasyGoTransfer.THREAD_MODE_MAIN);
            return this;
        }

        public <R, V> ThreadEasyGo mainThread(@NonNull Transfer<R, V> transfer) {
            setTransfer(transfer, ThreadEasyGoTransfer.THREAD_MODE_MAIN);
            return this;
        }

        private void setTransfer(@NonNull Transfer<?, ?> transfer, int threadMode) {
            if(mTrail == null) {
                mTrail = ThreadEasyGoTransfer.of(transfer, threadMode, mCount++);
                mHead = mTrail;
            } else {
                mTrail.setNext(ThreadEasyGoTransfer.of(transfer, threadMode, mCount++));
                mTrail = mTrail.next();
            }
        }

        private void transfer(@Nullable final ThreadEasyGoTransfer transfer, final @Nullable Object value) {
            if(transfer == null) {
                return;
            }
            if(transfer.isWorkerThread()) {
                runOnWorkerThread(() -> execute(transfer, value));
            } else if(transfer.isMainThread()) {
                runOnMainThread(() -> execute(transfer, value));
            }
        }

        private void execute(@NonNull final ThreadEasyGoTransfer transfer, final @Nullable Object value) {
            final Object rValue = transfer.getTransfer().transfer(EasyUtils.transition(value));
            transfer(transfer.next(), rValue);
        }

        public void serial() {
            serial(null);
        }

        public void serial(@Nullable Object value) {
            transfer(mHead, value);
        }

        public void parallelMain(@Nullable Object... values) {
            parallelMain(null, values);
        }

        public void parallelMain(@Nullable MultipleTransfer transfer, @Nullable Object... values) {
            mMultipleTransfer = transfer == null ? null : ThreadEasyGoTransfer.of(transfer, ThreadEasyGoTransfer.THREAD_MODE_MAIN, -1);
            Nulls.of(mParams)
                    .nullNewThis(CopyOnWriteArrayList::new)
                    .doNotNull(r -> mParams = r)
                    .justNext(values)
                    .doNotNull(v -> EasyUtils.forEach(v, data -> mParams.add(data)));
            parallel();
        }

        public void parallelWorker(@Nullable Object... values) {
            parallelWorker(null, values);
        }

        public void parallelWorker(@Nullable MultipleTransfer transfer, @Nullable Object... values) {
            mMultipleTransfer = transfer == null ? null : ThreadEasyGoTransfer.of(transfer, ThreadEasyGoTransfer.THREAD_MODE_WORKER, -1);
            Nulls.of(mParams)
                    .nullNewThis(CopyOnWriteArrayList::new)
                    .doNotNull(r -> mParams = r)
                    .justNext(values)
                    .doNotNull(v -> EasyUtils.forEach(v, data -> mParams.add(data)));
            parallel();
        }

        private void parallel() {
            if(mHead == null) {
                return;
            }
            final AtomicInteger count = new AtomicInteger(mCount);
            final ConcurrentHashMap<Integer, Object> map = mMultipleTransfer == null ? null : new ConcurrentHashMap<>(8);
            ThreadEasyGoTransfer record = mHead;
            do {
                final ThreadEasyGoTransfer temp = record;
                record = temp.next();
                if(temp.isWorkerThread()) {
                    postOnWorkerThread(() -> execute(temp, count, map));
                } else if(temp.isMainThread()) {
                    runOnMainThread(() -> execute(temp, count, map));
                }
            } while (record != null);
        }

        private void execute(@NonNull final ThreadEasyGoTransfer transfer,
                             @NonNull final AtomicInteger count,
                             @Nullable final ConcurrentHashMap<Integer, Object> map) {
            final Object param = EasyUtils.getValue(mParams, transfer.getId());
            final Object rValue = transfer.getTransfer().transfer(EasyUtils.transition(param));
            Nulls.of(mMultipleTransfer).doNotNull(mt -> {
                if(mt.isWorkerThread()) {
                    runOnWorkerThread(() -> ((MultipleTransfer) mt.getTransfer()).transferSynchronized(transfer.getId(), rValue));
                } else if(mt.isMainThread()) {
                    runOnMainThread(() -> ((MultipleTransfer) mt.getTransfer()).singleTransfer(transfer.getId(), rValue));
                }
            });
            Nulls.of(map).doNotNull(r -> r.put(transfer.getId(), rValue));
            if(count.decrementAndGet() <= 0) {
                Nulls.of(mMultipleTransfer).doNotNull(mt -> {
                    if(mt.isWorkerThread()) {
                        runOnWorkerThread(() -> transfer(map, EasyUtils.transition(mt.getTransfer())));
                    } else if(mt.isMainThread()) {
                        runOnMainThread(() -> transfer(map, EasyUtils.transition(mt.getTransfer())));
                    }
                });
            }
        }

        private void transfer(@Nullable final ConcurrentHashMap<Integer, Object> map,
                              @NonNull final MultipleTransfer transfer) {
            Nulls.of(map).doNull(transfer::multipleTransfer)
                    .doNotNull(r -> transfer.multipleTransfer(
                            EasyUtils.getValue(r, 0),
                            EasyUtils.getValue(r, 1),
                            EasyUtils.getValue(r, 2),
                            EasyUtils.getValue(r, 3),
                            EasyUtils.getValue(r, 4),
                            EasyUtils.getValue(r, 5),
                            EasyUtils.getValue(r, 6),
                            EasyUtils.getValue(r, 7), r));
        }
    }

    @Keep
    private static class ThreadEasyGoTransfer {
        private static final int THREAD_MODE_MAIN = 0;
        private static final int THREAD_MODE_WORKER = 1;
        private ThreadEasyGoTransfer mNext;
        private final Transfer<?, ?> mTransfer;
        private final int mThreadMode;
        private final int mId;

        public static ThreadEasyGoTransfer of(@NonNull Transfer<?, ?> transfer, int threadMode, int id) {
            return new ThreadEasyGoTransfer(transfer, threadMode, id);
        }

        private ThreadEasyGoTransfer(Transfer<?, ?> transfer, int threadMode, int id) {
            mTransfer = transfer;
            mThreadMode = threadMode;
            mId = id;
        }

        public @NonNull Transfer<?, ?> getTransfer() {
            return mTransfer;
        }

        public boolean isMainThread() {
            return mThreadMode == THREAD_MODE_MAIN;
        }

        public boolean isWorkerThread() {
            return mThreadMode == THREAD_MODE_WORKER;
        }

        public void setNext(@NonNull ThreadEasyGoTransfer next) {
            mNext = next;
        }

        public boolean hasNext() {
            return mNext != null;
        }

        public @Nullable ThreadEasyGoTransfer next() {
            return mNext;
        }

        public int getId() {
            return mId;
        }
    }

    @Keep
    public static abstract class MultipleTransfer implements Transfer<Object, Object>  {
        @Override
        public Object transfer(Object value) {
            return null;
        }

        private synchronized void transferSynchronized(int position, Object value) {
            singleTransfer(position, value);
        }

        /**
         * Parallel  value
         * @param position Thread position
         * @param value Value
         */
        public void singleTransfer (int position, Object value) {

        }

        /**
         * Parallel  values
         * Position 0...7 is 1...8 thread return
         * The last is all params:ConcurrentHashMap<Integer, Object>
         * @param values Values
         */
        public void  multipleTransfer (Object... values) {

        }
    }

    public static void timesInterval(int times, long interval, @NonNull Runnable runnable) {
        timesInterval(times, interval, t -> runnable.run());
    }

    public static void timesInterval(int times, long interval, @NonNull Carry<Integer> carry) {
        if(times >= 0) {
            postDelayedMainThread(() -> {
                carry.carry(times);
                timesInterval(times - 1, interval, carry);
            }, interval);
        }
    }

    public static TimesInterval timesInterval() {
        return new TimesInterval();
    }

    @Keep
    public static class TimesInterval {

        private final int mWhat;
        private long mInterval = 1000;
        private Carry<Integer> mCarry = r -> {};

        private TimesInterval() {
            mWhat = hashCode();
        }

        public TimesInterval interval(long interval) {
            mInterval = interval;
            return this;
        }

        public TimesInterval carry(@NonNull Carry<Integer> carry) {
            mCarry = carry;
            return this;
        }

        public TimesInterval start(int times) {
            timesInterval(times);
            return this;
        }

        private void timesInterval(int times) {
            if(times >= 0) {
                Message msg = Message.obtain(MAIN_HANDLER, () -> {
                    mCarry.carry(times);
                    timesInterval(times - 1);
                });
                msg.what = mWhat;
                MAIN_HANDLER.sendMessageDelayed(msg, mInterval);
            }
        }

        public void interrupt() {
            MAIN_HANDLER.removeMessages(mWhat);
        }
    }

    public static EasyGoHandlerThread newHandlerThread() {
        return new EasyGoHandlerThread();
    }

    public static class EasyGoHandlerThread extends HandlerThread {

        private static final AtomicInteger EXECUTOR_NUMBER = new AtomicInteger(1);
        private final LazyInit<Handler> handlerCreator = LazyInit.lazy(() -> new Handler(getLooper()));

        EasyGoHandlerThread() {
            super("easy-go-handler-thread-" + EXECUTOR_NUMBER.getAndIncrement());
        }

        public EasyGoHandlerThread startMyself() {
            start();
            return this;
        }

        @NonNull
        public Handler getHandler() {
            return handlerCreator.value();
        }
    }

    public static EasyGoExecutor newExecutor(@NonNull Handler handler) {
        return new EasyGoExecutor(handler);
    }

    public static final class EasyGoExecutor implements Executor {

        private final Handler handler;

        EasyGoExecutor(@NonNull Handler handler) {
            this.handler = handler;
        }

        @Override
        public void execute(Runnable command) {
            handler.post(command);
        }
    }

    public static EasyGoExecutor newExecutor() {
        return newExecutor(newHandlerThread().startMyself().getHandler());
    }
}
