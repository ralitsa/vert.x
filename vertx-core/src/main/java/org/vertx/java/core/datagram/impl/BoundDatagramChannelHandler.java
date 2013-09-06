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

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.socket.DatagramPacket;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.impl.VertxInternal;

import java.util.Map;



/**
 * @author <a href="mailto:nmaurer@redhat.com">Norman Maurer</a>
 */
class BoundDatagramChannelHandler extends DatagramChannelHandler {
  BoundDatagramChannelHandler(VertxInternal vertx, Map<Channel, AbstractDatagramChannel> connectionMap) {
    super(vertx, connectionMap);
  }

  @Override
  protected Object safeObject(Object msg) throws Exception {
    if (msg instanceof DatagramPacket) {
      DatagramPacket packet = (DatagramPacket) msg;
      ByteBuf content = packet.content();
      if (content.isDirect())  {
        content = safeBuffer(content);
      }
      return new DefaultDatagramPacket(packet.sender(), new Buffer(content));
    }
    return msg;
  }
}