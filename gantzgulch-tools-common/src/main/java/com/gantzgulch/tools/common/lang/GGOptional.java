package com.gantzgulch.tools.common.lang;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class GGOptional {

    public static <O,T> T ifPresentOrElse(final Optional<O> o, final Function<O,T> action, Supplier<T> orElse) {

        if( o.isPresent() ){
            return action.apply(o.get());
        }

        return orElse.get();
    }

}
