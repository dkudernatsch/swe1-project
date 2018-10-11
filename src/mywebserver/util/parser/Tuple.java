package mywebserver.util.parser;

public class Tuple
{

    public static class Tuple1<A>{
        public Tuple1(A a){
            first = a;
        }
        public final A first;
    }

    public static class Tuple2<A, B>{
        public Tuple2(Tuple1<A> a, B b){
            first =  a.first;
            second = b;
        }
        public final A first;
        public final B second;
    }

    public static class Tuple3<A, B, C>{
        public Tuple3(Tuple2<A, B> a, C b){
            first =  a.first;
            second = a.second;
            third = b;
        }
        public final A first;
        public final B second;
        public final C third;
    }

    public static class Tuple4<A, B, C, D> {
        public Tuple4(Tuple3<A, B, C> a, D b){
            first =  a.first;
            second = a.second;
            third = a.third;
            fourth = b;
        }

        public final A first;
        public final B second;
        public final C third;
        public final D fourth;
    }

    public static class Tuple5<A, B, C, D, E>{

        public Tuple5(Tuple4<A, B, C, D> a, E b){
            first =  a.first;
            second = a.second;
            third = a.third;
            fourth = a.fourth;
            fifth = b;
        }

        public final A first;
        public final B second;
        public final C third;
        public final D fourth;
        public final E fifth;
    }

    public static class Tuple6<A, B, C, D, E, F> {

        public Tuple6(Tuple5<A, B, C, D, E> a, F b){
            first =  a.first;
            second = a.second;
            third = a.third;
            fourth = a.fourth;
            fifth = a.fifth;
            sixth = b;
        }

        public final A first;
        public final B second;
        public final C third;
        public final D fourth;
        public final E fifth;
        public final F sixth;
    }

    public static class Tuple7<A, B, C, D, E, F, G> {

        public Tuple7(Tuple6<A, B, C, D, E, F> a, G b){
            first =  a.first;
            second = a.second;
            third = a.third;
            fourth = a.fourth;
            fifth = a.fifth;
            sixth = a.sixth;
            seventh = b;
        }

        public final A first;
        public final B second;
        public final C third;
        public final D fourth;
        public final E fifth;
        public final F sixth;
        public final G seventh;
    }

}
