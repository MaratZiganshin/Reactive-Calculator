package main;

public enum Operation {
    /**
     * + operation
     */
    PLUS{
        @Override
        public String result(double a, double b) {
            return "a + b = " + (a + b);
        }
    },
    /**
     * - operation
     */
    MINUS{
        @Override
        public String result(double a, double b){
            return "a - b = " + (a - b);
        }
    },
    /**
     * * operation
     */
    MULT{
        @Override
        public String result(double a, double b){
            return "a * b = " + (a * b);
        }
    },
    /**
     * / operation
     */
    DIV{
        @Override
        public String result(double a, double b) throws ArithmeticException{
            if (b == 0) {
                throw new ArithmeticException("Divide by zero!");
            }
            return "a : b = " + (a / b);
        }
    },
    NONE{
        @Override
        public String result(double a, double b){
            return null;
        }
    };

    /**
     * Make string like ""a op b = " + (a op b)", where op is current operation
     * @return
     */
    public abstract String result(double a, double b);
}
