/*
 * Copyright 2017-2021 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micronaut.core.type;

import io.micronaut.core.annotation.NonNull;

/**
 * An interface for types that can be represented as an {@link Argument}.
 *
 * @param <T> The argument type
 * @since 3.0.0
 * @author graemerocher
 * @param <T> The generic type
 */
public interface ArgumentCoercible<T> {
    /**
     * @return The argument
     */
    @NonNull Argument<T> asArgument();
}
