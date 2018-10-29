package mywebserver.util.parser;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface Parser<T> {

    ParseResult<T> parse(CharSequence input);

    default ParseResult<List<T>> many(CharSequence input) {
        ParseResult<T> result;
        CharSequence currentInput = input;
        List<T> pathSegments = new ArrayList<>();

        do {
            result = parse(currentInput);
            if (result.success()) {
                pathSegments.add(result.getMatched());
                currentInput = result.getRemaining();
            }

        } while (result.success() && currentInput.length() > 0);
        if (pathSegments.size() > 0) {
            return new ParseResult<>(pathSegments, result.getRemaining(), true);
        } else {
            return new ParseResult<>(null, input, false);
        }
    }

    default <R> Parser<R> map(Function<T,R> fn) {
        return input -> ParseResult.map(this.parse(input), fn);
    }

    public static <A> Parser<Optional<A>> opt(Parser<A> p) {
        return input -> {
            ParseResult<A> result = p.parse(input);
            return new ParseResult<>(Optional.ofNullable(result.getMatched()), result.getRemaining(), true);
        };
    }

    public static <R> Parser<Tuple.Tuple1<R>> chain(Parser<R> p) {
        return input -> {
            ParseResult<R> result = p.parse(input);
            if (result.success()) {
                return new ParseResult<>(new Tuple.Tuple1<>(result.getMatched()), result.getRemaining(), true);
            } else {
                return new ParseResult<>(null, result.getRemaining(), false);
            }
        };
    }

    public static <A, B> Parser<Tuple.Tuple2<A, B>> chain(Parser<A> p1, Parser<B> p2) {
        return input -> {
            ParseResult<Tuple.Tuple1<A>> r1 = chain(p1).parse(input);
            if (r1.success()) {
                ParseResult<B> r2 = p2.parse(r1.getRemaining());
                if (r2.success()) {
                    return new ParseResult<>(new Tuple.Tuple2<>(r1.getMatched(), r2.getMatched()), r2.getRemaining(),true);
                } else {
                    return new ParseResult<>(null, r2.getRemaining(), false);
                }
            } else {
                return new ParseResult<>(null, r1.getRemaining(), false);
            }
        };
    }

    public static <A, B, C> Parser<Tuple.Tuple3<A, B, C>> chain(Parser<A> p1, Parser<B> p2, Parser<C> p3) {
        return input -> {
            ParseResult<Tuple.Tuple2<A, B>> r1 = chain(p1, p2).parse(input);
            if (r1.success()) {
                ParseResult<C> r2 = p3.parse(r1.getRemaining());
                if (r2.success()) {
                    return new ParseResult<>(new Tuple.Tuple3<>(r1.getMatched(), r2.getMatched()), r2.getRemaining(),true);
                } else {
                    return new ParseResult<>(null, r2.getRemaining(), false);
                }
            } else {
                return new ParseResult<>(null, r1.getRemaining(), false);
            }
        };
    }

    public static <A, B, C, D> Parser<Tuple.Tuple4<A, B, C, D>> chain(Parser<A> p1, Parser<B> p2, Parser<C> p3, Parser<D> p4) {
        return input -> {
            ParseResult<Tuple.Tuple3<A, B, C>> r1 = chain(p1, p2, p3).parse(input);
            if (r1.success()) {
                ParseResult<D> r2 = p4.parse(r1.getRemaining());
                if (r2.success()) {
                    return new ParseResult<>(new Tuple.Tuple4<>(r1.getMatched(), r2.getMatched()), r2.getRemaining(),true);
                } else {
                    return new ParseResult<>(null, r2.getRemaining(), false);
                }
            } else {
                return new ParseResult<>(null, r1.getRemaining(), false);
            }
        };
    }

    public static <A, B, C, D, E> Parser<Tuple.Tuple5<A, B, C, D, E>> chain(Parser<A> p1, Parser<B> p2, Parser<C> p3, Parser<D> p4, Parser<E> p5) {
        return input -> {
            ParseResult<Tuple.Tuple4<A, B, C, D>> r1 = chain(p1, p2, p3, p4).parse(input);
            if (r1.success()) {
                ParseResult<E> r2 = p5.parse(r1.getRemaining());
                if (r2.success()) {
                    return new ParseResult<>(new Tuple.Tuple5<>(r1.getMatched(), r2.getMatched()), r2.getRemaining(),true);
                } else {
                    return new ParseResult<>(null, r2.getRemaining(), false);
                }
            } else {
                return new ParseResult<>(null, r1.getRemaining(), false);
            }
        };
    }

    public static <A, B, C, D, E, F> Parser<Tuple.Tuple6<A, B, C, D, E, F>> chain(Parser<A> p1, Parser<B> p2, Parser<C> p3, Parser<D> p4, Parser<E> p5, Parser<F> p6) {
        return input -> {
            ParseResult<Tuple.Tuple5<A, B, C, D, E>> r1 = chain(p1, p2, p3, p4, p5).parse(input);
            if (r1.success()) {
                ParseResult<F> r2 = p6.parse(r1.getRemaining());
                if (r2.success()) {
                    return new ParseResult<>(new Tuple.Tuple6<>(r1.getMatched(), r2.getMatched()), r2.getRemaining(),true);
                } else {
                    return new ParseResult<>(null, r2.getRemaining(), false);
                }
            } else {
                return new ParseResult<>(null, r1.getRemaining(), false);
            }
        };
    }

    public static <A, B, C, D, E, F, G> Parser<Tuple.Tuple7<A, B, C, D, E, F, G>> chain(Parser<A> p1, Parser<B> p2, Parser<C> p3, Parser<D> p4, Parser<E> p5, Parser<F> p6, Parser<G> p7) {
        return input -> {
            ParseResult<Tuple.Tuple6<A, B, C, D, E, F>> r1 = chain(p1, p2, p3, p4, p5, p6).parse(input);
            if (r1.success()) {
                ParseResult<G> r2 = p7.parse(r1.getRemaining());
                if (r2.success()) {
                    return new ParseResult<>(new Tuple.Tuple7<>(r1.getMatched(), r2.getMatched()), r2.getRemaining(),true);
                } else {
                    return new ParseResult<>(null, r2.getRemaining(), false);
                }
            } else {
                return new ParseResult<>(null, r1.getRemaining(), false);
            }
        };
    }

}
