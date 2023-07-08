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

    interface P1<R1, P11> extends Workshop<R1> {

        /**
         * Make: Param -> Data
         * @return Return Data
         */
        @Override
        default R1 make() {
            return make();
        }

        /**
         * Make: Param -> Data
         * @param param1 Param
         * @return Return Data
         */
        R1 make(P11 param1);
    }

    interface P2<R2, P21, P22> extends Workshop.P1<R2, P21> {

        /**
         * Make: Param -> Data
         * @param param Param
         * @return Return Data
         */
        @Override
        default R2 make(P21 param) {
            return make(param, null);
        }

        /**
         * Make: (Param1,Param2) -> Data
         * @param param1 Param1
         * @param param2 Param2
         * @return Return Data
         */
        R2 make(P21 param1, P22 param2);
    }

    interface P3<R3, P31, P32, P33> extends Workshop.P2<R3, P31, P32> {

        /**
         * Make: (Param1,Param2) -> Data
         * @param param1 Param1
         * @param param2 Param2
         * @return Return Data
         */
        @Override
        default R3 make(P31 param1, P32 param2) {
            return make(param1, param2, null);
        }

        /**
         * Make: (Param1,Param2,Param3) -> Data
         * @param param1 Param1
         * @param param2 Param2
         * @param param3 Param3
         * @return Return Data
         */
        R3 make(P31 param1, P32 param2, P33 param3);
    }

    interface P4<R4, P41, P42, P43, P44> extends Workshop.P3<R4, P41, P42, P43> {

        /**
         * Make: (Param1,Param2,Param3) -> Data
         * @param param1 Param1
         * @param param2 Param2
         * @param param3 Param3
         * @return Return Data
         */
        @Override
        default R4 make(P41 param1, P42 param2, P43 param3) {
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
        R4 make(P41 param1, P42 param2, P43 param3, P44 param4);
    }

    interface P5<R5, P51, P52, P53, P54, P55> extends Workshop.P4<R5, P51, P52, P53, P54> {

        /**
         * Make: (Param1,Param2,Param3,Param4) -> Data
         * @param param1 Param1
         * @param param2 Param2
         * @param param3 Param3
         * @param param4 Param4
         * @return Return Data
         */
        @Override
        default R5 make(P51 param1, P52 param2, P53 param3, P54 param4) {
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
        R5 make(P51 param1, P52 param2, P53 param3, P54 param4, P55 param5);
    }

    interface P6<R6, P61, P62, P63, P64, P65, P66> extends Workshop.P5<R6, P61, P62, P63, P64, P65> {

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
        default R6 make(P61 param1, P62 param2, P63 param3, P64 param4, P65 param5) {
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
        R6 make(P61 param1, P62 param2, P63 param3, P64 param4, P65 param5, P66 param6);
    }
}
