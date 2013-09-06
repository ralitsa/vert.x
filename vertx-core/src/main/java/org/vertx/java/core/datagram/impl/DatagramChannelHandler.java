/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.vertx.java.core.datagram.impl;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.vertx.java.core.impl.DefaultContext;
import org.vertx.java.core.impl.VertxInternal;
import org.vertx.java.core.net.impl.VertxHandler;

import java.util.Map;

/**
 * @author <a href="mailto:nmaurer@redhat.com">Norman Maurer</a>
 */
abstract class DatagramChannelHandler extends VertxHandler<AbstractDatagramChannel> {
  DatagramChannelHandler(VertxInternal vertx, Map<Channel, AbstractDatagramChannel> connectionMap) {
        super(vertx, connectionMap);
  }

  @SuppressWarnings("unchecked")
  @Override
  protected void channelRead(AbstractDatagramChannel channel, DefaultContext context, ChannelHandlerContext chctx, Object msg) throws Exception {
    channel.handleMessage(msg);
  }
}