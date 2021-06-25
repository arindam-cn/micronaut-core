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
package io.micronaut.http.client.netty;

import io.micronaut.core.annotation.Experimental;
import io.micronaut.core.annotation.Internal;
import io.micronaut.core.io.buffer.ByteBuffer;
import io.micronaut.http.HttpResponse;
import reactor.core.publisher.Signal;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
/**
 * Consumer to use with reactive sequence of {@link ByteBuffer} in the body of a Http Response in combination with `Flux::onEach` to emulate `Flowable::doAfterNext`.
 *
 * @author Sergio del Amo
 * @since 3.0.0
 */
@Experimental
@Internal
public class HttpResponseByteBufferReleaseConsumer implements Consumer<Signal<HttpResponse<ByteBuffer<?>>>> {

    private final List<HttpResponse<ByteBuffer<?>>> elements = new ArrayList<>();

    @Override
    public void accept(Signal<HttpResponse<ByteBuffer<?>>> signal) {
        if (signal.isOnNext()) {
            HttpResponse<ByteBuffer<?>> next = signal.get();
            if (next != null) {
                elements.add(next);
            }
        }
        if (signal.isOnComplete() || signal.isOnError()) {
            for (HttpResponse<ByteBuffer<?>> buffer : elements) {
                ByteBufferUtils.release(buffer);
            }
        }
    }
}
