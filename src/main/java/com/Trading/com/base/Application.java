package com.Trading.com.base;


import java.util.*;

class Application implements SignalHandler {

       private static Map<Integer, Runnable> methodMap = new HashMap<>();

    /**
     * Add All respective signals in commonBehaviour method
     * @param sigVal
     * @throws NoSuchMethodException
     */
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

        //This method invoke all cases
        methodMap.put(100, () -> {
            algo.doAlgo();
        });
    }

    /**
     * Method handleSignal Override from SignalHandler Interface
     * @param signal
     */
    @Override
    public void handleSignal(int signal) {

        try {
            if (signal <0 || signal>50) {
                commonBehaviour(51);
                methodMap.get(51).run();
                methodMap.get(100).run();
            }else {
                commonBehaviour(signal);
                methodMap.get(signal).run();
                methodMap.get(100).run();
            }
        } catch (NoSuchMethodException a) {
            System.out.println(a.getMessage());
        }finally {
            methodMap.clear();
        }
    }
}
