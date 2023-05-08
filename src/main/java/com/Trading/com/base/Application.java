
package com.Trading.com.base;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Application implements SignalHandler {
    private static final ReentrantLock lock = new ReentrantLock(true);
    Logger logger = LoggerFactory.getLogger(SignalController.class);
    private static Map<Integer, Runnable> methodMap = new HashMap();



    public void commonBehaviour(int sigVal) throws NoSuchMethodException {
        Algo algo = new Algo();
        if (sigVal == 1) {
            methodMap.put(1, () -> {
                algo.setUp();
                algo.setAlgoParam(1, 60);
                algo.performCalc();
                algo.submitToMarket();
            });
        } else if (sigVal == 2) {
            methodMap.put(2, () -> {
                algo.reverse();
                algo.setAlgoParam(1, 80);
                algo.submitToMarket();
            });
        } else if (sigVal == 3) {
            methodMap.put(3, () -> {
                algo.setAlgoParam(1, 90);
                algo.setAlgoParam(2, 15);
                algo.performCalc();
                algo.submitToMarket();
            });
        } else {
            methodMap.put(51, () -> {
                algo.cancelTrades();
            });
        }

        methodMap.put(100, () -> {
            algo.doAlgo();
        });
    }

    public void handleSignal(int signal) {
         try {
             logger.trace("inside handleSignal method");
             lock.lock();
            if (signal > 0 && signal <= 3) {
                 commonBehaviour(signal);
                 methodMap.get(signal).run();
                 methodMap.get(100).run();
            } else {
                 commonBehaviour(51);
                 methodMap.get(51).run();
                 methodMap.get(100).run();
            }
        } catch (NoSuchMethodException exp) {
             logger.trace(exp.getMessage());
        } finally {
            methodMap.clear();
            lock.unlock();
             logger.trace("finally method called...");
        }

    }
}
