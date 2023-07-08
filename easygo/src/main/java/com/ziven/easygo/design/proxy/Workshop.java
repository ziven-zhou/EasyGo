package com.ziven.easygo.design.proxy;

import androidx.annotation.Keep;

/**
 * @author Ziven
 */
@Keep
public interface Workshop<R> {
    /**
     * Make: Param -> Data
     * @return Return Data
     */
    R make();

    interface P1<R, P1> extends Workshop<R> {

        /**
         * Make: Param -> Data
         * @return Return Data
         */
        @Override
        default R make() {
            return make();
        }

        /**
         * Make: Param -> Data
         * @param param1 Param
         * @return Return Data
         */
        R make(P1 param1);
    }

    interface P2<R, P1, P2> extends Workshop.P1<R, P1> {

        /**
         * Make: Param -> Data
         * @param param Param
         * @return Return Data
         */
        @Override
        default R make(P1 param) {
            return make(param, null);
        }

        /**
         * Make: (Param1,Param2) -> Data
         * @param param1 Param1
         * @param param2 Param2
         * @return Return Data
         */
        R make(P1 param1, P2 param2);
    }

    interface P3<R, P1, P2, P3> extends Workshop.P2<R, P1, P2> {

        /**
         * Make: (Param1,Param2) -> Data
         * @param param1 Param1
         * @param param2 Param2
         * @return Return Data
         */
        @Override
        default R make(P1 param1, P2 param2) {
            return make(param1, param2, null);
        }

        /**
         * Make: (Param1,Param2,Param3) -> Data
         * @param param1 Param1
         * @param param2 Param2
         * @param param3 Param3
         * @return Return Data
         */
        R make(P1 param1, P2 param2, P3 param3);
    }

    interface P4<R, P1, P2, P3, P4> extends Workshop.P3<R, P1, P2, P3> {

        /**
         * Make: (Param1,Param2,Param3) -> Data
         * @param param1 Param1
         * @param param2 Param2
         * @param param3 Param3
         * @return Return Data
         */
        @Override
        default R make(P1 param1, P2 param2, P3 param3) {
            return make(param1, param2, param3, null);
        }

        /**
         * Make: (Param1,Param2,Param3,Param4) -> Data
         * @param param1 Param1
         * @param param2 Param2
         * @param param3 Param3
         * @param param4 Param4
         * @return Return Data
         */
        R make(P1 param1, P2 param2, P3 param3, P4 param4);
    }

    interface P5<R, P1, P2, P3, P4, P5> extends Workshop.P4<R, P1, P2, P3, P4> {

        /**
         * Make: (Param1,Param2,Param3,Param4) -> Data
         * @param param1 Param1
         * @param param2 Param2
         * @param param3 Param3
         * @param param4 Param4
         * @return Return Data
         */
        @Override
        default R make(P1 param1, P2 param2, P3 param3, P4 param4) {
            return make(param1, param2, param3, param4, null);
        }

        /**
         * Make: (Param1,Param2,Param3,Param4,Param5) -> Data
         * @param param1 Param1
         * @param param2 Param2
         * @param param3 Param3
         * @param param4 Param4
         * @param param5 Param5
         * @return Return Data
         */
        R make(P1 param1, P2 param2, P3 param3, P4 param4, P5 param5);
    }

    interface P6<R, P1, P2, P3, P4, P5, P6> extends Workshop.P5<R, P1, P2, P3, P4, P5> {

        /**
         * Make: (Param1,Param2,Param3,Param4,Param5) -> Data
         * @param param1 Param1
         * @param param2 Param2
         * @param param3 Param3
         * @param param4 Param4
         * @param param5 Param5
         * @return Return Data
         */
        @Override
        default R make(P1 param1, P2 param2, P3 param3, P4 param4, P5 param5) {
            return make(param1, param2, param3, param4, param5, null);
        }

        /**
         * Make: (Param1,Param2,Param3,Param4,Param5,Param6) -> Data
         * @param param1 Param1
         * @param param2 Param2
         * @param param3 Param3
         * @param param4 Param4
         * @param param5 Param5
         * @param param6 Param6
         * @return Return Data
         */
        R make(P1 param1, P2 param2, P3 param3, P4 param4, P5 param5, P6 param6);
    }
}
