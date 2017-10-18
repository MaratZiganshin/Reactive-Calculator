package main;

public enum Operation {
    PLUS{
        @Override
        public String result(double a, double b) {
            return "a + b = " + (a + b);
        }
    },
    MINUS{
        @Override
        public String result(double a, double b){
            return "a - b = " + (a - b);
        }
    },
    MULT{
        @Override
        public String result(double a, double b){
            return "a * b = " + (a * b);
        }
    },
    DIV{
        @Override
        public String result(double a, double b){
            return "a / b = " + (a / b);
        }
    },
    NONE{
        @Override
        public String result(double a, double b){
            return null;
        }
    };
    public abstract String result(double a, double b);
}
